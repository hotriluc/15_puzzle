package defPackage;
import java.util.PriorityQueue;
import java.util.Stack;
 
class Solver{
    private int N ; 
    private int minMoves ;
    public static int[] correctRow;
    public static int[] correctCol;
 
    private class Node implements Comparable<Node>{
        private Board board ; 
        private int moves ; 
        private Node prevNode ; 
        public Node(Board board,int moves,Node prev){
            this.board = board ; 
            this.moves = moves ; 
            this.prevNode = prev ; 
        }
        public int compareTo(Node that){
            int thisPriority = this.moves+this.board.manhattan() ; 
            int thatPriority = that.moves+that.board.manhattan() ; 
            if(thisPriority<thatPriority){
                return -1 ; 
            }else if(thisPriority>thatPriority){
                return 1 ; 
            }else{
                return 0 ; 
            }
        }
    }
 
    private Node lastNode ; 
    private boolean solvable ; 
 
    public Solver(Board initial){
        N = initial.dimension() ; 
        PriorityQueue<Node> pq = new PriorityQueue<Node>() ; 
        PriorityQueue<Node> pq2 = new PriorityQueue<Node>() ; 
        pq.add(new Node(initial,0,null)) ; 
        pq2.add(new Node(initial.twin(),0,null)) ; 
        while(true){
            Node removed = pq.poll();
            Node removed2 = pq2.poll();
            if(removed.board.isGoal()){
                minMoves = removed.moves ; 
                lastNode = removed ; 
                solvable = true ; 
                break ; 
            }
            if(removed2.board.isGoal()){
                minMoves = -1 ; 
                solvable = false ; 
                break ; 
            }
 
            Iterable<Board> neighbors = removed.board.neighbors() ; 
            Iterable<Board> neighbors2 = removed2.board.neighbors() ; 
            for(Board board : neighbors){
                if(removed.prevNode != null && removed.prevNode.board.equals(board) ){
                    continue ; 
                }
                pq.add(new Node(board,removed.moves+1,removed)) ; 
            }
            for(Board board : neighbors2){
                if(removed2.prevNode != null && removed2.prevNode.board.equals(board) ){
                    continue ; 
                }
                pq2.add(new Node(board,removed2.moves+1,removed2)) ; 
            }
        }
    }
 
    public boolean isSolvable(){
        return solvable ; 
    }
 
    public int moves(){
        return minMoves ; 
    }
 
    public Iterable<Board> solution(){
        if(!isSolvable()){
            return null ;
        }
        Stack<Board> stack = new Stack<Board>() ; 
        Node node = lastNode ; 
        while(true){
            if(node == null) break ; 
            Board board = node.board ; 
            node = node.prevNode ; 
            stack.push(board) ; 
        }
        return stack ; 
    }
 
    static void initCorrectRowsCols(int N){
    	correctRow = new int[N*N] ; 
        int z = 0 ; 
        for(int i = 0 ; i < N ; i++ ){
            for(int j = 0 ; j < N ; j++ ){
                correctRow[z++] = i ; 
            }
        }
        z = 0 ; 
        correctCol = new int[N*N] ; 
        for(int i = 0 ; i < N ; i++ ){
            for(int j = 0 ; j < N ; j++ ){
                correctCol[z++] = j ; 
            }
        }
    }
    
    public static void Solve(int[][] brd,int ns){
    	initCorrectRowsCols(ns);
        Board initial = new Board(brd);
        Solver solver = new Solver(initial);
        if (!solver.isSolvable())
        	Puzzle_Game.lblStatus.setText("No solution possible");
        else {
            Puzzle_Game.lblStatus.setText("Minimum number of moves: " + solver.moves());
            Stack<Board> stack = new Stack<Board>();
            Board b=null;
            for (Board board : solver.solution()){
            	b=stack.push(board);
                Puzzle_Game.steps.add(b.getStep());
            }
            while(!stack.isEmpty()){
            	System.out.println(stack.pop());
            }
        }
    }
}
 
