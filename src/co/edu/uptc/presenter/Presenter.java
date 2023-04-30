package co.edu.uptc.presenter;

import co.edu.uptc.pojo.HitBox;
import co.edu.uptc.pojo.Plane;
import co.edu.uptc.utils.Utils;

import javax.swing.*;
import java.util.*;

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
        Thread thread = new Thread(() -> {
            while (!finishGame) {
                if (true) {
                    Plane plane = new Plane();
                    addPlane(plane, view.getDimension().width, view.getDimension().height);
                    movePlaneToCenter(plane);
                    verifyCollision(plane);
                    Utils.sleepThread((int) (Double.parseDouble(properties.getProperty("GENERATION_SPEED_IN_SECONDS")) * 1000));
                }
            }
            restartGame();
        });
        thread.start();
    }

    private void movePlaneToCenter(Plane plane) {
        Thread movementThread = new Thread(() -> {
            while (!finishGame) {
                model.movePlaneToCenter(plane, view.getDimension());
                Utils.sleepThread(1000/plane.getSpeed());
                view.updateView();
                verifyCollision(plane);
            }
        });
        movementThread.start();
    }

    private void addPlane(Plane plane, int panelWidth, int panelHeight) {
        view.addPlane(plane);
        plane.setImage(new ImageIcon(properties.getProperty("PLANE_IMAGE_URL")).getImage());
        plane.setHitBox(new HitBox(plane.getImage().getWidth(null), plane.getImage().getHeight(null)));
        plane.setCoordinates(model.generateCoordinates(plane.getHitBox(), panelWidth, panelHeight));
        plane.setSpeed(model.generateSpeed());
        plane.setId(model.generateUniqueId(view.getPlaneList()));
//        System.out.println("Plane added");
//        System.out.println("Plane coordinates: " + plane.getCoordinates().getX() + ", " + plane.getCoordinates().getY());
    }

    private void restartGame() {
        if (view.getConfirmation("¿Desea reiniciar el juego?")) {
            view.clearPlanes();
            finishGame = false;
            startGame();
        } else {
            System.exit(0);
        }

    }

    private void verifyCollision(Plane plane) {
        if (model.verifyCollision(plane, view.getPlaneList())) {
            System.out.println("Collision detected");
            stopGame();
            view.showNotification("El juego ha terminado, 2 aviones colisionaron");
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
