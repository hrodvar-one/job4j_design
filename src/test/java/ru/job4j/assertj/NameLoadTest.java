package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkName() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("name"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void  checkMessage() {
        NameLoad nameLoad = new NameLoad();
        String word = "name";
        assertThatThrownBy(() -> nameLoad.parse(word))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*проверяем факт наличия сообщения*/
                .message()
                .isNotEmpty();
    }

    @Test
    void  checkWordMessage() {
        NameLoad nameLoad = new NameLoad();
        String word = "name";
        assertThatThrownBy(() -> nameLoad.parse(word))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(word)
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("name");
    }

    @Test
    void  checkWhatNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        var words = new String[0];
        assertThatThrownBy(() -> nameLoad.parse(words))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("empty");
    }

    @Test
    void  checkWhatNameStartsWithEquals() {
        NameLoad nameLoad = new NameLoad();
        var words = new String[]{"=test", "=test2"};
        assertThatThrownBy(() -> nameLoad.parse(words))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void  checkWhatNameEndsWithEquals() {
        NameLoad nameLoad = new NameLoad();
        var words = new String[]{"test=", "test2="};
        assertThatThrownBy(() -> nameLoad.parse(words))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("does not contain a value");
    }
}