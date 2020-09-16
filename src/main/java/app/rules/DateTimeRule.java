package app.rules;

public class DateTimeRule implements Rule {
    @Override
    public Result evaluate(String source) {
        if (source.equals("SiteSpecific<DateTime>"))
            return new Result("datetime", "9999-12-31T00:00:00.0Z");
        return null;
    }
}
