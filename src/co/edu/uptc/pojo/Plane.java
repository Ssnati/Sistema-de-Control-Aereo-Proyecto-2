package co.edu.uptc.pojo;

import java.awt.*;

public class Plane {
    private String urlImage;
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private Color color;

    public Plane(String urlImage, int x, int y, int width, int height, int speed, Color color) {
        this.urlImage = urlImage;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
    }

    public Plane() {
        urlImage = "";
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        speed = 0;
        color = Color.BLACK;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
