package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.HitBox;
import co.edu.uptc.pojo.Plane;
import co.edu.uptc.utils.Utils;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Properties;

public class Presenter implements Contract.Presenter {
    private final Properties properties;
    private Contract.Model model;
    private Contract.View view;
    private boolean finishGame;

    public Presenter(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void setModel(Contract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(Contract.View view) {
        this.view = view;
    }

    @Override
    public void startGame() {
        int panelWidth = view.getDimension().width;
        int panelHeight = view.getDimension().height;
        System.out.println(panelHeight + ", " + panelWidth);
        while (!finishGame) {
            Plane plane = new Plane();
            plane.setImage(new ImageIcon(properties.getProperty("PLANE_IMAGE_URL")).getImage());
            plane.setHitBox(new HitBox(plane.getImage().getWidth(null), plane.getImage().getHeight(null)));
            plane.setCoordinates(model.generateCoordinates(plane.getHitBox(), panelWidth, panelHeight));
            plane.setSpeed(model.generateSpeed());
            Utils.sleepThread(Integer.parseInt(properties.getProperty("GENERATION_SPEED_IN_SECONDS"))*1000);
            view.addPlane(plane);
            System.out.println("Plane added");
            System.out.println("Plane coordinates: " + plane.getCoordinates().getX() + ", " + plane.getCoordinates().getY());
        }
    }

    @Override
    public void stopGame() {
        finishGame = true;
    }

    private boolean finishGame() {
        return false;
    }
}
