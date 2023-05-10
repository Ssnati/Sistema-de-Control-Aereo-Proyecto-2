package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Airstrip;
import co.edu.uptc.pojo.Plane;

import java.awt.*;
import java.util.ArrayList;

public class Presenter implements Contract.Presenter {
    private Contract.Model model;
    private Contract.View view;
    private boolean finishGame;
    private boolean pauseGame;

    public Presenter() {
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
        model.startGame();
    }

    @Override
    public void setFinishGame(boolean finishGame) {
        this.finishGame = finishGame;
    }

    @Override
    public void setPlaneToConfigure(int idPlane) {
        view.setPlaneToConfigure(model.searchPlane(idPlane));
    }

    @Override
    public boolean gameHasFinished() {
        return finishGame;
    }

    @Override
    public int getGameWidth() {
        return view.getDimension().width;
    }

    @Override
    public int getGameHeight() {
        return view.getDimension().height;
    }

    @Override
    public void updateView() {
        view.updateView(model.getPlanes(), model.getPlanesArrived());
    }

    @Override
    public Plane searchPlane(int xPos, int yPos) {
        return model.searchPlane(xPos, yPos);
    }

    @Override
    public Plane searchPlane(int id) {
        return model.searchPlane(id);
    }

    @Override
    public void showNotification(String message) {
        view.showNotification(message);
    }

    @Override
    public void removeRoute(int id) {
        model.searchPlane(id).setRoute(new ArrayList<>());
    }

    @Override
    public void addCoordinateToRoute(int planeIdSelected, int x, int y) {
        model.addCoordinateToRoute(planeIdSelected, x, y);
    }

    @Override
    public void pauseAndContinue() {
        pauseGame = !pauseGame;
        if (!pauseGame) model.notifyContinueGame();
    }

    @Override
    public boolean gameIsPaused() {
        return pauseGame;
    }

    @Override
    public void setPlaneSpeed(int planeSelectedId, int speed) {
        model.searchPlane(planeSelectedId).setSpeed(speed);
    }

    @Override
    public void setViewAirstrip(Airstrip airstrip) {
        view.setAirstrip(airstrip);
    }

    @Override
    public void loadDefaultData() {
        model.loadDefaultData();
    }

    @Override
    public void changePlaneColor(int planeSelected, Color color) {
        model.changePlaneColor(planeSelected, color);
    }

    @Override
    public boolean getConfirmation(String message) {
        return view.getConfirmation(message);
    }

    @Override
    public void clearPlanes() {
        view.clearPlanes();
        model.clearPlanes();
    }
}
