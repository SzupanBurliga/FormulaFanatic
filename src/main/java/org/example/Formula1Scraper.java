package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import org.apache.logging.log4j.*;

public class Formula1Scraper {
    private static final Logger logger = LogManager.getLogger(Formula1Scraper.class);
    static Document doc;

    public Formula1Scraper(){
        String url = "https://www.formula1.com/en/results.html/2023/races/1226/abu-dhabi/race-result.html";
        logger.info("Start scraping from " +  url);
        try {
            doc = Jsoup.connect(url).get();
            Elements body = doc.select("tbody");
            int Driver_num = body.select("tr").size();
            logger.info("Git.pl");
            System.out.println(body.select("td").get(1).text());
            for (int i = 0; i < (9 * body.select("tr").size()); i++) {
                System.out.println(body.select("td").get(i).text());

            }

        } catch (IOException e) {
            logger.error("JBZD.pl");
        }

    }
    public static void main(String[] args) {
        Formula1Scraper test = new Formula1Scraper();
    }

}