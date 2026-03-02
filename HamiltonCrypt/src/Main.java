import javax.swing.SwingUtilities;

/**
 * Entry point for the Hamiltonian Cryptography application.
 */
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        // Initialize a 4x4 grid with 100px spacing
        graph.createNodes(4, 4, 100);
        graph.connectAllNodes(4, 4);

        SwingUtilities.invokeLater(() -> {
            new MainWindow(graph).setVisible(true);
        });
    }
}