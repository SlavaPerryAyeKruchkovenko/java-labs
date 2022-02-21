package com.company;

import com.map.*;

import java.util.Scanner;

public class Main {
    /*
        example
        #s###
        #...#
        ..#.#
        .#..#
        ###.f
     */
    public static void main(String[] args){
        System.out.println("print maze");
        String[][] arr = new String[5][];
        for (int i = 0;i < 5; i++){
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            arr[i] = str.split("");
        }
        map map = new map();
        arr = map.findPath(arr);
        for(String [] str: arr){
            System.out.println(String.join("", str));
        }
    }
}
