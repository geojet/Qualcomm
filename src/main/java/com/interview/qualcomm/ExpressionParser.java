package com.interview.qualcomm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
/**
 * The class reads the in.txt and evaluates the expressions
 * writes to the out.txt
 *
 */
public class ExpressionParser {
    /**
     * Reads the input from path given and writes the out.txt in the same folder
     *
     * @param path of the folder containing in.txt
     */
    public static void parseAndEvaluateFile(String path) {
        //overwrites file, if file exists.
        Writer fileWriter = null;
        try {

            String[] expressionsArray = getExpressionsArray(path);

            fileWriter = new FileWriter(path + "/out.txt", false);
            evaluateAndUpdate(expressionsArray, fileWriter);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * splits the string in file by newline and returns an array of expressions
     *
     * @param path of the folder containing in.txt
     * @return a string array with each line of expression
     */
    private static String[] getExpressionsArray(String path) {
        String inputFileContent = null;
        try {
            inputFileContent = Files.readString(Path.of(path + "/in.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputFileContent.split("\\r?\\n");
    }
    /**
     * Reads each array of expression and calls updateFIle
     *
     * @param expressionArray of expressions
     * @param fileWriter file pointer to write the output
     */
    private static void evaluateAndUpdate(String[] expressionArray, Writer fileWriter) {
        Arrays.stream(expressionArray)
                .forEach(expression -> updateFile(expression, fileWriter));
    }
    /**
     * The main function
     *
     * @param expression to be added to the output
     * @param fileWriter file pointer to write the output
     */
    private static void updateFile(String expression, Writer fileWriter) {
        try {
            String newLine = "\n";
            String value = ExpressionRegexCheckUpdated.parseAndEvaluateExpression(new String(expression));
            String evaluatedOutput = expression.contains(newLine)?expression.substring(0,expression.length() - 2) + "=" : expression.concat("=") + value;
            fileWriter.append(evaluatedOutput);
            fileWriter.append(newLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
