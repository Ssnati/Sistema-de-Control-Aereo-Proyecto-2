package co.edu.uptc.pojo;

import java.awt.*;

public class Airstrip {
    private Coordinate coordinate;
    private HitBox hitBox;
    private Image image;

    public Airstrip() {
    }

    public Airstrip(Coordinate coordinate, HitBox hitBox, Image image) {
        this.coordinate = coordinate;
        this.hitBox = hitBox;
        this.image = image;
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
}
