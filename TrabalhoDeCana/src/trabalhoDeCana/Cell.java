package trabalhoDeCana;

import java.util.List;
import java.util.ArrayList;

public class Cell {
	private List<Integer> listPossibleNumbers;
	private int cellNumber;
	
	public Cell() {
		this.listPossibleNumbers = new ArrayList<Integer>();
	}
	
	public List<Integer> getListPossibleNumbers() {
		return listPossibleNumbers;
	}

	public int getCellNumber() {
		return cellNumber;
	}

	public void addPossibleNumber(int number) {
		this.listPossibleNumbers.add(number);
	}
	
	public void addPossibleCellNumber(int index) {
		this.cellNumber = listPossibleNumbers.get(index);
	}
	
	public void addCellNumber(int number) {
		this.cellNumber = number;
	}
	
	public void removeCellNumber() {
		this.cellNumber = 0;
	}
	
}
