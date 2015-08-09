package main.java.com.jersey.businesslogic.login;


import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Logger;

public class TestCallbackHandler implements CallbackHandler {

	private static Logger LOGGER = Logger.getLogger(TestLoginModule.class);
	String name;
	String password;

	public TestCallbackHandler(String name, String password) {
		System.out.println("Callback Handler - constructor called");
		this.name = name;
		this.password = password;
		LOGGER.info("############Login callback handler###################");
	}

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		System.out.println("Callback Handler - handle called");
		LOGGER.info("############Callback Handler - handle called###################");

		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof NameCallback) {
				NameCallback nameCallback = (NameCallback) callbacks[i];
				nameCallback.setName(name);
			} else if (callbacks[i] instanceof PasswordCallback) {
				PasswordCallback passwordCallback = (PasswordCallback) callbacks[i];
				passwordCallback.setPassword(password.toCharArray());
			} else {
				throw new UnsupportedCallbackException(callbacks[i], "The submitted Callback is unsupported");
			}
		}
	}
}
