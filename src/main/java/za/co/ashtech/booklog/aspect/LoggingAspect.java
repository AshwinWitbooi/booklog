package za.co.ashtech.booklog.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;

@Aspect
@Configuration
public class LoggingAspect {
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* za.co.ashtech.booklog.service.*.*(..))")
	public void aroundControllerLayerPC() {}
	
	@Around("aroundControllerLayerPC()")
	public Object aroundServiceLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug("CONTROLLER LAYER AROUND ADVICE");
		
		HttpServletRequest request = 
		        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		String httpMethod = request.getMethod();
				
		Object response = null;
		
		joinPoint.getSignature().toShortString().replace("BookLogServiceImpl.", "").replace("(..)", "");
		
		ObjectMapper objectMapper = new ObjectMapper();

		if(httpMethod.equalsIgnoreCase("POST")) {
			Object body = (Object) joinPoint.getArgs()[0];
			
			logger.info("REQUEST: "+objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body));			
		}
		
		if(httpMethod.equalsIgnoreCase("GET")) {
			
			logger.info("RESPONSE: "+objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));			
		}
		
		
		
		return response;

	}

}
