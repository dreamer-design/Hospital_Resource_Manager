package dsa.structs;

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
     * Adds EDGE between NODE||VERTEX undirected
     * @param id1
     * @param id1 
     */
    public void addCorridore(int id1, int id2) {
        Department v1 = getDepartment(id1);
        Department v2 = getDepartment(id2);
        v1.addEdge(v2);
        v2.addEdge(v1);
    }
    
    // ACCESSOR toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Department v : Departments) {  // requires Iterator
            sb.append(v.getId()).append(": ").append( v.getName() ).append(": ");
            for ( Object o : v.getAdjList() ) {
                Department adjVertex = (Department) o;
                sb.append(adjVertex.getId()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
