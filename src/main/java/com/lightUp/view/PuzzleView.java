package com.lightUp.view;

import com.lightUp.controller.ControllerImpl;
import com.lightUp.model.CellType;
import com.lightUp.model.PuzzleImpl;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PuzzleView implements FXComponent {
  private final ControllerImpl controller;

  public PuzzleView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane gridPane = new GridPane();
    gridPane.setHgap(1);
    gridPane.setVgap(1);
    PuzzleImpl puzzle = (PuzzleImpl) controller.getActivePuzzle();
    for (int i = 0; i < puzzle.getHeight(); i++) {
      for (int j = 0; j < puzzle.getWidth(); j++) {
        if (puzzle.getCellType(i, j) == CellType.CLUE) {
          Label clueLabel = new Label(String.valueOf(puzzle.getClue(i, j)));
          if (controller.isClueSatisfied(i, j)) {
            clueLabel.setStyle("-fx-background-color: green;");
          } else {
            clueLabel.setStyle("-fx-background-color: darkgray;");
          }
          clueLabel.setAlignment(Pos.CENTER);
          clueLabel.setMinSize(30, 30);
          gridPane.add(clueLabel, i, j);
        }
        if (puzzle.getCellType(i, j) == CellType.WALL) {
          Rectangle wall = new Rectangle(30, 30);
          wall.setFill(Color.BLACK);
          gridPane.add(wall, i, j);
        }
        if (puzzle.getCellType(i, j) == CellType.CORRIDOR) {
          Button corridor = new Button();
          corridor.setMinSize(30, 30);
          corridor.setMaxSize(30, 30);
          if (controller.isLamp(i, j)) {
            Image lamp;
            if (!controller.isIllegal(i, j)) {
              lamp = new Image("file:src/main/resources/light-bulb.png");
            } else {
              lamp = new Image("file:src/main/resources/red-light-bulb.png");
            }
            ImageView lampView = new ImageView(lamp);
            lampView.setFitWidth(30);
            lampView.setFitHeight(30);
            corridor.setGraphic(lampView);
          } else if (controller.isLit(i, j)) {
            corridor.setStyle(
                "-fx-background-color: yellow; -fx-border-width: 1px; -fx-border-style: solid;");
          } else {
            corridor.setStyle(
                "-fx-background-color: white; -fx-border-width: 1px; -fx-border-style: solid;");
          }
          int finalI = i;
          int finalJ = j;
          corridor.setOnAction(
              e -> {
                controller.clickCell(finalI, finalJ);
              });
          gridPane.add(corridor, i, j);
        }
      }
    }
    return gridPane;
  }
}
