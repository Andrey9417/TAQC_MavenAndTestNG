import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class RyanairTest {
    @Test
    public void searchTicketTest(){
        Configuration.timeout = 20000;

        open("https://www.ryanair.com/");
        $("#input-button__departure").clear();
        $("#input-button__departure").setValue("Vienna");
        $("#input-button__destination").setValue("Kyiv");
        $("[data-id=KBP]").click();

        $("[data-id='2021-06-18']").click();
        $("[data-id='2021-06-28']").click();

        $("[data-ref='counter.counter__increment']").click();
        $("button.passengers__confirm-button").click();

        $("button.flight-search-widget__start-search").click();

        $$("h3.header__title").shouldHaveSize(2);
        $$("h3.header__title").get(0).shouldHave(Condition.text(" Vienna до Київ-Бориспіль  "));
        $$("h3.header__title").get(1).shouldHave(Condition.text(" Київ-Бориспіль до Vienna  "));

        $("button[data-ref='2021-06-18']").shouldBe(Condition.visible);
        $("button[data-ref='2021-06-28']").shouldBe(Condition.visible);

    }
}
