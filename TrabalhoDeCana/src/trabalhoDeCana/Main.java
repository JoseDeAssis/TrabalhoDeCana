package trabalhoDeCana;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sudoku sudoku = new Sudoku();
		sudoku.fillSudoku("C:\\Users\\ze_le\\Documents\\TrabalhoCana\\sudokuQuestions\\s01c.txt");

		if (sudoku.solve()) 
			sudoku.showSudokuBoard();
//		sudoku.showPossiblities();
	}

}
