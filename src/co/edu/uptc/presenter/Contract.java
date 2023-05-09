package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Airstrip;
import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.HitBox;
import co.edu.uptc.pojo.Plane;

import java.awt.*;
import java.util.List;

public interface Contract {
    interface View {
        void setPresenter(Presenter presenter);

        void start();

        Dimension getDimension();

        List<Plane> getPlaneList();

        void showNotification(String message);

        boolean getConfirmation(String message);

        void clearPlanes();

        void updateView(List<Plane> planes, int planesArrived);

        void setPlaneToConfigure(Plane plane);

        void setAirstrip(Airstrip airstrip);
    }

    interface Model {
        void setPresenter(Presenter presenter);

        boolean verifyPlaneArrived(Plane plane);

        void addPlane(Plane plane, int panelWidth, int panelHeight);

        void startGame();

        Plane searchPlane(int xPos, int yPos);

        Plane searchPlane(int id);

        void addCoordinateToRoute(int planeIdSelected, int x, int y);

        void loadDefaultData();

        int getPlanesArrived();

        List<Plane> getPlanes();

        void notifyContinueGame();

        void changePlaneColor(int idPlane, Color color);

        void clearPlanes();
    }

    interface Presenter {
        void setModel(Model model);

        void setView(View view);

        void startGame();

        void stopGame();

        void setPlaneToConfigure(int idPlane);

        boolean gameHasFinished();

        int getGameWidth();

        int getGameHeight();

        void updateView();

        Plane searchPlane(int xPos, int yPos);

        Plane searchPlane(int id);

        void showNotification(String message);

        void removeRoute(int id);

        void addCoordinateToRoute(int planeIdSelected, int x, int y);

        void pauseAndContinue();

        boolean gameIsPaused();

        void setPlaneSpeed(int planeSelectedId, int speed);

        void setViewAirstrip(Airstrip airstrip);

        void loadDefaultData();

        void changePlaneColor(int idPlane, Color color);
    }
}
