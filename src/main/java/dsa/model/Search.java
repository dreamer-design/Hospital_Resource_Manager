package dsa.model;

import dsa.data.Corridor;
import dsa.data.Department;
import dsa.structs.Graph;
import dsa.structs.LinkedList;
import dsa.structs.Queue;
import dsa.structs.QueuePriority;
import dsa.structs.Stack;

/**
 *
 */
public class Search {
    
    public static LinkedList<Queue> levels = new LinkedList<>(); // each level?

    
        /**
     * A* shortest path src, dest, without mapping version)
     * g(n) cost to reach the node
     * h(n) heuristic estimate
     * f(n) total estimated cost
     * use a heap to store/retrieve nodes based o their f(n) values
     * @param src
     * @param dest
     * @return shortest path value
     */
//    public static int ShortestPath(Department src, Department dest) {
    public static Float a_star(Graph g, Department start, Department dest) {
        if( bfs(g, start, dest) == false ) return null; // optional
        
        // initialize all nodes
        for (Department d : g.getDepartments()) {
            d.setVisited(false);
            d.setCameFrom(null);
            d.setG(Float.POSITIVE_INFINITY);
            d.setF(Float.POSITIVE_INFINITY);
        }
        
        // main vars
        QueuePriority<Department> openSet = new QueuePriority( );
        start.setG(0);
        start.setF( hu(start, dest) ); // heuristic only
        
        // start at the root node
        openSet.enqueue(start);
            
        // goto the best next node until you reach the target
        while( !openSet.isEmpty() && openSet.peek() != null) {
            System.out.println("*");
            
            Department current = openSet.dequeue();
            if (current == dest) {
                System.out.println( reconPath(dest) ); // reconstruct path output
                return current.getG();
            } // found return distance travelled
            current.setVisited(true);
            
            // expand current node and get the best path(s)
            // since using a (priority queue) and it checks neighbours. 
            for( Department neighbor :  current.getDepAdjList()) {
                if ( neighbor.getVisited() ) continue;

                float tentativeG = current.getG() + current.getAdjCorridorLength(neighbor); // start -> current -> neighbor
                
                System.out.printf("dep: %s, l: %.0f, cg: %.0f, tG: %.0f\n", neighbor.getId(), current.getAdjCorridorLength(neighbor), current.getG(), tentativeG);
                if (tentativeG < neighbor.getG()) {                 // path to neighbor is cheaper than any path before
                    neighbor.setCameFrom(current);                  // for path recon
                    neighbor.setG(tentativeG);                      // UPDATE: best known: travelled + this nodes length
                    neighbor.setF(tentativeG + hu(neighbor, dest)); // used by compareTo. total cost this node to end
                    openSet.enqueue(neighbor);
                }
            }
            
        System.out.printf("G: %.0f\n", current.getG() ); // tally
        }
        return null; // not found
    }

    private static LinkedList<Department> reconPath(Department dest) {
        // path recon
        LinkedList<Department> path = new LinkedList<>();
        Department current = dest;

        while (current != null) {
            path.insertFirst(current);
            current = current.getCameFrom();
        }
        return path;
    }

    /**
     * the heuristic is just the hypotenuse to the dest 
     * as the crow flies
     * euclidean
     * bee line
     * @param a
     * @param b
     * @return float bird
     */
    public static float hu(Department a, Department b) {
        int[] p1 = a.getLoc();
        int[] p2 = b.getLoc();
        return (float) Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
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
        
        // 1. Clear visited flags for all vertices
//        var verticesList = DataModel.getGraphInstance().getDepartments();  // im going to start using var for references
        var verticesList = g.getDepartments();  // im going to start using var for references
        for (Department v : verticesList) v.clearVisited();
        
        // 2. set startng vertice 
//        var start = src;
        var v = src; // ref pseudo
        v.setVisited(); // set root as visited
        
        // enque v into Q
        Q.enqueue(v);
        
        while( !Q.isEmpty() ) {
            // var set to object   // change once ive go it down
            v = Q.dequeue(); // dqueue the next node
            
            // then go through list an find unvisited
            // pseudo: for each vertex w in v's adjacency list that is 
            for( Corridor corridor : v.getAdjList() ) {
                Department w = corridor.getTarget();
                if( !w.getVisited() ) { // unvisited
                    T.enqueue(v); // why??
                    T.enqueue(w); // add  to traversed
                    w.setVisited(); // set visited
                    Q.enqueue(w); // add to working memory
                }
                if( w == dest) return true;
            }
            levels.insertLast(Q); // should be a level
            System.out.print(Q);
        }
        
        return false;
    }
    
