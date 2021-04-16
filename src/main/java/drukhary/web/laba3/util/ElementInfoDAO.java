package drukhary.web.laba3.util;

import drukhary.web.laba3.model.ElementInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ElementInfoDAO {
    public static void saveElementInfo(ElementInfo elementInfo) {
        try {
            PreparedStatement pst = JDBCConnectionFactory.getConnection().prepareStatement("INSERT INTO \"elements\" " +
                    "(date,\"processTime\",x, y, radius, result)" +
                    " VALUES(?, ?, ?, ?, ?, ?);");
            pst.setTimestamp(1, Timestamp.from(elementInfo.getInstant()));
            pst.setDouble(2, elementInfo.getProcessTime());
            pst.setDouble(3, elementInfo.getY());
            pst.setDouble(4, elementInfo.getX());
            pst.setDouble(5, elementInfo.getRadius());
            pst.setBoolean(6, elementInfo.isResult());
            pst.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static List<ElementInfo> findAllElementInfos() {
        List<ElementInfo> elementInfos = new ArrayList<ElementInfo>();
        try {
            PreparedStatement prepareStatement = JDBCConnectionFactory.getConnection().prepareStatement("SELECT "+
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return elementInfos;
    }
    public static void deleteElementInfos() {
        try {
            PreparedStatement pst = JDBCConnectionFactory.getConnection().prepareStatement("DELETE FROM \"elements\"");
            pst.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
