package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.*;

public class GUI {
    private static BufferedImage image;
    private static final Logger logger = LogManager.getLogger(RacesScraper.class);
    public GUI() {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("FormulaFanatic");
            frame.setSize(1200, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            JPanel mainPanel = new JPanel(new BorderLayout());
            JPanel photoPanel = new JPanel(new FlowLayout());

            displayImage(photoPanel, "fota.jpg");

            int horizontalGap = 100;
            int verticalGap = 5;

            Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 18);
            Font title = new Font("Arial", Font.BOLD | Font.ITALIC, 26);
            JButton back = new JButton("Menu Główne");
            JButton back2 = new JButton("Menu Główne");
            JButton back3 = new JButton("Menu Główne");
            JButton back4 = new JButton("powrut do kierowcow :)");
            back.setFont(font);
            back.setBackground(Color.LIGHT_GRAY);
            back2.setBackground(Color.LIGHT_GRAY);




            // drivers panels
            JPanel driverMainPanel = new JPanel();
            driverMainPanel.setBackground(Color.BLACK);
            JPanel driverPanel = new JPanel(new BorderLayout());

            driverMainPanel.setLayout(new GridLayout(5, 4, 5, 5));
            driverPanel.setBackground(Color.BLACK);
            driverPanel.add(back, BorderLayout.NORTH);
            driverPanel.add(driverMainPanel, BorderLayout.CENTER);

            JPanel driverChose = new JPanel();
            JPanel driverPhoto = new JPanel();
            JPanel driverArry = new JPanel();
            driverChose.add(driverPhoto, BorderLayout.WEST);
            driverChose.add(driverArry, BorderLayout.EAST);


            //team panel
            JPanel teamPanel = new JPanel();
            teamPanel.add(new JLabel("Random team info"));
            teamPanel.add(back2, BorderLayout.CENTER);
            teamPanel.setBackground(Color.GRAY);
           // displayImage(teamPanel, "pobranyplik.jpg");
            teamsInfo(teamPanel);
            // Input panel for entering the year

            JPanel inputPanel = new JPanel();
            JTextField yearField = new JTextField(4); // Adjust the size as needed
            JButton yearSubmitButton = new JButton("Submit Year");




            inputPanel.add(new JLabel("Enter Year: "));
            inputPanel.add(yearField);
            inputPanel.add(yearSubmitButton);
            inputPanel.setBackground(Color.GRAY);
            String year;
            year = yearField.getText().toString();

            JPanel racesPanel = new JPanel();
            JPanel racesPanel1 = new JPanel();
            JPanel racesPanel2 = new JPanel();
            BoxLayout racesLayout = new BoxLayout(racesPanel,BoxLayout.Y_AXIS);

            racesPanel.setLayout(racesLayout);
            racesPanel1.setLayout(new GridLayout(2,1));
            JLabel text = new JLabel(year + " RACE RESULTS");
            text.setFont(title);
            racesPanel2.setBackground(Color.RED);
            racesPanel1.add(inputPanel, BorderLayout.NORTH);
            racesPanel1.add(text,BorderLayout.CENTER);
            racesPanel.add(racesPanel1);
            racesPanel.add(racesPanel2);
            races(year,racesPanel2,back3);

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);
            JPanel driverDetailsPanel = new JPanel(new CardLayout());

            GridLayout gridLayout = new GridLayout(2,1);
            driverDetailsPanel.setLayout(gridLayout);
            driverDetailsPanel.add(back4);

            cardPanel.add(photoPanel, "photo");
            cardPanel.add(driverPanel, "driver");
            cardPanel.add(teamPanel, "teams");
            cardPanel.add(racesPanel,"races");
            // Create a new panel for driver details using CardLayout
            cardPanel.add(driverDetailsPanel, "driverDetails");


            driver(driverMainPanel, cardLayout, cardPanel, driverDetailsPanel);

            Font buttonFont = new Font("Arial", Font.BOLD , 18);
            Dimension rozmiar = new Dimension(240,65);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setPreferredSize(new Dimension(1200,100));
            JButton kierowcy = new JButton("Drivers 2023");
            JButton druzyny = new JButton("Teams 2023");
            JButton tabela = new JButton("Season Race Results");
            kierowcy.setFont(buttonFont);
            druzyny.setFont(buttonFont);
            tabela.setFont(buttonFont);
            kierowcy.setPreferredSize(rozmiar);
            druzyny.setPreferredSize(rozmiar);
            tabela.setPreferredSize(rozmiar);

