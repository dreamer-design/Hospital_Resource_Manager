package dsa.data;

import static dsa.model.DataModel.GRID_SIZE;
import dsa.structs.LinkedList;
import java.util.Random;

/**
 *
 * This class represents a single patient record
 */
// DSAGraphNode class using a linked list for adjacency list
public class Department {
    int id;
    String name;
    int[] loc;
//    LinkedList<Department> corridors; // edge list
    LinkedList<Corridor> corridors; // edge list
    Boolean visited; // for searches

    /**
     * NODE
     * @param id
     * @param name 
     */
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        corridors = new LinkedList<>();

        this.loc = new int[2];
        Random rng = new Random();
        loc[0] = rng.nextInt(10);
        loc[1] = rng.nextInt(10);
    }

    /**
     * NODE
     * @param id
     * @param name 
     * @param x in graph units
     * @param y (units)
     */
    public Department(int id, String name, int x, int y) {
        this.id = id;
        this.name = name;
        corridors = new LinkedList<>();

        this.loc = new int[2];
        loc[0] = x;
        loc[1] = y;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Corridor> getAdjList() {
        return corridors;
    }

    public int[] getLoc() {
        return loc;
    }

//    /**
//     * add a reference to the VERTEX to the EDGE LIST
//     * @param vertex 
//     */
//    public void addEdge(Department vertex) {
//        corridors.insertLast(vertex);
//    }
    
    /**
     * add a corridor with specific length
     * @param length
     * @param vertex 
     */
    public void addCorridor(float length, Department vertex) {
        corridors.insertLast(new Corridor(length, vertex));
    }

    public void setVisited() {
        this.visited = true;
    }

    public void clearVisited() {
        this.visited = false;
    }

    public Boolean getVisited() {
        return visited;
    }
    
    @Override
    public String toString() {
        Integer i  = id;
        return i.toString();
    }
}