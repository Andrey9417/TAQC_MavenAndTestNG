import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldFirstQuestionTest extends GeneralTestClass{

    FieldFirstQuestion obj = new FieldFirstQuestion();

    @BeforeMethod
    public void beforeMethodInput(){
        System.out.println("Test for Field \"Input\" was launched");
    }

    @AfterMethod
    public void afterMethodInput(){
        System.out.println("Test for Field \"Input\" was finished");
    }

    @Test
    public void test1EvaluateFirstQuestion(){

        Assert.assertEquals(obj.evaluateFirstQuestion("mouse"), "correct");

    }

    @Test
    public void test2EvaluateFirstQuestion(){

        Assert.assertEquals(obj.evaluateFirstQuestion("keyboard"), "correct");

    }

    @Test
    public void test3EvaluateFirstQuestion(){

        Assert.assertEquals(obj.evaluateFirstQuestion("scanner"), "correct");

    }

    @Test
    public void test4EvaluateFirstQuestion(){

        Assert.assertEquals(obj.evaluateFirstQuestion("gegregre"), "wrong");

    }

    @Test
    public void test5EvaluateFirstQuestion(){

        Assert.assertEquals(obj.evaluateFirstQuestion("gerger2"), "invalid");

    }

    @Test
    public void test6EvaluateFirstQuestion(){

        Assert.assertEquals(obj.evaluateFirstQuestion("mousemousemousemouse"), "invalid");

    }

    @Test
    public void testEvaluateFirstQuestion() {

        System.out.println("test");
    }
}
