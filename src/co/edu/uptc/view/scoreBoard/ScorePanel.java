package co.edu.uptc.view.scoreBoard;

import co.edu.uptc.presenter.Contract;
import co.edu.uptc.utils.Utils;
import co.edu.uptc.view.Globals;
import jdk.jshell.execution.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScorePanel extends JPanel {
    private final Contract.Presenter presenter;
    private JLabel flyingPlaneLabel;
    private JLabel arrivedPlaneLabel;
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton exitButton;


    public ScorePanel(Contract.Presenter presenter) {
        this.presenter = presenter;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300, 183));
        setBackground(new Color(113, 96, 82));
        initComponents();
    }

    private void initComponents() {
        flyingPlaneLabel = new JLabel("Aviones volando: 0");
        arrivedPlaneLabel = new JLabel("Aviones aterrizados: 0");
        startButton = new JButton("Iniciar");
        ImageIcon pauseIcon = new ImageIcon("src/resources/assets/Play_and_pause.png");
        pauseButton = new JButton(new ImageIcon(pauseIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        stopButton = new JButton("Detener");
        exitButton = new JButton("Salir");

        flyingPlaneLabel.setFont(Globals.getProgramFont(15));
        arrivedPlaneLabel.setFont(Globals.getProgramFont(15));
        flyingPlaneLabel.setForeground(Color.white);
        arrivedPlaneLabel.setForeground(Color.white);

        setButtonFeatures(startButton, new Color(41, 121, 255));
        setButtonFeatures(pauseButton, new Color(255, 109, 0));
        setButtonFeatures(stopButton, new Color(158, 158, 158));
        setButtonFeatures(exitButton, new Color(139, 0, 0));

        addListeners();
        addComponents();
    }

    private void addListeners() {
        startButton.addActionListener(e -> {
            Thread thread = new Thread(presenter::startGame);
            thread.start();
        });
        pauseButton.addActionListener(e -> {
            presenter.pauseAndContinue();
            System.out.println(Utils.getPurpleMessage() + "Game paused: " + presenter.gameIsPaused() + Utils.getResetMessage());
        });
        stopButton.addActionListener(e -> {
            presenter.setFinishGame(true);
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void setButtonFeatures(JButton button, Color backgroundColor) {
        button.setFont(Globals.getProgramFont(15));
        button.setBackground(backgroundColor);
        button.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button.setFocusPainted(false);
        button.setForeground(Color.black);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(backgroundColor.brighter());
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });
    }

    private void addComponents() {
        addFlyingPlaneLabel();
        addArrivedPlaneLabel();
        addStartButton();
        addPauseButton();
        addStopButton();
        addExitButton();
    }

    private void addFlyingPlaneLabel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        add(flyingPlaneLabel, gbc);
    }

    private void addArrivedPlaneLabel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10, 0, 20, 0);
        add(arrivedPlaneLabel, gbc);
    }

    private void addStartButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        add(startButton, gbc);
    }

    private void addPauseButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.ipadx = 12;
        gbc.ipady = 12;
        add(pauseButton, gbc);
    }

    private void addStopButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 12;
        gbc.ipady = 12;
        add(stopButton, gbc);
    }

    private void addExitButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.ipadx = 12;
        gbc.ipady = 12;
        add(exitButton, gbc);
    }

    public void setQuantityArrivedPlane(int arrivedPlane) {
        arrivedPlaneLabel.setText("Aviones aterrizados: " + arrivedPlane);
    }

    public void setQuantityFlyingPlane(int flyingPlane) {
        flyingPlaneLabel.setText("Aviones volando: " + flyingPlane);
    }

    public JLabel getFlyingPlaneLabel() {
        return flyingPlaneLabel;
    }

    public void setFlyingPlaneLabel(JLabel flyingPlaneLabel) {
        this.flyingPlaneLabel = flyingPlaneLabel;
    }

    public JLabel getArrivedPlaneLabel() {
        return arrivedPlaneLabel;
    }

    public void setArrivedPlaneLabel(JLabel arrivedPlaneLabel) {
        this.arrivedPlaneLabel = arrivedPlaneLabel;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(JButton pauseButton) {
        this.pauseButton = pauseButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public void setStopButton(JButton stopButton) {
        this.stopButton = stopButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public void clearPlanes() {
        setQuantityArrivedPlane(0);
        setQuantityFlyingPlane(0);
    }
}
