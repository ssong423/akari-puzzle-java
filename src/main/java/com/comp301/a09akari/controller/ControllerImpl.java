package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelImpl;

import java.util.Random;

public class ControllerImpl implements ClassicMvcController {
  private Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    int nextIndex = model.getActivePuzzleIndex() + 1;
    if (nextIndex == model.getPuzzleLibrarySize()) {
      throw new IndexOutOfBoundsException();
    }
    model.setActivePuzzleIndex(nextIndex);
    // notifyObservers();
  }

  @Override
  public void clickPrevPuzzle() {
    int prevIndex = model.getActivePuzzleIndex() - 1;
    if (prevIndex == -1) {
      throw new IndexOutOfBoundsException();
    }
    model.setActivePuzzleIndex(prevIndex);
  }

  @Override
  public void clickRandPuzzle() {
    Random random = new Random();
    int randomIndex = random.nextInt(model.getPuzzleLibrarySize());
    model.setActivePuzzleIndex(randomIndex);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else if (model.isLampIllegal(r, c)) {
      System.out.println("Illegal lamp placement!");
      model.addLamp(r, c);
      ///////////////
    } else {
      model.addLamp(r, c);
    }
  }
}
