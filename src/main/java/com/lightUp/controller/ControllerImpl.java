package com.lightUp.controller;

import com.lightUp.model.Model;
import com.lightUp.model.Puzzle;

public class ControllerImpl implements AlternateMvcController {
  private final Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    if (model.getActivePuzzleIndex() < model.getPuzzleLibrarySize() - 1) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
      model.resetPuzzle();
    }
  }

  @Override
  public void clickPrevPuzzle() {
    if (model.getActivePuzzleIndex() > 0) {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
      model.resetPuzzle();
    }
  }

  @Override
  public void clickRandPuzzle() {
    int num = (int) (Math.random() * model.getPuzzleLibrarySize());
    while (num == model.getActivePuzzleIndex()) {
      num = (int) (Math.random() * model.getPuzzleLibrarySize());
    }
    model.setActivePuzzleIndex(num);
    model.resetPuzzle();
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  @Override
  public boolean isLamp(int r, int c) {
    return model.isLamp(r, c);
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }

  public int getActivePuzzleIndex() {
    return model.getActivePuzzleIndex();
  }

  public boolean isIllegal(int r, int c) {
    return model.isLampIllegal(r, c);
  }
}
