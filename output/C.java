
package generatedodl;

import java.lang.String;
import java.lang.reflect.Field;
import orm.*;
import java.sql.*;
import java.util.*;
import java.util.stream.*;
import orm.Utils;
import java.io.UnsupportedEncodingException;


public class C   implements Entity{
    public Byte b;
    public String c;
    public Integer s;
    public Integer i;
    public Long l;
    public Double d;
    public Long o;

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
            		st.executeUpdate("CREATE TABLE C (b bit(8),c char(1),s smallint,i integer,l bigint,d double precision,o bigserial PRIMARY KEY)");
            		st.close();
        }

        @Override
        public List<Entity> selectAll(Connection connection) throws SQLException,IllegalAccessException {

            List<Entity> resultList = new ArrayList<>();
            Field[] fields = C.class.getFields();

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM C");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                C obj = new C();
                for (int i = 0; i < columnCount; i++) {
                    Field f = fields[i];
                    Object columnValue = Utils.fromSQLToJavaType(rsmd.getColumnTypeName(i+1), rs.getObject(i+1));
                    f.setAccessible(true);
                    f.set(obj, columnValue);

                }
                resultList.add(obj);
            }

            rs.close();
            st.close();

            return resultList;
        }



        @Override
        public void insert(Connection connection) throws SQLException, IllegalAccessException, UnsupportedEncodingException{
                List<Field> fields =  Arrays.asList(C.class.getFields());
                Stream<Field> streamFields = fields.stream();

                String values = streamFields.map(f -> {
                    try {
                        Object value = ((Field)f).get(this);
                        if(value instanceof String)
                            return "\'" + value + "\'";
                        else if(value instanceof Byte)
                            return "B\'"+ Utils.byteToBinary(((Byte)value).byteValue())+"\'";
                        else if (value == null)
                            return "DEFAULT";
                        else
                            return String.valueOf(value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();

                    }
                    return "";
                }).collect(Collectors.joining(", "));

                streamFields = fields.stream();
                String fieldNames = streamFields.map(f -> {return f.getName();}).collect(Collectors.joining(", "));

                String query = "INSERT into C VALUES (" + values + ")";

                Statement st = connection.createStatement();
                st.executeUpdate(query);
                st.close();
        }

}