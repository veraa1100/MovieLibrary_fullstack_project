package ui;

import model.Movie;
import model.MovieList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewMovies extends JPanel {
    private MovieList movielist;
    JLabel viewLBL = new JLabel();
    JLabel movieText = new JLabel();
    JButton refresh = new JButton();


    public ViewMovies(MovieList movielist) {
        init();
        this.movielist = movielist;
    }

    private void init() {
        viewLBL.setFont(new Font("Lucida", 0, 25));
        viewLBL.setText("Movies you watched: ");
        movieText.setText("");
        refresh.setText("Refresh");

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                refreshing(evt);
            }
        });


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        horzLayout(layout);
        vertLayout(layout);
    }


    private void horzLayout(GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(viewLBL)
                                        .addComponent(movieText)
                                        .addComponent(refresh))
                        ));
    }

    private void vertLayout(GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addContainerGap()
                                .addGap(30, 30, 30)
                                .addComponent(viewLBL)
                                .addGap(18, 18, 18)
                                .addComponent(movieText)
                                .addGap(18, 18, 18)
                                .addComponent(refresh)));
    }

    private void refreshing(ActionEvent evt) {
        String print = movielist.viewMovie();
        movieText.setText(print);

    }


}
