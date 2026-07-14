# Block Blast! (Java & Processing)

An interactive, grid-based puzzle game built in Java using the Processing library. Inspired by the popular mobile game *Block Blast*, this project challenges players to strategically place unique shapes on a grid, clear lines, and chase high scores.

---

## Gameplay Overview

The objective of the game is simple: get the highest score possible by filling up rows and columns

* **Drag & Drop:** Drag shapes from the dock below and drop them onto the grid
* **Line Clears:** When an entire row or column is filled, it is cleared, and you earn points
* **Smart Snapping:** Release a block near the grid, and it will automatically snap to the nearest valid coordinates
* **Invalid Move Protection:** If you try to place a block on top of another or outside the boundaries, it will automatically bounce back to its original slot
* **Game Over:** If there is no space left on the grid to place your remaining shapes, the game ends

---

## Features

* **Dynamic Shape Spawner:** Randomly generates sets of three shapes from a pool of 10 unique templates
* **Real-time Score Calculator:** Tracks and displays your current score prominently on screen
* **Custom Grid Engine:** Built on a robust $10 \times 10$ matrix that dynamically detects complete lines and manages block states
* **Smooth Visuals:** Powered by Processing to ensure responsive rendering and interactive mouse tracking

---

## Code Structure

The project is structured into three main classes to keep the codebase clean and modular:

### 1. `Runner.java` (The Game Controller)
* Extends `PApplet` to run the game window and render the interface.
* Manages the active game state, mouse drag-and-drop events, and high-level game logic (like spawning new blocks when the dock is empty)

### 2. `Grid.java` (The Board Manager)
* Manages the internal $10 \times 10$ multi-dimensional array representation of the board
* Handles collision checking (`isValidPlacement`), placing shapes on the board (`placeBlock`), and clearing filled rows or columns (`checkFullRows` / `checkFullColumns`)

### 3. `Block.java` (The Shape Object)
* Defines individual block shapes using smaller matrices (e.g., $2 \times 2$ square, $3 \times 1$ line)
* Determines rendering coordinates and tracks whether the player’s mouse is currently grabbing a specific block

