import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single vertex in the graph.
 * Stores spatial coordinates for the GUI and adjacency information.
 */
public class Node {
    private int id;
    private int x, y; // Visual coordinates on the screen
    private List<Integer> neighbors; // List of connected node IDs
    private boolean visited; // Flag used for backtracking algorithms

    public Node(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.neighbors = new ArrayList<>();
        this.visited = false;
    }

    public int getId() { return id; }
    public int getX() { return x; }
    public int getY() { return y; }
    public List<Integer> getNeighbors() { return neighbors; }
    public boolean isVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }

    /**
     * Adds a neighbor ID if it is not already in the adjacency list.
     */
    public void addNeighbor(int neighborId) {
        if (!neighbors.contains(neighborId)) {
            neighbors.add(neighborId);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}