package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.*;

import model.Character;
import persistence.JsonReader;
import persistence.JsonWriter;

// represents GUI for the Genshin application
public class Gui extends JFrame {

    JLabel loadCommand;
    Color bg;
    JPanel panelCont = new JPanel();
    JPanel startPanel = new JPanel();
    JPanel profilePanel = new JPanel();
    JPanel mainPanel = new JPanel();
    CardLayout cl = new CardLayout();
    CardLayout wcl = new CardLayout();
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private static final String JSON_STORE = "./data/lists.json";
    JTextField txtProfileInput;
    Profile profile;
    JLabel dialogue;
    JPanel workPanel;
    JButton addCharacter;
    JButton removeCharacter;
    JButton viewList;
    JButton quit;
    JTextArea output = new JTextArea();
    JComboBox<String> characters = new JComboBox<>();
    JTextField name;
    JComboBox<String> vision;
    JComboBox<String> weapon;
    JComboBox<String> level;
    JComboBox<String> lists;
    Boolean counter = false;
    Boolean counter2 = false;

    // EFFECTS: starts running the application
    public Gui() {
        setTitle("Genshin Character Tracker");
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/img.png")).getImage());
        bg = new Color(171, 167, 254);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initCardLayout();
        setVisible(true);
    }

    // MODIFIES: panelCont
    // EFFECTS: initializes the different panels the game needs (start, main, and create profile)
    private void initCardLayout() {
        panelCont.setLayout(cl);
        initStartPanel();
        dialogue = new JLabel("", SwingConstants.CENTER);
        panelCont.add(startPanel, "start");
        panelCont.add(profilePanel, "profile");
        panelCont.add(mainPanel, "main");
        cl.show(panelCont, "start");
        add(panelCont);
    }

    // MODIFIES: initCont
    // EFFECTS: initializes the start panel you see at the beginning of the game
    private void initStartPanel() {
        initPanel(startPanel);
        GridBagConstraints c = new GridBagConstraints();
        JLabel pageLabel = new JLabel("Paimon 2.0 - A tracker for your Genshin Characters", SwingConstants.CENTER);
        pageLabel.setFont(new java.awt.Font("lemon/milk", Font.PLAIN, 20));
        c.gridwidth = 3;
        startPanel.add(pageLabel, c);
        ImageIcon logo = new ImageIcon(getClass().getResource("/images/genshin.png"));
        Image scaleImage = logo.getImage().getScaledInstance(600, 200,Image.SCALE_SMOOTH);
        c.weighty = 2;
        c.gridy = 1;
        startPanel.add(new JLabel(new ImageIcon(scaleImage)), c);
        loadCommand = new JLabel("Welcome back! Would you like Paimon to restore list information "
                + "from the previous session?", SwingConstants.CENTER);
        c.gridy = 2;
        startPanel.add(loadCommand, c);
        c.gridwidth = 1;
        c.weightx = 1;
        c.gridy = 3;
        startPanel.add(new JButton(new LoadAction()), c);
        c.gridx = 2;
        c.weighty = 10;
        startPanel.add(new JButton(new CreateProfileAction()), c);
    }

    // MODIFIES: panel
    // EFFECTS: does the basic initialization tasks that every panel needs
    private void initPanel(JPanel panel) {
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setBackground(bg);
        panel.setLayout(new GridBagLayout());
        setVisible(true);
    }

