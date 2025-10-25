package dsa.data;

import dsa.structs.LinkedList;
import java.util.Random;

/**
 *
 * This class represents a single patient record
 */
// DSAGraphNode class using a linked list for adjacency list
public class Department implements Comparable<Department> {
    int id;
    String name;
    int[] loc;
//    LinkedList<Department> corridors; // edge list
    LinkedList<Corridor> corridors; // edge list
    
    // search book keeping
    Boolean visited;    // closedSet
    float g;            // cost so far
    float f;            // total cost ( g + h )
    Department cameFrom;

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
        
        // search book keeping
        visited = false;    // closedSet
        g = 0;            // cost so far
        f = 0;            // total cost ( g + h )
        cameFrom = null;
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

    public LinkedList<Department> getDepAdjList() {
        LinkedList<Department> adjList = new LinkedList();
        for( Corridor edge: corridors)
            adjList.insertLast(edge.target);
        return adjList;
    }

    public Float getAdjCorridorLength(Department b) {
        // go through the corridors from a
        for( Corridor c : corridors ) {
            if( c.getTarget() == b )
                return c.getLength();
        }
        return null;
    }

    public int[] getLoc() {
        return loc;
    }
    
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

    public void setVisited(Boolean flag) {
        this.visited = flag;
    }

    public void clearVisited() {
        this.visited = false;
    }

    public Boolean getVisited() {
        return visited;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public Department getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(Department cameFrom) {
        this.cameFrom = cameFrom;
    }
    
    @Override
    public String toString() {
        Integer i  = id;
        return i.toString();
    }
    
    /**
     * Compare departments by ID for priority ordering
     * Lower ID = higher priority
     * @param other the department to compare to
     * @return negative if this has higher priority, positive if lower priority, 0 if equal
     */
    @Override
    public int compareTo(Department other) {
        return Float.compare(this.f, other.f);  // lower f = higher priority
    }
}