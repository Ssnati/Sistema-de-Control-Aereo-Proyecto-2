package co.edu.uptc.view;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;
import co.edu.uptc.view.game.GamePanel;
import co.edu.uptc.view.planeConfig.ConfigPanel;
import co.edu.uptc.view.scoreBoard.ScorePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainPanel extends JPanel {
    private final Contract.Presenter presenter;
    private GamePanel gamePanel;
    private ConfigPanel configPanel;
    private ScorePanel scorePanel;

    public MainPanel(Contract.Presenter presenter) {
        this.presenter = presenter;
        setLayout(new GridBagLayout());
        setBackground(Color.black);
        initComponents();
    }

    private void initComponents() {
        gamePanel = new GamePanel(presenter);
        configPanel = new ConfigPanel(presenter);
        scorePanel = new ScorePanel(presenter);
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

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    public void setConfigPanel(ConfigPanel configPanel) {
        this.configPanel = configPanel;
    }

    public ScorePanel getScorePanel() {
        return scorePanel;
    }

    public void setScorePanel(ScorePanel scorePanel) {
        this.scorePanel = scorePanel;
    }

    public void clearPlanes() {
        gamePanel.clearPlanes();
        scorePanel.clearPlanes();
        configPanel.clearPlanes();
    }

    public void updateView(List<Plane> planes) {
        gamePanel.setPlanes(planes);
        scorePanel.setQuantityFlyingPlane(planes.size());
        gamePanel.repaint();
        scorePanel.repaint();
        configPanel.repaint();
    }
}
