package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent {
  private ControllerImpl controller;

  public ControlView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox controls = new HBox(10);

    Button nextButton = new Button("Next Puzzle");
    Label messageLabel = new Label();
    nextButton.setOnAction(
        e -> {
          try {
            controller.clickNextPuzzle();
            messageLabel.setText("");
          } catch (IndexOutOfBoundsException ex) {
            System.out.println("This is the last puzzle.");
            messageLabel.setText("This is the last puzzle.");
          }
        });
    controls.getChildren().add(nextButton);

    Button prevButton = new Button("Prev Puzzle");
    prevButton.setOnAction(
        e -> {
          try {
            controller.clickPrevPuzzle();
            messageLabel.setText("");
          } catch (IndexOutOfBoundsException ex) {
            System.out.println("This is the first puzzle.");
            messageLabel.setText("This is the first puzzle.");
          }
        });
    controls.getChildren().add(prevButton);

    Button randButton = new Button("Random Puzzle");
    randButton.setOnAction(
        e -> {
          controller.clickRandPuzzle();
          messageLabel.setText("");
        });
    controls.getChildren().add(randButton);

    Button resetButton = new Button("Reset Puzzle");
    resetButton.setOnAction(
        e -> {
          controller.clickResetPuzzle();
          messageLabel.setText("");
        });
    controls.getChildren().add(resetButton);

    controls.getChildren().add(messageLabel);

    return controls;
  }
}
