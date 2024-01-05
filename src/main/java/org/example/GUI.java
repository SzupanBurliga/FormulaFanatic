package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
            displayImage(photoPanel, "C:\\Users\\szbur\\IdeaProjects\\Formula1\\src\\main\\java\\org\\example\\fota.jpg");

            int horizontalGap = 100;
            int verticalGap = 5;
            Font font = new Font("ComicSans", Font.BOLD | Font.ITALIC, 16);
            JButton back = new JButton("Powrut");
            JButton back2 = new JButton("Powrut");
            back.setFont(font);
            back.setBackground(Color.RED);
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

    private static void displayImage(JPanel panel, String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            panel.add(label);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void driver(JPanel panel) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("makswersztapen.png")));
        for (int i = 0; i < 20; i++) {
            JButton chuje = new JButton();
            chuje.setIcon(icon);
            chuje.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
            panel.add(chuje);
        }
        // tutaj scraper do kierowców ^ (do buttonów ofc)
        // sens działania wiadomo ocb

    }
    private void teams(JPanel panel){

    }

    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}
