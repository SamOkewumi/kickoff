package com.bptn.project.kickoff;

/*
 * A class that stores all the ANSI color codes as static variables so it can be referenced throughout the different files. 
 * Allows for the utilization of colors outside the default black and white.
 * Adds visual enhancements to the console printouts. 
 */

public class Color {
	
	//Regular
	public static final String RESET = "\u001B[0m";   //resets the color back to the default. 
	public static final String BLACK = "\u001B[30m";  // BLACK
	public static final String RED = "\u001B[31m";    // RED
	public static final String GREEN = "\u001B[32m";  // GREEN
	public static final String YELLOW = "\u001B[33m"; // YELLOW
	public static final String BLUE = "\u001B[34m";   // BLUE
	public static final String PURPLE = "\u001B[35m"; // PURPLE
	public static final String CYAN = "\u001B[36m";   // CYAN
	public static final String WHITE = "\u001B[37m";  // WHITE
	
	//Background
	public static final String BLACK_BACKGROUND = "\u001B[40m";  // BLACK
	public static final String RED_BACKGROUND = "\u001B[41m";    // RED
	public static final String GREEN_BACKGROUND = "\u001B[42m";  // GREEN
	public static final String YELLOW_BACKGROUND = "\u001B[43m"; // YELLOW
	public static final String BLUE_BACKGROUND = "\u001B[44m";   // BLUE
	public static final String PURPLE_BACKGROUND = "\u001B[45m"; // PURPLE
	public static final String CYAN_BACKGROUND = "\u001B[46m";   // CYAN
	public static final String WHITE_BACKGROUND = "\u001B[47m";  // WHITE
	
	//Bold
	public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE
    
    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
    
    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
    
    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m"; // BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";   // RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m"; // GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";  // BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m";// PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m"; // WHITE
    
    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

}
