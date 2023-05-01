package co.edu.uptc.model;

import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.HitBox;
import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;

import java.awt.*;
import java.util.List;

public class ManagerModel implements Contract.Model {

    private Contract.Presenter presenter;

    public ManagerModel(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

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

    @Override
    public int generateSpeed() {
        int random = (int) (Math.random() * Integer.parseInt(PropertiesManager.getInstance().getProperty("MAX_SPEED_INT")));
        return random == 0 ? 1 : random;
    }

    @Override
    public boolean verifyCollision(Plane planeToVerify, List<Plane> planeList) {
        for (Plane plane : planeList) {
            if (planeToVerify != plane) {
                if (planesCrash(planeToVerify, plane)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void movePlaneToCenter(Plane plane, Dimension dimension) {
        double x = plane.getCoordinates().getX();
        double y = plane.getCoordinates().getY();
        double angle = Math.atan2((double) dimension.height / 2 - y, (double) dimension.width / 2 - x);
        double xOneMove = (Math.cos(angle));
        double yOneMove = (Math.sin(angle));
        Coordinate coordinate = new Coordinate(x + xOneMove,y + yOneMove);
        plane.setCoordinates(coordinate);
        plane.getCoordinatesList().add(coordinate);
//        System.out.println("Moviendo avion: " + plane.getId() + " a: " + plane.getCoordinates().getX() + ", " + plane.getCoordinates().getY());
    }
    @Override
    public int generateUniqueId(List<Plane> planeList) {
        int id = 0;
        List<Integer> idList = planeList.stream().map(Plane::getId).toList();
        while (idList.contains(id)) {
            id++;
        }
        return id;
    }

    @Override
    public List<Coordinate> followRoute(Plane plane, List<Coordinate> route) {
        Coordinate coordinate = route.get(0);
        Coordinate coordinateInMiddle = new Coordinate(coordinate.getX() - (double) plane.getHitBox().getWidth() /2, coordinate.getY() - (double) plane.getHitBox().getHeight() /2);
        plane.setCoordinates(coordinateInMiddle);
        plane.getCoordinatesList().add(coordinateInMiddle);
        route.remove(0);
        return route;
    }

    private boolean planesCrash(Plane plane1, Plane plane2) {
        return plane1.getCoordinates().getX() < plane2.getCoordinates().getX() + plane2.getHitBox().getWidth() &&
                plane1.getCoordinates().getX() + plane1.getHitBox().getWidth() > plane2.getCoordinates().getX() &&
                plane1.getCoordinates().getY() < plane2.getCoordinates().getY() + plane2.getHitBox().getHeight() &&
                plane1.getCoordinates().getY() + plane1.getHitBox().getHeight() > plane2.getCoordinates().getY();
    }
}
