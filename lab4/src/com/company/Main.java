package com.company;

import com.map.*;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        try {
            String path = new File("").getAbsolutePath()+"\\assets\\example.txt";
            String content = new String(Files.readAllBytes(Paths.get(path)));
            String[][] arr = Arrays.stream(content.split("\n"))
                    .map(s -> s.split(""))
                    .toArray(String[][]::new);
            map _map = new map();
            arr = _map.findPath(arr);
            for(String [] str: arr){
                System.out.println(String.join("", str));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
