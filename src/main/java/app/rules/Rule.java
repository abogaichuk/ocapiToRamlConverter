package app.rules;

import app.entity.DataType;

public interface Rule {
    Result evaluate(String source);
//    Result getResult();
}
