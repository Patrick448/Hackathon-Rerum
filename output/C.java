
package generatedodl;

import java.lang.String;
import java.lang.reflect.Field;
import orm.*;

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
        public void create(Object connection){

        }
}