package com.lightUp.view;

import com.lightUp.controller.ControllerImpl;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ControlView implements FXComponent {
  private final ControllerImpl controller;

  public ControlView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Pane hbox = new HBox();
    Button next = new Button("Next");
    next.setOnAction(
        (ActionEvent e) -> {
          controller.clickNextPuzzle();
        });
    Button prev = new Button("Prev");
    prev.setOnAction(
        (ActionEvent e) -> {
          controller.clickPrevPuzzle();
        });
    Button rand = new Button("Random");
    rand.setOnAction(
        (ActionEvent e) -> {
          controller.clickRandPuzzle();
        });
    Button reset = new Button("Reset");
    reset.setOnAction(
        (ActionEvent e) -> {
          controller.clickResetPuzzle();
        });
    hbox.getChildren().add(prev);
    hbox.getChildren().add(next);
    hbox.getChildren().add(rand);
    hbox.getChildren().add(reset);
    return hbox;
  }
}
