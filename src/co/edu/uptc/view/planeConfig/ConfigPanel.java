package co.edu.uptc.view.planeConfig;

import co.edu.uptc.view.Globals;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.util.Properties;

public class ConfigPanel extends JPanel {
    private JLabel title;
    private JLabel speedLabel;
    private JSlider speedSlider;
    private JLabel colorLabel;
    private JColorChooser colorChooser;
    private static final Color BACKGROUND_COLOR = new Color(87, 74, 59);
    private Properties properties;

    public ConfigPanel() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300, 317));
        setBackground(BACKGROUND_COLOR);
        initComponents();
    }

    private void initComponents() {
        title = new JLabel("Configuración de aviones");
        speedLabel = new JLabel("Velocidad");
        speedSlider = new JSlider(1, 10, 5);
        colorLabel = new JLabel("Color");
        colorChooser = new JColorChooser();

        title.setFont(Globals.getProgramFont(20));
        title.setForeground(Color.white);
        speedLabel.setFont(Globals.getProgramFont(15));
        speedLabel.setForeground(Color.white);
        colorLabel.setFont(Globals.getProgramFont(15));
        colorLabel.setForeground(Color.white);

        sliderFeatures(speedSlider);
        colorChooserFeatures(colorChooser);

        addComponents();
    }

    private void colorChooserFeatures(JColorChooser colorChooser) {
        colorChooser.setPreviewPanel(new JPanel());
        colorChooser.getPreviewPanel().setBackground(BACKGROUND_COLOR);
        AbstractColorChooserPanel colorPanel = colorChooser.getChooserPanels()[2];
        colorPanel.setBackground(BACKGROUND_COLOR);
        colorPanel.getComponent(0).setVisible(false);
        colorChooser.setChooserPanels(new AbstractColorChooserPanel[]{colorPanel});
        colorChooser.getSelectionModel().addChangeListener(e -> {
            Color color = colorChooser.getColor();
            System.out.println(color);
        });
        colorChooser.setBackground(BACKGROUND_COLOR);
        colorChooser.setForeground(Color.white);
    }

    private void sliderFeatures(JSlider speedSlider) {
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setBackground(BACKGROUND_COLOR);
        speedSlider.setForeground(Color.white);
    }

    private void addComponents() {
        addTitle();
        addSpeedLabel();
        addSpeedSlider();
        addColorLabel();
        addColorChooser();
    }

    private void addTitle() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(title, gbc);
    }

    private void addSpeedLabel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(speedLabel, gbc);
    }

    private void addSpeedSlider() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(speedSlider, gbc);
    }

    private void addColorLabel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(colorLabel, gbc);
    }

    private void addColorChooser() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(colorChooser, gbc);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void clearPlanes() {
        colorChooser.setColor(Color.white);
        speedSlider.setValue(5);
    }
}
