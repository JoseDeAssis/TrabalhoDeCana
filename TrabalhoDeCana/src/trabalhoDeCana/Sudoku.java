package trabalhoDeCana;

import java.io.*;
import java.util.Scanner;

public class Sudoku {
	private static final int ROW = 9;
	private static final int COLUMN = 9;
	private static final int EMPTY = 0;
	private Cell[][] sudoku;

	public Sudoku() {
		this.sudoku = new Cell[COLUMN][ROW];
		for (int col = 0; col < COLUMN; col++) {
			for (int row = 0; row < ROW; row++) {
				sudoku[col][row] = new Cell();
			}
		}
	}

	public void fillSudoku(String path) {
		try {
			FileReader file = new FileReader(path);
			Scanner lerArquivo = new Scanner(file);
			int col = 0;

			while (lerArquivo.hasNextLine() && col < 9) {
				for (int i = 0; i < 9; i++) {
					sudoku[col][i].addCellNumber(lerArquivo.nextInt());
				}
				col++;
			}

			lerArquivo.close();
		} catch (IOException e) {
			System.out.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

	}

	public boolean isInRow(int row, int number) {
		for (int i = 0; i < ROW; i++) {
			if (sudoku[i][row].getCellNumber() == number) {
				return true;
			}

		}
		return false;
	}

	public boolean isInColumn(int column, int number) {
		for (int i = 0; i < COLUMN; i++) {
			if (sudoku[column][i].getCellNumber() == number) {
				return true;
			}

		}
		return false;
	}

	public boolean isInSubGrade(int row, int column, int number) {
		int rowBox = row - row % 3;
		int colBox = column - column % 3;

		for (int i = rowBox; i < rowBox + 3; i++) {
			for (int j = colBox; j < colBox + 3; j++) {
				if (sudoku[j][i].getCellNumber() == number) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isOk(int row, int column, int number) {
		return !isInRow(row, number) && !isInColumn(column, number) && !isInSubGrade(row, column, number);
	}

	public boolean solve() {
		for (int col = 0; col < ROW; col++) {
			for (int row = 0; row < COLUMN; row++) {
				if (sudoku[col][row].getCellNumber() == EMPTY) {
					for (int number = 1; number < 10; number++) {
						if (isOk(row, col, number)) {
							sudoku[col][row].addCellNumber(number);
							if (solve()) {
								return true;
							} else {
								sudoku[col][row].removeCellNumber();
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	public void showSudokuBoard() {
		for (int row = 0; row < ROW; row++) {
			System.out.print("| ");
			for (int col = 0; col < COLUMN; col++) {
				System.out.print(sudoku[row][col].getCellNumber() + " | ");
			}
			System.out.println();
			System.out.println("-------------------------------------");
		}
	}

	public void verifySolve() {
		for (int i = 0; i < 9; i++) {
			int isInCol = 0;
			int isInRow = 0;
			for (int row = 0; row < 9; row++) {
				if (sudoku[i][row].getCellNumber() == i)
					isInCol++;
				if (isInCol > 1)
					System.out.println("deu ruim na coluna " + i);
			}
			for (int col = 0; col < 9; col++) {
				if (sudoku[col][i].getCellNumber() == i)
					isInRow++;
				if (isInRow > 1)
					System.out.println("deu ruim na linha " + i);
			}
		}
	}

	public boolean couldBeInColumn(int column, int number) {
		for (int row = 0; row < ROW; row++) {
			if (sudoku[column][row].getCellNumber() == number)
				return false;
		}
		return true;
	}

	public boolean couldBeInRow(int row, int number) {
		for (int col = 0; col < ROW; col++) {
			if (sudoku[col][row].getCellNumber() == number)
				return false;
		}
		return true;
	}

	public boolean couldBeInSubGrade(int row, int column, int number) {
		int rowBox = row - row % 3;
		int colBox = column - column % 3;

		for (int i = rowBox; i < rowBox + 3; i++) {
			for (int j = colBox; j < colBox + 3; j++) {
				if (sudoku[j][i].getCellNumber() == number) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean isOkForSolve2(int row, int column, int number) {
		return couldBeInColumn(column, number) && couldBeInRow(row, number) && couldBeInSubGrade(row, column, number);
	}

	private void analyseSudoku() {
		for (int col = 0; col < ROW; col++) {
			for (int row = 0; row < COLUMN; row++) {
				if (sudoku[col][row].getCellNumber() == EMPTY) {
					for (int number = 1; number < 10; number++) {
						if (isOkForSolve2(row, col, number)) {
							sudoku[col][row].addPossibleNumber(number);
						}
					}
				}
			}
		}
	}

	public boolean solveMethod2() {
		analyseSudoku();
		for (int col = 0; col < ROW; col++) {
			for (int row = 0; row < COLUMN; row++) {
				if (sudoku[col][row].getCellNumber() == EMPTY) {
					for (int number = 1; number < 10; number++) {
						if (isOk(row, col, number)) {
							sudoku[col][row].addCellNumber();
							if (solve()) {
								return true;
							} else {
								sudoku[col][row].removeCellNumber();
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	public void showPossiblities() {
		analyseSudoku();
		for(int col = 0; col < COLUMN; col++) {
			for(int row = 0; row < ROW; row++) {
				for(Integer number: sudoku[col][row].getListPossibleNumbers()) {
					System.out.print(number);
				}
				System.out.print(" | ");
			}
			System.out.println();
		}
	}

}
