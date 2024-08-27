package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    private final ElementsCollection searchResults = $(".thumbnails").$$(".span3");
    private final SelenideElement noSearchResults = $(".content p");

    public SearchPage searchResultShouldBeMoreThanZero() {
        searchResults.shouldHave(sizeGreaterThan(0));
        return this;
    }

    public SearchPage searchDoesNotFindAnything() {
        noSearchResults.shouldHave(text("ничего не найдено"));
        return this;
    }

}
