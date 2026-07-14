import processing.core.PApplet;

public class Block {
    public int [][] shape;
    public int x;
    public int y;
    public int originalX;
    public int originalY;
    public int size = 40;

    public Block(int[][] shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.originalX = x; // under the grid
        this.originalY = y; // under the grid
    }

    //draws the blocks
    public void draw(PApplet p) {
        p.fill(255,100,100);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1) {
                    p.rect(x + j * size, y + i * size, size, size);
                }
            }
        }
    }

    // checks where the mouse is
    public boolean placement(int mouseX, int mouseY) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1) {
                    // only check the blocks part of the shape
                    int blockX = x + j * size;
                    int blockY = y + i * size;
                    // if mouse is inside the block
                    if (mouseX >= blockX && mouseX <= blockX + size && mouseY >= blockY && mouseY <= blockY + size) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}