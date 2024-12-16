package com.lightUp.view;

import com.lightUp.SamplePuzzles;
import com.lightUp.controller.ControllerImpl;
import com.lightUp.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    PuzzleLibrary puzzleLibrary = new PuzzleLibraryImpl();
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    puzzleLibrary.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));

    Model model = new ModelImpl(puzzleLibrary);
    ControllerImpl controller = new ControllerImpl(model);

    ControlView c = new ControlView(controller);
    MessageView m = new MessageView(controller);
    PuzzleView p = new PuzzleView(controller);

    stage.setTitle("Akari");
    Pane pane = new VBox();
    pane.getChildren().add(m.render());
    pane.getChildren().add(p.render());
    pane.getChildren().add(c.render());
    Scene scene = new Scene(pane, 400, 400);
    stage.setScene(scene);
    stage.show();
    ModelObserver observer =
        model1 -> {
          pane.getChildren().clear();
          pane.getChildren().add(m.render());
          pane.getChildren().add(p.render());
          pane.getChildren().add(c.render());
        };
    model.addObserver(observer);
  }
}
