package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {

    PuzzleImpl puzzle1 = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
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
    ControllerImpl controller = new ControllerImpl(model);
    PuzzleView puzzleview = new PuzzleView(model, controller, stage);
    //ModelObserver puzzleview = new PuzzleView(model, controller, stage);
    model.addObserver(puzzleview);

    stage.setTitle("Akari");
    puzzleview.update(model);
    stage.show();
    // TODO: Create your Model, View, and Controller instances and launch your GUI
  }
}
