package orm;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.List;
import java.util.Map;

public interface Entity {

    public Object getAttr(String attrName) throws NoSuchFieldException, IllegalAccessException;
    public void setAttr(String attrName, Object value) throws NoSuchFieldException, IllegalAccessException;
    public void create(Connection connection)  throws SQLException;


    public Entity fromList(List<String> values, Connection connection) throws SQLException,IllegalAccessException;
    //public void insert(Connection connection, Object... values) throws  SQLException;

    public  List<Entity> selectAll(Connection connection) throws  SQLException,IllegalAccessException;

    public  void update(Connection connection) throws  SQLException, IllegalAccessException, NoSuchFieldException;

    public  void insert(Connection connection) throws  SQLException, IllegalAccessException, UnsupportedEncodingException;
    public Entity select(Connection connection)  throws  SQLException,IllegalAccessException, NoSuchFieldException;

    public  void delete(Connection connection) throws  NoSuchFieldException, SQLException, IllegalAccessException;

    public String getValuesListString(Connection connection);

}
