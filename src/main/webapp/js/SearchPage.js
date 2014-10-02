/**
 *  @file Groups together functions relating to Search Pages in the application   
 *	@author Adrian Mclaughlin
 * 	@version 1
 */

/**
 * Highlights a selected row in a table and checks a checkbox and unchecks the other checkboxes in the table
 * @param {Object} DOM Element
 */
function selected(element){
	$(".checked").each(function(){
		var checkbox=$(this);
		checkbox.removeClass("checked");
		checkbox.prop("checked",false);
	});
	if(element!=null)
	{
		element=$(element);
		element.addClass("checked");
		element.find("input[type=\"checkbox\"]").prop("checked",true);
		console.log(element);
	}
	
}

/**
 *  Remove the event onsubmit for the add button
 *  @param {Object} HTML DOM Element
 */
function addClicked(button)
{
	var forms=document.getElementsByTagName("form");
	if(forms!=null && forms.length>0)
	{
		 forms[0].onsubmit="";
	}
}

/**
 * Test to see that at least one checbox has been checked
 * Displays an alert box if no checkboxes have been checked
 * ToDo refactor to use jquery
 * @param {Object} form element to be checked
 * @param {string} itemName The user friendly name of the element that should be selected
 * @param {Object} alertDiv The div element to display an error message if required
 * @returns true if an itemName has been selected
 */
function isChecked(form,itemName,alertDiv)
{
	var list=document.getElementsByName("edit");
	console.log(list.length+"\n");
	var checked=false;
	for(var t in list)
	{
		console.log(list[t]+" "+list[t].checked+"\n");
		if(list[t].checked)
		{
			checked=true;
		}
	}
	if(!checked)
	{
		var alertBox=document.getElementById(alertDiv);
		if(alertBox!=null)
		{
			alertBox.innerHTML="<Strong>Error:</Strong>A "+itemName+" is not selected";
			alertBox.style.display="block";
		}
		else
		{
			alert("A "+itemName+" is not selected");
		}
		$(document).click(function()
		{
				$("#"+alertDiv).hide();
		});
		return false;
	}
	else
	{
		return true;
	}	
}

/**
 * Sets the value of an input element to edit
 * @param{Object} HTML input element 
 */
function enable(id)
{
	id.value="edit";	
}

/**
 * function is executed when script is loaded
 */
$(document).ready(function(){
	/* 
	 * Stops the search menu closing when clicked on 
	 * http://stackoverflow.com/questions/10863821/bootstrap-dropdown-closing-when-clicked 
	 */
	$('.navbar-form').click(function(e) {
	    e.stopPropagation();
	});
	/* The user's enter keypress on the search element should submit a search */
	$('.navbar-form').keypress(function(e) {
		var code = (e.keyCode ? e.keyCode : e.which);
		if(code==13)
		{
			$("#search-btn").click();
			e.stopPropagation();
			return false;
		}	
    });
});