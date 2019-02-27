public class KnightBoard{
  int[][] board;
  int[][] moves = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
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
  private boolean addKnight(int row, int col, int level) {
    if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return false;
    if (board[row][col] != 0) return false;
    board[row][col] = level;
    return true;
  }
  private boolean removeKnight(int row, int col){
       if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) return false;
       if (board[row][col] == 0) return false;
       board[row][col] = 0;
       return true;
   }
  private boolean solveH(int row ,int col, int level) {
    //System.out.println(toString());
    if (level > board.length * board[0].length) return true;
    if (addKnight(row, col, level)) {
      for (int i = 0; i < moves.length; i++) {
        int r = row + moves[i][0];
        int c = col + moves[i][1];
        if (r >= 0 && r < board.length && c >= 0 && c < board[row].length && solveH(r, c, level + 1)) {
          return true;
        }
      }
      removeKnight(row, col);
    }
    return false;
  }
}
