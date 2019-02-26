public class KnightBoard{
  int[][] board;
  int[] moves = {1, 2, 2, 1, 2, -1, 1, -2, -1, -2, -2, -1, -2, 1, -1, 2};
  int[][] numMoves;
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols) {
      board = new int[startingRows][startingCols];
      numMoves = new int[startingRows][startingCols];
      for (int r = 0; r < numMoves.length; r++) {
        for ( int c = 0; c < numMoves[r].length; c++) {
          if ((r == 0 || r == numMoves.length - 1) && (c == 0 || c == numMoves[r].length - 1)) numMoves[r][c] = 2;
          if (((r == 0 || r == numMoves.length - 1) && (c == 1 || c == numMoves[r].length - 2)) ||
             ((c == 0 || c == numMoves[r].length - 1) && (r == 1 || r == numMoves.length - 2))) numMoves[r][c] = 3;
          if (((r == 0 || r == numMoves.length - 1) && (c > 1 && c < numMoves[r].length - 2)) ||
             ((c == 0 || c == numMoves[r].length - 1) && (r > 1 && r < numMoves.length - 2)) ||
             ((r == 1 || r == numMoves.length - 2) && (c == 1 || c == numMoves[r].length - 2))) numMoves[r][c] = 4;
          if (((r == 1 || r == numMoves.length - 2) && (c > 1 && c < numMoves[r].length - 2)) ||
             ((c == 1 || c == numMoves[r].length - 2) && (r > 1 && r < numMoves.length - 2))) numMoves[r][c] = 6;
          if ((r > 1 && r < numMoves.length - 2 && c > 1 && c < numMoves[r].length - 2)) numMoves[r][c] = 8;
        }
      }
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
          if (c != 0) throw new IllegalStateException("Board is not empty");
        }
      }
    } catch (IllegalStateException e) {
      e.printStackTrace();
      return false;
    }
    return solveH(startingRow, startingCol, 1);
  }
  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  // or out of bounds.
  public int countSolutions(int startingRow, int startingCol) {return 0;}
  private boolean solveH(int row ,int col, int level) {
    System.out.println(toString());
    if (level == board.length * board[0].length + 1) return true;
    if (level < board.length * board[0].length + 1) {
      if (board[row][col] == 0) {
        board[row][col] = level;
        for (int i = 0; i < moves.length; i+=2) {
          if (row + moves[i] >= 0 && row + moves[i] < board.length && col + moves[i+1] >= 0 && col + moves[i+1] < board[row].length) {
            if (solveH(row + moves[i], col + moves[i+1], level + 1)) {
              return true;
            }
          }
        }
        board[row][col] = 0;
      }
      }
    return false;
  }
  //&& board[row + moves[i]][col + moves[i+1]] == 0
  // level is the # of the knight
}
class Point {
  int possibleMoves;
  int[] position = new int[2];
  public Point()
}
