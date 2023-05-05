package co.edu.uptc.model;

import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.HitBox;
import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;
import co.edu.uptc.utils.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerModel implements Contract.Model {

    private Contract.Presenter presenter;
    private List<Plane> planes;

    public ManagerModel(Contract.Presenter presenter) {
        this.presenter = presenter;
        planes = new ArrayList<>();
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
    public void movePlaneToCenter(Plane plane, int gameWidth, int gameHeight) {
        double x = plane.getCoordinates().getX();
        double y = plane.getCoordinates().getY();
        double angle = Math.atan2((double) gameHeight / 2 - y, (double) gameWidth / 2 - x);
        double xOneMove = (Math.cos(angle));
        double yOneMove = (Math.sin(angle));
        Coordinate coordinate = new Coordinate(x + xOneMove, y + yOneMove);
        plane.setCoordinates(coordinate);
        plane.getCoordinatesList().add(coordinate);
    }

    private void startMovementThread(Plane plane, int gameWidth, int gameHeight) {
        Thread movementTread = new Thread(() -> {
            while (!presenter.gameHasFinished()) {
//                if(!presenter.isPauseGame()){
                    if (plane.getRoute().size() > 0) {
                        plane.setRoute(followRoute(plane, plane.getRoute()));
                    } else {
                        movePlaneToCenter(plane, gameWidth, gameHeight);
                    }
                    Utils.sleepThread(1000 / plane.getSpeed());
                    presenter.updateView(planes);
                    gameEnded(plane);
                    verifyPlaneArrived(plane);
//                }
            }
        });
        movementTread.start();
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
        Coordinate coordinateInMiddle = new Coordinate(coordinate.getX() - (double) plane.getHitBox().getWidth() / 2, coordinate.getY() - (double) plane.getHitBox().getHeight() / 2);
        plane.setCoordinates(coordinateInMiddle);
        plane.getCoordinatesList().add(coordinateInMiddle);
        route.remove(0);
        return route;
    }

    @Override
    public void verifyPlaneArrived(Plane plane) {

    }

    @Override
    public void addPlane(Plane plane, int panelWidth, int panelHeight) {
        plane.setImage(new ImageIcon(PropertiesManager.getInstance().getProperty("PLANE_IMAGE_URL")).getImage());
        plane.setHitBox(new HitBox(plane.getImage().getWidth(null), plane.getImage().getHeight(null)));
        plane.setCoordinates(generateCoordinates(plane.getHitBox(), panelWidth, panelHeight));
        plane.setSpeed(generateSpeed());
        plane.setId(generateUniqueId(planes));
        planes.add(plane);
    }

    @Override
    public void startGame() {
        Thread thread = new Thread(() -> {
            while (!presenter.gameHasFinished()) {
//                System.out.println("Esta pausado: " + !presenter.gameIsPaused());
//                if(!presenter.gameIsPaused()){
//                    System.out.println("Se ha iniciado el juego" + presenter.gameIsPaused());
                    boolean listEmpty = planes.size() < 3;
//                boolean listEmpty = true;
                    if (listEmpty) {
                        Plane plane = new Plane();
                        int gameWidth = presenter.getGameWidth();
                        int gameHeight = presenter.getGameHeight();
                        addPlane(plane, gameWidth, gameHeight);
                        System.out.println("Se ha creado un nuevo avion: " + plane.getId());
                        presenter.updateView(planes);
                        startMovementThread(plane, gameWidth, gameHeight);
                        gameEnded(plane);
                        Utils.sleepThread((int) (Double.parseDouble(PropertiesManager.getInstance().getProperty("GENERATION_SPEED_IN_SECONDS")) * 1000));
//                    presenter.updateView(planes);
                    }
                    presenter.updateView(planes);
                }
//            }
        });
        thread.start();
    }

    private void gameEnded(Plane plane) {
        if (verifyCollision(plane, planes)) {
            presenter.stopGame();
            presenter.showNotification("El juego ha terminado, 2 aviones se han chocado");
        }
    }

    @Override
    public Plane searchPlane(int xPos, int yPos) {
        for (Plane plane : planes) {
            if (plane.getCoordinates().getX() <= xPos && plane.getCoordinates().getX() + plane.getHitBox().getWidth() >= xPos) {
                if (plane.getCoordinates().getY() <= yPos && plane.getCoordinates().getY() + plane.getHitBox().getHeight() >= yPos) {
                    return plane;
                }
            }
        }
        return null;
    }

    @Override
    public Plane searchPlane(int id) {
        for (Plane plane : planes) {
            if (plane.getId() == id) {
                return plane;
            }
        }
        return null;
    }

    @Override
    public void addCoordinateToRoute(int planeIdSelected, int x, int y) {
        Plane plane = searchPlane(planeIdSelected);
        if (plane != null) {
            Coordinate coordinate = new Coordinate(x, y);
            plane.getRoute().add(coordinate);
        }
    }

    private boolean planesCrash(Plane plane1, Plane plane2) {
        return plane1.getCoordinates().getX() < plane2.getCoordinates().getX() + plane2.getHitBox().getWidth() &&
                plane1.getCoordinates().getX() + plane1.getHitBox().getWidth() > plane2.getCoordinates().getX() &&
                plane1.getCoordinates().getY() < plane2.getCoordinates().getY() + plane2.getHitBox().getHeight() &&
                plane1.getCoordinates().getY() + plane1.getHitBox().getHeight() > plane2.getCoordinates().getY();
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }
}
