package com.lightUp.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private final PuzzleLibrary library;
  private int activePuzzle;
  private boolean[][] lamps;
  private final List<ModelObserver> observers;
  private int width;
  private int length;

  public ModelImpl(PuzzleLibrary library) {
    this.library = library;
    activePuzzle = 0;
    lamps = new boolean[getActivePuzzle().getHeight()][getActivePuzzle().getWidth()];
    this.observers = new ArrayList<ModelObserver>();
    width = getActivePuzzle().getWidth();
    length = getActivePuzzle().getHeight();
  }

  @Override
  public void addLamp(int r, int c) {
    if (r > length - 1 || c > width - 1) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamps[r][c] = true;
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r > length - 1 || c > width - 1) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamps[r][c] = false;
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r >= length || c >= width || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (lamps[r][c]) {
      return true;
    }
    Puzzle puzzle = library.getPuzzle(activePuzzle);
    if (r < length - 1) {
      for (int i = r + 1; i < length; i++) {
        if (puzzle.getCellType(i, c) == CellType.WALL
            || puzzle.getCellType(i, c) == CellType.CLUE) {
          break;
        }
        if (lamps[i][c]) {
          return true;
        }
      }
    }
    if (c < width - 1) {
      for (int i = c + 1; i < width; i++) {
        if (puzzle.getCellType(r, i) == CellType.WALL
            || puzzle.getCellType(r, i) == CellType.CLUE) {
          break;
        }
        if (lamps[r][i]) {
          return true;
        }
      }
    }
    if (r > 0) {
      for (int i = r - 1; i >= 0; i--) {
        if (puzzle.getCellType(i, c) == CellType.WALL
            || puzzle.getCellType(i, c) == CellType.CLUE) {
          break;
        }
        if (lamps[i][c]) {
          return true;
        }
      }
    }
    if (c > 0) {
      for (int i = c - 1; i >= 0; i--) {
        if (puzzle.getCellType(r, i) == CellType.WALL
            || puzzle.getCellType(r, i) == CellType.CLUE) {
          break;
        }
        if (lamps[r][i]) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r > length - 1 || c > width - 1 || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lamps[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r > length - 1 || c > width - 1 || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (!lamps[r][c]) {
      throw new IllegalArgumentException();
    }
    Puzzle puzzle = library.getPuzzle(activePuzzle);
    if (r < length - 1) {
      for (int i = r + 1; i < length; i++) {
        if (puzzle.getCellType(i, c) == CellType.WALL
            || puzzle.getCellType(i, c) == CellType.CLUE) {
          break;
        }
        if (lamps[i][c]) {
          return true;
        }
      }
    }
    if (c < width - 1) {
      for (int i = c + 1; i < width; i++) {
        if (puzzle.getCellType(r, i) == CellType.WALL
            || puzzle.getCellType(r, i) == CellType.CLUE) {
          break;
        }
        if (lamps[r][i]) {
          return true;
        }
      }
    }
    if (r > 0) {
      for (int i = r - 1; i >= 0; i--) {
        if (puzzle.getCellType(i, c) == CellType.WALL
            || puzzle.getCellType(i, c) == CellType.CLUE) {
          break;
        }
        if (lamps[i][c]) {
          return true;
        }
      }
    }
    if (c > 0) {
      for (int i = c - 1; i >= 0; i--) {
        if (puzzle.getCellType(r, i) == CellType.WALL
            || puzzle.getCellType(r, i) == CellType.CLUE) {
          break;
        }
        if (lamps[r][i]) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return library.getPuzzle(activePuzzle);
  }

  @Override
  public int getActivePuzzleIndex() {
    return activePuzzle;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index > getPuzzleLibrarySize() - 1 || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    activePuzzle = index;
    width = library.getPuzzle(activePuzzle).getWidth();
    length = library.getPuzzle(activePuzzle).getHeight();
    lamps =
        new boolean[library.getPuzzle(activePuzzle).getHeight()]
            [library.getPuzzle(activePuzzle).getWidth()];
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < width; j++) {
        lamps[i][j] = false;
      }
    }
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public boolean isSolved() {
    Puzzle puzzle = library.getPuzzle(activePuzzle);
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < width; j++) {
        CellType type = puzzle.getCellType(i, j);
        if (type == CellType.CORRIDOR && !isLit(i, j)) {
          return false;
        }
        if (type == CellType.CLUE && !isClueSatisfied(i, j)) {
          return false;
        }
        if (lamps[i][j] && isLampIllegal(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r > length - 1 || c > width - 1) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int count = 0;
    if (r > 0 && lamps[r - 1][c]) {
      count++;
    }
    if (c > 0 && lamps[r][c - 1]) {
      count++;
    }
    if (r < length - 1 && lamps[r + 1][c]) {
      count++;
    }
    if (c < width - 1 && lamps[r][c + 1]) {
      count++;
    }
    return library.getPuzzle(activePuzzle).getClue(r, c) == count;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }
}
