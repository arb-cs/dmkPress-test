package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import page.MainPage;
import page.SearchPage;
import java.util.stream.Stream;

public class SearchTests {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    @Tag("BLOCKER")
    @ValueSource(strings = {
            "Python", "Java", "978-5-85582-347-9"
    })
    @ParameterizedTest(name = "For search query {0}, books are returned")
    void searchForExistedBooksTest(String searchQuery) {
        mainPage.openPage()
                .enterSearchArgument(searchQuery);
        searchPage.searchResultShouldBeMoreThanZero();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/isbn.csv", numLinesToSkip = 1)
    void searchOnlyByIsbnTest(String isbn) {
        mainPage.openPage()
                .enterSearchArgument(isbn);
        searchPage.searchResultShouldBeMoreThanZero();
    }

    static Stream<String> searchForIncorrectValuesTest() {
        return Stream.of("noting", "!?%()");
    }

    @ParameterizedTest
    @MethodSource("searchForIncorrectValuesTest")
    void searchForIncorrectValuesTest(String searchQuery) {
        mainPage.openPage()
                .enterSearchArgument(searchQuery);
        searchPage.searchDoesNotFindAnything();
    }

}
