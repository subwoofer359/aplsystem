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
		final String smtpUsername="subwoofer359@gmail.com";
		final String smtpPassword="edomoisac1";
		
		String from="subwoofer359@gmail.com";
		String to="adrianjmclaughlin@gmail.com";
		
		int smtpPort=465;
		System.out.println("Sending email...");
		try
		{
			Properties prop=new Properties();
			prop.put("mail.smtp.host",smtpHost);
			prop.put("mail.smtp.port", smtpPort);
			prop.put("mail.smtp.socketFactory.port", 465);
			prop.put("mail.smtp.auth","true");
			prop.put("mail.smtp.starttls.enable","true");
//			prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//			prop.put("mail.smtp.socketFactory.fallback", "false");
			prop.put("mail.smtp.user",smtpUsername);
			         
			
			
//			Session session=Session.getInstance(prop);
			Session session=Session.getDefaultInstance(prop,new Authenticator() {

	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(smtpUsername, smtpPassword);
	            }

	        });
			session.setDebug(true);
			
			Message msg=new MimeMessage(session);
			
			InternetAddress addressFrom =new InternetAddress(from);
			msg.setFrom(addressFrom);
			
			InternetAddress[] address=new InternetAddress[1];
			address[0]=new InternetAddress(to);
			msg.setRecipients(Message.RecipientType.TO, address);
			
			msg.setSubject(message);
			msg.setContent(message,"text/plain");
			
//			Transport transport=session.getTransport("smtp");
//			
//			transport.connect(smtpHost,smtpPort,smtpUsername,smtpPassword);
		
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
