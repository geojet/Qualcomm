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
    /** tests if brackets are given priority
     */
    @Test
    public void testEvaluate() {
        Assert.assertEquals("2", ExpressionRegexCheck.parseAndEvaluateExpression("1*(2+4)/3"));
    }
    /** tests file in.txt is read from given path and output is updated in out.txt
     */
    @Test
    public void testParseAndEvaluateFile() throws IOException {
        String path = "src/test/resources";
        String expectedOutput = Files.readString(Path.of(path + "/expectedOutput.txt"));
        ExpressionParser.parseAndEvaluateFile("src/test/resources");
        String output = Files.readString(Path.of(path + "/expectedOutput.txt"));
        Assert.assertEquals(expectedOutput, output);
    }
}
