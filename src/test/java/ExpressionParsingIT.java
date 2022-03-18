import com.interview.qualcomm.ExpressionParser;
import com.interview.qualcomm.ExpressionRegexCheck;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
    public void testEvaluate2() {
        Assert.assertEquals("2", ExpressionRegexCheck.parseAndEvaluateExpression("3*2/3"));
    }
    /** tests if order of execution is from left to right
     */
    @Test
    public void testEvaluate3() {
        Assert.assertEquals("0", ExpressionRegexCheck.parseAndEvaluateExpression("2/3*3"));
    }
    /** tests if brackets are given priority
     */
    @Test
    public void testEvaluate() {
        Assert.assertEquals("2", ExpressionRegexCheck.parseAndEvaluateExpression("1*(2+4)/3"));
    }
    /** tests having more than 1 operator inside brackets are given priority
     */
    @Test
    public void testEvaluate1() {
        Assert.assertEquals("2", ExpressionRegexCheck.parseAndEvaluateExpression("1*(2-3/4)"));
    }
    /** tests file in.txt is read from given path and output is updated in out.txt
     */
    @Test
    public void testParseAndEvaluateFile() throws IOException {
        String path = "src/test/resources";
        String expectedOutput = Files.readString(Path.of(path + "/expectedOutput.txt"));
        ExpressionParser.parseAndEvaluateFile("src/test/resources");
        String output = Files.readString(Path.of(path + "/out.txt"));
        Assert.assertEquals(expectedOutput, output);
    }
}
