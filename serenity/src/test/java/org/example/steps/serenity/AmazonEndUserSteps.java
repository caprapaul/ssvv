package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.AmazonPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class AmazonEndUserSteps {
    AmazonPage amazonPage;

    @Step
    public void selectSort(){
        amazonPage.selectSort();
    }

    @Step
    public void is_the_home_page() {
        amazonPage.open();
    }

    @Step
    public void should_see_product_id(String productId) {
        assertThat(amazonPage.getProductIds(), hasItem(containsString(productId)));
    }

}
