package jdbctest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String IP = "3.89.231.188";
    String dbURL = "jdbc:oracle:thin:@"+IP+":1521:XE";
    String dbUsername = "hr";
    String dbPassword ="hr";



  @DisplayName("ResultSet Method")//changes the name of annotatation name
  @Test
  public void test1() throws SQLException {

      Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("select * from departments");


      //next() --> move pointer to first row
      //resultSet.next();

      //System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getInt(3) + " - " + resultSet.getInt(4));


/*
      while(resultSet.next()){
          System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getInt(3) + " - " + resultSet.getInt(4));
      }

 */

      resultSet = statement.executeQuery("select  * from regions");

      while (resultSet.next()){
          System.out.println(resultSet.getInt(1) +" - "+resultSet.getString(2));
      }


      //close connection
      connection.close();
      statement.close();
      resultSet.close();

  }


  @Test
    public void test2() throws SQLException {

      Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
      Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      ResultSet resultSet = statement.executeQuery("select * from employees");


      resultSet.last();
      //how to find row number
      System.out.println(resultSet.getRow());

      //to move first row after we use  .last method
      resultSet.beforeFirst();

      //print all second column
      while (resultSet.next()){
          System.out.println(resultSet.getString(2));
      }


      connection.close();
      statement.close();
      resultSet.close();


  }

    @Test
    public void test3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //get database related data inside the dbMetaData object
        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());


        //get the resultSEt  metadata //rsmd
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        //how many column  we have
        int colCount = rsMetaData.getColumnCount();
        System.out.println("colCount = " + colCount);

        //getting the column names
        System.out.println(rsMetaData.getColumnName(1));
        System.out.println(rsMetaData.getColumnName(2));

        //print the all the column names dynamically
        for (int i = 1; i < colCount ; i++) {
            System.out.println(rsMetaData.getColumnName(i));
        }



        connection.close();
        statement.close();
        resultSet.close();


    }




}
