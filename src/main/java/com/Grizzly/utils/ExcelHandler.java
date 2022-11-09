package com.Grizzly.utils;

import java.util.Map;
import java.util.TreeMap;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelHandler 
{
	public static Map<String,String> getTestDataInMap(String qury, String testDataFile,String sheetName,String testCaseId) throws Exception
	{
		Map<String,String> TestDataInMap=new TreeMap<String,String>();		
		String query=null;
		/*qury = "SELECT * FROM %s WHERE Run='Yes' and TestCaseId='%s'";*/
		query=String.format(qury,sheetName,testCaseId);
		Fillo fillo=new Fillo();
		Connection conn=null;
		Recordset recordset=null;
		try
		{
			conn=fillo.getConnection(testDataFile);
			recordset=conn.executeQuery(query);
			while(recordset.next())
			{
				for(String field:recordset.getFieldNames())
				{
					TestDataInMap.put(field, recordset.getField(field));
				}
			}
		}
		catch(FilloException e)
		{
			e.printStackTrace();
			throw new Exception("Test data cannot find . . .");			
		}
		conn.close();
		return TestDataInMap;		
	}

	public static void updateCellValueToExcel(String qury,String testDataFilePath,String sheetName,String colName,String value,String testCaseId)
	{
		Connection conn=null;
		Fillo fillo =new Fillo();
		try{
			conn=fillo.getConnection(testDataFilePath);
			/*qury = "UPDATE %s SET TestCaseStatus='%s' where TestCaseID='%s'";*/
			String query=String.format(qury,sheetName,colName,value,testCaseId);
			conn.executeUpdate(query);
		} catch(FilloException e){
			e.printStackTrace();
		}	
		conn.close();
	}

}
