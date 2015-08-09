package main.java.com.jersey.presentation;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericController {
	
	static{
		PropertyConfigurator.configure("log4j.properties");
	}
    @Autowired
	private ModuleManager moduleManager;
    
	private static Logger LOGGER = Logger.getLogger(GenericController.class);
    
    public ModuleManager getModuleManager(){
    	try{
    	LOGGER.info("Getting module Manager - "+ this.moduleManager);
    	}catch(Exception e){
    		LOGGER.error("Exception Occured - "+ e);
    	}
    	return this.moduleManager;
    }
}
