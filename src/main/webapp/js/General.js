/**
 * file: general.js
 * Purpose: Groups together functions used by all the pages
 */

/**
 * @param message is added to the list of messages already in the Panel
 */
function showPanelMessage(message)
	{
		if(message!=null && message!="")
		{
			var currentText=$("#alert .panel-body").html();
			if(currentText!=null || currentText!="")
			{
				currentText=currentText+"<p>"+message+"</p>";
				$("#alert .panel-body").html(currentText);
			}
			else
			{
				$("#alert .panel-body").html("<p>"+message+"</p>");
			}
			$("#alert").show();
			
		}
	}

function showAlertMessage(elementId,message)
{
	if(message!=null && message!="")
	{
		$(elementId).html("${message}");
		$(elementId).show();
	}
};

/* Hide Alert div if document is clicked */
$(document).ready(function(){
		$(document).click(function()
		{
			$("#alert").hide();
		});
});