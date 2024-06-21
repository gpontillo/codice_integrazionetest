package org.base;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class WordDuplicateRemoverTest {

    // T1, T2
    @Test
    public void inputIsNullOrEmpty() {
        WordDuplicateRemover wordDuplicateRemover = new WordDuplicateRemover();
        assertEquals(null, wordDuplicateRemover.removeWordDuplicates(null, "gagagigo"));
        assertEquals("", wordDuplicateRemover.removeWordDuplicates("", "gagagigo"));
    }

    // T3, T4
    @Test
    public void wordIsNullOrEmpty() {
        WordDuplicateRemover wordDuplicateRemover = new WordDuplicateRemover();
        assertEquals("gagagigo", wordDuplicateRemover.removeWordDuplicates("gagagigo", null));
        assertEquals("gagagigo", wordDuplicateRemover.removeWordDuplicates("gagagigo", ""));
    }

    // Da T5 a T12
    @ParameterizedTest
    @MethodSource("forInputOfVariousLength")
    public void inputAndWordVariousLength(String expected, String input, String word) {
        WordDuplicateRemover wordDuplicateRemover = new WordDuplicateRemover();
        assertEquals(expected, wordDuplicateRemover.removeWordDuplicates(input, word));
    }

    public static Stream<Arguments> forInputOfVariousLength() {
        return Stream.of(
                Arguments.of("a", "a", "b"),                                    // T5
                Arguments.of("a", "a", "a"),                                    // T6
                Arguments.of("a a b", "a a b", "c"),                            // T7
                Arguments.of("a a b", "a a b", "b"),                            // T8
                Arguments.of("a b", "a a b", "a"),                              // T9
                Arguments.of("test test ciao", "test test ciao", "duck"),       // T10
                Arguments.of("test test ciao", "test test ciao", "ciao"),       // T11
                Arguments.of("test ciao", "test test ciao", "test")             // T12
        );
    }

    // T13
    @Test
    public void wordSentence() {
        WordDuplicateRemover wordDuplicateRemover = new WordDuplicateRemover();
        assertEquals("tre tigri contro", wordDuplicateRemover.removeWordDuplicates("tre tigri contro tre tigri", "tre tigri"));
    }

    // T14
    @Test
    public void inputWithSpecialChar() {
        WordDuplicateRemover wordDuplicateRemover = new WordDuplicateRemover();
        assertEquals("$@ / \n gagagigo", wordDuplicateRemover.removeWordDuplicates("$@ / \n gagagigo gagagigo", "gagagigo"));
    }

    // T15
    @Test
    public void wordWithSpecialChar() {
        WordDuplicateRemover wordDuplicateRemover = new WordDuplicateRemover();
        assertEquals("gagagigo $\n@ gagagigo", wordDuplicateRemover.removeWordDuplicates("gagagigo $\n@ $\n@ gagagigo", "$\n@"));
    }

    // T16
    @Test
    public void duplicateWordWithPuntaction() {
        WordDuplicateRemover wordDuplicateRemover = new WordDuplicateRemover();
        assertEquals("test ciao", wordDuplicateRemover.removeWordDuplicates("test test, test ciao", "test"));
    }
}
