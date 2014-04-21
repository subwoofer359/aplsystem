/**
 * 
 */

/**
 * Hide all HTML div except the div the user requested
 * @param element HTML Select element
 * @param tabs an array of div ids 
 */ 
function displayDiv(element,tabs)
{
	console.log("HideInfo:"+element);
	//Get the user option form the select element
	var selectedOption=element.options[element.selectedIndex];
	//Loop through the HTML divs and hide them except for the requested div
	for(var i=0;i<tabs.length;i++)
	{
		if(tabs[i]!=selectedOption.value)
		{
			var element=document.getElementById(tabs[i]);
			element.style.visibility="hidden";
			console.log("Element:"+tabs[i]+" is hidden");	
		}
	}
	//Make the request HTML div visible
	var element=document.getElementById(selectedOption.value);
	element.style.visibility="visible";
	console.log("Element:"+selectedOption.value+" is visible");
	
}


/*
 * When the enter button is pressed change HTML div to the next one and focus on the first element
 * SELECT ELEMENT NAME="pageSelect"
 */
function checkEnterNextPage(event,element)
{
	var SELECTELEMENTNAME="pageSelect";
	var code = (event.keyCode ? event.keyCode : event.which);
 	if(code == 13) 
	{
 		// Get the current HTML div index
		var selectElement=document.getElementById(SELECTELEMENTNAME);
		var selectedIndex=selectElement.selectedIndex;
		console.log("Index:"+selectedIndex);
		//Change to the next HTML div
		selectElement.selectedIndex=selectedIndex+1;
		//Cause the next DIV to be displayed
		displayDiv(selectElement);
		//Move focus to next Input statement
		nextInput(event,element); //Todo Remove this out of this function
	}
}