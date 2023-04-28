package co.edu.uptc.view;

import co.edu.uptc.view.game.GamePanel;
import co.edu.uptc.view.planeConfig.ConfigPanel;
import co.edu.uptc.view.scoreBoard.ScorePanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private GamePanel gamePanel;
    private ConfigPanel configPanel;
    private ScorePanel scorePanel;

    public MainPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.black);
        initComponents();
    }

    private void initComponents() {
        gamePanel = new GamePanel();
        configPanel = new ConfigPanel();
        scorePanel = new ScorePanel();

        addComponents();
    }

    private void addComponents() {
        addScorePanel();
        addConfigPanel();
        addGamePanel();
    }

    private void addScorePanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(scorePanel, constraints);
    }

    private void addConfigPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(configPanel, constraints);
    }

    private void addGamePanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        add(gamePanel, constraints);
    }
}
