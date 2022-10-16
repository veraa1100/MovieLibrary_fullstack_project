package ui;

import model.MovieList;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuPanel inst = null;
                try {
                    inst = new MenuPanel();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }
}
