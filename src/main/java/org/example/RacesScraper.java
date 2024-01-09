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
    public static List<Race> getData(String year) {
        List<Race> races = new ArrayList();
        String url = "https://www.formula1.com/en/results.html/"+year+"/races.html";
        logger.info("Scrapowanie informacji z: "+url);
        try {
            doc = Jsoup.connect(url).get();
            Elements body = doc.select("tbody");

            logger.info("OK");

            for (int i = 0; i < body.select("tr").size(); i++) {
                //System.out.println(body.select("td").get(i).text());
                //raceData.add(body.select("tr").get(i).text());
                //System.out.println(raceData.get(i));
                //System.out.println(body.select("tr").get(i).text());
                //
                // System.out.println(body.select("tr").get(i).text());
                Elements td = body.select("tr").get(i).select("td");
                String country = td.get(1).text();
                String date = td.get(2).text();
                String winner = td.get(3).text();
                String team = td.get(4).text();
                String laps = td.get(5).text();
                String time = td.get(6).text();

                races.add(new Race(country, date, winner, team, laps, time));
            }


        } catch (IOException e) {
            logger.error("Błąd Scrapera, nie pobrano informacji z: "+ url);
        }

    }

    public static void main(String[] args) {
        List<Race> races = getData("2023");
        System.out.println(races.get(0).country());

    }

}