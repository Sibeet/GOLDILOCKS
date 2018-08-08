//LengthTest.java
//DBMS 내부에 정해진 Literal 등의 한계 길이를 반환한다.

import java.sql.*;
import javax.sql.*;

public class LengthTest
{
    protected static final String GOLDILOCKS_DRIVER_CLASS = "sunje.goldilocks.jdbc.GoldilocksDriver";
    protected static final String URL_BASIC = "jdbc:goldilocks://192.168.0.120:48900/test";
    protected static final String URL_NAMED = "jdbc:goldilocks://192.168.0.120:48900/test?program=MySample";
    protected static final String URL_FOR_DEBUGGING = URL_BASIC + "?global_logger=console&trace_log=on&query_log=on&protocol_log=on";

    public static Connection createConnectionByDriverManager(String id, String password) throws SQLException
    {
        try
        {
            Class.forName(GOLDILOCKS_DRIVER_CLASS);
        }
        catch (ClassNotFoundException sException)
        {
        }

        return DriverManager.getConnection(URL_BASIC, "TEST", "test");
    }

    public static Connection createConnectionByDataSource(String id, String password) throws SQLException
    {
        sunje.goldilocks.jdbc.GoldilocksDataSource sDataSource = new sunje.goldilocks.jdbc.GoldilocksDataSource();
        
        sDataSource.setDatabaseName("test");
        sDataSource.setServerName("192.168.0.120");
        sDataSource.setPortNumber(48900);
        sDataSource.setUser(id);
        sDataSource.setPassword(password);

        return sDataSource.getConnection();
    }
    
    public static void main(String[] args) throws SQLException
    {
        Connection con = createConnectionByDriverManager("TEST", "test");
	
	DatabaseMetaData dmd = con.getMetaData();

	try
	{
	    int binary = dmd.getMaxBinaryLiteralLength();
	    int catalog = dmd.getMaxCatalogNameLength();
	    int literal = dmd.getMaxCharLiteralLength();
	    int column = dmd.getMaxColumnsInSelect();
	    int stateLength = dmd.getMaxStatementLength();
	    int statement = dmd.getMaxStatements();
	    int table = dmd.getMaxTablesInSelect();
	    int tablename = dmd.getMaxTableNameLength();
	    int columnname = dmd.getMaxColumnNameLength();
	    System.out.println("Binary Length is : "+binary);
	    System.out.println("Catalog Length is : "+catalog);
	    System.out.println("CharLiteral Length is : "+literal);
	    System.out.println("Column Length is : "+column);
	    System.out.println("Statement Length is : "+stateLength);
	    System.out.println("Max Statement Value is : "+statement);
	    System.out.println("Max Table is : "+table);
	    System.out.println("Table Name Length is : "+tablename);
	    System.out.println("Max Column Name Length is : "+columnname);
	    
	}
	catch(SQLException sException)
	{
	    System.out.println("SException");
	}
		
        con.close();

    }
}

