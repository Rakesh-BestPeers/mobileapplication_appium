package com.Grizzly.constants;

public interface TestDataQueryReader {

    /*queryID = SELECT * FROM %s WHERE Run='Yes' and IDNo='%s'*/
   public String queryTestData = "SELECT * FROM %s WHERE Run='Yes' and TestData='%s'";
   public String updateQuery = "UPDATE %s SET %s='%s' WHERE TestData='%s'";

}
