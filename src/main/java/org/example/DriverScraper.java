package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.*;
public class DriverScraper {
    private static final Logger logger = LogManager.getLogger(DriverScraper.class);
    static Document doc;
    public static Map<String, String> driverData = new HashMap<>();

    public static void getData(String driver) {
        String url = "https://www.formula1.com/en/drivers/" + driver + ".html";
        String[] atributes = {"team", "country", "podiums", "points", "grand_prix_entered", "world_champ", "highest_finish", "highest_position", "birth_date", "birth_place"};

        try {
            doc = Jsoup.connect(url).get();
            Elements body = doc.select("tbody");
            logger.info("Scrapowanie informacji z: "+url);
            for (int i = 0; i < 10; i++) {
              //  System.out.println(body.select("td").get(i).text());
                driverData.put(atributes[i], body.select("td").get(i).text());
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Błąd Scrapera, nie pobrano informacji z: "+ url);
        }
    }


}