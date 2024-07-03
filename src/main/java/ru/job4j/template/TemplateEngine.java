package ru.job4j.template;

import java.util.Map;

public class TemplateEngine implements Generator {

    @Override
    public String produce(String template, Map<String, String> args) {
        StringBuilder result = new StringBuilder(template);

        for (String key : args.keySet()) {
            if (!template.contains("${" + key + "}")) {
                throw new IllegalArgumentException("Ключ '" + key + "' не найден в шаблоне");
            }
        }

        for (Map.Entry<String, String> entry : args.entrySet()) {
            String placeholder = "${" + entry.getKey() + "}";
            int start = result.indexOf(placeholder);

            while (start != -1) {
                result.replace(start, start + placeholder.length(), entry.getValue());
                start = result.indexOf(placeholder, start + entry.getValue().length());
            }
        }

        return result.toString();
    }
}
