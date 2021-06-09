import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class WizzairTest {

    @Test
    public void searchTicketTest(){
        Configuration.timeout = 80000;

        open("https://wizzair.com/");
        $("#search-departure-station").setValue("Vienna");
        $x("//mark[contains(text(),'Vienna')]").click();
        $("#search-arrival-station").setValue("Kyiv - Zhulyany");
        $x("//mark[contains(text(),'Kyiv - Zhulyany')]").click();

        $("#search-departure-date").click();
        $x("//button[@data-pika-year='2021'][@data-pika-month='5'][@data-pika-day='18']").click();
        $x("//button[@data-pika-year='2021'][@data-pika-month='5'][@data-pika-day='25']").click();
        $x("//button[@data-test='calendar-shrinkable-ok-button']").click();

        $("#search-passenger").click();
        $(".stepper__button.stepper__button--add").click();
        $x("//button[@data-test='flight-search-hide-panel']").click();

        $x("//span[contains(text(),'Search')]").click();

        switchTo().window(1);

        $$x("//div[@class='flight-select__fare-selector transition-fade-in transition-zoom-in transition-fade-in-enter transition-zoom-in-enter']").shouldHaveSize(2);
        $$x("//time[@class='flight-select__flight-info__day']").get(0).shouldHave(Condition.text(" Fri, 18 Jun 2021 "));
        $$x("//time[@class='flight-select__flight-info__day']").get(1).shouldHave(Condition.text(" Fri, 25 Jun 2021 "));
    }
}
