/**
 * This is a ConnectionPool. The use of this class is to create up to five connections from sql to java.
 * It uses a singleton connectionPool and the process is: create connection, add to pool, use connection, return when finished.
 * It throws ClassNotFoundException, SqlException and InterruptedException.
 *
 * THE FUNCTIONS AND THEIR USES:
 * getConnection - This function returns a connection from the ConnectionPool.
 * restoreConnection - This function returns the connection to the connectionPool.
 * CloseAllConnections - This function closes all connections and return them to the connectionPool.
 * createNewConnection - creates a new connection from the dataBase when all the connections are used.
 */

package Coupon_System.ConnectionPool;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import Coupon_System.DesignColors.TextColors;



public class ConnectionPool {
    private static ConnectionPool instance = new ConnectionPool();
     ArrayList<Connection> connections = new ArrayList<Connection>();
     ArrayList<Connection> connectionsInUse = new ArrayList<Connection>();

    static ArrayList<Connection> connectionsInWait = new ArrayList<Connection>();
    public static int validConnections = 5;


    //    private static final String CONNECTION_STRING = "jdbc:mysql://127.0.0.1:3306/?user=root&password=root";
    private  static final String DOMAIN_STRING = "localhost" ;
    private  static final String DB_NAME = "couponsystem" ;
    private  static final String USER_NAME = "root" ;
    private  static final String PASSWORD = "root" ;
    private  static final String CONNECTION_STRING = "jdbc:mysql://" + DOMAIN_STRING + "/" + DB_NAME +"?user=" + USER_NAME + "&password=" + PASSWORD ;


    private ConnectionPool()  {
       try {
           init();
       }
       catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("failed to init");
        }
    }

    Connection connection;

    public Connection getConnection() throws SQLException, InterruptedException {
        if(connections.size()>0) {
            Connection sendConnection = connections.get(0);
            connectionsInUse.add(sendConnection);
            connections.remove(0);

            return sendConnection;
        }
        else {
            CreateNewConnection();
            Connection sendConnection = connections.get(0);
            connectionsInUse.add(sendConnection);
            connections.remove(0);
            return sendConnection;

        }

        }
        public void CreateNewConnection() throws SQLException {
            System.out.println(TextColors.ANSI_GREEN + "Connection " + (connections.size()) +""+TextColors.ANSI_RESET);
            connection = DriverManager.getConnection(CONNECTION_STRING);
            connections.add(connection);
        }


    public void restoreConnection(Connection connection) throws SQLException {
     connectionsInUse.remove(connection);
     connections.add(connection);
    }

    public  void closeAllConnections() throws SQLException {
        for(int i=0; i<connections.size() ; i++)
                connections.get(i).close();
        for(int i=0; i<connectionsInUse.size() ; i++)
            connectionsInUse.get(i).close();
        System.out.println(TextColors.ANSI_PURPLE+"All connections were closed successfully!"+TextColors.ANSI_RESET);



    }

    public static ConnectionPool getInstance(){
        return instance;
    }

    private void init() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println(TextColors.ANSI_GREEN+"JDBC driver loaded successfully!"+TextColors.ANSI_RESET);
        System.out.println(TextColors.ANSI_GREEN+"CONNECTION_STRING: " +CONNECTION_STRING + "" +TextColors.ANSI_RESET);
        for(int i=0; i<5 ; i++) {
            System.out.println(TextColors.ANSI_GREEN + "Connection " + (i+1) +""+TextColors.ANSI_RESET);
            connection = DriverManager.getConnection(CONNECTION_STRING);
            connections.add(connection);
        }
        System.out.println(TextColors.ANSI_GREEN+"Connected to database"+TextColors.ANSI_RESET);
    }






}
