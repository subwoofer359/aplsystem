package myservlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.amc.servlet.APLSystemServlet;
import org.amc.servlet.action.SaveJobTemplateAction;
import org.amc.servlet.model.JobTemplate;
import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestAPLServlet 
{
	private APLSystemServlet apl;
	
	public TestAPLServlet()
	{
		
	}
	
	
	@Test
	public void testAPLSystemServlet()
	{
		ServletConfig config = mock(ServletConfig.class);
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestURI()).thenReturn("/myservlet/APLSystemServlet");
        when(request.getRequestDispatcher("/JSP/Main.jsp")).thenReturn(dispatcher);
        try
        {
        	apl =new APLSystemServlet();
        	apl.init(config);
        	apl.doGet(request, response);
        	apl.destroy();
        	verify(dispatcher).forward(request, response);
        	
        }
        catch(IOException e)
        {
        	
        }
        catch(ServletException se)
        {
        	
        }        
	}
	
	@Test
	public void testJobTemplate_display()
	{
		ServletConfig config = mock(ServletConfig.class);
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestURI()).thenReturn("/myservlet/JobTemplate_display");
        when(request.getRequestDispatcher("/JSP/JobTemplate.jsp")).thenReturn(dispatcher);
        try
        {
        	apl =new APLSystemServlet();
        	apl.init(config);
        	apl.doGet(request, response);
        	apl.destroy();
        	verify(dispatcher).forward(request, response);
        	
        }
        catch(IOException e)
        {
        	
        }
        catch(ServletException se)
        {
        	
        }        
	}
	
	@Test
	public void testLogout()
	{
		ServletConfig config = mock(ServletConfig.class);
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session= mock(HttpSession.class);
        
        when(request.getRequestURI()).thenReturn("/myservlet/logout");
        
        
        when(request.getSession()).thenReturn(session);
		
        
        
        try
        {
        	apl =new APLSystemServlet();
        	apl.init(config);
        	apl.doGet(request, response);
        	apl.destroy();
        	verify(request).logout();
        	verify(request).authenticate(response);
        	
        }
        catch(IOException e)
        {
        	
        }
        catch(ServletException se)
        {
        	
        }        
	}
	
	@Test
	public void testJobTemplate_save()
	{
		ServletConfig config = mock(ServletConfig.class);
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
  
        when(request.getRequestURI()).thenReturn("/myservlet/JobTemplate_save");
        
        //Enter mode
        when(request.getParameter("mode")).thenReturn("Enter");
        
		
        when(request.getParameter("name")).thenReturn("Pot");
		when(request.getParameter("colour")).thenReturn("Green");;
		//checkedbox
		when(request.getParameter("external")).thenReturn("true");		
		when(request.getParameter("part_id")).thenReturn("Part:494949");
		when(request.getParameter("qss_no")).thenReturn("QSS TA22");
		when(request.getParameter("revision")).thenReturn("2");
		when(request.getParameter("version")).thenReturn("60g");
		when(request.getParameter("company")).thenReturn("Tosara");
		
		when(request.getRequestDispatcher("/JSP/JobTemplate.jsp")).thenReturn(dispatcher);
        
        
        try
        {
        	apl =new APLSystemServlet();
        	apl.init(config);
        	apl.doGet(request, response);
        	apl.destroy();
        	verify(request).setAttribute(eq("form"),any(JobTemplate.class));
        	verify(request).setAttribute(eq("result"),anyString());
        	
        }
        catch(IOException e)
        {
        	
        }
        catch(ServletException se)
        {
        	
        }        
	}
	
}
