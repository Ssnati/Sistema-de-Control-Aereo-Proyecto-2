package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.HitBox;
import co.edu.uptc.pojo.Plane;

import java.awt.*;
import java.util.List;
import java.util.Map;

public interface Contract {
    interface View {
        void setPresenter(Presenter presenter);

        void start();

        void addPlane(Plane plane);

        Dimension getDimension();

        List<Plane> getPlaneList();

        void showNotification(String message);

        boolean getConfirmation(String message);

        void clearPlanes();

        void updateView();

        Map<Integer, List<Coordinate>> getRoutes();

        void setPlaneToConfigure(Plane plane);
    }

    interface Model {
        void setPresenter(Presenter presenter);

        Coordinate generateCoordinates(HitBox hitBox, int panelWidth, int panelHeight);

        int generateSpeed();

        boolean verifyCollision(Plane plane, List<Plane> planeList);

        void movePlaneToCenter(Plane plane, Dimension dimension);

        int generateUniqueId(List<Plane> planeList);

        List<Coordinate> followRoute(Plane plane, List<Coordinate> coordinates);

        void verifyPlaneArrived(Plane plane);
    }

    interface Presenter {
        void setModel(Model model);

        void setView(View view);

        void startGame();

        void stopGame();
        void setPlaneToConfigure(Plane plane);
    }
}
