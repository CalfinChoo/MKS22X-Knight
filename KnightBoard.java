import java.util.ArrayList;
import java.util.Collections;
public class KnightBoard{
  int[][] board;
  int[][] optBoard;
  int[][] moves = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols) {
      board = new int[startingRows][startingCols];      //initializes board
      optBoard = new int[startingRows][startingCols];   //initializes optimized board
      fillOptBoard();                                   //fills in optimized board
  }
  public void fillOptBoard() { //fills spaces with values depending on how many moves can be made from that space
    for (int r = 0; r < optBoard.length; r++) {
      for (int c = 0; c < optBoard[r].length; c++) {
        int count = 0;
        for (int[] m : moves) {
          if (r + m[0] >= 0 && r + m[0] < optBoard.length && c + m[1] >= 0 && c + m[1] < optBoard[0].length) count++;
        }
        optBoard[r][c] = count;
      }
    }
  }
  public String printOptBoard() {
    String s = "";
    for (int[] r : optBoard) {
      for (int c : r) {
        s += c;
      }
      s += "\n";
    }
    return s;
  }
  public String toString() {
    String s = "";
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        if (board[r][c] == 0) s += " _ ";
        else {
          if (board[r][c] / 10 > 0) s += board[r][c] + " ";
          else s += " " + board[r][c] + " ";
        }
      }
      s += "\n";
    }
    return s;
  }
  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  // or out of bounds.
  public boolean solve(int startingRow, int startingCol) {
    try{
      for (int[] r : board) {
        for (int c : r) {
          if (c != 0) throw new IllegalStateException("Board is not empty");    //checks if board is empty; if not, throws exception
        }
      }
    } catch (IllegalStateException e) {
      e.printStackTrace();
      return false;
    }
    return optSolveH(startingRow, startingCol, 1);  //calls helper
  }
  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  // or out of bounds.
  public int countSolutions(int startingRow, int startingCol) {
    try{
      for (int[] r : board) {
        for (int c : r) {
          if (c != 0) throw new IllegalStateException("Board is not empty");    //checks if board is empty; if not, throws exception
        }
      }
    } catch (IllegalStateException e) {
      e.printStackTrace();
    }
    board[startingRow][startingCol] = 1;
    return countH(startingRow, startingCol, 2, 0);  //calls helper
  }
  private int countH(int row, int col, int level, int sol) {
    if (level == board.length * board[0].length + 1) sol++;
    for (int i = 0; i < moves.length; i++) {
      int r = row + moves[i][0];
      int c = col + moves[i][1];
      if (addKnight(r, c, level)) {  //attempts to add knight
        int solUpdate = countH(r, c, level + 1, sol);  //checks for a solution, updates the solution counter, continues
        if (solUpdate > sol) sol = solUpdate;
        removeKnight(r, c);
      }
    }
    return sol;  //if no solutions are found, return num solutions
  }
  private boolean addKnight(int row, int col, int level) {  // attempts to add a knight to a position
    if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return false;
    if (board[row][col] != 0) return false;
    board[row][col] = level;
    return true;
  }
  private boolean removeKnight(int row, int col){  // attempts to remove a knight from a position
       if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return false;
       if (board[row][col] == 0) return false;
       board[row][col] = 0;
       return true;
   }
  private boolean solveH(int row ,int col, int level) {  //helper function of solveH (not optimized):
    //System.out.println(toString());
    if (level > board.length * board[0].length) return true; //base case
    if (addKnight(row, col, level)) { //attempts to add a knight to position
      for (int i = 0; i < moves.length; i++) { //loops thru possible moves
        int r = row + moves[i][0];
        int c = col + moves[i][1];
        if (r >= 0 && r < board.length && c >= 0 && c < board[row].length && solveH(r, c, level + 1)) { //returns true if solution is found
          return true;
        }
      }
      removeKnight(row, col); //removes knight if goes down a dead-end branch (backtracks)
    }
    return false; //returns false if no solution is found
  }
  private boolean optSolveH(int row, int col, int level) { //helper function of solveH (optimized) :
    //System.out.println(toString());
    board[row][col] = level;
    if (level == board.length * board[0].length) return true; //base case
    ArrayList<Coordinate> possMoves = new ArrayList<Coordinate>();
    for (int i = 0; i < moves.length; i++) { //loops thru possible moves and adds them to a list
      int r = row + moves[i][0];
      int c = col + moves[i][1];
      if (r >= 0 && r < board.length && c >= 0 && c < board[r].length && board[r][c] == 0) {
        possMoves.add(new Coordinate(r, c, optBoard[r][c]));
      }
    }
    Collections.sort(possMoves);  //sorts list based on Coordinate class compareTo
    for (Coordinate i : possMoves) {  //decrements from value in account for the knight's current position
      optBoard[i.getR()][i.getC()]--;
    }
    for (Coordinate i : possMoves) {  //stores value before changing it and calling recursive function; if it backtracks the value can be retrieved
      int prev = optBoard[i.getR()][i.getC()]--;
      optBoard[i.getR()][i.getC()] = 0;
      if (optSolveH(i.getR(), i.getC(), level + 1)) {  //returns true if solution is found
        return true;
      }
      optBoard[i.getR()][i.getC()] = prev;
    }
    board[row][col] = 0;  //returns false if no solution is found
    return false;
  }
}
class Coordinate implements Comparable<Coordinate> { //subclass for a space on the board containing the value
  int value;  //value = number of possible moves from this space
  int r;
  int c;
  public Coordinate(int row, int col, int val) {
    r = row;
    c = col;
    value = val;
  }
  public int compareTo(Coordinate c) {
    return value - c.value;
  }
  public int getR() {return r;}
  public int getC() {return c;}
}
