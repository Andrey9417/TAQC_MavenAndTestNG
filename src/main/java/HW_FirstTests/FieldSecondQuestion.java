package HW_FirstTests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldSecondQuestion {

    public String evaluateSecondQuestion(String answer) {

        System.out.println("Q2: How many beats are in byte?");
        if (answer.equals("8")) return "correct";
        Pattern p = Pattern.compile("[0-9]{1,3}");
        Matcher m = p.matcher(answer);
        if (m.matches()){
            return "wrong";
        } else {
            return "invalid";
        }
    }
}
