import processing.core.PApplet;
public class Grid {
    public int col = 10;
    public int row = 10;
    public int blockSize = 40;
    public int [][] grid;

    public Grid() {
        grid = new int[col][row];
    }

    // draws the grid
    public void initializeGrid(PApplet p) {
        p.stroke(0,0,0);
        p.fill(255,255,255);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                p.rect(i * blockSize + 100, j * blockSize + 70, blockSize, blockSize);
            }
        }
        // fill in the shapes on the grid after it gets placed
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (grid[i][j] == 1) {
                    p.fill(255, 100, 100);
                    p.rect(i * blockSize + 100, j * blockSize + 70, blockSize, blockSize);
                }
            }
        }
    }

    // checks if the block fits in the current grid state
    public boolean isValidPlacement(Block block) {
        for (int i = 0; i < block.shape.length; i++) {
            for (int j = 0; j < block.shape[0].length; j++) {
                if (block.shape[i][j] == 1) {
                    int gridCol = (block.x - 100) / blockSize + j;
                    int gridRow = (block.y - 70) / blockSize + i;

                    // checks if the shape is outside the grid
                    if (gridCol < 0 || gridCol >= col || gridRow < 0 || gridRow >= row) {
                        return false;
                    }

                    // checks if a space is already filled
                    if (grid[gridCol][gridRow] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // removes the shape and places it in the grid
    public void placeBlock(Block block) {
        for (int i = 0; i < block.shape.length; i++) {
            for (int j = 0; j < block.shape[0].length; j++) {
                if (block.shape[i][j] == 1) {
                    int gridCol = (block.x - 100) / blockSize + j;
                    int gridRow = (block.y - 70) / blockSize + i;
                    grid[gridCol][gridRow] = 1;
                }
            }
        }
    }

    // checks for full rows and then clears them to earn points
    public int checkFullRows() {
        int cleared = 0;
        for (int j = 0; j < row; j++) {
            boolean full = true;
            for (int i = 0; i < col; i++) {
                if (grid[i][j] == 0) {
                    full = false;
                }
            }
            if (full) {
                // resets row
                for (int i = 0; i < col; i++) {
                    grid[i][j] = 0;
                }
                cleared++;
            }
        }
        return cleared;
    }

    // checks for full columns and then clears them to earn points
    public int checkFullColumns() {
        int cleared = 0;
        for (int i = 0; i < col; i++) {
            boolean full = true;
            for (int j = 0; j < row; j++) {
                if (grid[i][j] == 0) {
                    full = false;
                }
            }
            if (full) {
                // resets column
                for (int j = 0; j < row; j++) {
                    grid[i][j] = 0;
                }
                cleared++;
            }
        }
        return cleared;
    }

}