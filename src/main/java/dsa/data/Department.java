package dsa.data;
import dsa.structs.LinkedList;
import java.util.Random;

/**
 * This class represents a single Department record
 * location is a 10x10 grid
 */
public class Department {
    int id;
    String name;
    int[] loc;
    LinkedList<Department> corridors; // edge list

    /**
     * NODE with random locations
     * @param id
     * @param name 
     */
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        corridors = new LinkedList<>();

        this.loc = new int[2];
        Random rng = new Random();
        // store location in GRID units (0-9)
        loc[0] = rng.nextInt(10);
        loc[1] = rng.nextInt(10);
    }

    /**
     * NODE with locations
     * @param id
     * @param name 
     * @param x 
     * @param y 
     */
    public Department(int id, String name, int x, int y) {
        this.id = id;
        this.name = name;
        corridors = new LinkedList<>();

        // copy grid-unit location (do not convert to pixels here)
        this.loc = new int[] { x, y };
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Department> getAdjList() {
        return corridors;
    }

    public int[] getLoc() {
        return loc;
    }

    /**
     * add a reference to the VERTEX to the EDGE LIST
     * @param vertex 
     */
    public void addEdge(Department vertex) {
        corridors.insertLast(vertex);
    }

//        // MUTATOR setVisited
//        public void setVisited() {
//            visited = true;
//        }
//
//        // MUTATOR clearVisited
//        public void clearVisited() {
//            visited = false;
//        }
//
//        // ACCESSOR getVisited
//        public boolean getVisited() {
//            return visited;
//        }
}