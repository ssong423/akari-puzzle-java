package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MessageView implements FXComponent {
  private ControllerImpl controller;
  private Model model;

  public MessageView(ControllerImpl controller, Model model) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    VBox controls = new VBox(10);
    Label messageLabel = new Label();
    if (model.isSolved()) {
      messageLabel.setText("success");
    } else {
      messageLabel.setText("");
    }

    controls.getChildren().add(messageLabel);
    return controls;
  }
}
