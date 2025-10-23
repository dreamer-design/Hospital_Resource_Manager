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
     * A* shortest path src, dest
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
        // maybe check a path exists first
        if( bfs(g, start, dest) == false ) return null;
        
        QueuePriority<Department> openSet = new QueuePriority( );

        float gn = 0; // cot to nest node
        float fn = 0; // total cost
//        float fn = gn + hu(start, dest); // f(n) total estimated cost start-finish
//        hn = hu(d, dest); // disance to target
                
        // start at the root node
        openSet.enqueue(start);

        // goto the best next node until you reach the target or dead end
        while( !openSet.isEmpty() && openSet.peek() != null) {
            System.out.println("*");
            // helper vars
            Department prev = openSet.dequeue();
            Department bestD = null;
            float bestV = 9999999;

            // expand current node and get the best path
            for( Department d : prev.getDepAdjList() ) {
                if( d == dest ); // ??this is the end??
                // get cost of adjacent node: f(n) = g(n) + h(n)
                float cost = gn + prev.getAdjCorridorLength(d) + hu(d, dest);
                
                if( cost < bestV) {
                    bestV = cost;
                    bestD = d;
                    
                    // ??placement of this so that it returns when found??
                    if( hu(bestD, dest) == 0 ) return fn;  // found return

                }
            }
            
            // is not dead end add to fn
            if( bestV != 9999999 && bestD != null ) {
                openSet.enqueue(bestD);
                fn += bestV;  // ??this should be moved to near the exit condition??
            }
        }

        return null;
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
    private static float hu(Department a, Department b) {
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
            System.out.println(Q);
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
        t.addCorridor(0, 6, 7);
        t.addCorridor(0, 11, 1);
        t.addCorridor(0, 20, 3);
        t.addCorridor(6, 24, 4);
        t.addCorridor(6, 76, 8);
        t.addCorridor(20, 22, 2);
        t.addCorridor(22, 33, 1);
        t.addCorridor(41, 50, 2);
        t.addCorridor(41, 52, 2);
        t.addCorridor(50, 70, 3);
        t.addCorridor(52, 54, 2);
        t.addCorridor(54, 72, 4);
        t.addCorridor(54, 74, 2);
        t.addCorridor(74, 76, 2);
        // crash if id does not exist
        
        if( Search.bfs(t, t.getDepartment(0), t.getDepartment(70)) ) 
            System.out.println("bfs, yes");
        else
            System.out.println("bfs, no");
        
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
        
        float r = Search.a_star(t, t.getDepartment(0), t.getDepartment(6));
        System.out.println(r);
        
        
        
    }
}
