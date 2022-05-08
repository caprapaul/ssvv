package org.example.features.sort;

import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.AmazonEndUserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/AmazonTestData.csv")
//@RunWith(SerenityRunner.class)
public class AmazonSortStory {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Getter
    @Setter
    private String id;

    @Steps
    public AmazonEndUserSteps anna;

    @Issue("#AMAZON-1")
    @Test
    public void sort_by_price_low_should_display_the_corresponding_product() {
        anna.is_the_home_page();
        anna.selectSort();
        anna.should_see_product_id(getId());
    }
}
