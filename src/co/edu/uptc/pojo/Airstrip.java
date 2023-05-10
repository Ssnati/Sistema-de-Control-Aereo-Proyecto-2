package co.edu.uptc.pojo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Airstrip {
    private Coordinate coordinate;
    private HitBox hitBox;
    private Image image;
    private List<Rectangle> arrivalHitBoxes;

    public Airstrip() {
        arrivalHitBoxes = new ArrayList<>();
    }

    public Airstrip(Coordinate coordinate, HitBox hitBox, Image image, List<Rectangle> arrivalHitBoxes) {
        this.coordinate = coordinate;
        this.hitBox = hitBox;
        this.image = image;
        this.arrivalHitBoxes = arrivalHitBoxes;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Rectangle> getArrivalHitBoxes() {
        return arrivalHitBoxes;
    }

    public void setArrivalHitBoxes(List<Rectangle> arrivalHitBoxes) {
        this.arrivalHitBoxes = arrivalHitBoxes;
    }
}