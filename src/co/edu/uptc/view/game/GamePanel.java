package co.edu.uptc.view.game;

import co.edu.uptc.pojo.Airstrip;
import co.edu.uptc.pojo.Coordinate;
import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;
import co.edu.uptc.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener {
    private Contract.Presenter presenter;
    private List<Plane> planes;
    private Graphics2D g2d;
    private int planeIdSelected;
    private Airstrip airstrip;

    public GamePanel(Contract.Presenter presenter) {
        this.presenter = presenter;
        planes = new ArrayList<>();
        planeIdSelected = -1;
        airstrip = new Airstrip();
        setPreferredSize(new Dimension(500, 500));
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        g2d = (Graphics2D) graphics;
        super.paintComponent(g2d);
        drawBackground();
        drawAirstrip();
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

    private void drawAirstrip() {
        g2d.drawImage(airstrip.getImage(), (int) airstrip.getCoordinate().getX(), (int) airstrip.getCoordinate().getY(), null);
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
            BufferedImage image = rotateImage(Utils.toBufferedImage(plane.getImage()), plane.getAngle());
            g2d.drawImage(image, (int) plane.getCoordinates().getX(), (int) plane.getCoordinates().getY(), null);
//            drawHitBox(g2d, plane);
        }
    }

    public static BufferedImage rotateImage(BufferedImage image, double angle) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage rotatedImage = new BufferedImage(width, height, image.getType());
        Graphics2D g2d = rotatedImage.createGraphics();
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle + 90), (double) width / 2, (double) height / 2);
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return rotatedImage;
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

    public Airstrip getAirstrip() {
        return airstrip;
    }

    public void setAirstrip(Airstrip airstrip) {
        this.airstrip = airstrip;
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