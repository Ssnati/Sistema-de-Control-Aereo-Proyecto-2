package co.edu.uptc.view.game;

import co.edu.uptc.pojo.Plane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private List<Plane> planes;
    public GamePanel() {
        planes = new ArrayList<>();
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawPlanes(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawPlanes(Graphics g) {
        for (Plane plane : planes) {
            g.setColor(plane.getColor());
            g.drawImage(plane.getImage(), plane.getCoordinates().getX(), plane.getCoordinates().getY(), null);
        }
    }

    public void addPlane(Plane plane){
        planes.add(plane);
    }
}
