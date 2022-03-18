import com.interview.qualcomm.ExpressionParser;
import com.interview.qualcomm.ExpressionRegexCheck;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Test class to run tests on application
 */
public class ExpressionParsingIT {
    /** tests if fractions are floored
     */
    @Test
    public void testParseAndEvaluateExpression() {
        Assert.assertEquals("0", ExpressionRegexCheck.parseAndEvaluateExpression("2/3"));
    }
    /** tests if brackets are parsed
     */
    @Test
    public void testEvaluateBracket() {
        Assert.assertEquals("0", ExpressionRegexCheck.parseAndEvaluateExpression("(2/3)"));
    }
    /** tests if order of execution is from left to right
     */
    @Test
    public void testEvaluate() {
        Assert.assertEquals("2", ExpressionRegexCheck.parseAndEvaluateExpression("3*2/3"));
    }
    /** tests if order of execution is from left to right
     */
    @Test
    public void testEvaluate1() {
        Assert.assertEquals("0", ExpressionRegexCheck.parseAndEvaluateExpression("2/3*3"));
    }
    /** tests for double digits
     */
    @Test
    public void testEvaluate2() {
        Assert.assertEquals("46", ExpressionRegexCheck.parseAndEvaluateExpression("23+23"));
    }
    /** tests for double digit more than 1 operator
     */
    @Test
    public void testEvaluate3() {
        Assert.assertEquals("23", ExpressionRegexCheck.parseAndEvaluateExpression("23+23-23"));
    }
    /** tests for order of execution
     */
    @Test
    public void testEvaluate4() {
        Assert.assertEquals("2", ExpressionRegexCheck.parseAndEvaluateExpression("1*(2+4)/3"));
    }
    /** tests having more than 1 operator inside brackets are given priority
     */
    @Test
    public void testEvaluate5() {
        Assert.assertEquals("2", ExpressionRegexCheck.parseAndEvaluateExpression("1*(2-3/4)"));
    }
    /** tests having more than 1 operator inside brackets are given priority
     */
    @Test
    public void testEvaluate6() {
        Assert.assertEquals("10", ExpressionRegexCheck.parseAndEvaluateExpression("(2+3+(1+4))"));
    }
    /** tests having more than 1 operator consecutively
     */
    @Test
    public void testEvaluate7() {
        Assert.assertEquals("-1", ExpressionRegexCheck.parseAndEvaluateExpression("1*-1"));
    }
    /** tests having long complex operations
     */
    @Test
    public void testEvaluate8() {
        Assert.assertEquals("24", ExpressionRegexCheck.parseAndEvaluateExpression("((1-(1+2)+(2+3+(1+4)))*3)"));
    }
    /** tests file in.txt is read from given path and output is updated in out.txt
     */
    @Test
    public void testParseAndEvaluateFile() throws IOException {
        String path = "src/test/resources";
        String expectedOutput = Files.readString(Path.of(path + "/expectedOutput.txt"));
        ExpressionParser.parseAndEvaluateFile(path);
        String output = Files.readString(Path.of(path + "/out.txt"));
        Assert.assertEquals(expectedOutput, output);
    }
}
