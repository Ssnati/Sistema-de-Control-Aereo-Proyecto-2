package co.edu.uptc.view;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class View extends JFrame implements Contract.View {
    private Properties properties;
    private Contract.Presenter presenter;
    private MainPanel mainPanel;

    public View(Contract.Presenter presenter) {
        this.presenter = presenter;
        setTitle("Plane Game");
        setUndecorated(true);
        setSize(new Dimension(800, 500));//Ancho +14, Alto +37
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.black);
        initComponents();
    }

    private void initComponents() {
        mainPanel = new MainPanel(presenter);
        add(mainPanel);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        setVisible(true);
    }

    @Override
    public void addPlane(Plane plane) {
        mainPanel.addPlane(plane);
    }

    @Override
    public Dimension getDimension() {
        return mainPanel.getGamePanel().getSize();
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
        mainPanel.setProperties(properties);
    }

    @Override
    public List<Plane> getPlaneList() {
        return mainPanel.getGamePanel().getPlanes();
    }

    @Override
    public void showNotification(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public boolean getConfirmation(String message) {
        return 0 == JOptionPane.showConfirmDialog(this, message, "Reinicio del juego", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void clearPlanes() {
        mainPanel.clearPlanes();
    }
}
