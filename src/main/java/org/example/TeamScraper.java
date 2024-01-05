package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.*;
public class TeamScraper {
    private static final Logger logger = LogManager.getLogger(DriverScraper.class);
    static Document doc;
    static Map<String, String> teamData = new HashMap<>();
    public static void getData(String team) {
        String url = "https://www.formula1.com/en/teams/"+team+".html";
        String[] atributes={"full_name", "base", "team_chief", "tech_chief", "chassis", "power_unit",
                "first_entry", "world_champ", "highest_finish", "pole_pos", "fastest_lap"};
        try {
            doc = Jsoup.connect(url).get();
            Elements body = doc.select("tbody");
            int Driver_num = body.select("tr").size();
            logger.info("OK");
            for (int i = 0; i < 11; i++) {
                teamData.put(atributes[i],body.select("td").get(i).text());
                //System.out.println(body.select("td").get(i).text());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            logger.error("nie pobrano informacji o kierowcy");
        }
    }

    public static void main(String[] args) {
        getData("McLaren");

        System.out.println(teamData.get("full_name"));
        System.out.println(teamData.get("chassis"));
    }
}