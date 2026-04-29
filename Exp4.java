import java.util.Scanner;

public class Code {
    static int vertices;
    static int adj[][];
    static String nodes[];

    static int getIndex(String name) {
        for (int i = 0; i < vertices; i++) {
            if (nodes[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    static void addEdge(String src, String dest) {
        int s = getIndex(src);
        int d = getIndex(dest);
        adj[s][d] = 1;
        adj[d][s] = 1; 
    }

    static void printList(int arr[], int size) {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(nodes[arr[i]]);
            if (i != size - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    static void bfs(String startName, String endName) {
        boolean visited[] = new boolean[vertices];
        int queue[] = new int[vertices];
        int closed[] = new int[vertices];
        int front = 0, rear = 0, closedCount = 0;
        int start = getIndex(startName);
        int end = getIndex(endName);
        queue[rear++] = start;
        System.out.println("\nBFS Traversal:");
        System.out.printf("%-30s %-10s %s\n", "OPEN", "X", "CLOSED");
        System.out.println("--------------------------------------------------------------");

        while (front < rear) {
            int openSize = rear - front;
            int openList[] = new int[openSize];
            for (int i = 0; i < openSize; i++) {
                openList[i] = queue[front + i];
            }

            int x = queue[front++];  
            closed[closedCount++] = x;
            visited[x] = true;

            System.out.printf("%-30s %-10s ", nodesToString(openList, openSize), nodes[x]);
            printList(closed, closedCount);
            System.out.println();

            if (x == end) { 
      break; 
}

            for (int i = 0; i < vertices; i++) {
                if (adj[x][i] == 1 && !visited[i]) {
                    visited[i] = true; // mark visited when added to queue
                    queue[rear++] = i;
                }
            }
        }

        System.out.print("\nFinal Path: ");
        printList(closed, closedCount);
        System.out.println("\n");
    }

    static void dfs(String startName, String endName) {
        boolean visited[] = new boolean[vertices];
        int stack[] = new int[vertices];
        int closed[] = new int[vertices];
        int top = -1, closedCount = 0;

        int start = getIndex(startName);
        int end = getIndex(endName);

        stack[++top] = start;
        System.out.println("\nDFS Traversal:");
        System.out.printf("%-30s %-10s %s\n", "OPEN", "X", "CLOSED");
        System.out.println("--------------------------------------------------------------");

        while (top != -1) {
            int openSize = top + 1;
            int openList[] = new int[openSize];
            for (int i = 0; i < openSize; i++) {
                openList[i] = stack[i];
            }

            int x = stack[top--];  // pop X
            closed[closedCount++] = x;
            visited[x] = true;

            System.out.printf("%-30s %-10s ", nodesToString(openList, openSize), nodes[x]);
            printList(closed, closedCount);
            System.out.println();

            if (x == end) { break; }

            for (int i = vertices - 1; i >= 0; i--) {
                if (adj[x][i] == 1 && !visited[i]) {
                    visited[i] = true;  // mark visited when pushing to stack
                    stack[++top] = i;
                }
            }
        }

        System.out.print("\nFinal Path: ");
        printList(closed, closedCount);
        System.out.println("\n");
    }

    static String nodesToString(int arr[], int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(nodes[arr[i]]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        vertices = sc.nextInt();
        sc.nextLine();

        adj = new int[vertices][vertices];
        nodes = new String[vertices];

        System.out.println("Enter node names (A B C or 0 1 2 etc):");
        for (int i = 0; i < vertices; i++) {
            nodes[i] = sc.next();
        }

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter edges (source destination):");
        for (int i = 0; i < e; i++) {
            String s = sc.next();
            String d = sc.next();
            addEdge(s, d);
        }

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. BFS");
            System.out.println("2. DFS");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            if(choice == 3) {
                System.out.println("Exiting...");
                return;
            }
            System.out.print("Enter starting node: ");
            String start = sc.next();
            System.out.print("Enter ending node: ");
            String end = sc.next();

            if (choice == 1) {
                bfs(start, end);
            } else if (choice == 2) {
                dfs(start, end);
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}
