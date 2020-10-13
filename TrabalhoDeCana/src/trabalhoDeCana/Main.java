package trabalhoDeCana;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sudoku sudoku = new Sudoku();
		sudoku.fillSudoku(args[1]);

		if(args[0].equals("1")) {
			if(sudoku.solveMethod1() && sudoku.saveSolvedSudoku(args[2])) {
				System.out.println("Arquivo criado com sucesso!");
			}
		} else if(args[0].equals("2")) {
			if(sudoku.solveMethod2() && sudoku.saveSolvedSudoku(args[2])) {
				System.out.println("Arquivo criado com sucesso!");
			}
		}
	}

}
