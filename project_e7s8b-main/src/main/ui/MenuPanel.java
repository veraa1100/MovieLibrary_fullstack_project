package ui;

import model.Event;
import model.EventLog;
import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;


public class MenuPanel extends JFrame {
    JLabel title = new JLabel();

    private MovieList movieList = new MovieList("My Movie List");
    private JPanel panel;
    JButton add = new JButton();
    JButton edit = new JButton();
    JButton view = new JButton();
    JButton find = new JButton();
    JButton save = new JButton();
    JButton load = new JButton();
    JButton quit = new JButton();
    JPanel buttonJPanel = new JPanel();
    JPanel viewJPanel = new JPanel();
    Image bufferedImage = ImageIO.read(new File("data/movieIcon.png"));
    Image myPicture = bufferedImage.getScaledInstance(500,500,Image.SCALE_DEFAULT);
    JLabel picLable = new JLabel(new ImageIcon(myPicture));
    JSplitPane splitJPanel = new JSplitPane();
    private static final String STORE = "./data/movielist2.json";




    public MenuPanel() throws IOException {
        super();
        initGUI();
    }



    public void initGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        title.setFont(new Font("Lucida", 0, 35));
        title.setText("Movie Library");
        add.setText("Add Movie");
        edit.setText("Edit Movie");
        view.setText("View Movies");
        find.setText("Find Movie");
        save.setText("Save");
        load.setText("Load");
        quit.setText("Quit");
        GroupLayout buttonJPanelLayout = new GroupLayout(buttonJPanel);
        buttonJPanel.setLayout(buttonJPanelLayout);
        horzLayout(buttonJPanelLayout);
        vertLayout(buttonJPanelLayout);
        splitJPanel.setLeftComponent(buttonJPanel);

        makeAction();

        GroupLayout viewJPanelLayout = new GroupLayout(viewJPanel);
        viewJPanel.setLayout(viewJPanelLayout);
        layout1(viewJPanelLayout);
        splitJPanel.setRightComponent(viewJPanel);
        bruh();
    }

    // Packs panel
    // MODIFIES: this
    // EFFECTS: packs all components and adds them
    private void bruh() {
        getContentPane().add(splitJPanel, BorderLayout.CENTER);
        pack();
    }

    // Sets horizontal layout
    // MODIFIES: this
    // EFFECTS: Sets horizontal layout for all labels, panels, and buttons
    private void horzLayout(GroupLayout l) {
        l.setHorizontalGroup(
                l.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(l.createSequentialGroup()
                                .addContainerGap(50, Short.MAX_VALUE)
                                .addGroup(l.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(title)
                                        .addComponent(add)
                                        .addComponent(edit)
                                        .addComponent(view)
                                        .addComponent(find)
                                        .addComponent(save)
                                        .addComponent(load)
                                        .addComponent(quit))
                                .addContainerGap(50, Short.MAX_VALUE)));
    }

    // Sets vertical layout
    // MODIFIES: this
    // EFFECTS: Sets vertical layout for all labels, panels, and buttons
    private void vertLayout(GroupLayout l) {
        l.setVerticalGroup(
                l.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGroup(l.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(title)
                                .addGap(21, 21, 21)
                                .addComponent(add)
                                .addGap(18, 18, 18)
                                .addComponent(edit)
                                .addGap(18, 18, 18)
                                .addComponent(view)
                                .addGap(18, 18, 18)
                                .addComponent(find)
                                .addGap(18, 18, 18)
                                .addGap(18, 18, 18)
                                .addComponent(save)
                                .addGap(18, 18, 18)
                                .addComponent(load)
                                .addGap(18, 18, 18)
                                .addComponent(quit)
                                .addContainerGap(100, Short.MAX_VALUE)));
    }

    // Sets layout
    // MODIFIES: this
    // EFFECTS: Sets layout for all labels, panels, and buttons
    private void layout1(GroupLayout viewJPanelLayout) {
        viewJPanelLayout.setHorizontalGroup(
                viewJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(picLable)
                        .addGap(0, 500, Short.MAX_VALUE)
        );
        viewJPanelLayout.setVerticalGroup(
                viewJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(picLable)
                        .addGap(0, 454, Short.MAX_VALUE)
        );
    }



    // Gives button functionality
    // MODIFIES: this
    // EFFECTS: Sets buttons to methods
    private void makeAction() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                createAdd(evt);
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printLog();
                System.exit(0);

            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createView(e);
            }
        });

        makeAction2();
    }

    private void printLog() {
        for (Iterator<model.Event> it = EventLog.getInstance().iterator(); it.hasNext(); ) {
            Event event = it.next();
            System.out.println(event.getDate() + " | " + event.getDescription());
        }
    }


    // Gives button functionality
    // MODIFIES: this
    // EFFECTS: Sets buttons to methods
    private void makeAction2() {
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEdit(e);
            }
        });

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createFind(e);
            }
        });

        makeAction3();


    }

    private void makeAction3() {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saver(e);
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loader(e);
            }
        });
    }

    // Displays add clothing panel
    // MODIFIES: this
    // EFFECTS: Sets right component to add movie panel
    private void createAdd(ActionEvent evt) {
        AddMovie create = new AddMovie(movieList);
        splitJPanel.setRightComponent(create);
    }

    private void createEdit(ActionEvent evt) {
        EditMovie create = new EditMovie(movieList);
        splitJPanel.setRightComponent(create);
    }

    private void createView(ActionEvent evt) {
        ViewMovies create = new ViewMovies(movieList);
        splitJPanel.setRightComponent(create);
    }

    private void createFind(ActionEvent evt) {
        FindMovie create = new FindMovie(movieList);
        splitJPanel.setRightComponent(create);
    }

    // Saves movielist data
    // EFFECTS: Writes all content to Json object, displays message if successful or fails
    private void saver(ActionEvent evt) {
        JsonWriter wr = new JsonWriter(STORE);
        try {
            wr.open();
            wr.write(movieList);
            wr.close();
            JOptionPane.showMessageDialog(null, "Data Saved!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file " + STORE);
        }
    }

    // Loads closet data
    // EFFECTS: Reads Json object and loads it, displays message if successful or fails
    private void loader(ActionEvent evt) {
        JsonReader wr = new JsonReader(STORE);
        try {
            movieList = wr.read();
            JOptionPane.showMessageDialog(null, "Data Loaded!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read to file " + STORE);
        }
    }

}