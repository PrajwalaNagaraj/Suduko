package com.company;

public class Main {



    // Input to the program
    public static int[][] GRID_TO_SOLVE = {
            {9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0},
    };
    //class variables
    private int[][] board;
    private static int EMPTY = 0;
    private static int SIZE = 9;

    //Initialization of the 2-D array
    public Main(int[][] board) {
        this.board = new int[SIZE][SIZE];

        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    //Checks the ROW constraint
    private boolean IsInRow(int row, int num) {
        for(int k = 0; k < SIZE; k++) {
            if(board[row][k] == num)
                return true;
        }
        return false;
    }

    //Checks the COL constraint
    private boolean IsInCol(int col, int num) {
        for(int k = 0; k < SIZE; k++) {
            if(board[k][col] == num)
                return true;
        }
        return false;
    }

    //Checks the 3*3 box constraint
    private boolean IsInBox(int row, int col, int num) {
        int r = row - row%3;
        int c = col - col%3;

        for(int i = r; i < r+3; i++) {
            for (int j = c; j < c+3; j++) {
                if(board[i][j] == num)
                    return true;
            }
        }
        return false;
    }

    private boolean isPlaceable(int row, int col, int num) {
        return (!IsInRow(row, num) && !IsInCol(col, num) && !IsInBox(row, col, num));
    }

    //Iterates through each possible new number at empty cells
    private boolean solve(){
        for (int r = 0; r < SIZE; r++) {
            for(int c = 0; c < SIZE; c++) {
                if(board[r][c] == EMPTY) {
                    for(int k = 1; k <= SIZE; k++) {
                        if(isPlaceable(r,c, k)){
                            board[r][c] = k;
                            if(solve()) {//recursively backtrack
                                return true;
                            }else{
                                board[r][c] = EMPTY;
                            }
                        }
                    }
                    return false;//None of the numbers fit in the range inside the cell
                }
            }
        }
        return true;
    }

    private void display() {
        for(int i = 0 ; i < SIZE; i++) {
            for( int j = 0; j < SIZE; j++) {
                System.out.print(" "+board[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Main suduko_solve = new Main(GRID_TO_SOLVE);
        System.out.println("Input:");
        suduko_solve.display();
        if(suduko_solve.solve()) {
            System.out.println("Suduku solved:");
            suduko_solve.display();
        }else{
            System.out.println("Suduku not solvable");
        }


    }
}
