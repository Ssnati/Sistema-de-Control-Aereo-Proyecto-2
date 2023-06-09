package co.edu.uptc.pojo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Plane {
    private int id;
    private Image image;
    private Coordinate coordinates;
    private HitBox hitBox;
    private int speed;
    private Color color;
    private List<Coordinate> coordinatesList;
    private List<Coordinate> route;
    private int[][] colorMatrix;
    private double angle;

    public Plane(Image image, Coordinate coordinates, HitBox hitBox, int speed, Color color, int id, List<Coordinate> coordinatesList, List<Coordinate> route, int[][] colorMatrix, double angle) {
        this.image = image;
        this.coordinates = coordinates;
        this.hitBox = hitBox;
        this.speed = speed;
        this.color = color;
        this.id = id;
        this.coordinatesList = coordinatesList;
        this.route = route;
        this.colorMatrix = colorMatrix;
        this.angle = angle;
    }

    public Plane() {
        id = 0;
        image = new ImageIcon().getImage();
        coordinates = new Coordinate();
        hitBox = new HitBox();
        speed = 0;
        color = new Color(-327167);
        coordinatesList = new ArrayList<>();
        route = new ArrayList<>();
        colorMatrix = new int[0][0];
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Coordinate> getCoordinatesList() {
        return coordinatesList;
    }

    public void setCoordinatesList(List<Coordinate> coordinatesList) {
        this.coordinatesList = coordinatesList;
    }

    public List<Coordinate> getRoute() {
        return route;
    }

    public void setRoute(List<Coordinate> route) {
        this.route = route;
    }

    public int[][] getColorMatrix() {
        return colorMatrix;
    }

    public void setColorMatrix(int[][] colorMatrix) {
        this.colorMatrix = colorMatrix;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}