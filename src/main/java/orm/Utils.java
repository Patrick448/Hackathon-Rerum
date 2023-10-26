package orm;

public class Utils {

    public static Object fromSQLToJavaType(String sqlType, Object object){

        switch (sqlType) {
            case "bit":
                return (Byte) Byte.parseByte((String) object, 2);
            case "char":
                return (Character) object;
            case "smallint":
                return (Short) object;
            case "integer":
                return (Integer) object;
            case "bigint":
                return (Long) object;
            case "double precision":
                return (Double) object;
            case "serial":
                return (Long) object;
            default:
                return object;
        }
    }

    public static Byte fromSQLToJavaByte(Object value){
        return (Byte) value;
    }

    public static Character fromSQLToJavaChar(Object value){
        return (Character) value;
    }

    public static Short fromSQLToJavaShort(Object value){
        return (Short) value;
    }

    public static Integer fromSQLToJavaInteger(Object value){
        return (Integer) value;
    }

    public static Long fromSQLToJavaLong(Object value){
        return (Long) value;
    }

    public static Double fromSQLToJavaDouble(Object value){
        return (Double) value;
    }

    public static Long fromSQLToJavaSerial(Object value){
        return (Long) value;
    }

    public static String fromSQLToJavaString(Object value){
        return (String) value;
    }

    public static String byteToBinary(byte value) {
        return String.format("%8s", Integer.toBinaryString(value & 0xFF)).replace(' ', '0');
    }

}
