package com.baseballtonight.view.block;

import com.baseballtonight.dao.reservation.BlockDAO;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.PageControll;

public class NavyBlockView implements BlockView {
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
					
				}  else if(crt_page == 5){
					
					showFourthPage();
					
				}  else if(crt_page == 6){
					
					showFifthPage();
					
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
					
					showFifthPage();
					
				}  else if(crt_page == 5){
					
					showSixthPage();
					
				}  else if(crt_page == 6){
					
					Coloring.purpleOut("다음 페이지가 없습니다.");
					
				}
				
				break;
		}
	}
	
	@Override
	public void showFirstPage() {
		crt_page = 1;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_301 = dao.getAvailableSeatNumOrSoldOutString(301);
		String seat_302 = dao.getAvailableSeatNumOrSoldOutString(402);
		String seat_303 = dao.getAvailableSeatNumOrSoldOutString(303);
		String seat_304 = dao.getAvailableSeatNumOrSoldOutString(304);
		String seat_305 = dao.getAvailableSeatNumOrSoldOutString(305);
		String seat_306 = dao.getAvailableSeatNumOrSoldOutString(306);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "		  				 								 [경기장 필드 방향]						 \n"
				+ "	(1/6)page														 \n"
				+ "+========================================================================================================================+\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|		306			|		305			|		304			|		303			|		302			|		301			|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "+========================================================================================================================+\n",
				seat_306,
				seat_305,
				seat_304,
				seat_303,
				seat_302,
				seat_301);

		System.out.println(blockView);
	}
	
	public void showSecondPage() {
		crt_page = 2;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_307 = dao.getAvailableSeatNumOrSoldOutString(307);
		String seat_308 = dao.getAvailableSeatNumOrSoldOutString(308);
		String seat_309 = dao.getAvailableSeatNumOrSoldOutString(309);
		String seat_310 = dao.getAvailableSeatNumOrSoldOutString(310);
		String seat_311 = dao.getAvailableSeatNumOrSoldOutString(311);
		String seat_312 = dao.getAvailableSeatNumOrSoldOutString(312);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "		  				 								 [경기장 필드 방향]						 \n"
				+ "	(2/6)page														 \n"
				+ "+========================================================================================================================+\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|		312			|		311			|		310			|		309			|		308			|		307			|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "+========================================================================================================================+\n",
				seat_312,
				seat_311,
				seat_310,
				seat_309,
				seat_308,
				seat_307
			);

		System.out.println(blockView);
	}
	
	public void showThirdPage() {
		crt_page = 3;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_313 = dao.getAvailableSeatNumOrSoldOutString(313);
		String seat_314 = dao.getAvailableSeatNumOrSoldOutString(314);
		String seat_315 = dao.getAvailableSeatNumOrSoldOutString(315);
		String seat_316 = dao.getAvailableSeatNumOrSoldOutString(316);
		String seat_317 = dao.getAvailableSeatNumOrSoldOutString(317);
		String seat_318 = dao.getAvailableSeatNumOrSoldOutString(318);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "		  				 								 [경기장 필드 방향]						 \n"
				+ "	(3/6)page																 \n"
				+ "+========================================================================================================================+\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|		318			|		317			|		316			|		315			|		314			|		313			|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s 		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|					|					|\n"
				+ "+========================================================================================================================+\n",
				seat_318,
				seat_317,
				seat_316,
				seat_315,
				seat_314,
				seat_313
			);

		System.out.println(blockView);
	}
	
	public void showFourthPage() {
		crt_page = 4;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_319 = dao.getAvailableSeatNumOrSoldOutString(319);
		String seat_320 = dao.getAvailableSeatNumOrSoldOutString(320);
		String seat_321 = dao.getAvailableSeatNumOrSoldOutString(321);
		String seat_322 = dao.getAvailableSeatNumOrSoldOutString(322);
		String seat_323 = dao.getAvailableSeatNumOrSoldOutString(323);
		String seat_324 = dao.getAvailableSeatNumOrSoldOutString(324);
		
		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
			+ "		  				 								 [경기장 필드 방향]						 \n"
			+ "	(4/6)page																 \n"
			+ "+========================================================================================================================+\n"
			+ "|					|					|					|					|					|					|\n"
			+ "|		324			|		323			|		322			|		321			|		320			|		319			|\n"
			+ "|					|					|					|					|					|					|\n"
			+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s 		|	예매가능: %s		|	예매가능: %s		|\n"
			+ "|					|					|					|					|					|					|\n"
			+ "+========================================================================================================================+\n",
			seat_324,
			seat_323,
			seat_322,
			seat_321,
			seat_320,
			seat_319
			);
		
		System.out.println(blockView);
	}
	
	public void showFifthPage() {
		crt_page = 5;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_325 = dao.getAvailableSeatNumOrSoldOutString(325);
		String seat_326 = dao.getAvailableSeatNumOrSoldOutString(326);
		String seat_327 = dao.getAvailableSeatNumOrSoldOutString(327);
		String seat_328 = dao.getAvailableSeatNumOrSoldOutString(328);
		String seat_329 = dao.getAvailableSeatNumOrSoldOutString(329);
		String seat_330 = dao.getAvailableSeatNumOrSoldOutString(330);
		
		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
			+ "		  				 								 [경기장 필드 방향]						 \n"
			+ "	(5/6)page																 \n"
			+ "+========================================================================================================================+\n"
			+ "|					|					|					|					|					|					|\n"
			+ "|		330			|		329			|		328			|		327			|		326			|		325			|\n"
			+ "|					|					|					|					|					|					|\n"
			+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s 		|	예매가능: %s		|	예매가능: %s		|\n"
			+ "|					|					|					|					|					|					|\n"
			+ "+========================================================================================================================+\n",
			seat_330,
			seat_329,
			seat_328,
			seat_327,
			seat_326,
			seat_325
			);
		
		System.out.println(blockView);
	}
	
	public void showSixthPage() {
		crt_page = 6;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_331 = dao.getAvailableSeatNumOrSoldOutString(331);
		String seat_332 = dao.getAvailableSeatNumOrSoldOutString(332);
		String seat_333 = dao.getAvailableSeatNumOrSoldOutString(333);
		String seat_334 = dao.getAvailableSeatNumOrSoldOutString(334);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "		  				 			  [경기장 필드 방향]						 \n"
				+ "	(6/6)page																 \n"
				+ "+================================================================================+\n"
				+ "|					|					|					|					|\n"
				+ "|		334			|		333			|		332			|		331			|\n"
				+ "|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|\n"
				+ "+================================================================================+\n",
				seat_334,
				seat_333,
				seat_332,
				seat_331
			);

		System.out.println(blockView);
	}
	
}
