package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Plane;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements Contract.Presenter {
    private Contract.Model model;
    private Contract.View view;
    private boolean finishGame;

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

    private void restartGame() {
        if (view.getConfirmation("¿Desea reiniciar el juego?")) {
            view.clearPlanes();
            finishGame = false;
            startGame();
        } else {
            System.exit(0);
        }

    }
    @Override
    public void stopGame() {
        finishGame = true;
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
    public void updateView(List<Plane> planes) {
        view.updateView(planes);
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

    private boolean finishGame() {
        return false;
    }
}
