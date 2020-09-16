package app.service;

import app.entity.DataType;

import java.util.stream.Stream;

public interface Parser {
    Stream<DataType> parse(String html);
}
