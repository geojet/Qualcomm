package com.interview.qualcomm;

import java.util.Scanner;

/**
 * The main application entry
 */
public class ExpressionParsingApplication {
    /**
     * The main function
     *
     * @param args the commandline args
     */
    public static void main(final String[] args) {
        //System.in is a standard input stream.
        Scanner sc= new Scanner(System.in);

        System.out.println("Enter the File path for parsing : ");
        //reads path string.
        String filePath = sc.nextLine();
        ExpressionParser.parseAndEvaluateFile(filePath);
    }
}
