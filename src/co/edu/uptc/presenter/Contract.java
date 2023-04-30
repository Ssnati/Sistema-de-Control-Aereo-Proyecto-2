package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.HitBox;
import co.edu.uptc.pojo.Plane;

import java.awt.*;
import java.util.Properties;

public interface Contract {
    public interface View {
        void setPresenter(Presenter presenter);

        public void start();

        void addPlane(Plane plane);

        Dimension getDimension();

        void setProperties(Properties properties);
    }

    public interface Model {
        void setPresenter(Presenter presenter);

        Coordinate generateCoordinates(HitBox hitBox, int panelWidth, int panelHeight);

        int generateSpeed();

        void setProperties(Properties properties);
    }

    public interface Presenter {
        void setModel(Model model);

        void setView(View view);

        void startGame();

        void stopGame();
    }
}
