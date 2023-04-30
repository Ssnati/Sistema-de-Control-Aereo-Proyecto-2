package co.edu.uptc.view.game;

import co.edu.uptc.pojo.Plane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GamePanel extends JPanel {
    private List<Plane> planes;
    private Properties properties;

    public GamePanel() {
        planes = new ArrayList<>();
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        super.paintComponent(g);
        drawBackground(g);
        drawRoad(g);
        drawPlanes(g);
        g.drawString(".",250,250);
    }

    private void drawRoad(Graphics2D g) {
        ImageIcon imageIcon = new ImageIcon(properties.getProperty("RUNWAY_IMAGE_URL"));
        g.drawImage(imageIcon.getImage(), 250 - imageIcon.getIconWidth() / 2, 300 - imageIcon.getIconHeight() / 2, null);
    }

    private void drawBackground(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawPlanes(Graphics2D g) {
        for (Plane plane : planes) {
            g.setColor(plane.getColor());
            g.drawImage(plane.getImage(), plane.getCoordinates().getX(), plane.getCoordinates().getY(), null);
        }
    }

    public void addPlane(Plane plane) {
        planes.add(plane);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public void clearPlanes() {
        planes.clear();
        repaint();
    }
}
