package app;

import app.entity.ObjectMap;
import app.service.Parser;
import app.service.Selenium;
import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Configuration
@ComponentScan("app")
public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Main.class);
        ctx.refresh();

        Parser parser = ctx.getBean(Parser.class);
        Selenium selenium = ctx.getBean(Selenium.class);
        if (args.length > 0) {
//        String link = "https://documentation.b2c.commercecloud.salesforce.com/DOC1/topic/com.demandware.dochelp/OCAPI/current/shop/Documents/ProductSearchResult.html#id-1340385521";
//        String link = "https://documentation.b2c.commercecloud.salesforce.com/DOC1/topic/com.demandware.dochelp/OCAPI/current/shop/Documents/Product.html#id1778982388";
//            String link = "https://documentation.b2c.commercecloud.salesforce.com/DOC1/topic/com.demandware.dochelp/OCAPI/current/shop/Documents/Category.html#id-1673207185";
            String link = args[0];
            System.out.println("parsing link: "+link);
            List<ObjectMap> objects = selenium.getPages(link, new HashSet<>(), new ArrayList<>());

            printValues(objects, parser);
            System.exit(0);
        } else {
            System.out.println("provide link for parsing, example:\n"
            +"https://documentation.b2c.commercecloud.salesforce.com/DOC1/topic/com.demandware.dochelp/OCAPI/current/shop/Documents/Product.html#id1778982388");
        }
    }

    private static void printValues(List<ObjectMap> objects, Parser parser) {
        System.out.println("types:");
        objects.forEach(obj -> {
            printObjectName(obj.getLink());
            System.out.println("    properties:");
            parser.parse(obj.getHtml()).forEach(System.out::println);
        });
    }

    private static void printObjectName(String link) {
        int startIndex = link.lastIndexOf("/")+1;
        int endIndex = link.lastIndexOf(".html");
        System.out.println("  "+link.substring(startIndex, endIndex)+":");
    }

}
