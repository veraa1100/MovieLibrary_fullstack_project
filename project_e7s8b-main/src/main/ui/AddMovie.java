package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMovie extends JPanel {
    private MovieList movielist;


    JLabel movieLabel = new JLabel();

    JLabel name = new JLabel();
    JLabel rating = new JLabel();
    JLabel review = new JLabel();
    JTextField nameInput = new JTextField();
    JTextField ratingInput = new JTextField();
    JTextField reviewInput = new JTextField();

    JButton saveButton = new JButton();


    public AddMovie(MovieList ml) {
        initComponents();
        this.movielist = ml;


    }

    private void initComponents() {


        movieLabel.setFont(new Font("Lucida", 0, 25));
        movieLabel.setText("Add a new Movie!");
        name.setText("Name");
        rating.setText("Rating");
        review.setText("Review");
        saveButton.setText("Add");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addActionPerformed(evt);
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
                                        .addComponent(movieLabel)
                                        .addComponent(name).addComponent(rating).addComponent(review))
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(
                                                GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(nameInput, GroupLayout.DEFAULT_SIZE, 126,
                                                Short.MAX_VALUE)
                                        .addComponent(ratingInput, GroupLayout.Alignment.LEADING)
                                        .addComponent(reviewInput, GroupLayout.Alignment.LEADING)

                                        .addComponent(saveButton))

                        ));
    }

    // Sets vertical layout
    // MODIFIES: this
    // EFFECTS: Sets vertical layout for all labels, panels, and buttons
    private void vertLayout(GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addContainerGap().addGap(30, 30, 30)
                                .addComponent(movieLabel).addGroup(layout.createParallelGroup(
                                                GroupLayout.Alignment.TRAILING)
                                        .addComponent(nameInput, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(name))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(rating)
                                        .addComponent(ratingInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(rating)

                                        .addComponent(reviewInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE).addComponent(review))

                                .addComponent(saveButton)));
    }

    // EFFECT: adding movie to the list
    private void addActionPerformed(ActionEvent evt) {
        try {
            String checkname = nameInput.getText();
            Movie checkmovie = movielist.findMovie(checkname);
            if (checkmovie == null) {
                int i = Integer.parseInt(ratingInput.getText());
                Movie m = new Movie(nameInput.getText(), i, reviewInput.getText());
                movielist.addMovie(m);
                JOptionPane.showMessageDialog(null, "Movie Added!");
            } else {
                JOptionPane.showMessageDialog(null, "This Movie has be already added!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number for ratings!");
        }
    }


}



