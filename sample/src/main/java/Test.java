import Model.Action;
import Model.Parameter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by aravindp on 20/6/16.
 */
public class Test {

    public static void main(String[] args) throws JsonProcessingException {

        Action action = new Action();
        action.setName("find_cab");

        Parameter pickup = new Parameter();
        pickup.setName("pickup");

        Parameter placeId = new Parameter();
        placeId.setName("placeId");

        List<Parameter> p1 = new ArrayList<>();
        p1.add(pickup);

        List<Parameter> p2 = new ArrayList<>();
        p2.add(placeId);

        Parameter product = new Parameter();
        product.setName("product");

        Parameter product1 = new Parameter();
        product1.setName("product1");

        List andPrList = new ArrayList<>();
        andPrList.add(product);
        andPrList.add(product1);

        List orList = new ArrayList<>();
        orList.add(p1);
        orList.add(andPrList);

        List andList = new ArrayList<>();
        andList.add(andPrList);
        andList.add(orList);
        andList.add(andPrList);

        List orListt = new ArrayList<>();
        orListt.add(p1);
        orListt.add(p2);

        action.setMandatory(andList); //  p or (p & p)

        // action.setMandatory(andPrList); // only and

        // action.setMandatory(orListt); // only or
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(action));

        List<List<Parameter>> obj = action.getMandatory();

        String abc = recur(obj);
        System.out.printf("result : " + abc);

        writeObj(action);
        readObj();
    }

    public static String recur(List obj){
        int i = 0;
        String abc = "";
        boolean flag = true;
        while (obj.size() > i){
            if (obj.get(i) instanceof List && ((List) obj.get(i)).size() > 1){
                if (i > 0) {
                    System.out.println(flag);
                    if (flag) {
                        abc = abc + " or ";
                    } else {
                        abc = abc + " and ";
                    }
                }
                abc = abc + "(";
                abc = abc + recur((List) obj.get(i));
                abc = abc + " )";
                flag = false;
            } else {
                Parameter parameter;

                boolean prev = flag;

                if (obj.get(i) instanceof List){
                    parameter = (Parameter) ((List) obj.get(i)).get(0);
                    flag = false;
                } else {
                    parameter = (Parameter) obj.get(i);
                    flag = true;
                }

                if (i > 0) {
                    if (prev == false && flag == false) {
                        abc = abc + " and ";
                    } else {
                        abc = abc + " or ";
                    }
                }

                abc = abc + " " + parameter.getName();

                //System.out.println(obj.size() + " " + i + " " + parameter.getName());
            }
            i++;
        }
        return abc;
    }


    public static String recur2(List obj){
        int i = 0;
        String abc = "";
        int prev = i;
        int prevSize = obj.size();
        while (obj.size() > i){
            if (obj.get(i) instanceof List){
                // System.out.println(obj.size() + " " + i);
                if (((List)obj.get(i)).size() > 1) {
                    abc = abc + "(";
                }
                abc = abc + recur((List) obj.get(i));
            } else {
                Parameter parameter = (Parameter) obj.get(i);
                abc = abc + " " + parameter.getName();
                if (prevSize == obj.size() && prev + 1 == i){
                    if (obj.size() > i + 1) {
                        abc = abc + " and ";
                        System.out.println(" and ");
                    }
                } else {
                    abc = abc + " or ";
                    System.out.println(" or ");
                }

                prev = i;
                prevSize = obj.size();

                System.out.println(obj.size() + " " + i + " " + parameter.getName());
                //System.out.println("size : " + prevSize + ", prev : " + prev );
            }
            i++;
        }

        if (obj.size() > 1) {
            abc = abc + " )";
        }
        //System.out.println(abc);
        return abc;
    }

    public static void writeObj(Action myObj){
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("/home/aravindp/myTest", true);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(myObj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(oos  != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readObj() {
        ObjectInputStream objectinputstream = null;

        try {
            FileInputStream streamIn = new FileInputStream("/home/aravindp/myTest");
            objectinputstream = new ObjectInputStream(streamIn);
            Action readCase = (Action) objectinputstream.readObject();
            System.out.println(readCase);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectinputstream != null) {
                try {
                    objectinputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
