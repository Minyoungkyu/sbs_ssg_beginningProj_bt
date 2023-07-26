package com.baseballtonight.service.reservation;

import java.util.HashSet;

import com.baseballtonight.dao.reservation.BlockDAO;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.PageControll;
import com.baseballtonight.util.UserInput;
import com.baseballtonight.view.block.BlockView;
import com.baseballtonight.view.block.BlueBlockView;
import com.baseballtonight.view.block.GreenBlockView;
import com.baseballtonight.view.block.NavyBlockView;
import com.baseballtonight.view.block.RedBlockView;
import com.baseballtonight.view.block.TableBlockView;

public class BlockService {
	static BlockDAO dao = new BlockDAO();
	static HashSet<String> page_cmd = new HashSet<>();
	static {
		// 페이지 이동 명령어 변경은, PageControll 객체의 cmd field를 변경.
		page_cmd.add(PageControll.PREVIOUS.cmd);
		page_cmd.add(PageControll.NEXT.cmd);
		page_cmd.add(PageControll.CHOICE.cmd);
	}
	
	static BlockView view;
	
	public static void showSeatBlocks(String seatType) {
		/*
		 * (Block)view 객체에, 적절한 seatType view 객체로써 초기화 해준다.
		 */
		switch(seatType){
		case "premium":
			/*  
			 * premium 타입의 경우, 단일 블록이기 때문에 블록 뷰를 보이지 않고 바로 메소드 종료.
			 *  -> 즉시 좌석 뷰로 넘어감.
			 */
			return; 
			
		case "table":
			view = new TableBlockView();
			break;
			
		case "blue":
			view = new BlueBlockView();
			break;
			
		case "red":
			view = new RedBlockView();
			break;
			
		case "navy":
			view = new NavyBlockView();
			break;
			
		case "green":
			view = new GreenBlockView();
			break;
		}
		
		
		// 실질적인 로직부.
		String cmdaside = Coloring.getCyan(PageControll.PREVIOUS.cmd + ": 이전 페이지")  +"  |  "+  Coloring.getYellow(PageControll.CHOICE.cmd + ": 블럭 선택하기")  +"  |  "+ Coloring.getCyan(PageControll.NEXT.cmd + ": 다음 페이지");
		
		// 초기 화면은 first page로 보여준다.
		view.showFirstPage();
		System.out.println(cmdaside);
		
		// 페이지 넘어가기 선택.
		while(true){
			String pageLocation = UserInput.receiveContainedString(page_cmd);
			
			// CHOICE 명령어 실행시, 즉시 블록 뷰 메소드를 종료하고, block 선택 페이지로 이동.
			if(pageLocation.equals(PageControll.CHOICE.cmd)){
				return;
			}

			// page controll 명령어에 따라서 이전, 다음 페이지 보이기.
			view.showBlock(pageLocation);
			System.out.println(cmdaside);
		}
	}

	public static int choiceSeatBlock(String seatType) throws InterruptedException {
		int seatBlock;
		
		// receiveContainedNum 메소드에 사용할 예매가능한 블록 번호 집합.
		HashSet<Integer> seatBlock_set = dao.getAvailableSeatBlockSet(seatType);
		
		// premium석 선택한 경우 기본값인 1을 seatBlock번호로 가진다.
		seatBlock = 1;
		
		
		// premium석과 다른 석의 블록 선택 로직 분기.
		if(!seatType.equals("premium")) {
			Coloring.greenOut(seatType + "석 블럭중, 관람을 원하시는 블럭 번호를 입력하여 주십시오.");
			
			// 출력 메세지 바꾸기 -> "매진된 블록입니다."
			seatBlock = UserInput.receiveContainedNum(seatBlock_set);
		} else {
			Coloring.greenOut("\n" + seatType + "석은 단일 블록입니다. \n"
				+ "별도의 블록을 선택하지 않고, 바로 좌석도 페이지로 이동합니다.");
			Thread.sleep(1000);
		}
		
		return seatBlock;
	}
}









