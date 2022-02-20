package com.company;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int[] arr;
        int res;
        try{
            arr = Arrays.asList(input.split(" ")).stream().mapToInt(Integer::parseInt).toArray();
            res = in.nextInt();
            Iterable<String> expressions = FindExpression(arr,res);
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
        return null;
    }
}
