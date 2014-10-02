/**
 * @file Groups together functions used by all the html pages
 * @author Adrian McLaughlin
 */

/**
 * Works on a bootstrap panel
 * @public
 * @param {string} message is added to the list of messages already in the Panel
 * @param {Object} elementId html element which contains a child element with class "panel-body"
 *                 which displays the message
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
 * If the parameter message has a value then the DOM elementId is made visible
 * @param {string} elementId Element which displays the message
 * @param {string} message The message which is displayed to the user
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
 * Compares the two passwords from two form input elements
 * @public 
 * @param {string} inputElementOne html form input whose value contains a password
 * @param {string} inputElementTwo html form input whose value contains a password
 * @returns {boolean} true if the passwords are the same
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