    // MODIFIES: profilePanel
    // EFFECTS: initializes the profilePanel where you create a new profile
    private void initProfilePanel() {
        initPanel(profilePanel);
        GridBagConstraints c = new GridBagConstraints();
        JLabel pageLabel = new JLabel("Paimon 2.0 - A tracker for your Genshin Characters", SwingConstants.CENTER);
        pageLabel.setFont(new java.awt.Font("lemon/milk", 0, 20));
        c.gridwidth = 3;
        profilePanel.add(pageLabel, c);
        ImageIcon logo = new ImageIcon(getClass().getResource("/images/genshin.png"));
        Image scaleImage = logo.getImage().getScaledInstance(600, 200,Image.SCALE_SMOOTH);
        c.weighty = 2;
        c.gridy = 1;
        profilePanel.add(new JLabel(new ImageIcon(scaleImage)), c);
        c.gridy = 2;
        profilePanel.add(new JLabel("Great! Paimon will start a new profile for you!"), c);
        JLabel labelName = new JLabel("Name");
        c.weighty = 0;
        c.gridy = 3;
        profilePanel.add(labelName, c);
        txtProfileInput = new JTextField(20);
        c.gridy = 4;
        profilePanel.add(txtProfileInput, c);
        c.gridy = 5;
        c.weighty = 5;
        profilePanel.add(new JButton(new ParseProfileAction()), c);
    }

    // MODIFIES: mainPanel
    // EFFECTS: initializes the main panel where you select actions
    private void initMainPanel() {
        initPanel(mainPanel);
        GridBagConstraints c = new GridBagConstraints();
        JLabel pageLabel = new JLabel("Paimon 2.0 - A tracker for your Genshin Characters", SwingConstants.CENTER);
        pageLabel.setFont(new java.awt.Font("lemon/milk", Font.PLAIN, 20));
        c.gridwidth = 4;
        mainPanel.add(pageLabel, c);
        c.weighty = 2;
        c.gridy = 1;
        mainPanel.add(initWorkPanel(), c);
        c.gridwidth = 1;
        c.gridy = 2;
        c.weightx = 1;
        mainPanel.add(addCharacter, c);
        c.gridx = 1;
        mainPanel.add(removeCharacter, c);
        c.gridx = 2;
        mainPanel.add(viewList, c);
        c.gridx = 3;
        mainPanel.add(quit, c);
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 3;
        mainPanel.add(initPaimonTextPanel(), c);
    }

    // MODIFIES: this
    // EFFECTS: initializes the work panel where you perform actions
    private JPanel initWorkPanel() {
        JPanel addPanel = new JPanel();
        JPanel removePanel = new JPanel();
        JPanel viewPanel = new JPanel();
        JPanel blankPanel = new JPanel();
        initAddPanel(addPanel);
        initRemovePanel(removePanel);
        initViewPanel(viewPanel);
        addCharacter = new JButton(new AddCharacterAction());
        removeCharacter = new JButton(new RemoveCharacterAction());
        viewList = new JButton(new ViewListAction());
        quit = new JButton(new QuitAction());
        workPanel = new JPanel();
        workPanel.setLayout(wcl);
        workPanel.setPreferredSize(new Dimension(900, 350));
        workPanel.setBackground(Color.WHITE);
        workPanel.add(addPanel, "add");
        workPanel.add(removePanel, "remove");
        workPanel.add(viewPanel, "view");
        workPanel.add(blankPanel, "blank");
        wcl.show(workPanel, "blank");
        return workPanel;
    }

    // MODIFIES: p
    // EFFECTS: initializes the view panel where you view character lists
    private void initViewPanel(JPanel p) {
        p.setLayout(new BorderLayout());
        JComboBox<String> list = new JComboBox();
        list.addActionListener(new ViewComboAction(list));
        for (int i = 0; i < profile.getSize(); i++) {
            list.addItem(profile.getList(i).getName());
        }
        p.add(list, BorderLayout.PAGE_START);
        output.setPreferredSize(new Dimension(100, 200));
        p.add(output, BorderLayout.CENTER);
        setVisible(true);

    }

