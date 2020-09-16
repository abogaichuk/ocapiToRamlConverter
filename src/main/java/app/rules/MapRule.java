package app.rules;

public class MapRule implements Rule {
    @Override
    public Result evaluate(String source) {
        if (source.startsWith("Map")) {
            String typedValue = source.replaceAll("Map\\[|\\]", "");
            String[] split = typedValue.split(",");
            if (split.length == 2) {
                source = "array\n"+"        items:\n"+"          type: object\n"+"          properties:\n"+
                        "            key: "+getType(split[0].trim().toLowerCase())+"\n"+
                        "            value: "+getType(split[1].trim().toLowerCase());
                return new Result(source, "");
            } else {
                System.err.println("Error parsing type: "+source);
            }
        }
        return null;
    }

    private String getType(String s) {
        return s.equals("decimal") ? "number" : s;
    }
}
