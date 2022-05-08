package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://www.amazon.com/s?i=specialty-aps&bbn=16225009011&rh=n%3A%2116225009011%2Cn%3A172541&ref=nav_em__nav_desktop_sa_intl_headphones_0_2_5_8/")
public class AmazonPage extends PageObject {
    @FindBy(id="a-autoid-0-announce")
    private WebElementFacade sortTrigger;

    public void selectSort() {
        sortTrigger.click();
        find(By.id("s-result-sort-select_1")).click();
    }

    public List<String> getProductIds() {
        WebElementFacade results = find(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']"));
        return results.findElements(By.xpath("*")).stream()
                .map(element -> element.getAttribute("data-asin") )
                .collect(Collectors.toList());
    }
}
