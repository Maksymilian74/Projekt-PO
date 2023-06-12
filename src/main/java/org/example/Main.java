package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JLabel[][] cells;
    private Forest forest;
    private Character character;
    private JPanel forestPanel;

    public Main() {

        setTitle("Forest Simulation");
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/forest.png"));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
        setSize(1000,787);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateForestDisplay() {
        int size = forest.getSize_x();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String symbol = forest.getCell(i, j);
                Color color = getColorForCell(symbol);
                cells[i][j].setText(symbol);
                cells[i][j].setForeground(color);
            }
        }
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setBounds(500,500,250,750);
        sidePanel.setBackground(new Color(99, 99, 109));

        forestPanel = new JPanel();
        forestPanel.setBackground(new Color(135, 237, 147, 255));
        forestPanel.setBounds(0,0,750,750);

        JPanel controlPanel = new JPanel();//new GridBagLayout()
        controlPanel.setBounds(750,300,250,450);
        controlPanel.setSize(750,300);
        controlPanel.setBackground(new Color(99, 99, 109));
        controlPanel.setLayout(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JLabel sizeLabel = new JLabel("Enter forest size: ");
        sizeLabel.setBounds(780,300,200,60);
        sizeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        JTextField sizeField = new JTextField(5);
        sizeField.setBounds(780,350,150,60);
        JButton generateButton = new JButton("Generate Forest");
        generateButton.setBounds(780,415,150,60);
        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.setBounds(780,480,150,60);
        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.setBounds(780,545,150,60);
        JButton moveLeftButton = new JButton("Move Left");
        moveLeftButton.setBounds(780,610,150,60);
        JButton moveRightButton = new JButton("Move Right");
        moveRightButton.setBounds(780,675,150,60);
        controlPanel.add(sizeLabel);
        controlPanel.add(sizeField);
        controlPanel.add(generateButton);
        controlPanel.add(moveUpButton);
        controlPanel.add(moveDownButton);
        controlPanel.add(moveLeftButton);
        controlPanel.add(moveRightButton);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.setBounds(750,0,250,300);
        infoPanel.setBackground(new Color(89, 89, 99));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 10));

        JLabel pusteLasLabel = new JLabel("Puste pole lasu: L");
        pusteLasLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel bohaterLabel = new JLabel("Główny bohater: \u263A ");
        bohaterLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel wilkLabel = new JLabel("Wilk: W");
        wilkLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel dabLabel = new JLabel("Dąb: D");
        dabLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel sosnaLabel = new JLabel("Sosna: S");
        sosnaLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel brzozaLabel = new JLabel("Brzoza: R");
        brzozaLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel pieprznikLabel = new JLabel("Pieprznik: I");
        pieprznikLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel maitakeLabel = new JLabel("Maitake: M");
        maitakeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel borowikLabel = new JLabel("Borowik: O");
        borowikLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel podgrzybekLabel = new JLabel("Podgrzybek: P");
        podgrzybekLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel muchomorCzerwonyLabel = new JLabel("Muchomor czerwony: C");
        muchomorCzerwonyLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel muchomorSromotnikowyLabel = new JLabel("Muchomor sromotnikowy: T");
        muchomorSromotnikowyLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel borowkaLabel = new JLabel("Borówki: B");
        borowkaLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel jagodyLabel = new JLabel("Jagody: J");
        jagodyLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel jezynyLabel = new JLabel("Jeżyny: E");
        jezynyLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JLabel malinyLabel = new JLabel("Maliny: A");
        malinyLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        infoPanel.add(pusteLasLabel);
        infoPanel.add(bohaterLabel);
        infoPanel.add(wilkLabel);
        infoPanel.add(dabLabel);
        infoPanel.add(sosnaLabel);
        infoPanel.add(brzozaLabel);
        infoPanel.add(maitakeLabel);
        infoPanel.add(pieprznikLabel);
        infoPanel.add(borowikLabel);
        infoPanel.add(podgrzybekLabel);
        infoPanel.add(muchomorCzerwonyLabel);
        infoPanel.add(muchomorSromotnikowyLabel);
        infoPanel.add(borowkaLabel);
        infoPanel.add(jagodyLabel);
        infoPanel.add(jezynyLabel);
        infoPanel.add(malinyLabel);

        mainPanel.add(forestPanel);
        mainPanel.add(sidePanel);
        sidePanel.add(infoPanel);
        sidePanel.add(controlPanel,BorderLayout.CENTER);
        add(mainPanel);


        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(sizeField.getText());
                forest = new Forest(size);
                character = new Character(forest);
                generateForestCells(size);
            }
        });

        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveUp();
                    updateForestDisplay();
                }
            }
        });

        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveDown();
                    updateForestDisplay();
                }
            }
        });

        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveLeft();
                    updateForestDisplay();
                }
            }
        });

        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveRight();
                    updateForestDisplay();
                }
            }
        });
    }

    private Color getColorForCell(String cell) {
        switch (cell) {
            case "☺":
                return new Color(76, 64, 65, 255);
            case "L":
                return new Color(11, 170, 11, 255);
            case "B":
                return Color.YELLOW;
            case "J":
                return Color.RED;
            case "E":
                return Color.YELLOW;
            case "A":
                return Color.MAGENTA;
            case "R":
                return Color.WHITE;
            case "S":
                return Color.GREEN;
            case "D":
                return Color.YELLOW;
            case "I":
                return Color.CYAN;
            case "M":
                return Color.GREEN;
            case "O":
                return Color.YELLOW;
            case "P":
                return Color.YELLOW;
            case "C":
                return Color.RED;
            default:
                return Color.BLACK;
        }
    }

    private void generateForestCells(int size) {
        forestPanel.removeAll();
        forestPanel.setLayout(new GridLayout(size, size));
        cells = new JLabel[size][size];


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new JLabel("", SwingConstants.CENTER);
                cells[i][j].setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
                cells[i][j].setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 255)));
                cells[i][j].setText("L");
                forestPanel.add(cells[i][j]);
            }
        }

        updateForestDisplay();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}