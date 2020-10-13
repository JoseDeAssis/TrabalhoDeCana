package trabalhoDeCana;

import java.io.*;
import java.util.Scanner;

public class Sudoku {
	private static final int ROW = 9;
	private static final int COLUMN = 9;
	private static final int EMPTY = 0;
	private Cell[][] sudoku;
	private long tempoInicial, tempoFinal;

	public Sudoku() {
		this.sudoku = new Cell[ROW][COLUMN];
		for (int row = 0; row < ROW; row++) {
			for (int col = 0; col < COLUMN; col++) {
				sudoku[col][row] = new Cell();
			}
		}
	}
	
	public long getTempoInicial() {
		return this.tempoInicial;
	}
	
	public long getTempoFinal() {
		return this.tempoFinal;
	}

	public void fillSudoku(String path) {
		try {
			tempoInicial = System.currentTimeMillis();
			FileReader fr = new FileReader(path);
			Scanner lerArquivo = new Scanner(fr);
			int row = 0;

			while (lerArquivo.hasNextLine() && row < 9) {
				for (int i = 0; i < 9; i++) {
					sudoku[i][row].addCellNumber(lerArquivo.nextInt());
				}
				row++;
			}

			lerArquivo.close();
			fr.close();
		} catch (IOException e) {
			System.out.println("Erro na abertura do arquivo: " + e.getMessage());
		}

	}

	public boolean saveSolvedSudoku(String path) {
		try {
			FileWriter arq = new FileWriter(path);
			BufferedWriter buffWrite = new BufferedWriter(arq);
			
			for(int row = 0; row < ROW; row++) {
				for(int col = 0; col < COLUMN; col++) {
					buffWrite.append(sudoku[col][row].getCellNumber() + " ");
				}
				buffWrite.append("\n");
			}

			buffWrite.close();
			arq.close();
		} catch (IOException e) {
			System.out.println("Erro na criação do arquivo: " + e.getMessage());
			return false;
		} 
		
		return true;
	}

	public boolean isInRow(int row, int number) {
		for (int i = 0; i < COLUMN; i++) {
			if (sudoku[i][row].getCellNumber() == number) {
				return true;
			}

		}
		return false;
	}

	public boolean isInColumn(int column, int number) {
		for (int i = 0; i < ROW; i++) {
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

	private boolean isOkForSolve1(int row, int column, int number) {
		return !isInRow(row, number) && !isInColumn(column, number) && !isInSubGrade(row, column, number);
	}

	public boolean solveMethod1() {
		for (int row = 0; row < ROW; row++) {
			for (int col = 0; col < COLUMN; col++) {
				if (sudoku[col][row].getCellNumber() == EMPTY) {
					for (int number = 1; number < 10; number++) {
						if (isOkForSolve1(row, col, number)) {
							sudoku[col][row].addCellNumber(number);
							if (solveMethod1()) {
								return true;
							} else {
								sudoku[col][row].removeCellNumber();
							}
						}
					}
					this.tempoFinal = System.currentTimeMillis();
					return false;
				}
			}
		}
		
		this.tempoFinal = System.currentTimeMillis();
		return true;
	}

	public void showSudokuBoard() {
		for (int row = 0; row < ROW; row++) {
			System.out.print("| ");
			for (int col = 0; col < COLUMN; col++) {
				System.out.print(sudoku[col][row].getCellNumber() + " | ");
			}
			System.out.println();
			System.out.println("-------------------------------------");
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
		for (int col = 0; col < COLUMN; col++) {
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
		for (int row = 0; row < COLUMN; row++) {
			for (int col = 0; col < ROW; col++) {
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

	private boolean method2() {
		for (int row = 0; row < ROW; row++) {
			for (int col = 0; col < COLUMN; col++) {
				if (sudoku[col][row].getCellNumber() == EMPTY) {
					for (int number = 0; number < sudoku[col][row].getListPossibleNumbers().size(); number++) {
						if (isOkForSolve2(row, col, sudoku[col][row].getListPossibleNumbers().get(number))) {
							sudoku[col][row].addPossibleCellNumber(number);
							if (method2()) {
								return true;
							} else {
								sudoku[col][row].removeCellNumber();
							}
						}
					}
					this.tempoFinal = System.currentTimeMillis();
					return false;
				}
			}
		}
		
		this.tempoFinal = System.currentTimeMillis();
		return true;
	}

	public boolean solveMethod2() {
		analyseSudoku();
		return method2();
	}

}
