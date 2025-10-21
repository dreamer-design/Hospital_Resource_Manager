package dsa.data;

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
    LinkedList<Department> corridors; // edge list
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
        loc[0] = rng.nextInt(30)*10+50;
        loc[1] = rng.nextInt(30)*10+10;
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

    public void setVisited() {
        this.visited = true;
    }

    public void clearVisited() {
        this.visited = false;
    }

    public Boolean getVisited() {
        return visited;
    }
}