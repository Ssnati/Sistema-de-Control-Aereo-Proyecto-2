package co.edu.uptc.pojo;

import java.awt.*;

public class Plane {
    private String urlImage;
    private Coordinate coordinates;
    private HitBox hitBox;
    private int speed;
    private Color color;

    public Plane(String urlImage, Coordinate coordinates, HitBox hitBox, int speed, Color color) {
        this.urlImage = urlImage;
        this.coordinates = coordinates;
        this.hitBox = hitBox;
        this.speed = speed;
        this.color = color;
    }

    public Plane() {
        urlImage = "";
        coordinates = new Coordinate();
        hitBox = new HitBox();
        speed = 0;
        color = Color.BLACK;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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
}