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
            frame.setSize(1000, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            JPanel mainPanel = new JPanel(new BorderLayout());
            JPanel photoPanel = new JPanel(new FlowLayout());

            displayImage(photoPanel, "fota.jpg");

            int horizontalGap = 100;
            int verticalGap = 5;
            Font font = new Font("ComicSans", Font.BOLD | Font.ITALIC, 18);
            JButton back = new JButton("Menu Główne");
            JButton back2 = new JButton("Menu Główne");
            back.setFont(font);
            back.setBackground(Color.LIGHT_GRAY);
            back2.setBackground(Color.RED);

            JPanel driverMainPanel = new JPanel();
            JPanel driverPanel = new JPanel(new BorderLayout());
            driverMainPanel.setLayout(new GridLayout(5, 4));
            driverPanel.setBackground(Color.BLACK);
            driverPanel.add(back, BorderLayout.NORTH);
            driverPanel.add(driverMainPanel, BorderLayout.CENTER);

            driver(driverMainPanel);

            JPanel teamPanel = new JPanel();
            teamPanel.add(new JLabel("Team info :))"));
            teamPanel.add(back2, BorderLayout.CENTER);
            teamPanel.setBackground(Color.PINK);
            displayImage(teamPanel, "pobranyplik.jpg");

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);
            cardPanel.add(photoPanel, "photo");
            cardPanel.add(driverPanel, "driver");
            cardPanel.add(teamPanel, "teams");

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


    private void driver(JPanel panel) {
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

                    // Dostosuj rozmiar obrazu do wymiarów przycisku
                    int buttonWidth = 150; // Zmiana szerokości przycisku
                    int buttonHeight = 150; // Zmiana wysokości przycisku

                    Image scaledImage = originalImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(scaledImage);
                    driverButton.setIcon(icon);
                    driverButton.setBackground(Color.DARK_GRAY);
                    panel.add(driverButton);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.err.println("Błąd przy wczytywaniu obrazu: " + imageName);
            }
        }
    }
    private void teams(JPanel panel){

    }

    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}
