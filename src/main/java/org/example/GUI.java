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

            JFrame frame = new JFrame("DriverData");
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

            driverMainPanel.setLayout(new GridLayout(5,4,5,5));
            driverPanel.setBackground(Color.BLACK);
            driverPanel.add(back, BorderLayout.NORTH);
            driverPanel.add(driverMainPanel, BorderLayout.CENTER);

            JPanel driverChose = new JPanel();
            JPanel driverPhoto = new JPanel();
            JPanel driverArry = new JPanel();
            driverChose.add(driverPhoto, BorderLayout.WEST);
            driverChose.add(driverArry,BorderLayout.EAST);



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
            JPanel panel1 = new JPanel();
            panel1.add(test);
            panel1.add(back3);
            driverDetailsPanel.add(panel1);

            cardPanel.add(photoPanel, "photo");
            cardPanel.add(driverPanel, "driver");
            cardPanel.add(teamPanel, "teams");
            // Create a new panel for driver details using CardLayout
            cardPanel.add(driverDetailsPanel, "driverDetails");


            driver(driverMainPanel, cardLayout, cardPanel);

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


    private void driver(JPanel panel, CardLayout cardLayout, JPanel cardPanel) {
        String[] lista = {"1max","2perez","3leclerc","4sainz","5russel","6hamilton","7ocon","8gasly","9norris","10piastri",
                "11bottas", "12zhou","13alonso","14stroll","15magnussen","16hulkenberg","17albon","18sargeant",
                "19tsunoda","20ricciardo"};

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
                    driverInfo(currentDriver);
                    driverButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Create a new panel for the specific driver
                            JPanel driverDetailsPanel = new JPanel(new BorderLayout());

                            // Switch to the new driver details panel
                            cardLayout.show(cardPanel, "driverDetails");
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
    private static void teams(){

    }

    private static void driverInfo(String driver){
        //testowo czy to ma sens
        switch (driver) {
            case "1max":
                System.out.println("test");
            case "2perez":
                System.out.println("test");
            case "3leclerc":
                System.out.println("test");
            case "4sainz":
                System.out.println("test");
            case "5russel":
                System.out.println("test");
            case "6hamilton":
                System.out.println("test");
            case "7ocon":
                System.out.println("test");
            case "8gasly":
                System.out.println("test");
            case "9norris":
                System.out.println("test");
            case "10piastri":
                System.out.println("test");
            case "11bottas":
                System.out.println("test");
            case "12zhou":
                System.out.println("test");
            case "13alonso":
                System.out.println("test");
            case "14stroll":
                System.out.println("test");
            case "15magnussen":
                System.out.println("test");
            case "16hulkenberg":
                System.out.println("test");
            case "17albon":
                System.out.println("test");
            case "18sargeant":
                System.out.println("test");
            case "19tsunoda":
                System.out.println("test");
            case "20ricciardo":
                System.out.println("test");
            default:
                System.out.println("test22");
        };
        }



    public static void main(String[] args) {
        GUI gui = new GUI();
        teams();
        DriverScraper hamilton = new DriverScraper();
        hamilton.getData("lewis-hamilton");
        System.out.println(hamilton.driverData.get("birth_date"));

    }
}
