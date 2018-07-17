import java.util.ArrayList;
//import java.util.Scanner;
/**
 * 
 * redesign of the paragraphs as the readers expected.
 * the input type is file_name line_length and null or <align_mode>
 * 
 * @author xueying ma(150011754)
 *
 */
public class AlignText {
	/**
	* separates the paragraph into different section then use the intoLines method get the lines and return the result.
	* @param text the string array of each section
	* @param column a integer of the max line lentgh
	* @param align a stirng abtou type of align mode
	* @return The arrayList of lines in string
	*/
	public static ArrayList<String> saveInArray(String[] text, int column, String align) {
		ArrayList<String> lines = new ArrayList<String>();
		int times = text.length;
		int i;
		//separates the paragraph in to different section
		for (i = 0; i < times; i++) {
			lines.addAll(IntoLines(text[i], column, align));
		}
		return lines;
	}
	/**
	* get the string of one section and find the max length character.
	* fist to find the next char of the max length point to make position that the max length is just end at a word.
	* if not then move back to find the whitespace,make sure end point not move back to the start point at the whole time
	* third if the word is max then the length of the line ,regard the word as a line. this line don't need any align type
	* Finally the last line of the section would not have justify mode
	* during the steps,finding the end of the line call the method type to make align mode
	* return a ArralyList of lines which is the lines of this section
	* @param section the string of each section
	* @param column a integer of the max line length
	* @param align a string about type of align mode
	* @return The arrayList of lines in string about those section
	*/
	public static ArrayList<String> IntoLines(String section, int column, String align) {
		int end = column - 1, start = 0, wordL = 0;
		String aLine;
		ArrayList<String> line = new ArrayList<String>();
		while (end < section.length() - 1) {
			//move the end as a point and traverse all the section. wordL is the last word's length in the line
			//start is the line's start point and make sure end point must bigger then start
			wordL = 0;
			while (start < end) {
				//to check the max length just the end of a word
				if (end - start == column - 1) {
					if (section.charAt(end + 1) == ' ') {
						aLine = type(section.substring(start, end + 1), align, column);
						line.add(aLine);
						end++;
						break;
					}
				}
				if (section.charAt(end) == ' ') {
					aLine = type(section.substring(start, end), align, column);
					line.add(aLine);
					break;
				}
				end--;
				wordL++;
			}
			//the word longer then line's length
			while (wordL >= column - 1 && end < section.length() - 1) {
				if (section.charAt(end) == ' ') {
					line.add(section.substring(start, end));
					break;
				}
				end++;
			}
			//move to the next line
			if (end != section.length() - 1) {
			start = end + 1;
			end = start + column - 1;
			}
		}
		//the last line of this section
		if (!align.toLowerCase().equals("j")) {
			aLine = type(section.substring(start, section.length()), align, column);
			line.add(aLine);
		} else {
			line.add(section.substring(start, section.length())); }
		return line;
	}
	/**
	* Attempts to distribute each line to 4 align mode.
	* @param aLine string of the line
	* @param align String of the align mode
	* @param max a integer of the max line length
	* @return The line of it's align mode
	*/
	public static String type(String aLine, String align, int max) {
		switch (align.toLowerCase()) {
		case "r":
			aLine = right(aLine, max);
			break;
		case "l":
			aLine = left(aLine, max);
			break;
		case "c":
			aLine = center(aLine, max);
			break;
		case "j":
			aLine = justify(aLine, max);
			break;
		}
		return aLine;
	}
	/**
	* translate the ArrayList to the String array.
	* @param line the ArrayList of the lines
	* @return the string array of the paragraphs
	*/
	public static String[] translate(ArrayList<String> line) {
		String[] paragraphs = new String[line.size()];
		for (int i = 0; i < line.size(); i++) {
			paragraphs[i] = line.get(i);
		}
		return paragraphs;
	}
	/**
	* the display right align mode by adding whitespace to left of the line.
	* @param aLine string of the line
	* @param max a integer of the max line length
	* @return The line of it's align mode
	*/
	public static String right(String aLine, int max) {
		int toAdd, i;
		toAdd = max - aLine.length();
		if (toAdd > 0) {
			for (i = 0; i < toAdd; i++) {
				aLine = " " + aLine;
			 }
			}
		return aLine;
	}
	/**
	* the display left align mode by adding whitespace to right of the line.
	* @param aLine string of the line
	* @param max a integer of the max line length
	* @return The line of it's align mode
	*/
	public static String left(String aLine, int max) {
		int toAdd, i;
		toAdd = max - aLine.length();
		if (toAdd > 0) {
			for (i = 0; i < toAdd; i++) {
				aLine = aLine + " ";
			}
		}
		return aLine;
	}
	/**
	* the display centre align mode by adding whitespace to two side of the line.
	* if there are the odd whitespace add one to the left
	* @param aLine string of the line
	* @param max a integer of the max line length
	* @return The line of it's align mode
	*/
	public static String center(String aLine, int max) {
		int toAdd, i;
		toAdd = max - aLine.length();
		if (toAdd > 0) {
			if (toAdd % 2 == 0) {
				for (i = 0; i < toAdd / 2; i++) {
					aLine = aLine +  " ";
					aLine = " " + aLine;
				}
			} else {
				for (i = 0; i < (toAdd - 1) / 2; i++) {
					aLine = aLine +  " ";
					aLine = " " + aLine;
				}
				aLine = " " + aLine;
			}
		}
		return aLine;
	}
	/**
	* the display justify align mode by adding whitespace to each whitespace form the end,if the toAdd whitespace more then the original have do again.
	* @param aLine string of the line
	* @param max a integer of the max line length
	* @return The line of it's align mode
	*/
	public static String justify(String aLine, int max) {
		int toAdd, n, loopTime;
		toAdd = max - aLine.length();
		loopTime = 0;
		while (toAdd != 0) {
			for (n = aLine.length() - 1; n > 0; n--) {
				if (aLine.charAt(n) == ' ' && toAdd != 0) {
					aLine = aLine.substring(0, n) + " " + aLine.substring(n, aLine.length());
					toAdd--;
					loopTime++;
				}
			}
			if (loopTime == 0) {
				return aLine;
			}
		}
		return aLine;
	}
	/**
	 * the main method try to read the arguments form the command line After figuring out how many arguments.
	 * @param args used to read the arguments
	 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int inputSize = 3;
		try {
			//Attempts to read arguments and get the paragraph form the class FileUtil
			//if don't have the 3rd arguments, set the align as right
			String align = "r";
			if (args.length == inputSize) {
				align = args[2];
			}
		String[] texts = FileUtil.readFile(args[0]);
		int column = Integer.parseInt(args[1]);
		//sent the paragraph line's length and align to the method saveInArray which to receive a ArrayList of each line
		//then translate the ArrayList to a string array. and print them out
		ArrayList<String> line = saveInArray(texts, column, align);
		String[] paragraphs = translate(line);
		for (int i = 0; i < paragraphs.length; i++) {
		System.out.println(paragraphs[i]); }
		} catch (Exception e) {
			if (args.length != inputSize) {
		System.out.println("usage: java AlignText file_name line_length");
		} else {
			System.out.println("usage: java AlignText file_name line_length <align_mode>");
		}
		}
	}

}
