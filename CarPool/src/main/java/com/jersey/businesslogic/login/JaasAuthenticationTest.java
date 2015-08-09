package main.java.com.jersey.businesslogic.login;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;

public class JaasAuthenticationTest {
	 private static Logger LOGGER = Logger.getLogger(TestLoginModule.class);
	public static void main(String[] args) {
		System.setProperty("java.security.auth.login.config", "G:\\journey QA Automation\\JourneyWS_Aug_14_2014\\CarPool\\src\\jaas.config");

		LOGGER.info("######################teting logging");
		String name = "anand.pateriya";
		String password = "pateriya88";

		try {
			LoginContext lc = new LoginContext("Test", new TestCallbackHandler(name, password));
			lc.login();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
}