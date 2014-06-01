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
	for(var i=0;i<elementsArray.length;i++)
	{
		if(elementsArray[i].type=="checkbox")
		{
			elementsArray[i].checked=false;
			//Set parent <TR> element background to original colour
			elementsArray[i].parentNode.parentNode.style.backgroundColor="";  
		}
	}
	
	console.log(element);
	var checkbox=element.getElementsByTagName("input");
	if(checkbox != null || checkbox.length>0)
	{
		console.log(checkbox[0]);
		checkbox[0].checked=true;
		element.style.backgroundColor="red";
	}
	else
	{
		console.log("Checkbox element not found");
	}
	
}



function addClicked(button)
{
	var forms=document.getElementsByTagName("form");
	if(forms!=null && forms.length>0)
	{
		 forms[0].onsubmit="";
	}
}

function isChecked(form,itemName)
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
		alert("A "+itemName+" is not selected");
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
