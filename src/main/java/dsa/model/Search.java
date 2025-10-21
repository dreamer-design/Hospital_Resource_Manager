package dsa.model;

import dsa.data.Department;
import dsa.structs.Graph;
import dsa.structs.LinkedList;
import dsa.structs.Queue;

/**
 *
 */
public class Search {
    
    
    
        /**
     * A* shortest path src, dest
     * @param src
     * @param dest
     */
    public static int ShortestPath(Department src, Department dest) {
        
        return 0;
    }
    
    /**
     * helper get max n levels to set size of bfs array.
     */
    public int nLevels(Graph g) {
        return 0;
    }
    
        /**
     * Breadth-First Search (BFS): 
     * Input = source department; 
     * Output = reachable departments grouped by level (hops).
     * @param src
     * @param dest
     */
    public static int bfs(Department src) {
        LinkedList<Queue> levels = new LinkedList<>(); // list of list to hold the levels
        Queue level = new Queue(); // working memory
        
        // get adjacency list for current corridor
        LinkedList<Department> current = src.getAdjList(); // 
        
        src.setVisited(); // set root as visited
        
        for( Department d : current) {
            level.enqueue(d); // add all adjacent nodes to a queue
            d.setVisited(); // been here
        }
        
        levels.insertLast(level); // add current level
        
        // recursively call?
        
//        Graph start = DataModel.getGraphInstance(); // do i nee this to chek for visited??
        return 0;
    }
    
//Declare T = DSAQueue and Q = DSAQueue
//Iterate through your vertices list and clear visited
//Reference a vertex from your vertices list as v
//Set v as visited
//Enqueue v into Q
//while Q is not empty
//v = Q.dequeue()
//for each vertex w in v's adjacency list that is unvisited
//T.enqueue(v)
//T.enqueue(w)
//Set w as visited
//Enqueue w into Q
    
}
