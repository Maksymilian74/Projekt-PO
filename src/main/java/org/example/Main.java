package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/*
    Klasa Main jest odpowiedzialna za przeprowadzenie symulacji, zainicjowanie wyskakujących okienek
*/

public class Main extends JFrame {

    private JLabel[][] cells;
    private Forest forest;
    private Character character;
    private JPanel forestPanel;
    private static JLabel infoLabel;
    private static boolean AutoMove;
    private static boolean EndWindowDisplayed;
    private static int Round;
    private boolean MapGenerated;
    private boolean StartStop;

    public Main() {
        Round = 0;
        MapGenerated = false;
        EndWindowDisplayed = true;
        AutoMove = true;
        StartStop = false;
        setTitle("Forest Simulation");
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/forest.png"));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
        setSize(1000,787);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static int getRound() {
        return Round;
    }
    public static void setEndWindowDisplayed() {
        EndWindowDisplayed = true;
    }
    public static void setAutoMove() {
        AutoMove = false;
    }

    public static void updateInfoLabel(String tekst) {
        infoLabel.setText(tekst);
    }

    /*
        Metoda odpowiedzialna za wyświetlenie okna podsumowania
    */

    public static void endWindow(Character character) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(89, 89, 99));

        JPanel endPanel = new JPanel(new BorderLayout());
        endPanel.setBackground(new Color(109, 109, 119));
        frame.add(endPanel);

        frame.setTitle("Podsumowanie");

        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPanel.setBackground(new Color(109, 109, 119));
        endPanel.add(contentPanel, BorderLayout.CENTER);

        Map<String, Integer> basket = character.getBasket();

