package com.lightUp.model;

public interface PuzzleLibrary {
  void addPuzzle(Puzzle puzzle);
  Puzzle getPuzzle(int index);
  int size();
}
