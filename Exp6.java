import java.util.*;
public class TicTacToe {
    static char[] board = new char[9];
    static int[][] wins = { 
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows 
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6}             // Diagonals
    };
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        System.out.println("Positions: 0-8");
        printBoard();
        while (true) {
            System.out.print("Your move (0-8): ");
            int move = sc.nextInt();
            if (move < 0 || move > 8 || board[move] != ' ') {
                System.out.println("Invalid move! Try again.");
                continue;
            }
            board[move] = 'X';
            printBoard();
            if (checkWinner('X')) {
                System.out.println("User Wins!"); break;
            }
            if (isDraw()) {
                System.out.println("Draw Game!"); break;
            }
            int compMove = getComputerMove();
            board[compMove] = 'O';
            System.out.println("Computer chooses: " + compMove);
            printBoard();
            if (checkWinner('O')) {
                System.out.println("Computer Wins!"); break;
            }
            if (isDraw()) {
                System.out.println("Draw Game!");  break;
            }
        }
    }
    static void printBoard() {
        System.out.printf("\n %c | %c | %c \n---+---+---\n %c | %c | %c \n---+---+---\n %c | %c | %c \n\n",
                board[0], board[1], board[2],
                board[3], board[4], board[5],
                board[6], board[7], board[8]);
    }
    static boolean checkWinner(char player) {
        for (int[] w : wins) 
            if (board[w[0]] == player && board[w[1]] == player && board[w[2]] == player) 
                return true;
        return false;
    }
    static boolean isDraw() {
        for (char c : board) 
            if (c == ' ') return false;
        return true;
    }
    static int getComputerMove() {
        int winMove = findWinningMove('O');
        if (winMove != -1) return winMove;
        int blockMove = findWinningMove('X');
        if (blockMove != -1) return blockMove;
       
        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < 9; i++) 
            if (board[i] == ' ') available.add(i);
        return available.get(random.nextInt(available.size()));
    }

    static int findWinningMove(char player) {
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = player; 
                boolean isWin = checkWinner(player);
                board[i] = ' '; 
                if (isWin) return i;
            }
        }
        return -1;
    }
}
