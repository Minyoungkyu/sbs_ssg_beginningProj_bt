package com.baseballtonight.util;

public class Coloring {
    public static final String red      = "\u001B[31m" ;
    public static final String green    = "\u001B[32m" ;
    public static final String yellow   = "\u001B[33m" ;
    public static final String blue     = "\u001B[34m" ;
    public static final String purple   = "\u001B[35m" ;
    public static final String cyan     = "\u001B[36m" ;
    public static final String exit     = "\u001B[0m" ;
    
    public static void redOut(String text) {
    	System.out.println(red + text+ exit);
    }
    public static void greenOut(String text) {
    	System.out.println(green + text + exit);
    }
    public static void yellowOut(String text) {
    	System.out.println(yellow + text + exit);
    }
    public static void blueOut(String text) {
    	System.out.println(blue + text + exit);
    }
    public static void purpleOut(String text) {
    	System.out.println(purple + text + exit);
    }
    public static void cyanOut(String text) {
    	System.out.println(cyan + text + exit);
    }
    
    public static String getRed(String text) {
    	return Coloring.red + text + Coloring.exit;
    }
    public static String getGreen(String text) {
    	return Coloring.green + text + Coloring.exit;
    }
    public static String getYellow(String text) {
    	return Coloring.yellow + text + Coloring.exit;
    }
    public static String getBlue(String text) {
    	return Coloring.blue + text + Coloring.exit;
    }
    public static String getPurple(String text) {
    	return Coloring.purple + text + Coloring.exit;
    }
    public static String getCyan(String text) {
    	return Coloring.cyan + text + Coloring.exit;
    }

}
