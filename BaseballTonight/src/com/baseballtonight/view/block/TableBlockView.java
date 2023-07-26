package com.baseballtonight.view.block;

import com.baseballtonight.dao.reservation.BlockDAO;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.PageControll;

public class TableBlockView implements BlockView{
	public static int crt_page = 1;
	
	static BlockDAO dao = new BlockDAO();

	
	// PageControll enum has 'CHOICE' too, but it cann't reach here. (already handled case)
	@SuppressWarnings("incomplete-switch") 
	@Override
	public void showBlock(String pageLocation) {
		PageControll location = PageControll.getPageControllByCmd(pageLocation);
			switch(location) {
			case PREVIOUS:
				
				if(crt_page == 1){
					Coloring.purpleOut("이전 페이지가 없습니다.");
				} else if(crt_page == 2){
					showFirstPage();
				}
				
				break;

				
			case NEXT:
				
				if(crt_page == 1){
					showSecondPage();
				} else if(crt_page == 2){
					Coloring.purpleOut("다음 페이지가 없습니다.");
				}
				
				break;
		}
	}
	
	@Override
	public void showFirstPage() {
		crt_page = 1;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_110 = dao.getAvailableSeatNumOrSoldOutString(110);
		String seat_111 = dao.getAvailableSeatNumOrSoldOutString(111);
		String seat_212 = dao.getAvailableSeatNumOrSoldOutString(212);
		String seat_213 = dao.getAvailableSeatNumOrSoldOutString(213);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "						[경기장 필드 방향]						 \n"
				+ "	(1/2)page\n"
				+ "+========================================+\n"
				+ "|					|					|\n"
				+ "|		111			|		110			|\n"
				+ "|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|\n"
				+ "+========================================+\n"
				+ "|					|					|\n"
				+ "|		213			|		212			|\n"
				+ "|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|\n"
				+ "+========================================+",
			seat_111,
			seat_110,
			seat_213,
			seat_212);

		System.out.println(blockView);
	}
	
	public void showSecondPage() {
		crt_page = 2;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_112 = dao.getAvailableSeatNumOrSoldOutString(112);
		String seat_113 = dao.getAvailableSeatNumOrSoldOutString(113);
		String seat_214 = dao.getAvailableSeatNumOrSoldOutString(214);
		String seat_215 = dao.getAvailableSeatNumOrSoldOutString(215);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "						[경기장 필드 방향]						 \n"
				+ "	(2/2)page\n"
				+ "+========================================+\n"
				+ "|					|					|\n"
				+ "|		113			|		112			|\n"
				+ "|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|\n"
				+ "+========================================+\n"
				+ "|					|					|\n"
				+ "|		215			|		214			|\n"
				+ "|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|\n"
				+ "+========================================+",
			seat_113,
			seat_112,
			seat_215,
			seat_214);

		System.out.println(blockView);
	}
}