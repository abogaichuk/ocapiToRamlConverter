package app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class DataPage {
    private final String html;
    private final Set<String> links;
}
