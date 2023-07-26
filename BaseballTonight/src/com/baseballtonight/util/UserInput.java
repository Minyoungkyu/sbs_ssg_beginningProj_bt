package com.baseballtonight.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class UserInput {

	public static boolean receiveYesOrNo() throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print(">>>");
			String userAnswer = rd.readLine().toLowerCase();
			if(userAnswer.equals("y")) {
				return true;
			} else if(userAnswer.equals("n")) {
				return false;
			} else {
				Coloring.redOut("유효하지 않은 답변입니다. 다시 입력해주십시오.\n");
			}
		}
	}

	public static String receiveSeatType() {
		HashSet<String> seatType_set = new HashSet<>();
		seatType_set.add("premium");
		seatType_set.add("table");
		seatType_set.add("blue");
		seatType_set.add("red");
		seatType_set.add("navy");
		seatType_set.add("green");

		String seatType = UserInput.receiveContainedString(seatType_set);

		return seatType;
	}

	public static int receiveContainedNum(HashSet<Integer> set) {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				System.out.print(">>>");
				int num = Integer.parseInt(rd.readLine());
				if(!set.contains(num)) {
					throw new NumberFormatException();
				}
				return num;
			} catch(NumberFormatException e) {
				Coloring.redOut("올바른 범위의 수를 입력하여 주십시오.");
			} catch(IOException e) {
				System.out.println("ConsoleUtil.receiveCustomRangeNum throw IOException!: " + e);
			}
		}
	}

	public static String receiveRestrictedString(String first) {
		while(true) {
			String input = UserInput.receiveNoSpaceString();
			if(input.equals(first)) {
				return input;
			} else {
				Coloring.redOut("유효한 입력이 아닙니다.");				
			}
		}
	}
	
	public static String receiveRestrictedString(String first, String second) {
		while(true) {
			String input = UserInput.receiveNoSpaceString();
			if(input.equals(first)) {
				return input;
			} else if(input.equals(second)){
				return input;
			} else {
				Coloring.redOut("유효한 입력이 아닙니다.");				
			}
		}
	}
	
	

	public static String receiveContainedString(HashSet<String> set) {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				System.out.print(">>>");
				String userInput = rd.readLine().toLowerCase();
				if(!set.contains(userInput)) {
					throw new NullPointerException();
				}
				return userInput;
			} catch(NullPointerException e) {
				Coloring.redOut("유효한 입력이 아닙니다.");
			} catch(IOException e) {
				System.out.println("ConsoleUtil.receiveContainedString throw IOException!: " + e);
			}
		}
	}

	public static int receiveLimitedRangeNum(int start, int end) {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				System.out.print(">>>");
				int num = Integer.parseInt(rd.readLine());
				if(num < start || num > end) {
					throw new NumberFormatException();
				}
				return num;
			} catch(NumberFormatException e) {
				Coloring.redOut("올바른 범위의 수를 입력하여 주십시오.");
			} catch(IOException e) {
				System.out.println("ConsoleUtil.receiveCustomRangeNum throw IOException!: " + e);
			}
		}
	}

	public static int receiveNaturalNumber() {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				int num = Integer.parseInt(rd.readLine());
				if(num <= 0) {
					throw new NumberFormatException();
				}
				return num;
			} catch(NumberFormatException e) {
				Coloring.redOut("0보다 큰 정수를 입력하여 주십시오.");
			} catch(IOException e) {
				System.out.println("ConsoleUtil.receiveNaturalNumber throw IOException!");
			}
		}
	}

	public static String receiveNoSpaceString() {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				System.out.print(">>>");
				String input = rd.readLine();
				if(input.contains(" ")) {
					throw new IOException();
				}
				return input;
			} catch(IOException e) {
				Coloring.redOut("공백이 포함될 수 없습니다. 다시 입력해주세요.");
			}
		}
	}
}
