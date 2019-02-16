public class Driver{
  public static void main(String[] args) {
    KnightBoard test = new KnightBoard(8, 8);
    // int num = 0;
    // for (int r = 0; r < test.board.length; r++) {
    //   for (int c = 0; c < test.board[r].length; c++) {
    //     test.board[r][c] = num++;
    //   }
    // }
    test.solve(1,0);
    System.out.println(test.toString());
  }
}
