package ru.netology.selenide;


import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    LocalDate today = LocalDate.now();
    LocalDate newDate = today.plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String formatDateNew = newDate.format(formatter);

@Test

     void  ValidRequest () {
        open ("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Уфа");
        $("[data-test-id=date] input").setValue(formatter.format(newDate));
        $("[data-test-id=name] input").setValue("Дмитрий Медведев");
        $("[data-test-id=phone] input").setValue("+79199999999");
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $(withText("Успешно!"))
                .shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно" +
                " забронирована на " + formatDateNew));
    }




}



