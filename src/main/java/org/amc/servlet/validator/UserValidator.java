package org.amc.servlet.validator;

import java.util.regex.Pattern;

import org.amc.model.User;
import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator
{
	@Override
	public boolean supports(Class<?> arg0)
	{
		// TODO Auto-generated method stub
		return User.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName","userName.empty","No Username given");
		User user=(User)obj;
		boolean checkEmail= Pattern.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b",user.getEmailAddress());
		if(!checkEmail)
		{
			errors.rejectValue("emailAddress","emailAddress.wrong.format" ,"Email in the wrong format");
		}
		
	}

}
