import org.apache.commons.lang3.ArrayUtils;

public class RPGBox {
	private String box = "";
	private String header = "";
	private String topFull = "";
	private String bottomFull = "";
	private boolean headerChanged = false;
	private String oldHeader = "";		//Actually new Header	
	private final String HEADER_PRESET_START = "!===== [ ";
	private final String HEADER_PRESET_END = " ] =====!";
	private final String newLine = "\n";
	private String[] lines = new String[0];	

	/**
	 * Simple empty box with a title
	 * @param header
	 */
	public RPGBox(String header) {
		constructEmptyHeaderBox(header);
	}		

	/**
	 * Cunstructs Box with only a title
	 * @param header the title
	 */
	private void constructEmptyHeaderBox(String header) {
		this.header = header;
		oldHeader = header;
		box += "```diff\n";
		topFull += this.HEADER_PRESET_START;		
		topFull += header;
		topFull += this.HEADER_PRESET_END;	
		box += topFull;
		box += this.newLine;
		String temp = "";
		for(int i = 0; i < header.length(); i++) {
			temp += "=";
		}
		bottomFull += "!================"+temp+"!";
		box += bottomFull;
		box += "```";
	}

	/**
	 * Changes the header (title) of the Box
	 * @param header the new title
	 */
	public void setHeader(String header) {
		oldHeader = header;
		this.header = header;
		headerChanged = true;
		refreshBox();
	}

	/**
	 * Gets the current header (title) 
	 * @return current header (title)
	 */
	public String getHeader() {
		return this.header;
	}

	/**
	 * Adds a string to the string array
	 * Automatically expands array if it is too small
	 * @param element
	 */
	public void addElement(String element) {
		if(lines.length == 0) {
			lines = new String[1];
			lines[0] = element;
		}else {
			boolean full = false;
			int tempInt = 0;

			for(int i = 0; i < lines.length; i++) {
				if(lines[i] != null) {
					tempInt++;
				}
			}

			if(tempInt == lines.length) {
				full = true;
			}

			if(!full) {
				for(int i = 0; i < lines.length; i++) {
					if(lines[i] != null) {
						lines[i] = element;
					}
				}
			}else {				
				String[] newLines = new String[lines.length+1];				
				for(int i = 0; i < lines.length; i++) {
					newLines[i] = lines[i];
				}
				newLines[newLines.length-1] = element;

				lines = newLines;
			}
		}
	}
	
	public void removeLine(int index) {
		if(lines.length > 0) {
			lines = ArrayUtils.removeElement(lines, lines[index]);
			refreshBox();
		}
	}

	/**
	 * Printes the lines array
	 */
	public void printLines() {
		for(int i = 0; i < lines.length; i++) {
			System.out.println("Lines ["+i+"]: "+lines[i]);
		}
	}

	/**
	 * Refreshes the box and extends the size of the header/footer if the line is bigger
	 * @param line the new line to compare incase its bigger
	 */
	public void refreshBox() {
		//if((line.length()+2 > this.bottomFull.length()) && oldHeader.length() == header.length()) {	
//		if(header.length() + 12 >= line.length() + 2 && headerChanged == false) {
//			System.out.println("1");
//			box = "";
//			topFull = "";
//			bottomFull = "";
//			String temp = "";
//			for(int i = 0; i < 12+header.length(); i++) {
//				temp += "=";
//			}
//			bottomFull += "!"+temp+"!";
//			topFull = "!=== [ "+header+" ] ===!";				
//			box += topFull;
//			box += this.newLine;			
//			box += bottomFull;
//			box += "```";	
//		}else if(headerChanged == false) {			
//			box = "";
//			topFull = "";
//			bottomFull = "";			
//			String temp = "";
//			for(int i = 0; i < line.length(); i++) {
//				temp += "=";
//			}
//			bottomFull += "!"+temp+"!";
//			box += "```diff\n";		
//			String temp2 = "!";
//			for(int i = 0; i < (bottomFull.length()-header.length()-8)/2; i++) {
//				temp2 += "=";
//			}
//			temp2 += " [ "+header+" ] ";
//			for(int i = 0; i < (bottomFull.length()-header.length()-8)/2; i++) {
//				temp2 += "=";
//			}
//			if((isOdd(header.length()) == false && isOdd(line.length()) == true) || (isOdd(header.length()) == true && isOdd(line.length()) == false) ) {				
//				temp2 += "=";
//			}
//			temp2 += "!";
//			topFull = temp2;	
//			box += topFull;
//			box += this.newLine;			
//			box += bottomFull;
//			box += "```";	
//		}else if(headerChanged == true) {
			headerChanged = false;
			int temp = 0;
			for (int counter = 1; counter < lines.length; counter++)
			{
			     if (lines[counter].length() > temp)
			     {
			      temp = lines[counter].length();
			     }
			}
			String tempString = "";
			for(int i = 0; i < temp; i++)
				tempString += "=";			
			box = "";
			topFull = "";
			bottomFull = "";
			String temp2 = "";
			for(int i = 0; i < tempString.length()-2; i++) {
				temp2 += "=";
			}
			bottomFull = "!"+temp2+"!";
			box += "```diff\n";		
			String temp3 = "!===";
			for(int i = 0; i < (bottomFull.length()-header.length()-14)/2; i++) {
				temp3 += "=";
			}
			temp3 += " [ "+header+" ] ";
			for(int i = 0; i < (bottomFull.length()-header.length()-14)/2; i++) {
				temp3 += "=";
			}
			if((isOdd(header.length()) == false && isOdd(tempString.length()) == true) || (isOdd(header.length()) == true && isOdd(tempString.length()) == false) ) {				
				temp3 += "=";
			}
			temp3 += "===!";
			topFull = temp3;
			box += topFull;
			box += this.newLine;			
			box += bottomFull;
		//}
	}
	
	/**
	 * Adds a grey colored line in the box
	 * @param line the line to add
	 */
	public void addLine(String line) {			
		addElement("% "+line);
		refreshBox();
	}

	/**
	 * Determens if an int is odd
	 * @param n the int to check
	 * @return true if its odd, false if it isnt
	 */
	private boolean isOdd(int n) {
		if (n % 2 == 1) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Adds a green colored line in the box
	 * @param line the line to add
	 */
	public void addGreenLine(String line) {		
		addElement("+ "+line);		
		refreshBox();
	}

	/**
	 * Adds a red colored line in the box
	 * @param line the Line
	 */
	public void addRedLine(String line) {		
		addElement("- "+line);
		refreshBox();
	}	

	/**
	 * Returns the inner lines of the box
	 * @return inner lines of Box
	 */
	public String[] getLines() {
		return this.lines;
	}

	/**
	 * Returns the final Box, ready to print on discord
	 * @return the box in String format
	 */
	public String getBox() {
		String r = "```diff\n";
		r += this.topFull + "\n";		
		for(int i = 0; i < lines.length; i++) {
			r += lines[i] + "\n";
		}			
		r += this.bottomFull+"```";
		return r;		
	}
}
