package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://store.steampowered.com/")
public class SteamPage extends PageObject {
    @FindBy(name="term")
    private WebElementFacade searchTerms;

    @FindBy(id="sort_by_trigger")
    private WebElementFacade sortTrigger;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
        searchTerms.sendKeys(Keys.ENTER);
    }

    public void selectSort(int sortIndex) {
        sortTrigger.click();
        WebElementFacade sortList = find(By.id("sort_by_droplist"));
        sortList.findBy(By.cssSelector(String.format("li:nth-child(%d)", sortIndex + 1))).click();
    }

    public boolean hasNoResultsFound() {
        WebElementFacade resultsContainer = find(By.id("search_result_container"));
        return resultsContainer.findElement(By.xpath("/p[2]")) != null;
    }

    public List<String> getAppIds() {
        WebElementFacade resultsRows = find(By.id("search_resultsRows"));
        return resultsRows.findElements(By.xpath("*")).stream()
                .map( element -> element.getAttribute("data-ds-appid") )
                .collect(Collectors.toList());
    }
}
