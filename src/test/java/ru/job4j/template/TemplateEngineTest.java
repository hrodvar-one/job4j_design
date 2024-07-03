package ru.job4j.template;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class TemplateEngineTest {

    @Test
    void whenTemplateIsTrue() {
        TemplateEngine templateEngine = new TemplateEngine();
        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("name", "Petr Arsentev");
        argsMap.put("subject", "you");
        String result = templateEngine.produce("I am a ${name}, Who are ${subject}? ", argsMap);
        assertThat(result).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

    @Test
    void whenTemplateContainsKeysThatAreNotInMap() {
        TemplateEngine templateEngine = new TemplateEngine();
        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("name", "Petr Arsentev");
        argsMap.put("subject", "you");
        assertThatThrownBy(() -> templateEngine.produce("I am a ${name}, Who are ${sex}? ", argsMap))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenMapContainsKeysThatAreNotInTemplate() {
        TemplateEngine templateEngine = new TemplateEngine();
        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("name", "Petr Arsentev");
        argsMap.put("subject", "you");
        argsMap.put("sex", "man");
        assertThatThrownBy(() -> templateEngine.produce("I am a ${name}, Who are ${subject}? ", argsMap))
                .isInstanceOf(IllegalArgumentException.class);
    }
}