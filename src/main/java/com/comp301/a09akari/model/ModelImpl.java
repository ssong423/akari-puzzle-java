package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private PuzzleLibrary puzzleLibrary;
  private Puzzle currentPuzzle;
  private int activePuzzleIndex;
  private int[][] lightLoca;
  private List<ModelObserver> observers;

  public ModelImpl(PuzzleLibrary library) {
    puzzleLibrary = library;
    currentPuzzle = library.getPuzzle(0);
    activePuzzleIndex = 0;
    lightLoca = new int[currentPuzzle.getHeight()][currentPuzzle.getWidth()];
    observers = new ArrayList<ModelObserver>();
    for (int i = 0; i < lightLoca.length; i++) {
      for (int j = 0; j < lightLoca[i].length; j++) {
        lightLoca[i][j] = 0;
      }
    }
  }

  @Override
  public void addLamp(int r, int c) {
    if (r < 0 || r >= currentPuzzle.getHeight() || c < 0 || c >= currentPuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Cell index out of bounds");
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Invalid cell value");
    }
    lightLoca[r][c] = 1;
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r < 0 || r >= currentPuzzle.getHeight() || c < 0 || c >= currentPuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Cell index out of bounds");
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Invalid cell value");
    }
    lightLoca[r][c] = 0;
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    // System.out.println(lightLoca[r][c]);
    try {
      if (isLampIllegal(r, c) || isLamp(r, c)) {
        return true;
      }
    } catch (IllegalArgumentException ex) {
      return false;
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0 || r >= currentPuzzle.getHeight() || c < 0 || c >= currentPuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Cell index out of bounds");
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Invalid cell value");
    }
    return lightLoca[r][c] == 1;
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || r >= currentPuzzle.getHeight() || c < 0 || c >= currentPuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Cell index out of bounds");
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("Invalid cell value");
    }
    //        if (!isLamp(r, c)){
    //            throw new IllegalArgumentException("Invalid cell value");
    //        }
    for (int i = 0; i < lightLoca.length; i++) {
      if (lightLoca[i][c] == 1) {
        if (i < r) {
          boolean allCORRIDOR = true;
          for (int j = i; j < r; j++) {
            boolean cur = currentPuzzle.getCellType(j, c) == CellType.CORRIDOR;
            allCORRIDOR = allCORRIDOR && cur;
          }
          if (allCORRIDOR) {
            return true;
          }
        }
        if (i > r) {
          boolean allCORRIDOR = true;
          for (int j = r; j < i; j++) {
            boolean cur = currentPuzzle.getCellType(j, c) == CellType.CORRIDOR;
            allCORRIDOR = allCORRIDOR && cur;
          }
          if (allCORRIDOR) {
            return true;
          }
        }
      }
    }
    for (int i = 0; i < lightLoca[r].length; i++) {
      if (lightLoca[r][i] == 1) {
        if (i < c) {
          boolean allCORRIDOR = true;
          for (int j = i; j < c; j++) {
            boolean cur = currentPuzzle.getCellType(r, j) == CellType.CORRIDOR;
            allCORRIDOR = allCORRIDOR && cur;
          }
          if (allCORRIDOR) {
            return true;
          }
        }
        if (i > c) {
          boolean allCORRIDOR = true;
          for (int j = c; j < i; j++) {
            boolean cur = currentPuzzle.getCellType(r, j) == CellType.CORRIDOR;
            allCORRIDOR = allCORRIDOR && cur;
          }
          if (allCORRIDOR) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return currentPuzzle;
  }

  @Override
  public int getActivePuzzleIndex() {
    return activePuzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index >= getPuzzleLibrarySize()) {
      throw new IndexOutOfBoundsException();
    }
    currentPuzzle = puzzleLibrary.getPuzzle(index);
    activePuzzleIndex = index;
    resetPuzzle();
    // notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return puzzleLibrary.size();
  }

  @Override
  public void resetPuzzle() {
    lightLoca = new int[currentPuzzle.getHeight()][currentPuzzle.getWidth()];
    for (int i = 0; i < lightLoca.length; i++) {
      for (int j = 0; j < lightLoca[i].length; j++) {
        lightLoca[i][j] = 0;
      }
    }
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < currentPuzzle.getHeight(); i++) {
      for (int j = 0; j < currentPuzzle.getWidth(); j++) {
        CellType cellType = currentPuzzle.getCellType(i, j);
        if (cellType == CellType.CLUE) {
          if (!isClueSatisfied(i, j)) {
            System.out.println("clue issue");
            return false;
          }
        }
        if (cellType == CellType.CORRIDOR) {
          if ((!isLit(i, j))) {
            System.out.println("is lit issue");
            return false;
          }
          if (isLamp(i, j) && isLampIllegal(i, j)) {
            System.out.println("lamp issue");
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || r >= currentPuzzle.getHeight() || c < 0 || c >= currentPuzzle.getWidth()) {
      throw new IndexOutOfBoundsException("Cell index out of bounds");
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException("Invalid cell value");
    }
    int up, down, left, right;
    try {
      up = lightLoca[r - 1][c];
    } catch (IndexOutOfBoundsException ex) {
      up = 0;
    }
    try {
      down = lightLoca[r + 1][c];
    } catch (IndexOutOfBoundsException ex) {
      down = 0;
    }
    try {
      left = lightLoca[r][c - 1];
    } catch (IndexOutOfBoundsException ex) {
      left = 0;
    }
    try {
      right = lightLoca[r][c + 1];
    } catch (IndexOutOfBoundsException ex) {
      right = 0;
    }
    if (currentPuzzle.getClue(r, c) == up + down + left + right) {
      return true;
    }
    return false;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }
}
