package com.company;
import java.util.*;
import com.calculator.*;
import com.operation.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("print array of nums");
        String input = in.nextLine();
        int[] arr;
        int res;
        try{
            arr = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();

            System.out.println("print necessary result");
            res = in.nextInt();
            Iterable<String> expressions = FindExpression(arr,res);
            for (String str : expressions) {
                System.out.println(str);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    /**
     * @param arr an array of nums in which to place the signs
     * @param res num is result of expression
     */
    public static Iterable<String> FindExpression(int[] arr, int res){
        List<operation> ops = new ArrayList<>();
        for(int i = 0; i < arr.length - 1; i++){
            ops.add(new none());
        }
        return FindExpression(arr,res,0,ops.toArray(new operation[arr.length - 1]),new ArrayList<>());
    }

    private static Iterable<String> FindExpression(int[] arr, int res,int index, operation[] operationPull,
                                                   List<String> expressions){
        operation[] operations = new operation[]{new none(),new add(),new sub(),new mul(),new div()};
        if(index == arr.length){
            return null;
        }
        try {
            for (operation operation : operations) {
                operationPull[index] = operation;
                String exp = pullToString(arr,operationPull);
                double result = calculator.calc(exp);
                if(result == res){
                    expressions.add(exp+String.format(" = %s", res));
                }
                FindExpression(arr,res,index+1,operationPull,expressions);
            }

        }catch (Exception ex){
            //System.out.println(index);
        }
        return expressions;
    }
    /**
     * @param arr an array of nums in which to place the signs
     * @param pull an array of operations which was placed
     */
    private static String pullToString(int[] arr,operation[] pull){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < arr.length - 1; i++){
            builder.append(arr[i]);
            builder.append(pull[i].toString());
        }
        builder.append(arr[arr.length-1]);
        return builder.toString();
    }
}
