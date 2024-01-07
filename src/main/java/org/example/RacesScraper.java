package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.*;

public class RacesScraper {
    private static final Logger logger = LogManager.getLogger(RacesScraper.class);
    static Document doc;
   static ArrayList<String> raceData = new ArrayList<>();
    public static void getData(String year) {
        String url = "https://www.formula1.com/en/results.html/"+year+"/races.html";
        logger.info("Scrapowanie informacji z: "+url);
        try {
            doc = Jsoup.connect(url).get();
            Elements body = doc.select("tbody");

            logger.info("OK");
            for (int i = 0; i < (8 * body.select("tr").size()); i++) {
                //System.out.println(body.select("td").get(i).text());
                raceData.add(body.select("td").get(i).text());
                System.out.println(raceData.get(i));
            }
        } catch (IOException e) {
            logger.error("Błąd Scrapera, nie pobrano informacji z: "+ url);
        }

    }

    public static void main(String[] args) {
        RacesScraper race = new RacesScraper();
        race.getData("2023");

    }
}