package co.edu.uptc.model;

import co.edu.uptc.pojo.Airstrip;
import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.HitBox;
import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;
import co.edu.uptc.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ManagerModel implements Contract.Model {

    private Contract.Presenter presenter;
    private List<Plane> planes;
    private Airstrip airstrip;
    private int planesArrived;
    private final Object lockGeneration = new Object();

    public ManagerModel(Contract.Presenter presenter) {
        this.presenter = presenter;
        planes = new ArrayList<>();
        airstrip = new Airstrip();
    }

    private Airstrip defaultAirstrip() {
        Airstrip airstrip = new Airstrip();
        airstrip.setImage(new ImageIcon(PropertiesManager.getInstance().getProperty("AIRSTRIP_IMAGE_URL")).getImage());
        airstrip.setHitBox(new HitBox(airstrip.getImage().getWidth(null), airstrip.getImage().getHeight(null)));
        double x = (double) (presenter.getGameWidth() - airstrip.getHitBox().getWidth()) / 2;
        double y = (double) (presenter.getGameHeight() + airstrip.getHitBox().getHeight()) / 2;
        airstrip.setCoordinate(new Coordinate(x, y));
        return airstrip;
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    public Coordinate generateCoordinates(HitBox hitBox, int panelWidth, int panelHeight) {
        int random = (int) (Math.random() * 4);
        Coordinate coordinate = new Coordinate();
        int x = (random == 0 || random == 2) ? (random == 0 ? 0 : panelWidth - hitBox.getWidth()) : (int) (Math.random() * (panelWidth - hitBox.getWidth()));
        int y = (random == 0 || random == 2) ? (int) (Math.random() * (panelHeight - hitBox.getHeight())) : (random == 1 ? 0 : panelHeight - hitBox.getHeight());
        coordinate.setX(x);
        coordinate.setY(y);
        return coordinate;
    }

    public int generateSpeed() {
        int random = (int) (Math.random() * Integer.parseInt(PropertiesManager.getInstance().getProperty("MAX_SPEED_INT")));
        return random == 0 ? 1 : random;
    }

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

    public void movePlaneToAngle(Plane plane) {
        System.out.println(Utils.getPurpleMessage() + "Plane " + plane.getId() + " is moving to angle" + Utils.getResetMessage());
        double angle = Math.toRadians(plane.getAngle());
        double xOneMove = (Math.cos(angle));
        double yOneMove = (Math.sin(angle));
        double x = plane.getCoordinates().getX();
        double y = plane.getCoordinates().getY();
        Coordinate coordinate = new Coordinate(x + xOneMove, y + yOneMove);
        plane.setCoordinates(coordinate);
        plane.getCoordinatesList().add(coordinate);
    }

    private void startMovementThread(Plane plane) {
        Thread movementTread = new Thread(() -> {
            while (!(presenter.gameHasFinished() || verifyPlaneArrived(plane))) {
                synchronized (plane){
                    if (presenter.gameIsPaused()){
                        try {
                            plane.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    movePlaneSynchro(plane);
                }
            }
            presenter.updateView();
        });
        movementTread.start();
    }

    private void movePlaneSynchro(Plane plane) {
        verifyPlaneIsOnBorder(plane);
        if (plane.getRoute().size() > 0) {
            plane.setRoute(followRoute(plane, plane.getRoute()));
            System.out.println(Utils.getBlueMessage() + "Plane " + plane.getId() + " is following route" + Utils.getResetMessage());
        }
        movePlaneToAngle(plane);
        Utils.sleepThread(1000);
        presenter.updateView();
        verifyGameEnded(plane);
//        System.out.println(Utils.getCyanMessage() + "Plane " + plane.getId() + " has moved" + Utils.getResetMessage());
    }

    private void verifyPlaneIsOnBorder(Plane plane) {
        if (plane.getCoordinates().getX() < 0 || plane.getCoordinates().getX() + plane.getHitBox().getWidth() > presenter.getGameWidth() ||
                plane.getCoordinates().getY() < 0 || plane.getCoordinates().getY() + plane.getHitBox().getHeight() > presenter.getGameHeight()) {
            System.out.println(Utils.getGreenMessage() + "Plane " + plane.getId() + " previous angle: " + plane.getAngle() + Utils.getResetMessage());
            plane.setAngle(getAngleToCenter(plane, presenter.getGameWidth(), presenter.getGameHeight()));
            plane.setRoute(new ArrayList<>());
            System.out.println(Utils.getRedMessage() + "Plane " + plane.getId() + " has changed angle: " + plane.getAngle() + Utils.getResetMessage());
        }
    }

    public int generateUniqueId(List<Plane> planeList) {
        int id = 0;
        List<Integer> idList = planeList.stream().map(Plane::getId).toList();
        while (idList.contains(id)) {
            id++;
        }
        return id;
    }

    public List<Coordinate> followRoute(Plane plane, List<Coordinate> route) {
        Coordinate coordinate = route.get(0);
        Coordinate coordinateInMiddle = new Coordinate(coordinate.getX() - (double) plane.getHitBox().getWidth() / 2, coordinate.getY() - (double) plane.getHitBox().getHeight() / 2);
        changeAngle(plane, coordinateInMiddle);
        plane.setCoordinates(coordinateInMiddle);
        plane.getCoordinatesList().add(coordinateInMiddle);
        route.remove(0);
        return route;
    }

    private void changeAngle(Plane plane, Coordinate coordinateToRotate) {
        Coordinate lastCoordinate = plane.getCoordinates();
        double angle = Math.toDegrees(Math.atan2(coordinateToRotate.getY() - lastCoordinate.getY(), coordinateToRotate.getX() - lastCoordinate.getX()));
        plane.setAngle(angle);
    }

    @Override
    public boolean verifyPlaneArrived(Plane plane) {
        Coordinate planeCoords = plane.getCoordinates();
        Coordinate airstripCoords = airstrip.getCoordinate();
        boolean hasArrived = planeCoords.getX() >= airstripCoords.getX() &&
                planeCoords.getX() <= airstripCoords.getX() + airstrip.getHitBox().getWidth() &&
                planeCoords.getY() >= airstripCoords.getY() &&
                planeCoords.getY() <= airstripCoords.getY() + airstrip.getHitBox().getHeight();
        if (hasArrived) {
            planes.remove(searchPlane(plane.getId()));
            planesArrived++;
        }
        return hasArrived;
    }

    @Override
    public void addPlane(Plane plane, int panelWidth, int panelHeight) {
        plane.setImage(new ImageIcon(PropertiesManager.getInstance().getProperty("PLANE_IMAGE_URL")).getImage());
        plane.setHitBox(new HitBox(plane.getImage().getWidth(null), plane.getImage().getHeight(null)));
        plane.setCoordinates(generateCoordinates(plane.getHitBox(), panelWidth, panelHeight));
        plane.setSpeed(generateSpeed());
        plane.setId(generateUniqueId(planes));
        setColorMatrix(plane);
        plane.setAngle(getAngleToCenter(plane, panelWidth, panelHeight));
        planes.add(plane);
    }

    private double getAngleToCenter(Plane plane, int panelWidth, int panelHeight) {
        double x = plane.getCoordinates().getX();
        double y = plane.getCoordinates().getY();
        double angle = Math.atan2((double) panelHeight / 2 - y, (double) panelWidth / 2 - x);
        return Math.toDegrees(angle);
    }

    private void setColorMatrix(Plane plane) {
        int[][] matrix = new int[plane.getHitBox().getWidth()][plane.getHitBox().getHeight()];
        BufferedImage image = Utils.toBufferedImage(plane.getImage());
        for (int i = 0; i < plane.getHitBox().getWidth(); i++) {
            for (int j = 0; j < plane.getHitBox().getHeight(); j++) {
                if (image.getRGB(i, j) == plane.getColor().getRGB()) {
                    matrix[i][j] = 1;
                }
            }
        }
        plane.setColorMatrix(matrix);
    }

    @Override
    public void startGame() {
        Thread thread = new Thread(() -> {
            while (!presenter.gameHasFinished()) {
                synchronized (lockGeneration) {
                    generatePlanesSynchro();
                }
                presenter.updateView();
            }
            presenter.showNotification("El juego ha terminado, 2 aviones se han estrellado y " + planesArrived + " han aterrizado");
//            presenter.restartGame();
        });
        thread.start();
    }

    private void generatePlanesSynchro() {
        while (presenter.gameIsPaused()) {
            try {
                lockGeneration.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        boolean listEmpty = planes.size() < 3;
//        boolean listEmpty = true;
        if (listEmpty) {
            Plane plane = new Plane();
            int gameWidth = presenter.getGameWidth();
            int gameHeight = presenter.getGameHeight();
            addPlane(plane, gameWidth, gameHeight);
            System.out.println("Se ha creado un nuevo avion: " + plane.getId());
            presenter.updateView();
            startMovementThread(plane);
            verifyGameEnded(plane);
            Utils.sleepThread((int) (Double.parseDouble(PropertiesManager.getInstance().getProperty("GENERATION_SPEED_IN_SECONDS")) * 1000));
        }
    }

    private void verifyGameEnded(Plane plane) {
        if (verifyCollision(plane, planes)) {
            presenter.stopGame();
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

    @Override
    public void loadDefaultData() {
        airstrip = defaultAirstrip();
        presenter.setViewAirstrip(airstrip);
    }

    @Override
    public void notifyContinueGame() {
        synchronized (lockGeneration) {
            lockGeneration.notify();
        }
        for (Plane plane : planes){
            synchronized (plane) {
                plane.notify();
            }
        }
    }

    @Override
    public void changePlaneColor(int idPlane, Color color) {
        Plane plane = searchPlane(idPlane);
        if (plane != null) {
            changePlaneImageColor(plane, color);
        }
    }

    @Override
    public void clearPlanes() {
        planes.clear();

    }

    private void changePlaneImageColor(Plane plane, Color color) {
        BufferedImage image = Utils.toBufferedImage(plane.getImage());
        for (int i = 0; i < plane.getHitBox().getWidth(); i++) {
            for (int j = 0; j < plane.getHitBox().getHeight(); j++) {
                if (plane.getColorMatrix()[i][j] == 1) {
                    image.setRGB(i, j, color.getRGB());
                }
            }
        }
        plane.setImage(image);
        plane.setColor(color);
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

    public int getPlanesArrived() {
        return planesArrived;
    }

    public void setPlanesArrived(int planesArrived) {
        this.planesArrived = planesArrived;
    }
}
