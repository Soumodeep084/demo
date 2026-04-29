import java.util.ArrayList;
import java.util.Scanner;

public class AStarAlgo {

    static int[][] goal = new int[3][3];
    static ArrayList<String> visited = new ArrayList<>();

    static String stateToString(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(state[i][j]);
            }
        }
        return sb.toString();
    }

    static int calculateH(int[][] state) {
        int h = 0;
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                if (state[i][j] != 0 && state[i][j] != goal[i][j])   h++; 
        return h;
    }

    static void printState(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) 
                System.out.print(state[i][j] + " "); 
            System.out.println();
        }
    }

    static int[][] copy(int[][] state) {
        int[][] newState = new int[3][3];
        for (int i = 0; i < 3; i++) 
            System.arraycopy(state[i], 0, newState[i], 0, 3);
        return newState;
    }

    static boolean isGoal(int[][] state) {
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                if (state[i][j] != goal[i][j])  return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] current = new int[3][3];

        System.out.print("Note: For Blank Tile Enter 0 \nEnter Initial State (3x3): ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                current[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter Goal State (3x3): ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                goal[i][j] = sc.nextInt();
            }
        }

        int g = 0;

        while (!isGoal(current)) {
            visited.add(stateToString(current));
            System.out.println("\nCurrent State (g=" + g + "):");
            printState(current);

            int blankRow = 0, blankCol = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (current[i][j] == 0) {
                        blankRow = i;
                        blankCol = j;
                    }
                }
            }

            int minF = Integer.MAX_VALUE;
            int minH = Integer.MAX_VALUE;
            int[][] bestState = null;
            String chosenMove = "";
            String[] moves = {"Up", "Down", "Left", "Right"};

            for (String move : moves) {
                int newRow = blankRow;
                int newCol = blankCol;
                if (move.equals("Up")) newRow--;
                if (move.equals("Down"))  newRow++;
                if (move.equals("Left"))  newCol--;
                if (move.equals("Right")) newCol++;

                if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                    int[][] temp = copy(current);
                    temp[blankRow][blankCol] = temp[newRow][newCol];
                    temp[newRow][newCol] = 0;

                    if (!visited.contains(stateToString(temp))) {
                        int h = calculateH(temp);
                        int newG = g + 1;
                        int f = newG + h;
                        System.out.println("Move: " + move + " -> g(n)=" + newG + " h(n)=" + h + " f(n)=" + f);
                        if (f < minF || (f == minF && h < minH)) {
                            minF = f;
                            minH = h;
                            bestState = temp;
                            chosenMove = move;
                        }
                    }
                }
            }
            if (bestState == null) {
                System.out.println("\nStuck! No valid unvisited moves available.");
                break;
            }
            System.out.println("\nChosen Move: " + chosenMove + " with f(n) = " + minF + " (h(n) = " + minH + ")");
            System.out.println("----------------------------------");
            current = bestState;
            g++;
        }

        if (isGoal(current)) {
            System.out.println("\nGoal State Reached!");
            printState(current);
        }
        sc.close();
    }
}
