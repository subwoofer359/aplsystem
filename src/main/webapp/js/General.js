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

/* Hide Alert div if document is clicked */
$(document).ready(function(){
		
});