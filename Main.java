public class Main {
    public static void main(String args[]) {
        boolean [][] grid = { 
            { false, false, false, false, false, false, false, false, false, false },
            { false, false, false, true, true, false, false, false, false, false },
            { false, false, false, false, true, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false },
            { false, false, false, true, true, false, false, false, false, false },
            { false, false, true, true, false, false, false, false, false, false },
            { false, false, false, false, false, true, false, false, false, false },
            { false, false, false, false, true, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false }
        };
        int xLength = grid.length;
        int yLength = grid[0].length;

        print("Conway's game:");
        print("X length: " + xLength);
        print("Y length: " + yLength);

        print("------------Input-------------");
        printGrid(grid, xLength, yLength);
        print("------------------------------");
        runGame(10, grid, xLength, yLength);
        print("End");
    }

    private static void runGame(int itterations, boolean[][] grid, int xLength, int yLength) {
        for (int i = 0; i < itterations; i++) {
            grid = createNextItteration(grid, xLength, yLength);
            printGrid(grid, xLength, yLength);
            print("------------------------------");
        }
    }

    private static boolean[][] createNextItteration(boolean[][] grid, int xLength, int yLength) {
        boolean[][] next = new boolean[xLength][yLength];

        // To be able to check the states of the surrounding neighbours
        for (int x = 1; x < xLength - 1; x++) {
            for (int y = 1; y < yLength - 1; y++) {
                // Determine amount of neighbours
                int count = determineAliveNeighours(grid, x, y);
                //print("Amount of alive neighbours: " + count);

                // Apply rules
                boolean state = determineState(grid[x][y], count);
                //print("This cell state: " + state);

                next[x][y] = state;
            }
        }

        //return grid; // TODO: return new grid
        return next;
    }

    private static int determineAliveNeighours(boolean[][] grid, int x, int y) {
        int checkCount = 0;

        int alive = 0;

        // Check surrounding ( 9 checks - self )
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (grid[i][j] && !(i == x && j == y)) {
                    alive++;
                }

                checkCount++;
            }
        }

        //print("check count: " + checkCount);

        return alive;
    }

    private static boolean determineState(boolean alive, int aliveNeighbours) {
        // Live cell, 2 OR 3 live neighbours survive
        // Dead cell, 3 life neighbours reanimate
        // All other live cells die, dead cells stay dead
        if (alive && (aliveNeighbours == 2 || aliveNeighbours == 3)) {
            return true;
        } else if (!alive && aliveNeighbours == 3) {
            return true;
        } else {
            return false;
        }
    }

    private static void printGrid(boolean[][] grid, int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                printState(grid[i][j]);
            }
            print("");
        }
    }

    private static void printState(boolean b) {
        System.out.print(b ? "*" : ".");
    }

    private static void print(String msg) {
        System.out.println(msg);
    }
}
