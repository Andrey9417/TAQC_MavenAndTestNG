package HW_1_Tests;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import utils.PropertiesReaderClassLoader;

public class GeneralTestClass {

    @AfterSuite()
    public void test2() {
        Assert.assertNotNull(PropertiesReaderClassLoader.getInstance().getValueFromProperty( "type"));
        String type = PropertiesReaderClassLoader.getInstance().getValueFromProperty( "type");
        String name = PropertiesReaderClassLoader.getInstance().getValueFromProperty( "name");
        System.out.println("type = " + type);
        System.out.println("name = " + name);
    }
}
