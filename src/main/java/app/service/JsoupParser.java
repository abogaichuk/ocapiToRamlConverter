package app.service;

import app.entity.DataType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class JsoupParser implements Parser {
    @Override
    public Stream<DataType> parse(String html) {
        Document document = Jsoup.parse(html, "UTF-8");
        return document.getElementsByClass("row").stream().skip(1)
                .map(element -> {
                    Elements ths = element.getElementsByTag("td");
                    return DataType.newBuilder()
                            .setName(ths.get(0).text())
                            .setType(ths.get(1).text())
                            .setConstraints(ths.get(2).text())
                            .setDescription(ths.get(3).text())
                            .build();
                });
    }
}
