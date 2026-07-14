# Block Blast! - Java Grid Puzzle Game

## Description
[cite_start]This project is an interactive, block-placement puzzle game built in Java using the Processing (`PApplet`) graphics engine, heavily inspired by the popular mobile game *Block Blast*[cite: 2]. [cite_start]The core objective of the game is to strategically drag randomly generated block shapes onto a 10x10 grid, clear full rows and columns to accumulate points, and strive for the highest score possible[cite: 3, 4, 6].

[cite_start]The game dynamically generates pieces in sets of three beneath the main board[cite: 7]. Players must carefully manage the grid space, as failing to clear lines efficiently will restrict placement options.

---

## Features & Gameplay Mechanics

* **Drag-and-Drop Interface:** Built entirely using native Processing mouse event handlers (`mousePressed`, `mouseDragged`, and `mouseReleased`). The program tracks mouse offsets relative to the shapes to deliver smooth, real-time dragging.
* **Intelligent Grid Snapping:** When a shape is released near the grid, the program runs math tracking coordinates to automatically snap the block into the exact matching grid slots.
* **Collision & Validation Engine:** Before a block is locked into the grid, the game validates the placement. If a player drops a piece outside the grid boundaries or overlaps an already occupied cell, the placement is rejected, and the block immediately snaps back to its original position beneath the board.
* **Dual-Axis Line Clearing:** The application independently evaluates the grid array along both axes. [cite_start]Completing an entire row or an entire column wipes those blocks back to empty and updates the player's score[cite: 4].
* **Dynamic Shape Spawning:** The game handles a collection of 10 distinct matrix templates (ranging from single dots and straight lines to L-shapes and T-shapes). [cite_start]The program presents three shapes at a time; once all three are successfully placed, a fresh trio is instantly rolled out[cite: 7].

---

## Code 

The underlying system follows an object-oriented design split across three core Java files:

### 1. `Block.java`
Manages the properties and rendering of individual player pieces.
* Defines shapes using custom 2D integer matrices where `1` represents a filled block and `0` represents empty space.
* Tracks screen position coordinates (`x`, `y`) along with baseline anchoring fields (`originalX`, `originalY`) for resetting failed placements.
* Handles localized collision detection to determine if a user's mouse click landed within the boundaries of an active block.

### 2. `Grid.java`
[cite_start]Acts as the central engine tracking the state of the board game world[cite: 7].
* Initializes and maintains a 10x10 multi-dimensional array representing the puzzle canvas.
* Contains the mathematical verification algorithms (`isValidPlacement`) that translate screen pixels into matrix indices to cross-check bounding box errors and grid availability.
* Controls the clearing operations for full columns and rows, resetting those matching matrix values back to `0`.

### 3. `Runner.java`
Acts as the execution driver extending `PApplet`.
* Configures the window frame dimensions (600x700 pixels) and orchestrates the primary rendering loop.
* [cite_start]Implements the structural game state loop, managing the active array list of available shapes, tracking the score counter, and updating the UI text fields in real-time[cite: 7].
* Processes global user inputs (mouse clicks, drags, and releases) to transition blocks from safe zones into active play.

---

## Controls & System Rules

* [cite_start]**Left-Click & Drag:** Click down on any of the three available shapes below the grid to pick it up, and move your mouse to guide it onto the grid canvas[cite: 3].
* **Release Mouse:** Drops the held shape into place. 
* [cite_start]**Scoring Matrix:** Successfully completing a row or column rewards you with points instantly[cite: 4]. [cite_start]Clearing multiple lines simultaneously boosts your score progression[cite: 4].
