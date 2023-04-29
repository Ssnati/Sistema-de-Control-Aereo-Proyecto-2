package co.edu.uptc.presenter;

import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.HitBox;

public interface Contract {
    public interface View {
        void setPresenter(Presenter presenter);

        public void start();
    }

    public interface Model {
        void setPresenter(Presenter presenter);

        Coordinate generateCoordinates(HitBox hitBox, int panelWidth, int panelHeight);
    }

    public interface Presenter {
        void setModel(Model model);

        void setView(View view);
    }
}