    // MODIFIES: p
    // EFFECTS: initializes the remove panel where you remove characters
    private void initRemovePanel(JPanel p) {
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 150));
        JComboBox<String> lists = new JComboBox();
        characters = new JComboBox();
        lists.addActionListener(new RemoveComboAction(lists));
        for (int i = 0; i < profile.getSize(); i++) {
            lists.addItem(profile.getList(i).getName());
        }
        JButton btnRemove = new JButton(new RemoveSelectedAction(lists, characters));
        p.add(new JLabel("List:"));
        p.add(lists);
        p.add(new JLabel("Character:"));
        p.add(characters);
        p.add(btnRemove);
        setVisible(true);
    }

    // MODIFIES: p
    // EFFECTS: initializes the add panel where you add new characters
    private void initAddPanel(JPanel p) {
        p.setLayout(new GridLayout(6, 2, 0, 0));
        lists = new JComboBox();
        for (int i = 0; i < profile.getSize(); i++) {
            lists.addItem(profile.getList(i).getName());
        }
        level = new JComboBox();
        for (int i = 1; i <= 90; i++) {
            level.addItem(Integer.toString(i));
        }
        JButton btnAdd = new JButton(new AddSelectedAction());
        p.add(new JLabel("List:  ", SwingConstants.RIGHT));
        p.add(lists);
        p.add(new JLabel("Name:  ", SwingConstants.RIGHT));
        p.add(name = new JTextField());
        p.add(new JLabel("Vision:  ", SwingConstants.RIGHT));
        p.add(vision = new JComboBox(Vision.values()));
        p.add(new JLabel("Weapon:  ", SwingConstants.RIGHT));
        p.add(weapon = new JComboBox(Weapon.values()));
        p.add(new JLabel("Level:  ", SwingConstants.RIGHT));
        p.add(level);
        p.add(new JLabel(""));
        p.add(btnAdd);
        setVisible(true);
    }

    // MODIFIES: paimonPanel
    // EFFECTS: initializes the text panel where paimon interacts with you
    private JPanel initPaimonTextPanel() {
        JPanel paimonPanel = new JPanel();
        paimonPanel.setBackground(Color.BLACK);
        paimonPanel.setPreferredSize(new Dimension(900, 200));
        paimonPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 10),
                BorderFactory.createLineBorder(Color.BLACK, 10)));
        paimonPanel.setLayout(new BorderLayout());
        ImageIcon logo = new ImageIcon(getClass().getResource("/images/p.png"));
        paimonPanel.add(new JLabel(logo), BorderLayout.LINE_START);
        JLabel lblName = new JLabel(profile.getName() + "'s Profile");
        lblName.setForeground(Color.WHITE);
        paimonPanel.add(lblName, BorderLayout.PAGE_START);
        dialogue.setForeground(Color.WHITE);
        paimonPanel.add(dialogue, BorderLayout.CENTER);
        return paimonPanel;
    }

    // represents loading button
    private class LoadAction extends AbstractAction {
        LoadAction() {
            super("Load");
        }


        // MODIFIES: this
        //EFFECTS: loads information from previous session from file
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                profile = jsonReader.read();
                dialogue.setText("<html> Paimon: Hi there! My name is Paimon! Welcome to your Genshin Tracker <br/>"
                        + "Woohoo, your previous work saved under "
                        + profile.getName() + " has been retrieved </html>");
            } catch (IOException e) {
                System.out.println("Arg! Paimon wasn't able to retrieve the profile! from " + JSON_STORE);
            }
            initMainPanel();
            cl.show(panelCont, "main");
        }
    }

    // represents a button for creating a profile
    private class CreateProfileAction extends AbstractAction {
        CreateProfileAction() {
            super("Create Profile");
        }

        // EFFECTS: takes user to the profile maker page
        @Override
        public void actionPerformed(ActionEvent evt) {
            initProfilePanel();
            cl.show(panelCont, "profile");
            dialogue.setText("Great! Paimon will start a new profile for you!");
        }
    }

    // represents a button for submitting new profile form
    private class ParseProfileAction extends AbstractAction {
        ParseProfileAction() {
            super("Submit");
        }

        // EFFECTS: submits information user has entered into the new profile form
        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = txtProfileInput.getText();
            profile = new Profile(name);
            CharacterList owned = new CharacterList("Owned Characters");
            profile.addList(owned);
            dialogue.setText("<html> Paimon: Hi there! My name is Paimon! Welcome to your Genshin Tracker <br/>"
                    + "Good news " + name + "! Your new profile has been created");
            initMainPanel();
            cl.show(panelCont, "main");
        }
    }

    // represents button for adding characters
    private class AddCharacterAction extends AbstractAction {
        AddCharacterAction() {
            super("Add Character");
        }

        // MODIFIES: GUI
        // EFFECTS: brings user to the add characters panel
        @Override
        public void actionPerformed(ActionEvent evt) {
            addCharacter.setEnabled(false);
            removeCharacter.setEnabled(true);
            viewList.setEnabled(true);
            wcl.show(workPanel, "add");
            dialogue.setText("Paimon: Ooh we’re adding a new character? That’s exciting!");
        }
    }

    // represents a button that submits the form for adding characters
    private class AddSelectedAction extends AbstractAction {
        AddSelectedAction() {
            super("Add");

        }

        // MODIFIES: GUI
        // EFFECTS: adds a character according to the information user submitted in the form
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                Character newCharacter = new Character(name.getText(), (Vision) vision.getSelectedItem(),
                        (Weapon) weapon.getSelectedItem(), Integer.parseInt((String) level.getSelectedItem()));
                for (int i = 0; i < profile.getSize(); i++) {
                    if (lists.getSelectedItem().equals(profile.getList(i).getName())) {
                        profile.getList(i).addCharacterToList(newCharacter);
                        dialogue.setText("Woohoo! \n" + newCharacter.getName() + " the " + newCharacter.getWeapon()
                                + " wielding level " + newCharacter.getLevel() + " " + newCharacter.getVision()
                                + " character has been added to your " + profile.getList(i).getName() + " list!");
                    }
                }
            } catch (NumberFormatException e) {
                dialogue.setText("Paimon: Arg! Paimon couldn't add that character! Please try again");
            }

        }
    }

    // represents a button for removing characters
    private class RemoveCharacterAction extends AbstractAction {
        RemoveCharacterAction() {
            super("Remove Character");
        }

        // MODIFIES: GUI
        // EFFECTS: brings user to the remove characters panel
        @Override
        public void actionPerformed(ActionEvent evt) {
            addCharacter.setEnabled(true);
            removeCharacter.setEnabled(false);
            viewList.setEnabled(true);
            wcl.show(workPanel, "remove");
            dialogue.setText("Paimon: You want to kick someone out? From which list?");
            counter2 = true;
        }
    }

    // represents a combo action for remove character
    private class RemoveComboAction extends AbstractAction {
        JComboBox<String> lists;

        RemoveComboAction(JComboBox lists) {
            this.lists = lists;
        }

        // EFFECTS: updates the current list that is printed to the screen
        @Override
        public void actionPerformed(ActionEvent evt) {
            String list = (String) lists.getSelectedItem();
            characters.removeAllItems();
            for (int i = 0; i < profile.getSize(); i++) {
                if (list.equals(profile.getList(i).getName())) {
                    if (counter2) {
                        if (profile.getList(i).getSize() == 0) {
                            dialogue.setText("Paimon: Uh oh, looks like the list "
                                    + "you selected has no characters to remove \n");
                        }
                    }
                    for (int j = 0; j < profile.getList(i).getSize(); j++) {
                        characters.addItem(profile.getList(i).getCharacter(j).getName());
                    }
                }
            }
        }
    }

    // represents a button that submits the form for removing characters
    private class RemoveSelectedAction extends AbstractAction {
        JComboBox lists;
        JComboBox characters;

        RemoveSelectedAction(JComboBox list, JComboBox character) {
            super("Remove");
            this.lists = list;
            this.characters = character;
        }

        // MODIFIES: lists, characters
        // EFFECTS: removes the character from the selected list and updates the list being displayed
        @Override
        public void actionPerformed(ActionEvent evt) {
            String list = (String) lists.getSelectedItem();
            String character = (String) characters.getSelectedItem();
            for (int i = 0; i < profile.getSize(); i++) {
                if (list.equals(profile.getList(i).getName())) {
                    for (int j = 0; j < profile.getList(i).getSize(); j++) {
                        if (character.equals(profile.getList(i).getCharacter(j).getName())) {
                            profile.getList(i).removeCharacterFromList(profile.getList(i).getCharacter(j));
                            dialogue.setText("Paimon: Goodbye " + character + "! I have removed them from your "
                                    + profile.getList(i).getName() + " list");
                        }
                    }
                }
            }
            characters.removeAllItems();
            for (int i = 0; i < profile.getSize(); i++) {
                if (list.equals(profile.getList(i).getName())) {
                    for (int j = 0; j < profile.getList(i).getSize(); j++) {
                        characters.addItem(profile.getList(i).getCharacter(j).getName());
                    }
                }
            }
        }
    }

    // represents a button for viewing lists
    private class ViewListAction extends AbstractAction {
        ViewListAction() {
            super("View List");
        }

        // MODIFIES: GUI
        // EFFECTS: brings user to the view characters panel
        @Override
        public void actionPerformed(ActionEvent evt) {
            addCharacter.setEnabled(true);
            removeCharacter.setEnabled(true);
            viewList.setEnabled(false);
            wcl.show(workPanel, "view");
            dialogue.setText("Paimon: Good idea! Lets view some lists");
            counter = true;
        }
    }

    // represents combo action for viewing lists
    private class ViewComboAction extends AbstractAction {
        JComboBox<String> list;

        ViewComboAction(JComboBox<String> list) {
            super("View");
            this.list = list;
        }

        // MODIFIES: GUI
        // EFFECTS: displays selected lists on screen
        @Override
        public void actionPerformed(ActionEvent evt) {
            String selectedString = (String) list.getSelectedItem();
            String stringOutput = "";
            for (int i = 0; i < profile.getSize(); i++) {
                if (selectedString.equals(profile.getList(i).getName())) {
                    CharacterList selected = profile.getList(i);
                    if (counter) {
                        if (selected.getSize() == 0) {
                            dialogue.setText("Paimon: You don't have any characters in this list!");
                        } else {
                            dialogue.setText(
                                    "Paimon: Here's a list of all the characters in your " + selectedString + " list!");
                        }
                    }

                    for (int j = 0; j < selected.getSize(); j++) {
                        stringOutput = stringOutput + "\n" + (j + 1) + ". " + selected.getCharacter(j).getName()
                            + "\n      Vision: " + selected.getCharacter(j).getVision()
                            + "\n      Weapon: " + selected.getCharacter(j).getWeapon()
                            + "\n      Level: " + selected.getCharacter(j).getLevel();
                    }
                }
            }
            output.setText(stringOutput);
            setVisible(true);
        }
    }

    // represents a button for quitting
    private class QuitAction extends AbstractAction {
        QuitAction() {
            super("Quit");
        }

        // MODIFIES: GUI
        // EFFECTS: quits the program and saves work if user chooses to do so
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFrame frame = new JFrame();
            int result = JOptionPane.showConfirmDialog(frame, "Paimon: Phew! That's enough list making for "
                    + "today! Would you like Paimon to save your work?");

            if (result == 0) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(profile);
                    jsonWriter.close();
                    System.exit(0);
                } catch (FileNotFoundException e) {
                    dialogue.setText("Paimon: Hmrph, Paimon is stumped and can't save your lists to " + JSON_STORE);
                }
            } else if (result == 1) {
                System.exit(0);
            }
        }
    }
}
