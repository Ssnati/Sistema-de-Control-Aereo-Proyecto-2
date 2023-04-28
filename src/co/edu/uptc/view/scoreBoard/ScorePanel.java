package co.edu.uptc.view.scoreBoard;

import co.edu.uptc.view.Globals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ScorePanel extends JPanel {
    private JLabel flyingPlaneLabel;
    private JLabel arrivedPlaneLabel;
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton exitButton;

    public ScorePanel() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300, 183));
        setBackground(new Color(113, 96, 82));
        initComponents();
    }

    private void initComponents() {
        flyingPlaneLabel = new JLabel("Aviones volando: 0");
        arrivedPlaneLabel = new JLabel("Aviones aterrizados: 0");
        startButton = new JButton("Iniciar");
        pauseButton = new JButton("Pausar");
        stopButton = new JButton("Detener");
        exitButton = new JButton("Salir");


        flyingPlaneLabel.setFont(Globals.getProgramFont(15));
        arrivedPlaneLabel.setFont(Globals.getProgramFont(15));
        startButton.setFont(Globals.getProgramFont(15));
        pauseButton.setFont(Globals.getProgramFont(15));
        stopButton.setFont(Globals.getProgramFont(15));
        exitButton.setFont(Globals.getProgramFont(15));

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
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(41, 121, 255).brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(41, 121, 255));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Start");
            }
        });
        pauseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pauseButton.setBackground(new Color(255, 109, 0).brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                pauseButton.setBackground(new Color(255, 109, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Pause");
            }
        });
        stopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stopButton.setBackground(new Color(158, 158, 158).brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                stopButton.setBackground(new Color(158, 158, 158));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Stop");
            }
        });
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(139, 0, 0).brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(139, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Exit");
            }
        });
    }

    private void setButtonFeatures(JButton button, Color backGroundColor) {
        button.setBackground(backGroundColor);
        button.setBorder(BorderFactory.createLineBorder(backGroundColor));
        button.setFocusPainted(false);
        button.setForeground(Color.black);
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
        gbc.insets = new Insets(10,0,20,0);
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
        gbc.insets = new Insets(0,10,0,10);
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
        gbc.insets = new Insets(10,0,0,0);
        gbc.ipadx = 12;
        gbc.ipady = 12;
        add(exitButton, gbc);
    }
}
