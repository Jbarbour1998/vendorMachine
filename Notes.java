package project;

/**
 * This class inherits all methods from payment and has a few of its own
 * @author Holly.B 40324690
 *
 */
public class Notes extends payment {
	
	/**
	 * This takes in the notes, validates them and adds them to the total in the note box
	 * @param note - the value of note that is being inserted into the machine. 
	 */
	public void addNotes(int note){
		String coinCommas = openFile();
		String[] coinSep = coinCommas.split(",");
		int tpound = Integer.parseInt(coinSep[0].trim());
		int opound = Integer.parseInt(coinSep[1].trim());
		int fifty = Integer.parseInt(coinSep[2].trim());
		int twenty = Integer.parseInt(coinSep[3].trim());
		int ten = Integer.parseInt(coinSep[4].trim());
		int cashbox = Integer.parseInt(coinSep[5].trim());
		int NotesBox = Integer.parseInt(coinSep[6].trim());
		int cardcount = Integer.parseInt(coinSep[7].trim());
		
		if (note == 500 || note == 1000 || note == 2000 ) {
			NotesBox += note;
		}
		else {
			System.out.println("Invalid Note");
		}
		
		String newcoinline= tpound + ","+ opound+","+ fifty+","+twenty+","+ten+","+cashbox +","+NotesBox+","+cardcount;
		writeFile(newcoinline);
	}
	/**
	 * This  resets the notes box to 0 to simulate the emptying of the notes
	 */
	public  void emptyNotesCashBox() {
		String coinCommas = openFile();
		String[] coinSep = coinCommas.split(",");
		int tpound = Integer.parseInt(coinSep[0].trim());
		int opound = Integer.parseInt(coinSep[1].trim());
		int fifty = Integer.parseInt(coinSep[2].trim());
		int twenty = Integer.parseInt(coinSep[3].trim());
		int ten = Integer.parseInt(coinSep[4].trim());
		int cashbox = Integer.parseInt(coinSep[5].trim());
		int NotesBox = Integer.parseInt(coinSep[6].trim());
		int cardcount = Integer.parseInt(coinSep[7].trim());
		
		NotesBox=0;
		String newcoinline= tpound + ","+ opound+","+ fifty+","+twenty+","+ten+","+cashbox +","+NotesBox+","+cardcount;
		writeFile(newcoinline);
	}
	
	/**
	 * This gets the value within the notes box and returns the value.
	 * @return NotesBox  which is  the value  of the total notes being held
	 */
	public int cashboxValue(){
		String coinCommas = openFile();
			String[] coinSep = coinCommas.split(",");
			int NotesBox = Integer.parseInt(coinSep[6].trim());
			return NotesBox;
	}
}
