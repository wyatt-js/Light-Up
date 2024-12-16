package com.lightUp.controller;

import com.lightUp.model.Puzzle;

public interface AlternateMvcController {
  void clickNextPuzzle();

  void clickPrevPuzzle();

  void clickRandPuzzle();

  void clickResetPuzzle();

  void clickCell(int r, int c);

  boolean isLit(int r, int c);

  boolean isLamp(int r, int c);

  boolean isClueSatisfied(int r, int c);

  boolean isSolved();

  Puzzle getActivePuzzle();
}
