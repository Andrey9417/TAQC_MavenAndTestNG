import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FieldSecondQuestionTest extends GeneralTestClass {

    FieldSecondQuestion field2 = new FieldSecondQuestion();

    @Parameters({"firstname"})
    @Test(priority = 3)
    public void testEvaluateSecondQuestion() {
        Assert.assertEquals(field2.evaluateSecondQuestion("8"), "correct");
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void test1EvaluateSecondQuestion() {
        Assert.assertNotEquals(field2.evaluateSecondQuestion("9"), "correct");
    }

    @Test(timeOut = 55)
    public void test2EvaluateSecondQuestion() {
        Assert.assertEquals(field2.evaluateSecondQuestion("8"), "correct");
    }

    @DataProvider
    public Object[][] data() {
        return new String[][]
                {{"0", "wrong"},
                 {"-1", "invalid"},
                 {"7", "wrong"},
                 {"1111", "invalid"},
                 {"wefwef", "invalid"}};
    }

    @Test(dataProvider = "data")
    public void test3EvaluateSecondQuestion(String first, String second) {
        Assert.assertEquals(field2.evaluateSecondQuestion(first), second);
    }

    @Test
    public void test4EvaluateSecondQuestion() {
        Assert.assertTrue(field2.evaluateSecondQuestion("999").equals("wrong"));
    }

    @Parameters({"correct answer for q2"})
    @Test
    public void test5EvaluateSecondQuestion(String value) {
        assertNotEquals(field2.evaluateSecondQuestion(value), "invalid");
    }
}