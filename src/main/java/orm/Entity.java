package orm;
import java.sql.*;

public interface Entity {

    public Object getAttr(String attrName) throws NoSuchFieldException, IllegalAccessException;
    public void setAttr(String attrName, Object value) throws NoSuchFieldException, IllegalAccessException;
    public void create(Connection connection)  throws SQLException;

}
