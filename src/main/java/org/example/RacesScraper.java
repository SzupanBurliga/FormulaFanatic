package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.*;

public class RacesScraper {
    private static final Logger logger = LogManager.getLogger(RacesScraper.class);
    static Document doc;
    public static void getData(String year) {
        String url = "https://www.formula1.com/en/results.html/"+year+"/races.html";
        logger.info("Start scraping from " +  url);
        try {
            doc = Jsoup.connect(url).get();
            Elements body = doc.select("tbody");
            String[] raceData = new String[8*body.select("tr").size()];
            logger.info("OK");
            for (int i = 0; i < (8 * body.select("tr").size()); i++) {
                //System.out.println(body.select("td").get(i).text());
                raceData[i]=body.select("td").get(i).text();

            }

        } catch (IOException e) {
            logger.error("nie pobrano informacji z linku"+ url);
        }

    }
  /*
    public static void main(String[] args) {
        RacesScraper.getData("2023");
        System.out.println(raceData[2]);
    }
*/
}