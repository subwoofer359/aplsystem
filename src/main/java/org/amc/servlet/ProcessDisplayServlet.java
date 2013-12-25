package org.amc.servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.amc.servlet.action.ProcessActionFactory;
import org.amc.servlet.model.MouldingProcess;
import org.amc.servlet.model.Part;

import com.google.visualization.datasource.DataSourceServlet;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.query.Query;


@WebServlet(description="ProcessDisplayServlet", urlPatterns={"TODO"})
public class ProcessDisplayServlet extends DataSourceServlet 
{
	
	protected final static long serialVersionUID=3300322L;
	@Override
	public DataTable generateDataTable(Query arg0, HttpServletRequest arg1)	throws DataSourceException 
	{
		
		MouldingProcess process=(MouldingProcess)arg1.getAttribute("process");
		
		float startPosition=process.getPosTran()+process.getShotSize();// 0 -> changeover -> shot size
		
		DataTable dTable=new DataTable();
		
		ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
		
		//cd.add(new ColumnDescription("id", ValueType.NUMBER,"ID"));
		cd.add(new ColumnDescription("Position", ValueType.TEXT,"Part Name"));
		cd.add(new ColumnDescription("Pressure",ValueType.NUMBER , "pressure"));
		
		
		dTable.addColumns(cd);
		
		
		dTable.addRowFromValues(startPosition,process.getMaxInjPre());
		dTable.addRowFromValues(process.getPosTran(),process.getHoldingPressure_1());
		
		
		return dTable;
		
	}

}
