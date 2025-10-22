package dsa.structs;

import dsa.data.Corridor;
import dsa.data.Department;

/** a DSAGraph class using linked lists to store the list of nodes 
 * Implement a Graph class using an adjacency list.
 *  Support dynamic insertion of departments (nodes) and corridors (weighted edges)
 * 
 */
public class Graph {
    LinkedList<Department> Departments; // departments (nodes)

    public Graph() {
        Departments = new LinkedList<>();
    }

    /**
     * get the Departments List
     * @return Department Linked List Reference
     */
    public LinkedList<Department> getDepartments() {
        return Departments;
    }

    /**
     * Add NODE||VERTEX
     * @param id
     * @param name 
     */
    public void addDepartment(int id, String name) {
        Departments.insertLast(new Department(id, name));
    }

    /**
     * Add NODE||VERTEX
     * @param id
     * @param name 
     * @param x 
     * @param y 
     */
    public void addDepartment(int id, String name, int x, int y) {
        Departments.insertLast(new Department(id, name, x, y));
    }

    /**
     * Get NODE||VERTEX
     * @param id
     * @return department
     */
    public Department getDepartment(int id) {
        for (var dep : Departments) {
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
    // ACCESSOR toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Department v : Departments) {  // requires Iterator
            sb.append(v.getId()).append(": ").append( v.getName() ).append(": ");
            for ( Corridor corridor : v.getAdjList() ) {
                sb.append(corridor.getTarget().getId()).append("(").append(corridor.getLength()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
