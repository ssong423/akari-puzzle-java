# Akari Puzzle Game (Light Up) 🔆

This is a fully interactive JavaFX-based implementation of the **Akari puzzle** (also known as *Light Up*), built using the Model-View-Controller (MVC) design pattern. The game logic and UI were designed from scratch and support a library of puzzles with dynamic validation, user interaction, and visual feedback.

## 🎮 Game Overview

**Akari** is a logic puzzle where the player places lamps in empty spaces to light up the entire board. A valid solution satisfies all of the following:
- Every corridor is lit by at least one lamp
- No lamps shine on each other
- All numbered wall clues have the exact number of lamps adjacent to them

This implementation supports:
- User-placed lamps and automatic light propagation
- Visual feedback for illegal placements and satisfied clues
- Dynamic reset and puzzle navigation (next, previous, random)

## 🧱 Tech Stack

- **Java 17**
- **JavaFX** for GUI
- **Maven** for build and dependency management
- **MVC architecture** with observer pattern

## 📸 Demo

<img src="screenshot.png" width="500" alt="Akari Puzzle Game Screenshot" />

## 🚀 Features

- ✅ Multiple built-in puzzles with different sizes
- ✅ Visual distinction for walls, clues, lit/unlit corridors, and lamps
- ✅ Dynamic feedback on clue satisfaction and illegal lamp placement
- ✅ "Puzzle Solved" success message when all constraints are met
- ✅ Reset button and puzzle navigation (next, previous, random)
- ✅ Responsive UI built using JavaFX

## 🗂 Project Structure

