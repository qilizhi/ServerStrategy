package com.dz.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dz.entities.OperationLog;
import com.dz.model.UserInfo;
import com.dz.repository.OperationLogReposity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author qlz 2017/1/04 16:53
 * 
 */
@Aspect
@Component
public class OperationAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// private String requestPath = null; // 请求的pathַ
	// private String userName = null; // username
	// private Map<?, ?> inputParamMap = null; // 输入 参数
	// private Map<String, Object> outputParamMap = null; // 输出参数
	private long startTimeMillis = 0; // 开始时间
	private long endTimeMillis = 0; // 结束时间
	private OperationLog operationLog = null;// 操作日志
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private OperationLogReposity logDao;

	/**
	 * 业务层前切入点
	 * 
	 * @param joinPoint
	 */
	@Before("execution(* com.dz.controller..*.*(..))")
	public void doBeforeInServiceLayer(JoinPoint joinPoint) {
		startTimeMillis = System.currentTimeMillis();// 统计controller开始时间
		operationLog.setStartTime(new Date());
		//配置序列化的特性，防止FAIL_ON_EMPTY_BEANS exception
	//	mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	//	mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
		//mapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
		//mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
	}

	/**
	 * 业务层后切入点
	 * 
	 * @param joinPoint
	 */
	@After("execution(* com.dz.controller..*.*(..))")
	public void doAfterInServiceLayer(JoinPoint joinPoint) {
		endTimeMillis = System.currentTimeMillis(); // 统计controller 结束时间
		operationLog.setEndTime(new Date());
		this.printOptLog();
		logDao.saveOrUpdate(operationLog);
		System.out.println("test");
	
	}

	/**
	 * 业务过程切入点
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.dz.controller..*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

	
		/**
		 * 1.sra.getrequest 2sra.getsession 3.sra.getservlet
		 */
		operationLog = new OperationLog();
		Object result = pjp.proceed();
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Object user=null;
		if(auth!=null){
			user = auth.getPrincipal();
		}
		if(user!=null){
		if(user instanceof UserInfo){
			UserInfo userInfo = (UserInfo) user;
			operationLog.setUsername(userInfo.getUsername());
			operationLog.setUserId(userInfo.getId());
		} else if (user instanceof User) {
			User userInfo = (User) user;
			operationLog.setUsername(userInfo.getUsername());
			//operationLog.setUserId(userInfo.getId());
		}  else{
			operationLog.setUsername(user.toString());

		}
		}
		operationLog.setOperation(request.getMethod());
		operationLog.setPath(request.getRequestURI());
		operationLog.setResult(mapper.writeValueAsString(result));
		operationLog.setParams(mapper.writeValueAsString(request
				.getParameterMap()));
		//Object j=pjp.getArgs();
		//OperationLog op=new OperationLog();
		//System.out.println(mapper.writeValueAsString(op));
		//System.out.println(pjp.getArgs().toString());
		//operationLog.setContent(mapper.writeValueAsString(args));
		operationLog.setMethod(pjp.getSignature().getName());
		operationLog.setClassName(pjp.getSignature().getDeclaringTypeName()
				.toString());
		/*
		 * userName = "qilizhi"; inputParamMap = request.getParameterMap();
		 * requestPath = request.getRequestURI(); outputParamMap = new
		 * HashMap<String, Object>(); Object result = pjp.proceed();// 获取返回的结果
		 * outputParamMap.put("result", result);
		 * outputParamMap.put("controller", pjp.getSignature()
		 * .getDeclaringTypeName()); outputParamMap.put("method",
		 * pjp.getSignature().getName()); outputParamMap.put("params",
		 * pjp.getArgs());
		 */
		// outputParamMap.put("pjp", pjp);

		//saveDb();
		return result;
	}

	/**
	 * 打印输出
	 */
	private void printOptLog() {
		try {
			String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(startTimeMillis);
			logger.info(mapper.writeValueAsString(operationLog));
			logger.info("operatTime:" + optTime);
			logger.info("执行时间:" + (endTimeMillis - startTimeMillis));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
