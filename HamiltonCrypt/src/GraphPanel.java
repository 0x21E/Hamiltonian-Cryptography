import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Visual interface for the user to interact with the graph.
 */
public class GraphPanel extends JPanel {
    private Graph graph;
    private MainWindow parent; // Reference to the main window for status updates
    private final int NODE_RADIUS = 15;

    private List<Integer> userPath;
    private Point currentMousePos;
    private List<Integer> lastLehmerCode = null;

    public GraphPanel(Graph graph, MainWindow parent) {
        this.graph = graph;
        this.parent = parent;
        this.userPath = new ArrayList<>();
        setBackground(Color.WHITE);

        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void clearPath() {
        userPath.clear();
        lastLehmerCode = null;
        repaint();
    }

    public List<Integer> getLastLehmerCode() {
        return lastLehmerCode;
    }

    public void showPath(List<Integer> path) {
        if (path == null || path.isEmpty()) return;
        this.userPath = new ArrayList<>(path);
        this.lastLehmerCode = graph.calculateLehmerCode(this.userPath);
        this.currentMousePos = null;
        repaint();
    }

    private Node getNodeAt(int x, int y) {
        for (Node node : graph.getNodes().values()) {
            int dx = node.getX() - x;
            int dy = node.getY() - y;
            if (dx * dx + dy * dy <= NODE_RADIUS * NODE_RADIUS) return node;
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw connections
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        for (int i = 0; i < userPath.size() - 1; i++) {
            Node n1 = graph.getNodes().get(userPath.get(i));
            Node n2 = graph.getNodes().get(userPath.get(i + 1));
            g2d.drawLine(n1.getX(), n1.getY(), n2.getX(), n2.getY());
        }

        // Draw active drag line
        if (!userPath.isEmpty() && currentMousePos != null) {
            Node lastNode = graph.getNodes().get(userPath.get(userPath.size() - 1));
            g2d.setColor(Color.GRAY);
            g2d.drawLine(lastNode.getX(), lastNode.getY(), currentMousePos.x, currentMousePos.y);
        }

        // Draw nodes
        for (Node node : graph.getNodes().values()) {
            g2d.setColor(userPath.contains(node.getId()) ? new Color(34, 139, 34) : new Color(70, 130, 180));
            g2d.fillOval(node.getX() - NODE_RADIUS, node.getY() - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
            g2d.setColor(Color.WHITE);
            g2d.drawString(String.valueOf(node.getId()), node.getX() - 4, node.getY() + 4);
        }
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            Node clicked = getNodeAt(e.getX(), e.getY());
            if (clicked != null) {
                userPath.clear();
                userPath.add(clicked.getId());
                currentMousePos = e.getPoint();
                parent.refreshTexts();
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (userPath.isEmpty()) return;
            currentMousePos = e.getPoint();
            Node hovered = getNodeAt(e.getX(), e.getY());
            if (hovered != null) {
                Node last = graph.getNodes().get(userPath.get(userPath.size() - 1));
                if (!userPath.contains(hovered.getId()) && last.getNeighbors().contains(hovered.getId())) {
                    userPath.add(hovered.getId());
                }
            }
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            currentMousePos = null;
            if (userPath.size() == graph.getNodes().size()) {
                lastLehmerCode = graph.calculateLehmerCode(userPath);
                parent.refreshTexts();
                JOptionPane.showMessageDialog(GraphPanel.this, LanguageManager.get("msg_path_valid") + lastLehmerCode);
            } else if (!userPath.isEmpty() && userPath.size() > 1) {
                JOptionPane.showMessageDialog(GraphPanel.this, LanguageManager.get("err_path_incomplete"));
                userPath.clear();
                parent.refreshTexts();
            }
            repaint();
        }
    }
}