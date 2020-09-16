package app.rules;

public class DecimalRule implements Rule {
    @Override
    public Result evaluate(String source) {
        if (source.equals("Decimal")) {
            return new Result("number", "0.1");
        }
        return null;
    }
}
