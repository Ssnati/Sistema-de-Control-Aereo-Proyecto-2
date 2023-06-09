package co.edu.uptc.presenter;

import co.edu.uptc.model.ManagerModel;
import co.edu.uptc.view.View;

public class ManagerGeneral {
    private Contract.View view;
    private Contract.Model model;
    private Contract.Presenter presenter;

    public static void main(String[] args) {
        ManagerGeneral generalManager = new ManagerGeneral();
        generalManager.createMVP();
//        generalManager.loadDefaultData();
        generalManager.runProject();
        generalManager.loadDefaultData();
    }

    private void loadDefaultData() {
        presenter.loadDefaultData();
    }

    private void runProject() {
        view.start();
    }

    private void createMVP() {
        presenter = new Presenter();
        view = new View(presenter);
        model = new ManagerModel(presenter);
        presenter.setView(view);
        presenter.setModel(model);
    }
}
