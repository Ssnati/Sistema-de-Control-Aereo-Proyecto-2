package co.edu.uptc.view;

import co.edu.uptc.presenter.Contract;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View extends JFrame implements Contract.View {
    private Contract.Presenter presenter;
    private MainPanel mainPanel;

    public View (){
        setTitle("Plane Game");
        setSize(new Dimension(814,537));//Ancho +14, Alto +37
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.white);
        initComponents();
    }

    private void initComponents() {
        mainPanel = new MainPanel();
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
}
