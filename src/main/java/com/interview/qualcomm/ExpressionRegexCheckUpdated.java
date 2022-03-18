package com.interview.qualcomm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The class to check for regex expressions and
 * execute them
 * This class has self defined arithmetic parsing and execution
 */
public class ExpressionRegexCheckUpdated {
    /**
     * finds pattern in expression
     * checks for expression which is inside brackets and evaluates them first
     *
     * @param expression single line expression to be evaluated
     */
    public static String parseAndEvaluateExpression(String expression) {
        //patter matches expressions surrounded by brackets
        Pattern p = Pattern.compile("\\([[0-9]{1,}[/,*,+,-]]{1,}[0-9]{1,}\\)");
        Matcher m = p.matcher(expression);
        while(m.find()) {
            //evaluates expressions inside brackets first
            String exp = m.group();
            expression = expression.replace(m.group(),parseAndEvaluateExpression(exp.substring(1,exp.length()-1)));
            m = p.matcher(expression);
        }
        return checkDivisionAndEvaluate(expression).toString();
    }
    /**
     * function to evaluate an arithmetic expression
     *
     * @param expression single expression without brackets
     * @return evaluates and returns a long value
     */
    private static Integer checkDivisionAndEvaluate(String expression) {
        Pattern p = Pattern.compile("[0-9]{1,}[/,*][0-9]{1,}");
        Matcher m = p.matcher(expression);
        while(m.find()) {
            //evaluates multiplication and division from left to right
            String exp = m.group();
            expression = expression.replace(exp, checkBasicAndEvaluate(exp).toString());
        }
        return checkBasicAndEvaluate(expression);
    }
    private static Integer checkBasicAndEvaluate(String expression) {
        Pattern p = Pattern.compile("[-]{0,1}[0-9]{1,}[-+/*][-]{0,1}[0-9]{1,}");
        Matcher m = p.matcher(expression);
        while (m.find()) {
            //evaluates multiplication and division from left to right
            String exp = m.group();
            String[] operands;
            if(exp.contains("+")) {
                operands = exp.split("[+]");
                expression = expression.replace(exp, String.valueOf(Integer.parseInt(operands[0]) + Integer.parseInt(operands[1])));
            } else if(exp.contains("/")) {
                operands = exp.split("[/]");
                expression = expression.replace(exp, String.valueOf(Integer.parseInt(operands[0]) / Integer.parseInt(operands[1])));
            } else if(exp.contains("*")) {
                operands = exp.split("[*]");
                expression = expression.replace(exp, String.valueOf(Integer.parseInt(operands[0]) * Integer.parseInt(operands[1])));
            } else {
                operands = exp.split("[-]");
                expression = expression.replace(exp, String.valueOf(Long.valueOf(Integer.parseInt(operands[0]) - Integer.parseInt(operands[1]))));
            }
            m = p.matcher(expression);
        }
        return Integer.parseInt(expression);
    }
}
