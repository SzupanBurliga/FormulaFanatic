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
            JButton back = new JButton("Menu Główne");
            JButton back2 = new JButton("Menu Główne");
            JButton back3 = new JButton("powrut do kierowcow :)");
            back.setFont(font);
            back.setBackground(Color.LIGHT_GRAY);
            back2.setBackground(Color.RED);
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
            teamPanel.add(new JLabel("Team info :))"));
            teamPanel.add(back2, BorderLayout.CENTER);
            teamPanel.setBackground(Color.PINK);
            displayImage(teamPanel, "pobranyplik.jpg");

            JPanel racesPanel = new JPanel();
            JPanel racesPanel1 = new JPanel();
            JPanel racesPanel2 = new JPanel();
            BoxLayout racesLayout = new BoxLayout(racesPanel2,BoxLayout.Y_AXIS);
            racesPanel2.setLayout(racesLayout);


            JLabel text = new JLabel(" RACE RESULTS");

            racesPanel1.add(text,BorderLayout.CENTER);
            racesPanel.add(racesPanel1);
            racesPanel.add(racesPanel2);

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);
            JPanel driverDetailsPanel = new JPanel(new CardLayout());

            GridLayout gridLayout = new GridLayout(2,1);
            driverDetailsPanel.setLayout(gridLayout);
            driverDetailsPanel.add(back3);

            cardPanel.add(photoPanel, "photo");
            cardPanel.add(driverPanel, "driver");
            cardPanel.add(teamPanel, "teams");
            cardPanel.add(racesPanel,"races");
            // Create a new panel for driver details using CardLayout
            cardPanel.add(driverDetailsPanel, "driverDetails");


            driver(driverMainPanel, cardLayout, cardPanel, driverDetailsPanel);

            JPanel buttonPanel = new JPanel();
            JButton kierowcy = new JButton("Kierowcy");
            JButton druzyny = new JButton("Drużyny");
            JButton tabela = new JButton("Tabela");
            buttonPanel.setBackground(Color.BLACK);

            mainPanel.add(cardPanel, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            buttonPanel.add(kierowcy);
            buttonPanel.add(druzyny);
            buttonPanel.add(tabela);

            frame.setResizable(false);
            frame.add(mainPanel);
            frame.setVisible(true);


            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, horizontalGap, verticalGap));

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

    private static void teamsInfo() {

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
                displayImage(infoPanel,photoName);
                infoPanel.add(driverText,BorderLayout.SOUTH);
                driverDetailsPanel.add(infoPanel);
                driverDetailsPanel.setName(driver);

                // Ustawienie nazwy kierowcy jako identyfikatora panelu
                driverDetailsPanel.setLayout(new GridLayout(2, 1));



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
                text1.setFont(fontLabel);text2.setFont(fontLabel);text3.setFont(fontLabel);text4.setFont(fontLabel);text5.setFont(fontLabel);
                text6.setFont(fontLabel);text7.setFont(fontLabel);text8.setFont(fontLabel);text9.setFont(fontLabel);text10.setFont(fontLabel);
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
                team.setFont(fontInfo);country.setFont(fontInfo);podiums.setFont(fontInfo);points.setFont(fontInfo);grand_prix_entered.setFont(fontInfo);
                world_champ.setFont(fontInfo);highest_finish.setFont(fontInfo);highest_position.setFont(fontInfo);birth_date.setFont(fontInfo);birth_place.setFont(fontInfo);

                driverText.add(text0);driverText.add(name);
                driverText.add(text1);driverText.add(team);driverText.add(text2);driverText.add(country);driverText.add(text3);
                driverText.add(podiums);driverText.add(text4);driverText.add(points);driverText.add(text5);driverText.add(grand_prix_entered);
                driverText.add(text6);driverText.add(world_champ);driverText.add(text7);driverText.add(highest_finish);driverText.add(text8);
                driverText.add(highest_position);driverText.add(text9);driverText.add(birth_date);driverText.add(text10);driverText.add(birth_place);



                // Dodajemy driverDetailsPanel do rodzicielskiego panelu
                cardPanel.add(driverDetailsPanel, driver);

                // Przełączamy układ kart na nowo dodany panel
            }
                cardLayout.show(cardPanel, driver);



    }

    public static void races() {
        JLabel races = new JLabel();
        RacesScraper race = new RacesScraper();
        race.getData("2010");
        String[] array;


    }
        public static void main (String[]args){
            GUI gui = new GUI();
            races();

        }
    }

