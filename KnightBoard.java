public class KnightBoard{
  int[][] board;
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols) {
      board = new int[startingRows][startingCols];
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
    return solveH(startingRow, startingCol, 1);
  }
  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  // or out of bounds.
  public int countSolutions(int startingRow, int startingCol) {return 0;}
  private boolean solveH(int row ,int col, int level) {
    //return true;
    if (level == board.length * board[0].length) return true;
    if (level < board.length * board[0].length) {
      if (row < board.length && row >= 0 && col < board[row].length && col >= 0) {
        if (board[row][col] == 0) {
        board[row][col] = level;
        // for (int r = -2; r < 3; r++) {
        //   for (int c = -2; c < 3; c++) {
        //     if (r != 0 && c != 0 && solveH(row + r, col + c, level + 1)) {
        //       return true;
        //     }
        //     board[row][col] = 0;
        //   }
        // }
        // return solveH(row-1, col-2, level+1) || solveH(row-2, col-1, level+1) || solveH(row-2, col+1, level+1) || solveH(row-1, col+2, level+1) ||
        //        solveH(row+1, col+2, level+1) || solveH(row+2, col+1, level+1) || solveH(row+2, col-1, level+1) || solveH(row+1, col-2, level+1);
        }
      }
    }
    return false;
  }
  // level is the # of the knight
}
