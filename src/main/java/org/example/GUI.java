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

public class GUI {
    private static BufferedImage image;

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


            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);
            JPanel driverDetailsPanel = new JPanel(new CardLayout());

            JLabel test = new JLabel("dupa");
            GridLayout gridLayout = new GridLayout(2,1);
            driverDetailsPanel.setLayout(gridLayout);
            driverDetailsPanel.add(back3);




            cardPanel.add(photoPanel, "photo");
            cardPanel.add(driverPanel, "driver");
            cardPanel.add(teamPanel, "teams");
            // Create a new panel for driver details using CardLayout
            cardPanel.add(driverDetailsPanel, "driverDetails");


            driver(driverMainPanel, cardLayout, cardPanel, driverDetailsPanel);

            JPanel buttonPanel = new JPanel();
            JButton kierowcy = new JButton("Kierowcy");
            JButton druzyny = new JButton(("Drużyny"));
            buttonPanel.setBackground(Color.BLACK);

            mainPanel.add(cardPanel, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            buttonPanel.add(kierowcy);
            buttonPanel.add(druzyny);

            frame.setResizable(false);
            frame.add(mainPanel);
            frame.setVisible(true);


            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, horizontalGap, verticalGap));

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
                image = ImageIO.read(inputStream);
                ImageIcon icon = new ImageIcon(image);
                JLabel label = new JLabel(icon);
                panel.add(label);
            } else {
                System.err.println("Nie udało się wczytać obrazu: " + imageName);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void driver(JPanel panel, CardLayout cardLayout, JPanel cardPanel, JPanel driverPanel) {
        String[] lista = {"1max", "2perez", "3leclerc", "4sainz", "5russel", "6hamilton", "7ocon", "8gasly", "9norris", "10piastri",
                "11bottas", "12zhou", "13alonso", "14stroll", "15magnussen", "16hulkenberg", "17albon", "18sargeant",
                "19tsunoda", "20ricciardo"};

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
                System.err.println("Błąd przy wczytywaniu obrazu: " + imageName);
            }
        }
    }

    private static void teams() {

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
                // Jeśli panel nie istnieje, tworzymy nowy
                JPanel driverDetailsPanel = new JPanel();
                driverDetailsPanel.setName(driver); // Ustawienie nazwy kierowcy jako identyfikatora panelu
                driverDetailsPanel.setLayout(new GridLayout(2, 1));

                // Możesz dodać komponenty do driverDetailsPanel zgodnie z Twoimi potrzebami
                JButton backButton = new JButton("Powrót do listy kierowców");
                backButton.setSize(50,100);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(cardPanel, "driver"); // Przełączanie do panelu z listą kierowców
                    }
                });
                DriverScraper scraper = new DriverScraper();
                scraper.getData(driver);
                JLabel label = new JLabel(scraper.driverData.get("birth_date"));
                JLabel label2 = new JLabel(scraper.driverData.get("team"));
                driverDetailsPanel.add(backButton);
                driverDetailsPanel.add(label);
                driverDetailsPanel.add(label2);
                // Dodajemy driverDetailsPanel do rodzicielskiego panelu
                cardPanel.add(driverDetailsPanel, driver);

                // Przełączamy układ kart na nowo dodany panel
            }
                cardLayout.show(cardPanel, driver);



    }


        public static void main (String[]args){
            GUI gui = new GUI();
            teams();
            DriverScraper hamilton = new DriverScraper();
            hamilton.getData("lewis-hamilton");
            System.out.println(hamilton.driverData.get("birth_date"));

        }
    }

