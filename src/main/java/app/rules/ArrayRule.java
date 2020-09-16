package app.rules;

public class ArrayRule implements Rule {
    @Override
    public Result evaluate(String source) {
        if (source.startsWith("[")) {
            String typeValue = source.replaceAll("\\[|\\]", "");
            if (typeValue.equals("String"))
                typeValue = typeValue.toLowerCase();
            source= "array\n"+"        items:\n"+"          type: "+typeValue;
            return new Result(source, "");
        }
        return null;
    }
}
