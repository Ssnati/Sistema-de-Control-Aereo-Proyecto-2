package co.edu.uptc.view;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Contract.View {
    private Contract.Presenter presenter;
    private MainPanel mainPanel;

    public View (Contract.Presenter presenter){
        this.presenter = presenter;
        setTitle("Plane Game");
        setUndecorated(true);
        setSize(new Dimension(800,500));//Ancho +14, Alto +37
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
}
