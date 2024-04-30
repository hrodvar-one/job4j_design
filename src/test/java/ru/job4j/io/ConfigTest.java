package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndEmptyLine() {
        String path = "./data/pair_with_comment_and_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("day")).isEqualTo("4");
    }

    @Test
    void whenPairWithViolationOfThePattern() {
        String path = "./data/pair_with_violation_of_the_pattern.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithTwoEqualSigns() {
        String path = "./data/pair_with_two_equal_signs.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("4")).isEqualTo("5=1");
        assertThat(config.value("6")).isEqualTo("re=");
    }
}