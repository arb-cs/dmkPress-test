package page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public MainPage openPage() {
        open("https://dmkpress.com/");
        return this;
    }

    private final SelenideElement searchInput = $("#main-search-input");

    public MainPage enterSearchArgument(String searchArgument) {
        searchInput.setValue(searchArgument).pressEnter();
        return this;
    }

}
