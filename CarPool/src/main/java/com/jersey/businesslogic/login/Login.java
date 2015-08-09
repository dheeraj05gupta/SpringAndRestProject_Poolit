package main.java.com.jersey.businesslogic.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static Logger LOGGER = Logger.getLogger(Login.class);

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 LOGGER.info("#############Login servlet");
		 response.setContentType("text/html;charset=UTF-8");
         //PrintWriter out = response.getWriter();
	     String username = request.getParameter("username");
	     String pass = request.getParameter("password");
	     PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println(pass +"______"+username);
			out.println("</body>");
			out.println("</html>");	
	        
	     try {
	    	 LoginContext lc = new LoginContext("Test", new TestCallbackHandler(username, pass));
	    	 out.println(pass +"______"+username);
	    	 lc.login();
	    	 out.println(pass +"______"+username);
			 Subject subject = lc.getSubject();
			    //get principals
			    subject.getPrincipals();
			    LOGGER.info("established new logincontext");
			} catch (LoginException e) {
				out.println(pass +"______"+username+"-----"+e);
				e.printStackTrace();
			}
	     
	    }  
}
