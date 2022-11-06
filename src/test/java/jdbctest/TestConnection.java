package jdbctest;


import java.sql.*;



public class TestConnection {

    public static void main(String[] args) throws SQLException {

        String IP = "3.89.231.188";
        String dbURL = "jdbc:oracle:thin:@"+IP+":1521:XE";
        String dbUsername = "hr";
        String dbPassword ="hr";

        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from regions");



        //next() --> move pointer to first row
        resultSet.next();

        //getting information with column name
        //System.out.println(resultSet.getString("region_name"));

        //getting information with column index(stars 1)
        //System.out.println(resultSet.getString(2));

        //1 - Europe
        //2 - African
        //System.out.println(resultSet.getString(1)+ " - " + resultSet.getString(2));
        //resultSet.next();
        //System.out.println(resultSet.getString(1)+ " - " + resultSet.getString(2));

        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+ " - " + resultSet.getString(2));
        }


        //close connection
        connection.close();
        statement.close();
        resultSet.close();


    }



}
