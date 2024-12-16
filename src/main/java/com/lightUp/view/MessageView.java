package com.lightUp.view;

import com.lightUp.controller.ControllerImpl;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MessageView implements FXComponent {
  private final ControllerImpl controller;

  public MessageView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Pane vbox = new VBox();
    Label solved = new Label("Congratulations! You solved this puzzle!");
    int index = controller.getActivePuzzleIndex() + 1;
    Label puzzle = new Label("Puzzle " + index + " of 5");
    vbox.getChildren().add(solved);
    vbox.getChildren().add(puzzle);
    solved.setVisible(controller.isSolved());
    return vbox;
  }
}
