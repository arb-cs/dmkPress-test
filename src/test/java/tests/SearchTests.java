package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import page.MainPage;
import page.SearchPage;
import java.util.stream.Stream;

public class SearchTests {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();

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

////    @CsvSource({
////            "noting, 1",
////            "!?%(), 2"
////    })

    static Stream<String> searchForIncorrectValuesTest() {
        return Stream.of("noting", "!?%()");
    }

    @ParameterizedTest
    @MethodSource
    void searchForIncorrectValuesTest(String searchQuery) {
        mainPage.openPage()
                .enterSearchArgument(searchQuery);
        searchPage.searchDoesNotFindAnything();
    }

}
