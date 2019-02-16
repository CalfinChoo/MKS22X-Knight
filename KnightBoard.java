public class KnightBoard{
  int[][] board;
  int[] moves = {1, 2, 2, 1, 2, -1, 1, -2, -1, -2, -2, -1, -2, 1, -1, 2};
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
    // try{
    //   for (int[] r : board) {
    //     for (int c : r) {
    //       if (board[r][c] != 0) throw new IllegalStateException();
    //     }
    //   }
    // }
    return solveH(startingRow, startingCol, 1);
  }
  //@throws IllegalStateException when the board contains non-zero values.
  //@throws IllegalArgumentException when either parameter is negative
  // or out of bounds.
  public int countSolutions(int startingRow, int startingCol) {return 0;}
  private boolean solveH(int row ,int col, int level) {
    //System.out.println(toString());
    if (level == board.length * board[0].length + 1) return true;
    if (row >= board.length || row < 0 || col >= board[row].length || col < 0) return false;
    if (level < board.length * board[0].length + 1) {
      if (board[row][col] == 0) {
        board[row][col] = level;
        for (int i = 0; i + 1 < moves.length; i+=2) {
          if (solveH(row + moves[i], col + moves[i+1], level + 1)) {
            return true;
          }
        }
        board[row][col] = 0;
      }
      }
    return false;
  }
  // level is the # of the knight
}
