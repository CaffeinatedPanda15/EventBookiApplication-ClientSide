package za.ac.cput;

import javax.swing.JFrame;

import za.ac.cput.views.HomePage;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Homepage");
        HomePage homePage = new HomePage();
        homePage.setGUI();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.add(homePage);
        frame.setVisible(true);
    }
}