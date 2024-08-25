package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import page.MainPage;

public class SearchTests {

    MainPage mainPage = new MainPage();

    @Tag("BLOCKER")
    @ValueSource(strings = {
            "Python", "Java", "978-5-85582-347-9"
    })
    @ParameterizedTest(name = "For search query {0}, books are returned")
    void searchForExistedBooks(String searchQuery) {
        mainPage.openPage()
                .enterSearchArgument(searchQuery)
                .searchResultShouldBeMoreThanZero();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/isbn.csv", numLinesToSkip = 1)
    void searchOnlyByIsbn(String isbn) {
        mainPage.openPage()
                .enterSearchArgument(isbn)
                .searchResultShouldBeMoreThanZero();
    }

    @ParameterizedTest
    @CsvSource({
            "noting, 1",
            "!?%(), 2"
    })
    void searchForIncorrectValues(String searchQuery) {
        mainPage.openPage()
                .enterSearchArgument(searchQuery)
                .searchDoesNotFindAnything();
    }

}
