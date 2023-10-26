package orm;

public class Utils {

    public static Object fromSQLToJavaType(String sqlType, Object object){

        switch (sqlType) {
            case "bit":
                return (Byte) Byte.parseByte((String) object, 2);
            case "char":
                return (Character) object;
            case "int2":
                return (Integer) object;
            case "int4":
                return (Integer) object;
            case "int8":
                return (Long) object;
            case "float8":
                return (Double) object;
            case "bigserial":
                return (Long) object;
            case "string":
                return (String) object;
            default:
                return object;
        }
    }

    public static Object fromSQLToJavaTypeUsingString(String sqlType, String value){

        switch (sqlType) {
            case "bit":
                return Byte.parseByte(value, 2);
            case "char":
                return (Character)value.toCharArray()[0];
            case "int2":
                return Integer.parseInt(value);
            case "int4":
                return Integer.parseInt(value);
            case "int8":
                return Long.parseLong(value);
            case "float8":
                return Double.parseDouble(value);
            case "bigserial":
                return Long.parseLong(value);
            case "string":
                return value;
            default:
                return value;
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
