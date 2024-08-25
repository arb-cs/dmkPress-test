package page;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public MainPage openPage() {
        open("https://dmkpress.com/");
        return this;
    }

    private final SelenideElement searchInput = $("#main-search-input");
    private final ElementsCollection searchResults = $(".thumbnails").$$(".span3");
    private final SelenideElement noSearchResults = $(".content p");

    public MainPage enterSearchArgument(String searchArgument) {
        searchInput.setValue(searchArgument).pressEnter();
        return this;
    }

    public MainPage searchResultShouldBeMoreThanZero() {
        searchResults.shouldHave(sizeGreaterThan(0));
        return this;
    }

    public MainPage searchDoesNotFindAnything() {
        noSearchResults.shouldHave(text("ничего не найдено"));
        return this;
    }

}