            buttonPanel.setBackground(Color.BLACK);

            mainPanel.add(cardPanel, BorderLayout.CENTER);
            photoPanel.add(buttonPanel, BorderLayout.SOUTH);

            buttonPanel.add(kierowcy);
            buttonPanel.add(druzyny);
            buttonPanel.add(tabela);

            frame.setResizable(false);
            frame.add(mainPanel);
            frame.setVisible(true);


            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, horizontalGap, verticalGap));

            yearSubmitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                }
            });

            tabela.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "races");
                }
            });
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "photo");
                }
            });

            back2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "photo");
                }
            });
            back3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "photo");
                }
            });
            back4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "driver");
                }
            });

            kierowcy.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "driver");
                }
            });

            druzyny.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "teams");
                }
            });

        });

    }

    private static void displayImage(JPanel panel, String imageName) {
        try {

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(imageName);

            if (inputStream != null) {
                BufferedImage image = ImageIO.read(inputStream);
                ImageIcon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                panel.add(label, BorderLayout.NORTH);
            } else {
                logger.error("Nie udało się wczytać obrazu: " + imageName);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void driver(JPanel panel, CardLayout cardLayout, JPanel cardPanel, JPanel driverPanel) {
        String[] lista = {"max-verstappen", "sergio-perez", "charles-leclerc", "carlos-sainz", "george-russell", "lewis-hamilton", "esteban-ocon", "pierre-gasly", "lando-norris", "oscar-piastri",
                "valtteri-bottas", "guanyu-zhou", "fernando-alonso", "lance-stroll", "kevin-magnussen", "nico-hulkenberg", "alexander-albon", "logan-sargeant", "yuki-tsunoda", "daniel-ricciardo"};

        for (int i = 0; i < 20; i++) {

            JButton driverButton = new JButton();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String imageName = lista[i] + ".png";
            InputStream inputStream = classLoader.getResourceAsStream(imageName);

            if (inputStream != null) {
                try {
                    BufferedImage originalImage = ImageIO.read(inputStream);
                    int buttonWidth = 150; // Zmiana szerokości przycisku
                    int buttonHeight = 150; // Zmiana wysokości przycisku

                    Image scaledImage = originalImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(scaledImage);
                    driverButton.setIcon(icon);
                    driverButton.setBackground(Color.DARK_GRAY);
                    panel.add(driverButton);
                    // przyciski kierowcow /\

                    String currentDriver = lista[i]; // capture current driverName in final variable
                    driverButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {


                            // Switch to the new driver details panel
                            cardLayout.show(cardPanel, "driverDetails");
                            driverInfo(currentDriver, driverPanel, cardPanel, cardLayout);
                        }
                    });

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                logger.error("Błąd przy wczytywaniu obrazu: " + imageName);
            }
        }
    }

    private static void teamsInfo(JPanel panel) {
        TeamScraper t_scraper = new TeamScraper();
        t_scraper.getData("Mercedes");
        JLabel text0 = new JLabel("Name ");
        JLabel text1 = new JLabel("Base");
        JLabel text2 = new JLabel("team_chief ");
        JLabel text3 = new JLabel("tech_chief ");
        JLabel text4 = new JLabel("chassis ");
        JLabel text5 = new JLabel("power_unit ");
        JLabel text6 = new JLabel("first_entry ");
        JLabel text7 = new JLabel("world_champ");
        JLabel text8 = new JLabel("highest_finish ");
        JLabel text9 = new JLabel("pole_pos ");
        JLabel text10 = new JLabel("fastest_lap ");

        JLabel fullName = new JLabel(t_scraper.teamData.get("full_name"));
        JLabel drivers = new JLabel("place for drivers");
        JLabel base = new JLabel(t_scraper.teamData.get("base"));
        JLabel teamChief = new JLabel(t_scraper.teamData.get("team_chief"));
        JLabel techChief = new JLabel(t_scraper.teamData.get("tech_chief"));
        JLabel chassis = new JLabel(t_scraper.teamData.get("chassis"));
        JLabel powerUnit = new JLabel(t_scraper.teamData.get("power_unit"));
        JLabel firstEntry = new JLabel(t_scraper.teamData.get("first_entry"));
        JLabel worldChamp = new JLabel(t_scraper.teamData.get("world_champ"));
        JLabel highFin = new JLabel(t_scraper.teamData.get("highest_finish"));
        JLabel polePos = new JLabel(t_scraper.teamData.get("pole_pos"));
        JLabel fastLap = new JLabel(t_scraper.teamData.get("fastest_lap"));
        JPanel team = new JPanel();
        team.setLayout(new GridLayout(12,2));
        panel.setLayout(new FlowLayout());
        panel.add(drivers);

        team.add(text0);
        team.add(fullName);
        team.add(text1);
        team.add(base);
        team.add(text2);
        team.add(teamChief);
        team.add(text3);
        team.add(techChief);
        team.add(text4);
        team.add(chassis);
        team.add(text5);
        team.add(powerUnit);
        team.add(text6);
        team.add(firstEntry);
        team.add(text7);
        team.add(worldChamp);
        team.add(text8);
        team.add(highFin);
        team.add(text9);
        team.add(polePos);
        team.add(text10);
        team.add(fastLap);
        panel.add(team);


    }


    private static void driverInfo(String driver, JPanel driverPanel, JPanel cardPanel, CardLayout cardLayout) {
        // Sprawdzamy, czy panel już istnieje w rodzicielskim panelu
        boolean panelExists = false;
        Component[] components = cardPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel existingPanel = (JPanel) component;
                if (Objects.equals(existingPanel.getName(), driver)) {
                    panelExists = true;
                    break;
                }
            }
        }
            if (!panelExists) {
                JButton backButton = new JButton("Powrót do listy kierowców");
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(backButton);


                // Jeśli panel nie istnieje, tworzymy nowy
                JPanel driverDetailsPanel = new JPanel();
                driverDetailsPanel.add(buttonPanel,BorderLayout.NORTH);
                JPanel driverText = new JPanel();
                driverText.setLayout(new GridLayout(12,2));
                String photoName = driver + ".png";
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new GridLayout(1,2));
                infoPanel.setPreferredSize(new Dimension(500,500));
                displayImage(infoPanel,photoName);
                infoPanel.add(driverText,BorderLayout.SOUTH);

                driverDetailsPanel.add(infoPanel);
                driverDetailsPanel.setName(driver);

                // Ustawienie nazwy kierowcy jako identyfikatora panelu
                driverDetailsPanel.setLayout(new BoxLayout(driverDetailsPanel,BoxLayout.Y_AXIS));



                backButton.setSize(50,100);
                Font fontLabel = new Font("Arial", Font.BOLD, 18);
                Font fontInfo = new Font("Arial",Font.PLAIN,18);

                String[] nameParts = driver.split("-");
                // Przekształcenie pierwszych liter każdego słowa na wielkie
                StringBuilder formattedName = new StringBuilder();
                for (String part : nameParts) {
                    formattedName.append(Character.toUpperCase(part.charAt(0)))
                            .append(part.substring(1)).append(" ");
                }
                formattedName.deleteCharAt(formattedName.length() - 1);


                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel, "driver"); // Przełączanie do panelu z listą kierowców
                    }
                });

                DriverScraper d_scraper = new DriverScraper();
                d_scraper.getData(driver);

              //  driverDetailsPanel.add(image);
                JPanel puste = new JPanel();
                JPanel puste2 = new JPanel();
                JLabel text0 = new JLabel("Name ");
                JLabel text1 = new JLabel("Team ");
                JLabel text2 = new JLabel("Country ");
                JLabel text3 = new JLabel("Podiums ");
                JLabel text4 = new JLabel("Points ");
                JLabel text5 = new JLabel("Grand Prix entered ");
                JLabel text6 = new JLabel("World Championships ");
                JLabel text7 = new JLabel("Highest race finish");
                JLabel text8 = new JLabel("Highest grid position ");
                JLabel text9 = new JLabel("Date of birth ");
                JLabel text10 = new JLabel("Place of birth ");
                text0.setFont(fontLabel);
                text1.setFont(fontLabel);
                text2.setFont(fontLabel);
                text3.setFont(fontLabel);
                text4.setFont(fontLabel);
                text5.setFont(fontLabel);
                text6.setFont(fontLabel);
                text7.setFont(fontLabel);
                text8.setFont(fontLabel);
                text9.setFont(fontLabel);
                text10.setFont(fontLabel);
                JLabel name = new JLabel(formattedName.toString());
                JLabel team = new JLabel(d_scraper.driverData.get("team"));
                JLabel country = new JLabel(d_scraper.driverData.get("country"));
                JLabel podiums = new JLabel(d_scraper.driverData.get("podiums"));
                JLabel points = new JLabel(d_scraper.driverData.get("points"));
                JLabel grand_prix_entered = new JLabel(d_scraper.driverData.get("grand_prix_entered"));
                JLabel world_champ = new JLabel(d_scraper.driverData.get("world_champ"));
                JLabel highest_finish = new JLabel(d_scraper.driverData.get("highest_finish"));
                JLabel highest_position = new JLabel(d_scraper.driverData.get("highest_position"));
                JLabel birth_date = new JLabel(d_scraper.driverData.get("birth_date"));
                JLabel birth_place = new JLabel(d_scraper.driverData.get("birth_place"));
                name.setFont(fontInfo);
                team.setFont(fontInfo);
                country.setFont(fontInfo);
                podiums.setFont(fontInfo);
                points.setFont(fontInfo);
                grand_prix_entered.setFont(fontInfo);
                world_champ.setFont(fontInfo);
                highest_finish.setFont(fontInfo);
                highest_position.setFont(fontInfo);
                birth_date.setFont(fontInfo);
                birth_place.setFont(fontInfo);
                //driverText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                driverText.add(text0);
                driverText.add(name);
                driverText.add(text1);
                driverText.add(team);
                driverText.add(text2);
                driverText.add(country);
                driverText.add(text3);
                driverText.add(podiums);
                driverText.add(text4);
                driverText.add(points);
                driverText.add(text5);
                driverText.add(grand_prix_entered);
                driverText.add(text6);
                driverText.add(world_champ);
                driverText.add(text7);
                driverText.add(highest_finish);
                driverText.add(text8);
                driverText.add(highest_position);
                driverText.add(text9);
                driverText.add(birth_date);
                driverText.add(text10);
                driverText.add(birth_place);
                driverText.add(puste);
                driverText.add(puste2);



                // Dodajemy driverDetailsPanel do rodzicielskiego panelu
                cardPanel.add(driverDetailsPanel, driver);

                // Przełączamy układ kart na nowo dodany panel
            }
                cardLayout.show(cardPanel, driver);



    }

    public static void races(String year, JPanel panel, JButton button) {
        List<Race> races = RacesScraper.getData(year);

        JPanel upperPanel = new JPanel();
        /*
        upperPanel.setLayout(new GridLayout(1, 7));
        Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 16);
        JLabel text0 = new JLabel("Grand Prix");
        JLabel text1 = new JLabel("Date");
        JLabel text2 = new JLabel("Winner");
        JLabel text3 = new JLabel("Car");
        JLabel text7 = new JLabel("  ");
        JLabel text4 = new JLabel("Laps");
        JLabel text5 = new JLabel("Time");
        text0.setFont(font);
        text1.setFont(font);
        text2.setFont(font);
        text3.setFont(font);
        text4.setFont(font);
        text5.setFont(font);

        upperPanel.setBackground(Color.BLUE);
        upperPanel.add(text0);
        upperPanel.add(text1);
        upperPanel.add(text2);
        upperPanel.add(text3);
        upperPanel.add(text7);
        upperPanel.add(text4);
        upperPanel.add(text5);
        */

        String[][] data = new String[races.size()][6];
        for (int i = 0; i < races.size(); i++) {
            String country = races.get(i).country();
            String date = races.get(i).date();
            String driver = races.get(i).driver().substring(0, races.get(i).driver().lastIndexOf(" "));
            String team = races.get(i).team();
            String laps = races.get(i).laps();
            String time = races.get(i).time();
            data[i][0] = country;
            data[i][1] = date;
            data[i][2] = driver;
            data[i][3] = team;
            data[i][4] = laps;
            data[i][5] = time;
        }

        String[] colNames = {"Grand Prix", "Date", "Winner", "Car", "Laps", "Time"};

        JTable table = new JTable(data, colNames);
        table.setPreferredSize(new Dimension(780, 400));
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(780, 400)); // Set the preferred size of the scroll pane
        upperPanel.add(button,BorderLayout.CENTER);
        panel.setLayout(new BorderLayout()); // Use BorderLayout for the panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(upperPanel, BorderLayout.SOUTH);
        panel.setBackground(Color.BLACK);

    }
        public static void main (String[]args){
            GUI gui = new GUI();


        }
    }

