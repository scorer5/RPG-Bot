
public class RPGBox {
	private String box = "";
	private String header = "";
	private String footer = "";
	private final String HEADER_PRESET_START = "!===== [ ";
	private final String HEADER_PRESET_END = " ] =====!";
	private String EMPTY_FOOTER = "!============!";
	private final String newLine = "\n";
	private String[] lines;	
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
		box += this.EMPTY_FOOTER;
		box += "```";
	}
	
	public void setHeader(String header) {
		this.header = header;
	}
	
	public String getHeader() {
		return this.header;
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
