package com.interview.qualcomm;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The class to check for regex expressions and
 * execute them
 */
public class ExpressionRegexCheck {
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
        }
        return checkDivisionAndEvaluate(expression).toString();
    }
    /**
     * function to evaluate an arithmetic expression
     *
     * @param expression single expression without brackets
     * @return evaluates and returns a long value
     */
    private static Long checkDivisionAndEvaluate(String expression) {
        Pattern p = Pattern.compile("[0-9]{1,}[/,*][0-9]{1,}");
        Matcher m = p.matcher(expression);
        Expression e;
        while(m.find()) {
            //evaluates multiplication and division from left to right
            String exp = m.group();
            e = new Expression(exp);
            exp = String.valueOf(Math.floor(e.calculate()));
            expression = expression.replace(m.group(), exp);;
        }
        e = new Expression(expression);
        return ((long) Math.floor(e.calculate()));
    }
}