package co.edu.uptc.presenter;

import co.edu.uptc.model.ManagerModel;
import co.edu.uptc.view.View;

import java.io.FileReader;
import java.util.Properties;

public class ManagerGeneral {
    private Contract.View view;
    private Contract.Model model;
    private Contract.Presenter presenter;

    public static void main(String[] args) {
        ManagerGeneral generalManager = new ManagerGeneral();
        generalManager.createMVP();
        generalManager.loadDefaultData();
        generalManager.runProject();
    }

    private void loadDefaultData() {
//        presenter.loadDefaultData();
    }

    private void runProject() {
        view.start();
    }

    private void createMVP() {
        Properties properties = loadProperties();
        presenter = new Presenter(properties);
        view = new View(presenter);
        model = new ManagerModel(presenter);
        presenter.setView(view);
        presenter.setModel(model);
        view.setProperties(properties);
        model.setProperties(properties);
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/resources/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}
