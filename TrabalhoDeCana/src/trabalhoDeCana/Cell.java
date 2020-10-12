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
	
	public void addCellNumber() {
		this.cellNumber = listPossibleNumbers.get(0);
	}
	
	public void addCellNumber(int number) {
		this.cellNumber = number;
	}
	
	public void removeCellNumber() {
		this.cellNumber = 0;
		
		if(!this.listPossibleNumbers.isEmpty())
			this.listPossibleNumbers.remove(0);
	}
	
}
