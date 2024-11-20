package ProyectoFinal;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CircuitoPanel extends JPanel {
    private HashMap<Integer, Integer> combis;

    private Color[] colores = {
        Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
        Color.ORANGE, Color.CYAN, Color.MAGENTA, Color.PINK,
        Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK, Color.WHITE
    };

    public CircuitoPanel(HashMap<Integer, Integer> combis) {
        this.combis = combis;
    }

    public void updateCombis(HashMap<Integer, Integer> combis) {
        this.combis = combis; // Actualiza el mapa de combis
        repaint(); // Repinta el panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircuit(g);
        drawCombis(g);
    }

    private void drawCircuit(Graphics g) {
        // Dibuja el circuito (puedes personalizar esto)
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(50, 50, 300, 300); // Ejemplo de un circuito cuadrado
    }

    private void drawCombis(Graphics g) {
        // Dibuja las combis en sus posiciones
        for (Integer combiId : combis.keySet()) {
            int paradaIndex = combis.get(combiId);
            int x = 50 + (paradaIndex % 10) * 30; // Ejemplo de posición X
            int y = 50 + (paradaIndex / 10) * 30; // Ejemplo de posición Y

            // Asignar color basado en el ID de la combi
            Color color = colores[combiId % colores.length]; // Asegúrate de que combiId esté dentro del rango
            g.setColor(color);
            g.fillOval(x, y, 20, 20); // Dibuja una combi como un círculo
            g.setColor(Color.BLACK); // Cambia el color para la etiqueta
            g.drawString("Combi " + combiId, x, y - 5); // Etiqueta la combi
        }
    }
}