package main.java.com.jersey.presentation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ModuleManager implements ApplicationContextAware {

	private static Logger LOGGER = Logger.getLogger(ModuleManager.class);
	ApplicationContext applicationContext = null;

	public <T extends IGenericCpClass> T getModule(Class<T> reqSpecificClass, String code, String version){
		LOGGER.info("Inside getmodule methhod");
		T requestedBean = null;
		requestedBean = this.applicationContext.getBean(reqSpecificClass);
		LOGGER.info("Requested Bean found and retrieved from Application context - "+requestedBean.getClass().getName());
		return requestedBean;
	}
	
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
    	LOGGER.info("Setting Application Context for module Manager");
    	this.applicationContext = applicationContext;
    	LOGGER.info("Application Context set to - "+applicationContext.getApplicationName()+"\n Bean Count - "+applicationContext.getBeanDefinitionCount());
	}

}
