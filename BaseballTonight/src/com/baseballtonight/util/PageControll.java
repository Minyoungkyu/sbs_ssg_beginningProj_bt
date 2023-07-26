package com.baseballtonight.util;

public enum PageControll {
	PREVIOUS("a"),
	NEXT("f"), 
	CHOICE("s");
	
	
	public final String cmd;
	
	PageControll(String cmd){
		this.cmd = cmd;
	}
	
	public static PageControll getPageControllByCmd(String cmd) {
		
		if(PREVIOUS.cmd.equals(cmd)){
			return PREVIOUS;
			
		} else if(NEXT.cmd.equals(cmd)){
			return NEXT;
			
		} else if(CHOICE.cmd.equals(cmd)){
			return CHOICE;
			
		} else {
			return null;
		}
	} 
}
