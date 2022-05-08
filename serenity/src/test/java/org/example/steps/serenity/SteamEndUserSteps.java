package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.SteamPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class SteamEndUserSteps {
    SteamPage steamPage;

    @Step
    public void enters(String keyword) {
        steamPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        steamPage.lookup_terms();
    }

    @Step
    public void chooses_sort(int sortIndex) {
        steamPage.selectSort(sortIndex);
    }

    @Step
    public void should_see_app_id(String appId) {
        assertThat(steamPage.getAppIds(), hasItem(containsString(appId)));
    }

    @Step
    public void should_see_no_results() {
        assertThat(steamPage.hasNoResultsFound(), is(true));
    }

    @Step
    public void is_the_home_page() {
        steamPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }
}
