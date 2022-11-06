import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMap_Example {

    String IP = "3.89.231.188";
    String dbURL = "jdbc:oracle:thin:@"+IP+":1521:XE";
    String dbUsername = "hr";
    String dbPassword ="hr";

    @Test
    public void test1(){

        //creating list for keeping all the rows maps
        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();

        row1.put("first_name", "Steven");
        row1.put("last_name", "King");
        row1.put("salary", "24000");
        row1.put("job_id", "AD_PRES");

        System.out.println(row1.toString());


        Map<String,Object> row2 = new HashMap<>();

        row2.put("first_name", "Neena");
        row2.put("last_name", "Kochhar");
        row2.put("salary", "17000");
        row2.put("job_id", "AD_VP");

        System.out.println(row2.toString());

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the Steven last name directly from the list
        System.out.println(queryData.get(0).get("last_name"));
    }

    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");


        //in order to get column names  we need resultSetmetadata
        ResultSetMetaData rsmn = resultSet.getMetaData();

        //creating list for keeping all the rows maps
        List<Map<String,Object>> queryData = new ArrayList<>();

        //move tofirst row
        resultSet.next();

        Map<String,Object> row1 = new HashMap<>();

        row1.put(rsmn.getColumnName(1), resultSet.getString(1));
        row1.put(rsmn.getColumnName(2), resultSet.getString(2));
        row1.put(rsmn.getColumnName(3), resultSet.getString(3));
        row1.put(rsmn.getColumnName(4), resultSet.getString(4));

        System.out.println(row1.toString());


        //move tofirst row
        resultSet.next();

        Map<String,Object> row2 = new HashMap<>();

        row2.put(rsmn.getColumnName(1), resultSet.getString(1));
        row2.put(rsmn.getColumnName(2), resultSet.getString(2));
        row2.put(rsmn.getColumnName(3), resultSet.getString(3));
        row2.put(rsmn.getColumnName(4), resultSet.getString(4));

        System.out.println(row2.toString());

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the Steven last name directly from the list
        System.out.println(queryData.get(0).get("LAST_NAME"));


        connection.close();
        statement.close();
        resultSet.close();
    }






}
