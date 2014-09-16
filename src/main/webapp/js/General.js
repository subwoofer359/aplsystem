/**
 * file: general.js
 * Purpose: Groups together functions used by all the pages
 */

/**
 * Works on a bootstrap panel
 * @param message is added to the list of messages already in the Panel
 * @param elementId html element which contains a child element with class "panel-body"
 * which displays the message
 */
function showPanelMessage(elementId, message)
	{
		if(message!=null && message!="")
		{
			var currentText=$(elementId+" .panel-body").html();
			if(currentText!=null || currentText!="")
			{
				currentText=currentText+"<p>"+message+"</p>";
				$(elementId+" .panel-body").html(currentText);
			}
			else
			{
				$(elementId+" .panel-body").html("<p>"+message+"</p>");
			}
			$(document).click(function()
			{
				$(elementId).hide();
			});
			
			$(elementId).show();
		}
	}

/**
 * @param elementId Element which displays the message
 * @param message The message which is displayed to the user
 */
function showAlertMessage(elementId,message)
{
	if(message!=null && message!="")
	{
		$(elementId).html(message);
		$(document).click(function()
		{
			$(elementId).hide();
		});
		$(elementId).show();
	}
};

/**
 * 
 * @param inputElementOne html form input whose value contains a password
 * @param inputElementTwo html form input whose value contains a password
 * @returns true if the passwords are the same
 */
function checkPassword(inputElementOne,inputElementTwo)
{
	console.log("Check Password function called");
	var password1=document.getElementById(inputElementOne);
	var password2=document.getElementById(inputElementTwo);
	
	if(password1.value != password2.value)
	{
		console.log("Passwords aren't the same");
		alert("Passwords aren't the same");
		return false
	}
	else
	{
		console.log("Passwords are the same");
		return true;
	}
}

/* Hide Alert div if document is clicked */
$(document).ready(function(){
		
});