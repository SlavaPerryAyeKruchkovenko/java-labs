package com.company;
import java.util.*;

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
        List<Operation> ops = new ArrayList<>();
        for(int i = 0; i < arr.length - 1; i++){
            ops.add(new None());
        }
        return FindExpression(arr,res,0,ops.toArray(new Operation[arr.length - 1]),new ArrayList<>());
    }

    private static Iterable<String> FindExpression(int[] arr, int res,int index, Operation[] operationPull,
                                                   List<String> expressions){
        Operation[] operations = new Operation[]{new None(),new Mul(),new Sub(),new Div(),new Add()};
        if(index == arr.length){
            return null;
        }
        try {
            for (Operation operation : operations) {
                operationPull[index] = operation;
                int result = calc(arr,operationPull);
                if(result == res){
                    expressions.add(pullToString(arr,operationPull,res));
                }
                FindExpression(arr,res,index+1,operationPull,expressions);
            }

        }catch (Exception ex){
            System.out.println(index);
        }
        return expressions;
    }
    private static int calc(int[] arr,Operation[] pull) throws Exception {
        Stack<Integer> stack = new Stack<>();
        for (Integer num : arr) {
            stack.push(num);
        }
        if(arr.length - pull.length < 1){
            throw new Exception("incorrect pull");
        }
        else{
            for (Operation operation : pull) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                int res = (int)operation.calculate(num1,num2);
                stack.push(res);
            }
            return stack.pop();
        }
    }
    private static String pullToString(int[] arr,Operation[] pull,int res){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < arr.length - 1; i++){
            builder.append(arr[i]);
            builder.append(pull[i].toString());
        }
        builder.append(arr[arr.length-1]);
        builder.append("=");
        builder.append(res);
        return builder.toString();
    }
}
