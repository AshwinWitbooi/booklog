package za.co.ashtech.booklog.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ch.qos.logback.classic.Logger;
import za.co.ashtech.booklog.db.dao.BookLogDao;
import za.co.ashtech.booklog.db.entity.TxLogEntity;
import za.co.ashtech.booklog.util.CONSTANTS;

@Aspect
@Configuration
public class TxLogAspect {
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(TxLogAspect.class);
	
	@Autowired
	private BookLogDao dao;
	private String signature = null;
	
	@Pointcut("execution(* za.co.ashtech.booklog.service.BookLogServiceImpl.*(..))")
	public void aroundServiceLayerPC() {}
	
	@Around("aroundServiceLayerPC()")
	public Object aroundServiceLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug(CONSTANTS.APPINFOMARKER,"SERVICE LAYER AROUND ADVICE");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 String username = null;
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		
		Object response = null;
		
		this.signature =  joinPoint.getSignature().toShortString().replace("BookLogServiceImpl.", "").replace("(..)", "");

		
		response = joinPoint.proceed();		
		
		TxLogEntity logEntity = new TxLogEntity();
		logEntity.setAction(this.getAction(this.signature));
		logEntity.setActionDate(new Date());
		logEntity.setActionResult("S");
		logEntity.setUsername(username);
		
		dao.persistTx(logEntity);
		
		return response;

	}
	
	@AfterThrowing (pointcut = "aroundServiceLayerPC()", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable {
		logger.info(CONSTANTS.APPINFOMARKER,"****LoggingAspect.logAfterThrowingAllMethods() " + ex);
        
		TxLogEntity logEntity = new TxLogEntity();
		logEntity.setAction(this.getAction(this.signature));
		logEntity.setActionDate(new Date());
		logEntity.setActionResult("F");
		logEntity.setUsername("ash@ashtech.co.za");
		
		dao.persistTx(logEntity);
		
		throw ex;
    }
	
	String getAction(String methodSignature){
		
		switch(methodSignature) {
		  case "createBook":
		    return "ADD";
		  case "updateBook":
			    return "UPD";
		  case "deleteBook":
			    return "DEL";
		  case "getBook":
			    return "GET";
		  case "getBooks":
			    return "GAB";
		  case "createUser":
			    return "CRU";
		  default:
		   return "ANO";
		}
		
	}


}
