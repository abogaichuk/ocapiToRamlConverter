package app.rules;

public class StringRule implements Rule {

    @Override
    public Result evaluate(String source) {
        if (source.equals("String") || source.equals("Localized<String>"))
            return new Result("string", "\"aaa\"");
        return null;
    }
}
