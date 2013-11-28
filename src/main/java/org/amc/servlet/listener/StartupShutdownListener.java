package org.amc.servlet.listener;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@WebListener
public class StartupShutdownListener implements ServletContextListener 
{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		
		System.out.println("Servlet shut down....");
		System.out.println(arg0.getServletContext().getServerInfo());
		System.out.println(System.currentTimeMillis());
		sendEmail("MyServlet:Has Shut down");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) 
	{
		
		System.out.println("Servlet started up....");
		System.out.println(arg0.getServletContext().getServerInfo());
		System.out.println(System.currentTimeMillis());
		sendEmail("MyServlet:Has Started up");
	}

	public boolean sendEmail(String message)
	{
		boolean result=false;
		String smtpHost="smtp.gmail.com";
		String smtpUsername="subwoofer359@gmail.com";
		String smtpPassword="edomoisac1";
		
		String from="subwoofer359@gmail.com";
		String to="adrianjmclaughlin@gmail.com";
		
		int smtpPort=587;
		System.out.println("Sending email...");
		try
		{
			Properties prop=new Properties();
			prop.put("mail.smtp.host",smtpHost);
			prop.put("mail.smtp.auth",true);
			prop.put("mail.smtp.starttls.enable",true);
			
			Session session=Session.getInstance(prop);
			
			Message msg=new MimeMessage(session);
			
			InternetAddress[] address=new InternetAddress[1];
			address[0]=new InternetAddress(to);
			msg.setRecipients(Message.RecipientType.TO, address);
			
			msg.setSubject(message);
			msg.setContent(message,"text/plain");
			
			Transport transport=session.getTransport("smtp");
			
			transport.connect(smtpHost,smtpPort,smtpUsername,smtpPassword);
			Transport.send(msg);
			
			
		}
		catch(javax.mail.MessagingException ex)
		{
			ex.printStackTrace();
			result=false;
		}
		
		
		return result;
	}
}