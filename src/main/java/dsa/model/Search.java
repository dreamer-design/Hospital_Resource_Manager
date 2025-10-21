package dsa.model;

import dsa.data.Department;
import dsa.structs.Graph;
import dsa.structs.LinkedList;
import dsa.structs.Queue;

/**
 *
 */
public class Search {
    
    public static LinkedList<Queue> levels = new LinkedList<>(); // each level?

    
        /**
     * A* shortest path src, dest
     * @param src
     * @param dest
     */
    public static int ShortestPath(Department src, Department dest) {
        
        return 0;
    }
    
        /**
     * Breadth-First Search (BFS): 
     * Input = source department; 
     * Output = reachable departments grouped by level (hops).
     * @param src
     * @param dest
     */
    public static Boolean bfs(Graph g, Department src, Department dest) {        
        Queue T = new Queue();  // traversed
        Queue Q = new Queue(); // working memory    // (Q) frontier
        
        // get adjacency list for current corridor
        LinkedList<Department> current = src.getAdjList(); //
        
        // 1. Clear visited flags for all vertices
//        var verticesList = DataModel.getGraphInstance().getDepartments();  // im going to start using var for references
        var verticesList = g.getDepartments();  // im going to start using var for references
        for (Department v : verticesList) {
            v.setVisited();
        }
        
        // 2. set startng vertice 
        var start = src;
        var v = start; // ref pseudo
        v.setVisited(); // set root as visited
        
        // enque v into Q
        Q.enqueue(v);
        
        while( !Q.isEmpty() ) {
            // var set to object   // change once ive go it down
            v = Q.dequeue(); // dqueue the next node
            
            // then go through list an find unvisited
            // pseudo: for each vertex w in v's adjacency list that is 
            for( Department w : v.getAdjList() ) {
                if( !w.getVisited() ) { // unvisited
                    T.enqueue(w); // add  to traversed
                    w.setVisited(); // set visited
                    Q.enqueue(w); // add to working memory
                }
                if( w == dest) return true;
            }
            levels.insertLast(Q); // should be a level
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        Graph t = new Graph();
        t.addDepartment(1, "Surgery");
        t.addDepartment(2, "Ward");
        t.addDepartment(3, "Waiting");
        t.addDepartment(4, "Emergency");
        t.addDepartment(5, "Administration");
        t.addDepartment(6, "Smoko");
        t.addCorridore(1, 2);
        t.addCorridore(2, 3);
        t.addCorridore(4, 2);
        t.addCorridore(1, 3);
        t.addCorridore(6, 1);
        t.addCorridore(5, 2);
        t.addCorridore(6, 2);
        
        Search.bfs(t, t.getDepartment(1), t.getDepartment(5));
    }
}
