/**  
*	@author Adrian Mclaughlin
* 	@version 1
*/
/**
 * Javascript Functions required by the JSP search pages
 */

/**
 * Highlights a selected row in a table and checks a checkbox and unchecks the other checkboxes in the table
 */
function selected(element)
{
	var elementsArray=document.getElementsByTagName("input");
	for(var i=0,elen=elementsArray.length;i<elen;i++)
	{
		if(elementsArray[i].type=="checkbox")
		{
			elementsArray[i].checked=false;
			//Set parent <TR> element background to original colour
			elementsArray[i].parentNode.parentNode.style.backgroundColor="";
			elementsArray[i].parentNode.parentNode.style.borderColor="";
		}
	}
	
	console.log(element);
	if(element!=null)
	{
		var checkbox=element.getElementsByTagName("input");
		if(checkbox != null || checkbox.length>0)
		{
			console.log(checkbox[0]);
			checkbox[0].checked=true;
			element.style.backgroundColor="red";
			element.style.borderColor="red";
		}
		else
		{
			console.log("Checkbox element not found");
		}
	}
	
}


/* Remove the event onsubmit for the add button */
function addClicked(button)
{
	var forms=document.getElementsByTagName("form");
	if(forms!=null && forms.length>0)
	{
		 forms[0].onsubmit="";
	}
}

/**
 * ToDo refactor to use jquery
 * @param form element to be checked
 * @param itemName The user friendly name of the element that should be selected
 * @param alertDiv The div element to display an error message if required
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



function enable(id)
{
	id.value="edit";	
}

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