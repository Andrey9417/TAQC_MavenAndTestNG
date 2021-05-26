package HW_FirstTests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldFirstQuestion {

    public String evaluateFirstQuestion(String answer){

        System.out.println("Q1: Name input device");
        switch (answer){
            case "mouse":
            case "keyboard":
            case "scanner": {
                return "correct";
            }
            default:{
                if(answer.length()<1 || answer.length()>15) {
                    return "invalid";
                }
                Pattern p = Pattern.compile("[a-zA-Z]+");
                Matcher m = p.matcher(answer);
                if (m.matches()){
                    return "wrong";
                } else {
                    return "invalid";
                }

            }
        }
    }
}
