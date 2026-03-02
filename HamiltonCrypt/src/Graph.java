import java.util.*;

/**
 * Manages the graph topology, node generation, and pathfinding logic.
 */
public class Graph {
    private Map<Integer, Node> nodes = new HashMap<>();

    public void addNode(Node node) {
        nodes.put(node.getId(), node);
    }

    /**
     * Initializes a grid of nodes with specified dimensions and spacing.
     */
    public void createNodes(int rows, int cols, int spacing) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int id = r * cols + c;
                int x = (c + 1) * spacing;
                int y = (r + 1) * spacing;
                addNode(new Node(id, x, y));
            }
        }
    }

    /**
     * Connects all adjacent nodes in the grid, including diagonals.
     */
    public void connectAllNodes(int rows, int cols) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int currentId = r * cols + c;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        if (dr == 0 && dc == 0) continue; // Skip self
                        int nr = r + dr;
                        int nc = c + dc;
                        // Boundary check
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                            nodes.get(currentId).addNeighbor(nr * cols + nc);
                        }
                    }
                }
            }
        }
    }

    /**
     * Entry point for the randomized Hamiltonian Path solver.
     */
    public List<Integer> findHamiltonianPath() {
        List<Integer> startNodes = new ArrayList<>(nodes.keySet());
        Collections.shuffle(startNodes); // Ensure different solutions on each run

        for (Integer startId : startNodes) {
            for (Node n : nodes.values()) n.setVisited(false);
            List<Integer> path = new ArrayList<>();
            Node startNode = nodes.get(startId);
            startNode.setVisited(true);
            path.add(startId);

            if (backtrack(startNode, path)) return path;
        }
        return new ArrayList<>();
    }

    /**
     * Recursive backtracking algorithm to find a path visiting all nodes.
     */
    private boolean backtrack(Node current, List<Integer> path) {
        if (path.size() == nodes.size()) return true;

        List<Integer> neighbors = new ArrayList<>(current.getNeighbors());
        Collections.shuffle(neighbors);

        for (int nid : neighbors) {
            Node next = nodes.get(nid);
            if (!next.isVisited()) {
                next.setVisited(true);
                path.add(nid);
                if (backtrack(next, path)) return true;
                // Step back (Un-visit)
                next.setVisited(false);
                path.remove(path.size() - 1);
            }
        }
        return false;
    }

    /**
     * Generates a Lehmer Code from a permutation path to serve as an encryption key.
     */
    public List<Integer> calculateLehmerCode(List<Integer> path) {
        List<Integer> lehmer = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            int count = 0;
            int currentVal = path.get(i);
            for (int j = i + 1; j < path.size(); j++) {
                if (path.get(j) < currentVal) count++;
            }
            lehmer.add(count);
        }
        return lehmer;
    }

    public Map<Integer, Node> getNodes() { return nodes; }
}