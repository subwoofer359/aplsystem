/**  
*	@author Adrian Mclaughlin
* 	@version 1
*/
/**
 * To move focus to the next input statement after the carriage return key is pressed
 */
//Stop the enter button from submitting the form
document.onkeypress=stopEnter;

//Add listener to Input elements to control focus
window.onload=addFocusListenerToTextInput;

function addFocusListenerToTextInput()
{
	//Get all Input Elements
	var inputs=document.getElementsByTagName("input");
	for(var i=0;i<inputs.length;i++)
	{
		//add Listener to change focus
		inputs[i].addEventListener("keypress",function(event,element){nextInput(event,this);});
		//console.log("adding listener to "+inputs[i]);
	}
}
/**
 * add Listener to change page on elements with the class="end"
 */
function addChangePageListenerInput(tabs)
{
	var elements=document.getElementsByClassName("end");
	console.log("addChangePageListenerInput called: Elements with the class 'end':"+elements.length+" found");
	for(var i=0;i<elements.length;i++)
	{
		elements[i].addEventListener("keypress",function(event,element){checkEnterNextPage(event,this,tabs);});
	}
}

/*
 * Change focus to the next Input element
 */
function nextInput(event,element)
{
	var code = (event.keyCode ? event.keyCode : event.which);
 	if(code == 13) 
	{
		console.log(element);
		var inputs=document.getElementsByTagName("input");
		var index=0;
		for(var i=0;i<inputs.length;i++)
		{
			if(inputs[i]==element)
			{
				index=i;
				break;
			}
		}
		index++;
		//If index is a valid index of the inputs array
		if(index<inputs.length)
		{
			inputs[index].focus();
		}
		return false;
	}
}



/*
 * Stop the enter key from submitting
 */
function stopEnter(event)
{
	var code = (event.keyCode ? event.keyCode : event.which);
 	if(code == 13) 
	{
 		event.stopPropagation();
		return false; 
	}

}