import processing.core.PApplet;
import java.util.ArrayList;
public class Runner extends PApplet {

    Grid grid;
    Block block;
    int offsetX;
    int offsetY;
    boolean dragging = false;
    ArrayList<Block> shapes = new ArrayList<>();
    Block currentShape;
    // https://www.programiz.com/java-programming/multidimensional-array
    int[][][] shapeTemplates = {
            { {1, 1}, {1, 1} },
            { {1}, {1}, {1} },
            { {1} },
            { {1, 1} },
            { {1, 1, 1} },
            { {1, 0}, {1, 0}, {1, 1} },
            { {1, 1, 1}, {0, 1, 0} },
            { {0, 1}, {1, 1}, {0, 1} },
            { {1, 1, 1, 1} },
            { {1,1,1}, {1,0,0} }
    };
    int score = 0;

    public static void main(String[] args) {
        PApplet.main("Runner");
    }

    public void setup() {
    }

    public void settings() {
        size(600, 700);
        grid = new Grid();
        addThreeShapes();
        block = currentShape;
    }

    // chooses a random shape from shapeTemplates
    public Block randomShape(int x, int y) {
        int shape = (int)random(shapeTemplates.length);
        return new Block(shapeTemplates[shape], x, y);
    }

    // adds in sets of three
    public void addThreeShapes(){
        shapes.add(randomShape(100, 500));
        shapes.add(randomShape(250, 500));
        shapes.add(randomShape(400, 500));
    }

    public void draw() {
        background(137, 192, 255);
        grid.initializeGrid(this);
        for (Block shape : shapes) {
            shape.draw(this);
        }
        fill(0,0,0);
        textSize(32);
        text("score: " + score, 250, 50);
        text("block blast!", 230, 650);
    }

    // https://stackoverflow.com/questions/74830408/processing-drag-and-drop-class-object#:~:text=In%20order%20to%20have%20multiple,in%20the%20initial%20class%20declaration.
    //starts dragging a shape
    public void mousePressed() {
        boolean found = false;
        for (Block shape : shapes) {
            if (!found && shape.placement(mouseX, mouseY)) {
                currentShape = shape;
                block = shape;
                dragging = true;
                offsetX = mouseX - block.x;
                offsetY = mouseY - block.y;
                found = true;  // selected shape
            }
        }
    }

    // https://stackoverflow.com/questions/74830408/processing-drag-and-drop-class-object#:~:text=In%20order%20to%20have%20multiple,in%20the%20initial%20class%20declaration.
    // drags the blocks
    public void mouseDragged() {
        if (dragging) {
            block.x = mouseX - offsetX;
            block.y = mouseY - offsetY;
        }
    }

    public void mouseReleased() {
        // ends dragging
        dragging = false;
        // https://gamedev.stackexchange.com/questions/33140/how-can-i-snap-a-game-objects-position-to-a-grid
        // makes the shape go to the nearest spot on the grid
        block.x = (int)Math.round((double)(block.x - 100) / block.size) * block.size + 100;
        block.y = (int)Math.round((double)(block.y - 70) / block.size) * block.size + 70;

        // checks if invalid
        if (!grid.isValidPlacement(block)) {
            block.x = block.originalX;
            block.y = block.originalY;
            println("invalid placement");
        } else {
            grid.placeBlock(block);
            // calculates scores if a row or column is cleared
            int rowsCleared = grid.checkFullRows();
            int colsCleared = grid.checkFullColumns();
            score += (rowsCleared * 10) + (colsCleared * 10);
            shapes.remove(block);
            // add another set of three when all the shapes are used
            if (shapes.size() == 0) {
                addThreeShapes();
            }
        }
    }
}