    public static Boolean dfs(Graph g, Department src, Department dest) {
        //Declare T = DSAQueue and S = DSAStack
        Queue T = new Queue();
        Stack S = new Stack();
        
        //Iterate through your vertices list and clear visited
        var verticesList = g.getDepartments();  // im going to start using var for references
        for (Department v : verticesList) v.clearVisited();
        
        //Reference a vertex from your vertices list as v
        // 2. set startng vertice 
        var v = src; // ref pseudo
        v.setVisited(); // set root as visited  
        
        //Push v onto S
        S.push(v);

        //while S is not empty
        while( !S.isEmpty() ) {
            //while there is an unvisited vertex w in v's adjacency list
            var a = v.getAdjList();
            for(Corridor corridor: a) {
                Department d = corridor.getTarget();
                if( d == dest) return true; // found
                if( !d.getVisited() ) {
                    var w = d;
                    T.enqueue(v);
                    T.enqueue(w);
                    w.setVisited();
                    S.push(w);
                    v = w; 
                }
            }
        v = S.pop();
        }
        return false;
    }
    
    public static void main(String[] args) {
        Graph t = new Graph();
        // Department nodes:
        t.addDepartment(0, "Node1", 0, 0);
        t.addDepartment(6, "Node2", 6, 0);
        t.addDepartment(11, "Node6", 1, 1);
        t.addDepartment(20, "Node4", 0, 2);
        t.addDepartment(22, "Node5", 2, 2);
        t.addDepartment(24, "Node8", 4, 2);
        t.addDepartment(33, "Node7", 3, 3);
        t.addDepartment(41, "Node13", 1, 4);
        t.addDepartment(50, "Node14", 0, 5);
        t.addDepartment(52, "Node12", 2, 5);
        t.addDepartment(54, "Node10", 4, 5);
        t.addDepartment(70, "Node15", 0, 7);
        t.addDepartment(72, "Node11", 2, 7);
        t.addDepartment(74, "Node9", 4, 7);
        t.addDepartment(76, "Node3", 6, 7);

        // Corridors (edges):
        t.addCorridor(0, 6);
        t.addCorridor(0, 11);
        t.addCorridor(0, 20);
        t.addCorridor(6, 24);
        t.addCorridor(6, 76);
        t.addCorridor(20, 22);
        t.addCorridor(22, 33);
        t.addCorridor(33, 41); // link
        t.addCorridor(41, 50);
        t.addCorridor(41, 52);
        t.addCorridor(50, 70);
        t.addCorridor(52, 54);
        t.addCorridor(54, 72);
        t.addCorridor(54, 74);
        t.addCorridor(74, 76);
        // crash if id does not exist
        
//        if( Search.bfs(t, t.getDepartment(0), t.getDepartment(70)) ) 
//            System.out.println("bfs, yes");
//        else
//            System.out.println("bfs, no");
        
//        System.out.println( Search.levels.toString() );
        
//        int i = 0;
//        for( Queue d : Search.levels ) {
//            System.out.println(i++);
//            System.out.println( d );
//        }

        if( Search.dfs(t, t.getDepartment(0), t.getDepartment(70)) ) 
            System.out.println("dfs, yes");
        else
            System.out.println("dfs, no");
        
//        float r = Search.a_star(t, t.getDepartment(0), t.getDepartment(6));
        float r = Search.a_star(t, t.getDepartment(0), t.getDepartment(50));
        System.out.println(r);
        
    }
}
