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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
import za.co.ashtech.booklog.util.CONSTANTS;

@Aspect
@Configuration
public class LoggingAspect {
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* za.co.ashtech.booklog.controller.*.*(..))")
	public void aroundControllerLayerPC() {}
	
	@Around("aroundControllerLayerPC()")
	public Object aroundServiceLayer(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug(CONSTANTS.APPINFOMARKER,"CONTROLLER LAYER AROUND ADVICE");
		
		Object response = null;
		HttpServletRequest request = null;
		String httpMethod = null;
				
		try {

			request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			
			if(request != null) {
				 httpMethod = request.getMethod();
			}
											
			ObjectMapper objectMapper = new ObjectMapper();

			if(httpMethod != null) {
				if(httpMethod.equalsIgnoreCase("POST")) {
					Object body = (Object) joinPoint.getArgs()[0];
					
					logger.info(CONSTANTS.APPINFOMARKER,"REQUEST: "+objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body));			
				}
				
				if(httpMethod.equalsIgnoreCase("GET")) {
					
					logger.info(CONSTANTS.APPINFOMARKER,"RESPONSE: "+objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));			
				}				
			}

		} catch (JsonProcessingException e) {
			logger.error(CONSTANTS.APPINFOMARKER,e.getMessage());
		}catch (NullPointerException e) {
			logger.error(CONSTANTS.APPINFOMARKER,e.getMessage());
		}
		
		
		
		return response;

	}

}
