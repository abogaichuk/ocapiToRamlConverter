package app.rules;

public class ObjectRule implements Rule {
    @Override
    public Result evaluate(String source) {
        return new Result(source, "");
    }
}
