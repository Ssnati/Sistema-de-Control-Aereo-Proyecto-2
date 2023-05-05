package co.edu.uptc.view.game;

import co.edu.uptc.model.PropertiesManager;
import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.*;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener {
    private List<Plane> planes;
    private Graphics2D g2d;
    private int planeIdSelected;
    private Contract.Presenter presenter;

    public GamePanel(Contract.Presenter presenter) {
        this.presenter = presenter;
        planes = new ArrayList<>();
        planeIdSelected = -1;
        setPreferredSize(new Dimension(500, 500));
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        g2d = (Graphics2D) graphics;
        super.paintComponent(g2d);
        drawBackground();
        drawRoad();
        drawPlanes();
        g2d.drawString(".", 250, 250);
    }

    private void drawRoute(Plane plane) {
        g2d.setColor(Color.white);
        List<Coordinate> coordinateList = plane.getRoute();
        for (int i = 0; i < coordinateList.size() - 1; i++) {
            int x = (int) coordinateList.get(i).getX();
            int y = (int) coordinateList.get(i).getY();
            int xNext = (int) coordinateList.get(i + 1).getX();
            int yNext = (int) coordinateList.get(i + 1).getY();
            g2d.drawLine(x, y, xNext, yNext);
        }
    }

    private void drawRoad() {
        ImageIcon imageIcon = new ImageIcon(PropertiesManager.getInstance().getProperty("RUNWAY_IMAGE_URL"));
        g2d.drawImage(imageIcon.getImage(), 250 - imageIcon.getIconWidth() / 2, 300 - imageIcon.getIconHeight() / 2, null);
    }

    private void drawBackground() {
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawPlanes() {
        for (Plane plane : planes) {
            drawTail(plane);
            if (plane.getRoute().size() > 0) drawRoute(plane);
            g2d.setColor(plane.getColor());
            g2d.drawImage(plane.getImage(), (int) plane.getCoordinates().getX(), (int) plane.getCoordinates().getY(), null);
            drawHitBox(g2d, plane);
        }
    }

    private void drawHitBox(Graphics2D g, Plane plane) {
        g.setColor(Color.white);
        g.drawRect((int) plane.getCoordinates().getX(), (int) plane.getCoordinates().getY(), plane.getHitBox().getWidth(), plane.getHitBox().getHeight());
    }

    private void drawTail(Plane plane) {
        g2d.setColor(Color.white);
        int xCenter = plane.getHitBox().getWidth() / 2;
        int yCenter = plane.getHitBox().getHeight() / 2;
        List<Coordinate> coordinatesList = plane.getCoordinatesList();
        for (int i = 0; i < coordinatesList.size() - 1; i++) {
            int x = (int) (coordinatesList.get(i).getX() + xCenter);
            int y = (int) (coordinatesList.get(i).getY() + yCenter);
            int xNext = (int) (coordinatesList.get(i + 1).getX() + xCenter);
            int yNext = (int) (coordinatesList.get(i + 1).getY() + yCenter);
            g2d.drawLine(x, y, xNext, yNext);
        }
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public void clearPlanes() {
        planes = new ArrayList<>();
        repaint();
    }

    private Plane getPlaneClicked(MouseEvent e) {
        return presenter.searchPlane(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (planeIdSelected != -1) {
            presenter.addCoordinateToRoute(planeIdSelected, e.getX(), e.getY());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Plane plane = getPlaneClicked(e);
        if (plane != null) {
            presenter.removeRoute(plane.getId());
            planeIdSelected = plane.getId();
            presenter.setPlaneToConfigure(planeIdSelected);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        planeIdSelected = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}