package app.entity;

import app.rules.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
public class DataType {
    private DataType() {}
    private static List<Rule> rules = new ArrayList<>();

    static {
        rules.add(new StringRule());
        rules.add(new IntegerRule());
        rules.add(new BooleanRule());
        rules.add(new DecimalRule());
        rules.add(new MapRule());
        rules.add(new ArrayRule());
        rules.add(new DateTimeRule());
        rules.add(new EnumRule());
        rules.add(new ObjectRule());
    }

    private String name, type, description, constraints, example;

    public static Builder newBuilder() {
        return new DataType().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setName(String name) {
            DataType.this.name = name;
            return this;
        }

        public Builder setType(String type) {
            Result result = rules.stream()
                    .map(rule -> rule.evaluate(type))
                    .filter(Objects::nonNull)
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("something wrong with type: "+ type));

            DataType.this.type = result.getType();
            DataType.this.example = result.getExample();
            return this;
        }

        public Builder setConstraints(String constraints) {
            if (!constraints.isEmpty()) {
                String[] split = constraints.split(",");
                constraints = Stream.of(split)
                        .filter(s -> s.trim().startsWith("mandatory") || s.trim().startsWith("maxLength") || s.trim().startsWith("minLength"))
                        .reduce("", (subtotal, element) -> subtotal + "\n        " +
                                element.trim().replace("=", ": ").replace("mandatory", "required"));
            }
            DataType.this.constraints = constraints;
            return this;
        }

        public Builder setDescription(String description) {
            DataType.this.description = description;
            return this;
        }

        public DataType build() {
            return DataType.this;
        }
    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty() || s.equals("\n");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("      ");
        sb.append(name)
                .append(":\n")
                .append("        type: ")
                .append(type)
                .append("\n")
                .append("        description: ")
                .append(description);
        if (!this.isEmpty(example))
            sb.append("\n")
                .append("        example: ")
                .append(example);
        if (!this.isEmpty(constraints.trim())) {
            sb.append(constraints);
        }
        return sb.toString();
    }
}

