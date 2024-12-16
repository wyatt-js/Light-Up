package com.lightUp.model;

public interface Model {
  void addLamp(int r, int c);

  void removeLamp(int r, int c);

  boolean isLit(int r, int c);

  boolean isLamp(int r, int c);

  boolean isLampIllegal(int r, int c);

  Puzzle getActivePuzzle();

  int getActivePuzzleIndex();

  void setActivePuzzleIndex(int index);

  int getPuzzleLibrarySize();

  void resetPuzzle();

  boolean isSolved();

  boolean isClueSatisfied(int r, int c);

  void addObserver(ModelObserver observer);
}
