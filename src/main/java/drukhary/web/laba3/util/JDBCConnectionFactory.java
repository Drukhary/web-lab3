package drukhary.web.laba3.util;

import org.hibernate.SessionFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnectionFactory {

    private DataSource ds;
    private static Connection connection=null;

    private static void connectWithDS() {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:/PostgresDS");

            connection = ds.getConnection();
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
    }

//    private void connectWithProps() {
//        String url;
//        String user;
//        String password;
//        try {
//            Class.forName("org.postgresql.Driver");
//            Properties prop = new Properties();
//            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
//            prop.load(inputStream);
//
//            url = prop.getProperty("db.url");
//            user = prop.getProperty("db.username");
//            password = prop.getProperty("db.password");
//
//            connection = DriverManager.getConnection(url, user, password);
//
//        } catch (ClassNotFoundException | SQLException | IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getConnection() {
        if (connection==null) {
//            try {
                connectWithDS();
//                String url = "jdbc:postgresql://0.0.0.0:5432/postgres";
//                String username = "postgres";
//                String password = "142857";
//                Class.forName("org.postgresql.Driver");
//
//                Properties prop = new Properties();
//                InputStream inputStream = JDBCConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties");
//                prop.load(inputStream);
//
//                url = prop.getProperty("db.url");
//                username = prop.getProperty("db.username");
//                password = prop.getProperty("db.password");
//
//                connection = DriverManager.getConnection(url, username, password);
//            } catch (SQLException | ClassNotFoundException | IOException e) {
//                e.printStackTrace();
//            }
        }
        return connection;
    }
}
