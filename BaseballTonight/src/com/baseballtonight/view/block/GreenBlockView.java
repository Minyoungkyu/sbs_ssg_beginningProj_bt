package com.baseballtonight.view.block;

import com.baseballtonight.dao.reservation.BlockDAO;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.PageControll;

public class GreenBlockView implements BlockView {
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
					
				} else if(crt_page == 3){
					
					showSecondPage();
					
				} else if(crt_page == 4){
					
					showThirdPage();
					
				}
				
				break;

				
			case NEXT:
				
				if(crt_page == 1){
					showSecondPage();
					
				} else if(crt_page == 2){
					
					showThirdPage();
					
				} else if(crt_page == 3){
					
					showFourthPage();
					
				} else if(crt_page == 4){
					
					Coloring.purpleOut("다음 페이지가 없습니다.");
					
				}
				
				break;
		}
	}
	
	@Override
	public void showFirstPage() {
		crt_page = 1;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_401 = dao.getAvailableSeatNumOrSoldOutString(401);
		String seat_402 = dao.getAvailableSeatNumOrSoldOutString(402);
		String seat_403 = dao.getAvailableSeatNumOrSoldOutString(403);
		String seat_404 = dao.getAvailableSeatNumOrSoldOutString(404);
		String seat_405 = dao.getAvailableSeatNumOrSoldOutString(405);
		String seat_406 = dao.getAvailableSeatNumOrSoldOutString(406);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "		  				 								 [경기장 필드 방향]						 \n"
				+ "	(1/4)page														 \n"
				+ "+========================================================================================================================+\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|		401			|		402			|		403			|		404			|		405			|		406			|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "+========================================================================================================================+\n",
			seat_401,
			seat_402,
			seat_403,
			seat_404,
			seat_405,
			seat_406);

		System.out.println(blockView);
	}
	
	public void showSecondPage() {
		crt_page = 2;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_407 = dao.getAvailableSeatNumOrSoldOutString(407);
		String seat_408 = dao.getAvailableSeatNumOrSoldOutString(408);
		String seat_409 = dao.getAvailableSeatNumOrSoldOutString(409);
		String seat_410 = dao.getAvailableSeatNumOrSoldOutString(410);
		String seat_411 = dao.getAvailableSeatNumOrSoldOutString(411);
		String seat_412 = dao.getAvailableSeatNumOrSoldOutString(412);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "		  				 								 [경기장 필드 방향]						 \n"
				+ "	(2/4)page														 \n"
				+ "+========================================================================================================================+\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|		407			|		408			|		409			|		410			|		411			|		412			|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "+========================================================================================================================+\n",
			seat_407,
			seat_408,
			seat_409,
			seat_410,
			seat_411,
			seat_412);

		System.out.println(blockView);
	}
	
	public void showThirdPage() {
		crt_page = 3;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_413 = dao.getAvailableSeatNumOrSoldOutString(413);
		String seat_414 = dao.getAvailableSeatNumOrSoldOutString(414);
		String seat_415 = dao.getAvailableSeatNumOrSoldOutString(415);
		String seat_416 = dao.getAvailableSeatNumOrSoldOutString(416);
		String seat_417 = dao.getAvailableSeatNumOrSoldOutString(417);
		String seat_418 = dao.getAvailableSeatNumOrSoldOutString(418);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "		  				 								 [경기장 필드 방향]						 \n"
				+ "	(3/4)page																 \n"
				+ "+========================================================================================================================+\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|		413			|		414			|		415			|		416			|		417			|		418			|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "+========================================================================================================================+\n",
			seat_413,
			seat_414,
			seat_415,
			seat_416,
			seat_417,
			seat_418);

		System.out.println(blockView);
	}
	
	public void showFourthPage() {
		crt_page = 4;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_419 = dao.getAvailableSeatNumOrSoldOutString(419);
		String seat_420 = dao.getAvailableSeatNumOrSoldOutString(420);
		String seat_421 = dao.getAvailableSeatNumOrSoldOutString(421);
		String seat_422 = dao.getAvailableSeatNumOrSoldOutString(422);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "		  				 			  [경기장 필드 방향]						 \n"
				+ "	(4/4)page																 \n"
				+ "+================================================================================+\n"
				+ "|					|					|					|					|\n"
				+ "|		419			|		420			|		421			|		422			|\n"
				+ "|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|\n"
				+ "+================================================================================+\n",
			seat_419,
			seat_420,
			seat_421,
			seat_422);

		System.out.println(blockView);
	}
	
}
