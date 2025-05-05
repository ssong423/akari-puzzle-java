package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;

public class PuzzleView implements FXComponent, ModelObserver {
  private Puzzle puzzle;
  private ControllerImpl controller;
  private ControlView controlView;
  private MessageView messageView;
  private Stage stage;
  private Model model;

  public PuzzleView(Model model, ControllerImpl controller, Stage stage) {
    this.model = model;
    this.controller = controller;
    this.controlView = new ControlView(controller);
    this.messageView = new MessageView(controller, model);
    this.stage = stage;
    update(model);
  }

  @Override
  public Parent render() {
    VBox mainLayout = new VBox(10);
    mainLayout.getChildren().add(controlView.render());
    GridPane grid = new GridPane();
    VBox controls = new VBox(10);
    Label messageLabel1 = new Label();
    messageLabel1.setText("puzzle " + (model.getActivePuzzleIndex() + 1) + " of 5: ");
    controls.getChildren().add(messageLabel1);
    mainLayout.getChildren().add(messageLabel1);
    mainLayout.getChildren().add(messageView.render());
    grid.setPrefHeight(model.getActivePuzzle().getHeight());
    grid.setPrefWidth(model.getActivePuzzle().getWidth());
    for (int i = 0; i < puzzle.getHeight(); i++) {
      for (int j = 0; j < puzzle.getWidth(); j++) {
        CellType type = puzzle.getCellType(i, j);
        switch (type) {
          case CORRIDOR:
            Button lightButton = new Button();
            lightButton.setMinSize(50, 50);
            lightButton.setMaxSize(50, 50);
            int row = i;
            int column = j;
            lightButton.setStyle("-fx-background-color: #ffffff; ");
            URL imageUrl = getClass().getResource("/light-bulb.png");
            Image lampImage = new Image(imageUrl.toExternalForm());
            ImageView imageView = new ImageView(lampImage);
            imageView.setFitWidth(30);
            imageView.setPreserveRatio(true);

            lightButton.setOnAction(
                e -> {
                  controller.clickCell(row, column);
                });

            if (puzzle.getCellType(i, j) == CellType.CORRIDOR && model.isLit(i, j)) {
              lightButton.setStyle("-fx-background-color: #ffffcc; ");
            }
            if (model.isLamp(row, column) && !model.isLampIllegal(row, column)) {
              lightButton.setGraphic(imageView);
            } else if (model.isLamp(row, column) && model.isLampIllegal(row, column)) {
              lightButton.setStyle("-fx-background-color: #fa8072; ");
            } else {
              lightButton.setGraphic(null);
            }

            grid.add(lightButton, j, i, 1, 1);
            break;

          case WALL:
            Button wallButton = new Button();
            wallButton.setMinSize(50, 50);
            wallButton.setMaxSize(50, 50);
            wallButton.setStyle("-fx-background-color: #000000; ");
            grid.add(wallButton, j, i, 1, 1);
            break;
          case CLUE:
            String s = Integer.toString(puzzle.getClue(i, j));
            Button clueButton = new Button(s);
            clueButton.setMinSize(50, 50);
            clueButton.setMaxSize(50, 50);
            if (model.isClueSatisfied(i, j)) {
              clueButton.setStyle("-fx-background-color: #3cb043; ");
            } else {
              clueButton.setStyle("-fx-background-color: #000000; ");
            }
            clueButton.setTextFill(Color.WHITE);
            clueButton.setFont(Font.font(20));
            grid.add(clueButton, j, i, 1, 1);
            break;
        }
      }
    }
    grid.setHgap(5);
    grid.setVgap(5);
    mainLayout.getChildren().add(grid);
    return mainLayout;
  }


  public void update(Model model) {
    this.puzzle = model.getActivePuzzle();
    System.out.println("Model updated. Rendering new scene...");
    Scene scene = new Scene(render());
    stage.setScene(scene);
  }
}
