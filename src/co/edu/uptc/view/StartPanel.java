package co.edu.uptc.view;

import co.edu.uptc.presenter.Contract;

import javax.swing.*;

public class StartPanel extends JPanel {
    private final Contract.Presenter presenter;

    public StartPanel(Contract.Presenter presenter) {
        this.presenter = presenter;
        setLayout(null);

    }

}
