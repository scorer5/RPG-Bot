
public class RPGBox {
	private String box = "";
	private String header = "";
	private String footer = "";
	private final String HEADER_PRESET_START = "!===== [ ";
	private final String HEADER_PRESET_END = " ] =====!";
	private String EMPTY_FOOTER = "!============!";
	private final String newLine = "\n";
	private String[] lines = new String[0];	
	/**
	 * Simple RPGBox
	 * @param header The title of the Box
	 * @param line the only line of the Box
	 */
	public RPGBox(String header, String line) {
		constructBox(header, line);
	}
	
	/**
	 * Constructs the main Box
	 */
	private void constructBox(String header, String line) {
		box += "```diff\n";
		box += this.HEADER_PRESET_START;		
		box += header;
		box += this.HEADER_PRESET_END;
		box += this.newLine;
		box += "+ "+line;
		box += this.newLine;
		String temp = "";
		for(int i = 0; i < header.length(); i++) {
			temp += "=";
		}
		this.EMPTY_FOOTER = "!================"+temp+"!";
		box += this.EMPTY_FOOTER;
		box += "```";
	}
	
	/**
	 * Changes the header (title) of the Box
	 * @param header the new title
	 */
	public void setHeader(String header) {
		this.header = header;
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
	
	/**
	 * Printes the lines array
	 */
	public void printLines() {
		for(int i = 0; i < lines.length; i++) {
			System.out.println("Lines ["+i+"]: "+lines[i]);
		}
	}
	
	/**
	 * Adds a grey colored line in the box
	 * @param line the line to add
	 */
	public void addLine(String line) {
		
	}
	
	/**
	 * Adds a green colored line in the box
	 * @param line the line to add
	 */
	public void addGreenLine(String line) {
		
	}
	
	/**
	 * Adds a red colored line in the box
	 * @param line the Line
	 */
	public void addRedLine(String line) {
		
	}
	
	/**
	 * Optional, sets a title at the bottom, in fashion of the Header
	 * @param line The line set the Footer
	 */
	public void setFooter(String line) {
		this.footer = line;
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
		return box;
	}
}
