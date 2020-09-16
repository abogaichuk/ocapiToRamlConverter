package app.service;

import app.entity.DataPage;
import app.entity.ObjectMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class Selenium {

    public List<ObjectMap> getPages(String link, Set<String> links, List<ObjectMap> objects) {
        DataPage page = getPage(link);
        objects.add(ObjectMap.newBuilder().setLink(link).setHtml(page.getHtml()).build());

        Set<String> linksOnPage = page.getLinks().stream()
                .filter(l -> !links.contains(l))
                .collect(Collectors.toSet());
        links.addAll(linksOnPage);

        for (String s : linksOnPage) {
            getPages(s, links, objects);
        }

        return objects;
    }

    private DataPage getPage(String link) {
        WebDriver driver = createDriver(link);
        String pageSource = driver.getPageSource();
        Set<String> links = driver.findElement(By.className("tbody"))
                .findElements(By.tagName("a")).stream()
                .filter(Objects::nonNull)
                .map(a -> a.getAttribute("href"))
                .collect(Collectors.toSet());
        driver.close();
        return new DataPage(pageSource, links);
    }

    private WebDriver createDriver(String link) {
        WebDriver driver = new ChromeDriver();
        driver.get(link);
        driver.switchTo().frame("HelpFrame");
        driver.switchTo().frame("ContentFrame");
        driver.switchTo().frame("ContentViewFrame");
        return driver;
    }
}
