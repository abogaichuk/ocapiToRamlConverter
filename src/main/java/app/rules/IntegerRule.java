package app.rules;

public class IntegerRule implements Rule {

    @Override
    public Result evaluate(String source) {
        if (source.equals("Integer"))
            return new Result(source.toLowerCase(), "1");
        return null;
    }
}
