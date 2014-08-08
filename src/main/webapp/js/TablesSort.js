/**  
*	@author Adrian Mclaughlin
* 	@version 1
*	
*	To sort HTML tables
*/

function tableSort(/*html table dom element */table,/* integer or string*/ columnToSortBy)
{
	//Presuming the table header element in a thead element
	if(table.tagName.toLowerCase()!='table')
	{
		while(table.tagName.toLowerCase()!='table' && table.parentNode!=null)
		{
			table=table.parentNode;
		}
	}
	//Check to see if columnToSortBy is a string and set columnToSortBy to the corresponding column index
	if(typeof columnToSortBy == 'string')
	{
		var thead;
		if(table.tHead)
		{
			thead=table.tHead.rows;
			if(thead.length>0)
			{
				var row=thead[0];
				var i;
				var rlen=row.cells.length;
				for(i=0;i<rlen;i++)
				{
					if(row.cells[i].innerHTML.indexOf(columnToSortBy)!=-1)
					{
						columnToSortBy=i;	
						break;
					}
				}
			}
		}
	}
	var tbody=table.tBodies;
	//Check that there is at least one tbody defined
	if(tbody.length<1)
	{
		return;
	}
	else
	{
		tbody=tbody[0];
	}

	var rowLen=tbody.rows.length;
	//Check the number of row in the table if the table is empty just return to caller
	if(rowLen<1)
	{
		return;
	}
	
	var rows=tbody.rows;
	//mxn array
	var arr=[];
	for(var i=0;i<rowLen;i++)
	{
		var cells=rows[i].cells;
		arr[i]=[];
		for(var j=0,cellLength=cells.length;j<cellLength;j++)
		{
	
			arr[i][j]=cells[j].innerHTML;
		}
	}
	console.log(arr);


	arr.sort(
	function(a,b)
	{
		if(a[columnToSortBy]<b[columnToSortBy])
			return -1;
		else if(a[columnToSortBy]>b[columnToSortBy])
			return 1;
		else
			return 0;
	}
	);

	for (var i = 0; i < rowLen; i++) 
	{
                //rows[i].innerHTML = "<td>" + arr[i].join("</td><td>") + "</td>";
				for(var t=0;t < rlen;t++)
				{
					cell=rows[i].cells[t];
					cell.innerHTML=arr[i][t];
				}
     }
}
