package orm;
import java.sql.*;
import java.util.List;
import java.util.Map;

public interface Entity {

    public Object getAttr(String attrName) throws NoSuchFieldException, IllegalAccessException;
    public void setAttr(String attrName, Object value) throws NoSuchFieldException, IllegalAccessException;
    public void create(Connection connection)  throws SQLException;
    public List<Map<String, Object>> selectAll(Connection connection) throws  SQLException;
}
