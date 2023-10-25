import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Scanner;


public class teste0 {

    public static Scanner input = new Scanner(System.in);
    public static void _main()
    {
        Integer _nlines;
        Integer _i;
        _nlines = (Integer)5;
        _i = (Integer)_nlines;
        int cont1 =0;
        while(cont1 < _nlines){
            int cont0 =0;
            while(cont0 < _i){
                System.out.print("*");
                cont0++;
            }
            _i = (Integer)(_i - 1);
            System.out.print("\n");
            cont1++;
        }
    }


    public static Object read_func (String type, int col, int line)
    {
        Object readObject = null;
        String read = input.nextLine();

        if(type.equals("Bool")){
            try{
                readObject = (String)read;
                if(!readObject.equals("true")&&!readObject.equals("false"))
                {
                    System.out.println("Error at line " + line + ":" +  col+ ": Unable to convert read value to "+ type);
                    System.exit(0);
                }
            }catch(Exception e){
                System.out.println("Error at line " + line + ":" + col + ": Unable to convert read value to "+ type);
                System.exit(0);
            }

        }else if(type.equals("Int")){
            try{
                readObject = Integer.parseInt(read);
            }catch(Exception e){
                System.out.println("Error at line " + line + ":" + col + ": Unable to convert read value to "+ type);
                System.exit(0);
            }
        }else if(type.equals("Float")){
            try{
                readObject = Float.parseFloat(read);
            }catch(Exception e){
                System.out.println("Error at line " + line + ":" + col + ": Unable to convert read value to "+ type);
                System.exit(0);
            }
        }else{
            readObject = (String)read.substring(0,1);
        }
        return readObject;
    }

    public static <T> ArrayList<T> initialize(int n, ArrayList<T> a){
            for(int j=0; j<n; j++){
                a.add(null);
            }
            return a;
    }

    public static void main(String args[]) {
        _main();
    }
}