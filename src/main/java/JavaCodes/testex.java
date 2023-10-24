package JavaCodes;

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Scanner;


public class testex {

    public static Scanner input = new Scanner(System.in);
    public static List<Object> _func()
    {
        System.out.print(2);
        List<Object> arr = new ArrayList<Object>();
        arr.add(6);
        return arr;
    }

    public static void _main()
    {
        Integer _x;
        _x = (Integer)(Integer)_func().get(0);
        int cont1 =0;
        while(cont1 < 2){
            System.out.print(0);
            int cont0 =0;
            while(cont0 < 3){
                System.out.print(1);
                cont0++;
            }
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