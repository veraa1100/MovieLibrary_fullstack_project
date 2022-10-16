package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditMovie extends JPanel {
    private MovieList movielist;


    JLabel movieLabel = new JLabel();

    JLabel name = new JLabel();
    JLabel rating = new JLabel();
    JLabel review = new JLabel();
    JTextField nameInput = new JTextField();
    JTextField ratingInput = new JTextField();
    JTextField reviewInput = new JTextField();

    JButton saveButton = new JButton();
    JButton searchButton = new JButton();


    public EditMovie(MovieList ml) {
        initComponents();
        this.movielist = ml;


    }

    private void initComponents() {


        movieLabel.setFont(new Font("Lucida", 0, 25));
        movieLabel.setText("Edit Movie");
        name.setText("Search for Movie name");
        rating.setText("New Rating");
        rating.setVisible(false);
        ratingInput.setVisible(false);
        review.setText("New Review");
        review.setVisible(false);
        reviewInput.setVisible(false);
        saveButton.setVisible(false);
        initButtons();
    }

    private void initButtons() {
        searchButton.setText("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        saveButton.setText("Save");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveActionPerformed(evt);
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
                                        .addComponent(searchButton)
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
                                .addComponent(searchButton)
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


    private void searchActionPerformed(ActionEvent evt) {
        String n = nameInput.getText();
        Movie movieToEdit = movielist.findMovie(n);
        if (movieToEdit == null) {
            JOptionPane.showMessageDialog(null, "Couldn't find this movie :(");
        } else {
            nameInput.setVisible(false);
            name.setText(nameInput.getText());
            rating.setVisible(true);
            ratingInput.setVisible(true);
            review.setVisible(true);
            reviewInput.setVisible(true);
            searchButton.setVisible(false);
            saveButton.setVisible(true);

        }
    }

    private void saveActionPerformed(ActionEvent evt) {
        String n = nameInput.getText();
        Movie movieToEdit = movielist.findMovie(n);
        int i = Integer.parseInt(ratingInput.getText());
        movieToEdit.editMovie(i,reviewInput.getText());
        JOptionPane.showMessageDialog(null, "Successfully updated");
    }



}

