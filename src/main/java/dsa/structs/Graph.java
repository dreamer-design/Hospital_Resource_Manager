package dsa.structs;

import dsa.data.Corridor;
import dsa.data.Department;
import dsa.model.Search;
import java.util.Iterator;

/** a DSAGraph class using linked lists to store the list of nodes 
 * Implement a Graph class using an adjacency list.
 *  Support dynamic insertion of departments (nodes) and corridors (weighted edges)
 * 
 */
public class Graph {
    LinkedList<Department> departments; // departments (nodes)

    public Graph() {
        departments = new LinkedList<>();
//        // need at lease 1 dep before adding a patient
//        departments.insertFirst( new Department(1, "foyer")); 
//        departments.insertLast( new Department(2, "exit"));
    }

    /**
     * get the departments List
     * @return Department Linked List Reference
     */
    public LinkedList<Department> getDepartments() {
        return departments;
    }

    /**
     * Add NODE||VERTEX
     * @param id
     * @param name 
     */
    public void addDepartment(int id, String name) {
        departments.insertLast(new Department(id, name));
    }

    /**
     * Add NODE||VERTEX
     * @param id
     * @param name 
     * @param x 
     * @param y 
     */
    public void addDepartment(int id, String name, int x, int y) {
        departments.insertLast(new Department(id, name, x, y));
    }

    /**
     * Get NODE||VERTEX
     * @param id
     * @return department
     */
    public Department getDepartment(int id) {
        for (var dep : departments) {
            if (dep.getId() == id) {
                return dep;
            }
        }
        return null; // not found
    }


    /**
     * Adds EDGE between NODE||VERTEX undirected with specific length
     * @param id1
     * @param id2
     * @param length
     */
    public void addCorridor(int id1, int id2, float length) {
        Department v1 = getDepartment(id1);
        Department v2 = getDepartment(id2);
        v1.addCorridor(length, v2);
        v2.addCorridor(length, v1);
    }
    
    /**
     * add a corridor (calculate length)
     */
    public void addCorridor(int id1, int id2) {
        Department v1 = getDepartment(id1);
        Department v2 = getDepartment(id2);
        float length = Search.hu(v1, v2);
        v1.addCorridor(length, v2);
        v2.addCorridor(length, v1);
    }
    
    public int getSize() {
        int i = 1; // new(1) = [0]
        Iterator<Department> it = departments.iterator();

        while (it.hasNext()) {
            it.next();   // move to next node
            i++;
        }
        return i;
    }
    
    // ACCESSOR toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Department v : departments) {  // requires Iterator
            sb.append(v.getId()).append(": ").append( v.getName() ).append(": ");
            for ( Corridor corridor : v.getAdjList() ) {
                sb.append(corridor.getTarget().getId()).append("(").append(corridor.getLength()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
