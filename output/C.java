
package generatedodl;

import java.lang.String;
import java.lang.reflect.Field;
import orm.*;
import java.sql.*;
import java.util.*;

public class C   implements Entity{
    public byte b;
    public char c;
    public short s;
    public int i;
    public long l;
    public double d;
    public long o;

    public void print(){
        System.out.println("Hello C");
    }

     @Override
     public Object getAttr(String attrName) throws NoSuchFieldException, IllegalAccessException{
          Field f = this.getClass().getDeclaredField(attrName);
          f.setAccessible(true);
          Object value = (Object) f.get(this);
          return value;
      }
        @Override
       public void setAttr(String attrName, Object value) throws NoSuchFieldException, IllegalAccessException{
            Field f = this.getClass().getDeclaredField(attrName);
            f.setAccessible(true);
            f.set(this, value);
        }

        @Override
        public void create(Connection connection) throws SQLException{
            		Statement st = connection.createStatement();
            		st.executeUpdate("CREATE TABLE C (b bit(8),c char(1),s smallint,i integer,l bigint,d double precision,o serial PRIMARY KEY)");
            		st.close();
        }

        @Override
        public List<Map<String, Object>> selectAll(Connection connection) throws SQLException {
            List<Map<String, Object>> resultList = new ArrayList<>();

            		Statement st = connection.createStatement();
            		ResultSet rs = st.executeQuery("SELECT * FROM C");
            		ResultSetMetaData rsmd = rs.getMetaData();
            		int columnCount = rsmd.getColumnCount();

            		while (rs.next()) {
            			Map<String, Object> rowMap = new HashMap<>();
            			for (int i = 1; i <= columnCount; i++) {
            				String columnName = rsmd.getColumnName(i);
            				Object columnValue = rs.getObject(i);
            				rowMap.put(columnName, columnValue);
            			}
            			resultList.add(rowMap);
            		}

            		rs.close();
            		st.close();

            	return resultList;
        }


}