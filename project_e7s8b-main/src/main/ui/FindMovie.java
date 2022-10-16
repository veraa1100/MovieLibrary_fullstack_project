package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindMovie extends JPanel {
    private MovieList movielist;
    JLabel findLabel = new JLabel();
    JLabel foundMovie = new JLabel();

    JLabel name = new JLabel();
    JTextField nameInput = new JTextField();

    JButton searchButton = new JButton();

    public FindMovie(MovieList ml) {
        initComponents();
        this.movielist = ml;

    }

    private void initComponents() {


        findLabel.setFont(new Font("Lucida", 0, 25));
        findLabel.setText("Find a Movie!");
        foundMovie.setText("");
        name.setText("Search for movie name: ");
        nameInput.setSize(80,30);
        searchButton.setText("Search");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        horzLayout(layout);
        vertLayout(layout);
    }

    // Sets layout
    // MODIFIES: this
    // EFFECTS: Sets layout for all labels, panels, and buttons
    private void horzLayout(GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(findLabel)
                                        .addComponent(name))
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(
                                                GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(nameInput, GroupLayout.DEFAULT_SIZE, 126,
                                                Short.MAX_VALUE)
                                        .addComponent(searchButton)
                                        .addGap(50, 50, 50)
                                        .addComponent(foundMovie))

                        ));
    }

    // Sets vertical layout
    // MODIFIES: this
    // EFFECTS: Sets vertical layout for all labels, panels, and buttons
    private void vertLayout(GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addContainerGap().addGap(30, 30, 30)
                                .addComponent(findLabel).addGroup(layout.createParallelGroup(
                                                GroupLayout.Alignment.TRAILING)
                                        .addComponent(nameInput, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(name))
                                .addComponent(searchButton)
                                .addGap(50, 50, 50)
                                .addComponent(foundMovie)));
    }


    private void searchActionPerformed(ActionEvent evt) {
        String n = nameInput.getText();
        Movie movie1 = movielist.findMovie(n);
        if (movie1 == null) {
            JOptionPane.showMessageDialog(null, "Couldn't find this movie :(");
        } else {
            foundMovie.setText(movie1.getName() + " " + movie1.getRating() + " " + "[" + movie1.getReview() + "]");

        }
    }


}
