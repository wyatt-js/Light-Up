package com.lightUp.model;

public class PuzzleImpl implements Puzzle {
  private final int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r > getHeight() - 1 || c > getWidth() - 1) {
      throw new IndexOutOfBoundsException();
    }
    int type = board[r][c];
    if (type <= 4) {
      return CellType.CLUE;
    } else if (type == 5) {
      return CellType.WALL;
    }
    return CellType.CORRIDOR;
  }

  @Override
  public int getClue(int r, int c) {
    if (r > getHeight() - 1 || c > getWidth() - 1) {
      throw new IndexOutOfBoundsException();
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return board[r][c];
  }
}
