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

    public Plane(Image image, Coordinate coordinates, HitBox hitBox, int speed, Color color, int id, List<Coordinate> coordinatesList) {
        this.image = image;
        this.coordinates = coordinates;
        this.hitBox = hitBox;
        this.speed = speed;
        this.color = color;
        this.id = id;
        this.coordinatesList = coordinatesList;
    }

    public Plane() {
        id = 0;
        image = new ImageIcon().getImage();
        coordinates = new Coordinate();
        hitBox = new HitBox();
        speed = 0;
        color = Color.red;
        coordinatesList = new ArrayList<>();
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
}