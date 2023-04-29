package co.edu.uptc.view.game;

import co.edu.uptc.pojo.Plane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {
    private List<Plane> planes;
    public GamePanel() {
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("src/resources/assets/Avion8BIT.png").getImage(), 0, 0, null);//Esquina superior izquierda
        g.drawImage(new ImageIcon("src/resources/assets/Avion8BIT.png").getImage(), 480, 0, null);//Esquina superior derecha
        g.drawImage(new ImageIcon("src/resources/assets/Avion8BIT.png").getImage(), 0, 480, null);//Esquina inferior izquierda
        g.drawImage(new ImageIcon("src/resources/assets/Avion8BIT.png").getImage(), 480, 480, null);//Esquina inferior derecha
    }

    public void addPlane(Plane plane){
        planes.add(plane);
    }
}
