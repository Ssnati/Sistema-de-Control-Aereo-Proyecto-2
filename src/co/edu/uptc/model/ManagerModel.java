package co.edu.uptc.model;

import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.HitBox;
import co.edu.uptc.presenter.Contract;

public class ManagerModel implements Contract.Model {
    private Contract.Presenter presenter;

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Coordinate generateCoordinates(HitBox hitBox, int panelWidth, int panelHeight) {
        int random = (int) (Math.random() * 4);
        Coordinate coordinate = new Coordinate();
        int x = (random == 0 || random == 2) ? (random == 0 ? 0 : panelWidth - hitBox.getWidth()) : (int) (Math.random() * (panelWidth - hitBox.getWidth()));
        int y = (random == 0 || random == 2) ? (int) (Math.random() * (panelHeight - hitBox.getHeight())) : (random == 1 ? 0 : panelHeight - hitBox.getHeight());
        coordinate.setX(x);
        coordinate.setY(y);
        return coordinate;
    }
}
