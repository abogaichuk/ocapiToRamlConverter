package app.rules;

import java.util.stream.Stream;

public class EnumRule implements Rule {
    @Override
    public Result evaluate(String source) {
        if (source.startsWith("Enum {")) {
            StringBuilder sb = new StringBuilder();
            sb.append("string\n")
                    .append("        enum:");

            String[] enums = source
                    .replaceAll("Enum \\{", "")
                    .replaceAll("}", "")
                    .split(",");
            if (enums.length > 0) {
                String combined = Stream.of(enums)
                        .reduce("", (subtotal, element) -> subtotal+"\n          - \"" + element.trim() + "\"");
                sb.append(combined);
                return new Result(sb.toString(), enums[0].trim());
            }
        }
        return null;
    }
}
