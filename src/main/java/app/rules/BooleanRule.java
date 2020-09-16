package app.rules;

public class BooleanRule implements Rule {

    @Override
    public Result evaluate(String source) {
        if (source.equals("Boolean"))
            return new Result(source.toLowerCase(), "true");
        return null;
    }
}
