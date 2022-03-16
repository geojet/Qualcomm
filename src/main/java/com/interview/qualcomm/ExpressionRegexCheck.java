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
        Pattern p = Pattern.compile("\\([0-9]{1,}[/,*,+,-][0-9]{1,}\\)");
        Matcher m = p.matcher(expression);
        while(m.find()) {
            //evaluates expressions inside brackets first
            expression = expression.replace(m.group(),evaluate(m.group()).toString());
        }
        return evaluate(expression).toString();
    }
    /**
     * function to evaluate an arithmetic expression
     *
     * @param expression single expression without brackets
     * @return evaluates and returns a long value
     */
    private static Long evaluate(String expression) {
        Expression e = new Expression(expression);
        return ((long) Math.floor(e.calculate()));
    }
}
