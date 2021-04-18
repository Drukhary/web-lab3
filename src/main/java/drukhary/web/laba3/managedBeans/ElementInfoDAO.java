package drukhary.web.laba3.managedBeans;

import drukhary.web.laba3.model.ElementInfo;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ElementInfoDAO implements Serializable {
    @Resource(lookup = "java:/PostgresDS")
    private DataSource dataSource;
    public void saveElementInfo(ElementInfo elementInfo) throws SQLException, NoSuchFieldException {
            Connection connection = dataSource.getConnection();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO \"elements\" " +
                    "(date,\"processTime\",x, y, radius, result)" +
                    " VALUES(?, ?, ?, ?, ?, ?);");
            pst.setTimestamp(1, Timestamp.from(elementInfo.getInstant()));
            pst.setDouble(2, elementInfo.getProcessTime());
            pst.setDouble(3, elementInfo.getY());
            pst.setDouble(4, elementInfo.getX());
            pst.setDouble(5, elementInfo.getRadius());
            pst.setBoolean(6, elementInfo.isResult());
            pst.execute();
            pst.close();
            connection.close();
    }
    public List<ElementInfo> findAllElementInfos() throws SQLException, NamingException, NoSuchFieldException {
        List<ElementInfo> elementInfos = new ArrayList<ElementInfo>();
            Connection connection = dataSource.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT "+
                    "date,\"processTime\",x, y, radius, result, id" +
                    " FROM \"elements\";");
            prepareStatement.execute();

            ResultSet result = prepareStatement.getResultSet();
            while(result.next()) {
                ElementInfo elementInfo = new ElementInfo();
                elementInfo.setInstant(result.getTimestamp("date").toInstant());
                elementInfo.setProcessTime(result.getDouble("processTime"));
                elementInfo.setX(result.getDouble("x"));
                elementInfo.setY(result.getDouble("y"));
                elementInfo.setRadius(result.getDouble("radius"));
                elementInfo.setResult(result.getBoolean("result"));
                elementInfo.setId(result.getInt("id"));
                elementInfos.add(elementInfo);
            }
            prepareStatement.close();
            result.close();
            connection.close();
        return elementInfos;
    }
    public void deleteElementInfos() throws SQLException, NamingException, NoSuchFieldException {
            Connection connection = dataSource.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM \"elements\"");
            prepareStatement.execute();
            prepareStatement.close();
            connection.close();
    }
}
