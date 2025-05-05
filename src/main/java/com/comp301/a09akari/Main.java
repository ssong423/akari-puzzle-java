package com.comp301.a09akari;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.ModelImpl;
import com.comp301.a09akari.model.PuzzleImpl;
import com.comp301.a09akari.model.PuzzleLibrary;
import com.comp301.a09akari.model.PuzzleLibraryImpl;
import com.comp301.a09akari.view.AppLauncher;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Application.launch(AppLauncher.class);
    PuzzleImpl puzzle1 = new PuzzleImpl(SamplePuzzles.PUZZLE_07);
    PuzzleImpl puzzle6 = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
    PuzzleImpl puzzle2 = new PuzzleImpl(SamplePuzzles.PUZZLE_02);
    PuzzleImpl puzzle3 = new PuzzleImpl(SamplePuzzles.PUZZLE_03);
    PuzzleImpl puzzle4 = new PuzzleImpl(SamplePuzzles.PUZZLE_04);
    PuzzleImpl puzzle5 = new PuzzleImpl(SamplePuzzles.PUZZLE_05);
    PuzzleLibrary library = new PuzzleLibraryImpl();
    library.addPuzzle(puzzle1);
    library.addPuzzle(puzzle2);
    library.addPuzzle(puzzle3);
    library.addPuzzle(puzzle4);
    library.addPuzzle(puzzle5);

    ModelImpl model = new ModelImpl(library);

    // puzzle01
    //    model.addLamp(0,1);
    //    model.addLamp(0,5);
    //    model.addLamp(1,4);
    //    model.addLamp(2,5);
    //    model.addLamp(3,0);
    //    model.addLamp(4,1);
    //    model.addLamp(5,0);
    //    model.addLamp(5, 3);
    //    model.addLamp(6, 2);

    // puzzle06
    //    model.addLamp(0, 1);
    //    model.addLamp(0, 4);
    //    model.addLamp(1, 0);
    //    model.addLamp(1, 2);
    //    model.addLamp(2, 1);
    //    model.addLamp(2, 3);
    //    model.addLamp(3, 2);

    // puzzle07
    model.setActivePuzzleIndex(0);
    model.addLamp(0, 0);

    //    ControllerImpl controller = new ControllerImpl(model);
    //    controller.clickNextPuzzle();
    //    //controller.clickNextPuzzle();
    //    //controller.clickNextPuzzle();
    //    //controller.clickNextPuzzle();
    //    //controller.clickNextPuzzle();
    //    model.getActivePuzzleIndex();
    //    System.out.println(model.getPuzzleLibrarySize());
    //    System.out.println(model.getActivePuzzleIndex());
    System.out.println(model.isLit(0, 0));
    System.out.println(model.isLampIllegal(0, 0));
    // System.out.println(model.isClueSatisfied(0,0));
    System.out.println(model.isSolved());
  }
}