        JLabel basketLabel = new JLabel();
        basketLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        basketLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 15, 20));
        StringBuilder basketText = new StringBuilder("<html>");
        basketText.append("Ilość ruchów: ").append(getRound()).append("<br>");
        basketText.append("Zawartość koszyka: ").append("<br>");
        for (Map.Entry<String, Integer> entry : basket.entrySet()) {
            String itemName = entry.getKey();
            int itemCount = entry.getValue();
            basketText.append(itemName).append(": ").append(itemCount).append("<br>");
        }
        basketText.append("</html>");
        basketLabel.setText(basketText.toString());
        contentPanel.add(basketLabel);
        frame.pack();

    }

    /*
        Metoda odpowiedzialna za aktualizowanie pól lasu
    */

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

    /*
        Metoda odpowiedzialna za automatyczne poruszanie się postaci, wywołuje metodę AutoMove z klasy Character
    */

    private void autoMoveCharacter() {
        Timer timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!AutoMove) {
                    return;
                }

                if (character != null && character.isAllItemsCollected() == false) {
                    Round++;
                    character.autoMove();
                    updateForestDisplay();

                    if ( EndWindowDisplayed == false) {
                        endWindow(character);
                        EndWindowDisplayed = true;
                    }
                }
            }
        });
        timer.start();
    }

    /*
        Metoda odpowiedzialna za inicjalizację okna symulacji
    */

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setBounds(500,500,250,750);
        sidePanel.setBackground(new Color(89, 89, 99));

        forestPanel = new JPanel();
        forestPanel.setBackground(new Color(135, 237, 147, 255));
        forestPanel.setBounds(0,0,750,750);

        JPanel controlPanel = new JPanel();//new GridBagLayout()
        controlPanel.setBounds(750,280,250,470);
        controlPanel.setSize(750,300);
        controlPanel.setBackground(new Color(89, 89, 99));
        controlPanel.setLayout(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JLabel automoveLabel = new JLabel("Automatyczne streowanie:");
        automoveLabel.setBounds(760,272,240,40);
        automoveLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        JButton startAutomoveButton = new JButton("Start");
        startAutomoveButton.setBounds(765,310,95,30);
        JButton stopAutomoveButton = new JButton("Stop");
        stopAutomoveButton.setBounds(870,310,95,30);
        JLabel sizeLabel = new JLabel("Enter forest size: ");
        sizeLabel.setBounds(780,330,200,60);
        sizeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        JTextField sizeField = new JTextField(4);
        sizeField.setBounds(780,375,100,40);
        JButton generateButton = new JButton("Generate Forest");
        generateButton.setBounds(780,420,135,50);
        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.setBounds(780,475,135,50);
        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.setBounds(780,530,135,50);
        JButton moveLeftButton = new JButton("Move Left");
        moveLeftButton.setBounds(780,585,135,50);
        JButton moveRightButton = new JButton("Move Right");
        moveRightButton.setBounds(780,640,135,50);
        infoLabel = new JLabel();
        infoLabel.setBounds(780,665,230,100);
        infoLabel.setForeground(Color.red);
        infoLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        controlPanel.add(automoveLabel);
        controlPanel.add(startAutomoveButton);
        controlPanel.add(stopAutomoveButton);
        controlPanel.add(sizeLabel);
        controlPanel.add(sizeField);
        controlPanel.add(generateButton);
        controlPanel.add(moveUpButton);
        controlPanel.add(moveDownButton);
        controlPanel.add(moveLeftButton);
        controlPanel.add(moveRightButton);
        controlPanel.add(infoLabel);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.setBounds(750,0,250,280);
        infoPanel.setBackground(new Color(89, 89, 99));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 10));

        JLabel pusteLasLabel = new JLabel("Puste pole lasu: L");
        pusteLasLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel bohaterLabel = new JLabel("Główny bohater: \u263A ");
        bohaterLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel wilkLabel = new JLabel("Wilk: W");
        wilkLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel dabLabel = new JLabel("Dąb: D");
        dabLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel sosnaLabel = new JLabel("Sosna: S");
        sosnaLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel brzozaLabel = new JLabel("Brzoza: R");
        brzozaLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel pieprznikLabel = new JLabel("Pieprznik: I");
        pieprznikLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel maitakeLabel = new JLabel("Maitake: M");
        maitakeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel borowikLabel = new JLabel("Borowik: O");
        borowikLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel podgrzybekLabel = new JLabel("Podgrzybek: P");
        podgrzybekLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel muchomorCzerwonyLabel = new JLabel("Muchomor czerwony: C");
        muchomorCzerwonyLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel muchomorSromotnikowyLabel = new JLabel("Muchomor sromotnikowy: T");
        muchomorSromotnikowyLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel borowkaLabel = new JLabel("Borówki: B");
        borowkaLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel jagodyLabel = new JLabel("Jagody: J");
        jagodyLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel jezynyLabel = new JLabel("Jeżyny: E");
        jezynyLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

        JLabel malinyLabel = new JLabel("Maliny: A");
        malinyLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));

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

        /*
             Przycisk odpowiedzialny za wystartowanie automatycznego ruchu
        */

        startAutomoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MapGenerated == true) {
                    if(StartStop == false) {
                        StartStop = true;
                        AutoMove = true;
                        Main.updateInfoLabel("Auto move ON");
                        autoMoveCharacter();
                    } else {
                        Main.updateInfoLabel("Już jest ON");
                    }
                } else {
                    Main.updateInfoLabel("Wygeneruj mapę");
                }
            }
        });

        /*
             Przycisk odpowiedzialny za wyłączenie automatycznego ruchu
        */

        stopAutomoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MapGenerated == true) {
                    if(StartStop == true) {
                        StartStop = false;
                        AutoMove = false;
                        Main.updateInfoLabel("Auto move OFF");
                        endWindow(character); //możliwość wyświetlania podsumowania po każdym wstrzymaniu ruchu
                    } else {
                        Main.updateInfoLabel("Już jest OFF");
                    }
                } else {
                    Main.updateInfoLabel("Wygeneruj mapę");
                }
            }
        });

        /*
             Przycisk odpowiedzialny za wygenerowanie lasu
        */

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sizeText = sizeField.getText();
                if (sizeText.isEmpty()) {
                    Main.updateInfoLabel("Podaj rozmiar");
                } else {
                    if (MapGenerated == false) {
                        MapGenerated = true;
                        int size = Integer.parseInt(sizeField.getText());
                        if (size > 0 && size <= 26) {
                            Main.updateInfoLabel("");
                            forest = new Forest(size);
                            character = new Character(forest);
                            generateForestCells(size);
                        } else {
                            Main.updateInfoLabel("Podaj liczbę (0;26]");
                        }
                    } else {
                        Main.updateInfoLabel("Mapa już wygenerowana");
                    }
                }
            }
        });

        /*
             Przycisk odpowiedzialny za poruszanie się w górę
        */

        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MapGenerated == true) {
                    if (character != null) {
                        Round++;
                        character.moveUp();
                        updateForestDisplay();
                    }
                } else {
                    Main.updateInfoLabel("Wygeneruj mapę");
                }
            }
        });

        /*
             Przycisk odpowiedzialny za poruszanie się w dół
        */

        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MapGenerated == true) {
                    if (character != null) {
                        Round++;
                        character.moveDown();
                        updateForestDisplay();
                    }
                } else {
                    Main.updateInfoLabel("Wygeneruj mapę");
                }
            }
        });

        /*
             Przycisk odpowiedzialny za poruszanie się w lewo
        */

        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MapGenerated == true) {
                    if (character != null) {
                        Round++;
                        character.moveLeft();
                        updateForestDisplay();
                    }
                } else {
                    Main.updateInfoLabel("Wygeneruj mapę");
                }
            }
        });

        /*
             Przycisk odpowiedzialny za poruszanie się w prawo
        */

        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MapGenerated == true) {
                    if (character != null) {
                        Round++;
                        character.moveRight();
                        updateForestDisplay();
                    }
                } else {
                    Main.updateInfoLabel("Wygeneruj mapę");
                }
            }
        });
    }

        /*
            Metoda odpowiedzialna za dobieranie kolorów w zależności od obiektu znajdującego się na danym polu
        */

    private Color getColorForCell(String cell) {
        switch (cell) {
            case "☺":
                return new Color(1, 1, 1);
            case "☹":
                return new Color(236, 144, 49);
            case "\uD83D\uDE21":
                return new Color(231, 14, 14);
            case "L":
                return new Color(16, 117, 13);
            case "B":
                return new Color(48, 7, 181);
            case "J":
                return new Color(35, 10, 115);
            case "E":
                return new Color(50, 36, 97);
            case "A":
                return new Color(161, 26, 104);
            case "R":
                return new Color(169, 166, 179);
            case "S":
                return new Color(84, 54, 40);
            case "D":
                return new Color(64, 36, 24);
            case "I":
                return new Color(207, 197, 12);
            case "M":
                return new Color(128, 114, 107);
            case "O":
                return new Color(112, 62, 35);
            case "P":
                return new Color(84, 46, 26);
            case "C":
                return new Color(212, 11, 11);
            case "T":
                return new Color(76, 130, 14);
            case "W":
                return new Color(72, 74, 69);
            default:
                return new Color(255, 255, 255);
        }
    }

        /*
            Metoda odpowiedzialna za generowanie komórek lasu
        */

    private void generateForestCells(int size) {
        forestPanel.removeAll();
        forestPanel.setLayout(new GridLayout(size, size));
        cells = new JLabel[size][size];


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new JLabel("", SwingConstants.CENTER);
                cells[i][j].setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
                cells[i][j].setBorder(BorderFactory.createLineBorder(new Color(235, 240, 235)));
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