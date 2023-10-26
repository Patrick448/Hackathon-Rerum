package orm;
public class Utils {

    public static <T extends String> Object fromSQLToJavaType(String sqlType, Object value){

        switch (sqlType) {
            case "bit":
                return (Byte) value;
            case "char":
                return (Character) value;
            case "smallint":
                return (Short) value;
            case "integer":
                return (Integer) value;
            case "bigint":
                return (Long) value;
            case "double precision":
                return (Double) value;
            case "serial":
                return (Long) value;
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
