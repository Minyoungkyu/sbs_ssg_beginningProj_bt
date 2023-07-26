package com.baseballtonight.view.block;

import com.baseballtonight.dao.reservation.BlockDAO;
import com.baseballtonight.util.Coloring;
import com.baseballtonight.util.PageControll;

public class RedBlockView implements BlockView{
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
		String seat_101 = dao.getAvailableSeatNumOrSoldOutString(101);
		String seat_102 = dao.getAvailableSeatNumOrSoldOutString(102);
		String seat_103 = dao.getAvailableSeatNumOrSoldOutString(103);
		String seat_201 = dao.getAvailableSeatNumOrSoldOutString(201);
		String seat_202 = dao.getAvailableSeatNumOrSoldOutString(202);
		String seat_203 = dao.getAvailableSeatNumOrSoldOutString(203);
		String seat_204 = dao.getAvailableSeatNumOrSoldOutString(204);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "						[경기장 필드 방향]						 \n"
				+ "	(1/4)page\n"
				+ "+============================================================+\n"
				+ "|					|					|					|\n"
				+ "|		103			|		102			|		101			|\n"
				+ "|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|\n"
				+ "+============================================================+==================+\n"
				+ "|					|					|					|					|\n"
				+ "|		204			|		203			|		202			|		201			|\n"
				+ "|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|\n"
				+ "+================================================================================+",
				seat_103,
				seat_102,
				seat_101,
				seat_204,
				seat_203,
				seat_202,
				seat_201);

		System.out.println(blockView);
	}
	
	public void showSecondPage() {
		crt_page = 2;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_104 = dao.getAvailableSeatNumOrSoldOutString(104);
		String seat_105 = dao.getAvailableSeatNumOrSoldOutString(105);
		String seat_106 = dao.getAvailableSeatNumOrSoldOutString(106);
		String seat_205 = dao.getAvailableSeatNumOrSoldOutString(205);
		String seat_206 = dao.getAvailableSeatNumOrSoldOutString(206);
		String seat_207 = dao.getAvailableSeatNumOrSoldOutString(207);
		String seat_208 = dao.getAvailableSeatNumOrSoldOutString(208);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "						[경기장 필드 방향]						 \n"
				+ "	(2/4)page\n"
				+ "+============================================================+\n"
				+ "|					|					|					|\n"
				+ "|		106			|		105			|		104			|\n"
				+ "|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|\n"
				+ "+============================================================+==================+\n"
				+ "|					|					|					|					|\n"
				+ "|		208			|		207			|		206			|		205			|\n"
				+ "|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|\n"
				+ "+================================================================================+",
				seat_106,
				seat_105,
				seat_104,
				seat_208,
				seat_207,
				seat_206,
				seat_205);

		System.out.println(blockView);
	}
	
	public void showThirdPage() {
		crt_page = 3;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_117 = dao.getAvailableSeatNumOrSoldOutString(117);
		String seat_118 = dao.getAvailableSeatNumOrSoldOutString(118);
		String seat_119 = dao.getAvailableSeatNumOrSoldOutString(119);
		String seat_219 = dao.getAvailableSeatNumOrSoldOutString(219);
		String seat_220 = dao.getAvailableSeatNumOrSoldOutString(220);
		String seat_221 = dao.getAvailableSeatNumOrSoldOutString(221);
		String seat_222 = dao.getAvailableSeatNumOrSoldOutString(222);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "						[경기장 필드 방향]						 \n"
				+ "	(3/4)page\n"
				+ "+============================================================+\n"
				+ "|					|					|					|\n"
				+ "|		119			|		118			|		117			|\n"
				+ "|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|\n"
				+ "+============================================================+==================+\n"
				+ "|					|					|					|					|\n"
				+ "|		222			|		221			|		220			|		219			|\n"
				+ "|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|\n"
				+ "+================================================================================+",
				seat_119,
				seat_118,
				seat_117,
				seat_222,
				seat_221,
				seat_220,
				seat_219);

		System.out.println(blockView);
	}
	
	public void showFourthPage() {
		crt_page = 4;
		
		// 예약 가능한 좌석 개수 불러오기.
		String seat_120 = dao.getAvailableSeatNumOrSoldOutString(120);
		String seat_121 = dao.getAvailableSeatNumOrSoldOutString(121);
		String seat_122 = dao.getAvailableSeatNumOrSoldOutString(122);
		String seat_223 = dao.getAvailableSeatNumOrSoldOutString(223);
		String seat_224 = dao.getAvailableSeatNumOrSoldOutString(224);
		String seat_225 = dao.getAvailableSeatNumOrSoldOutString(225);
		String seat_226 = dao.getAvailableSeatNumOrSoldOutString(226);

		// 출력 view.
		String blockView = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
				+ "						[경기장 필드 방향]						 \n"
				+ "	(4/4)page\n"
				+ "+============================================================+\n"
				+ "|					|					|					|\n"
				+ "|		122			|		121			|		120			|\n"
				+ "|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|\n"
				+ "+============================================================+==================+\n"
				+ "|					|					|					|					|\n"
				+ "|		226			|		225			|		224			|		223			|\n"
				+ "|					|					|					|					|\n"
				+ "|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|	예매가능: %s		|\n"
				+ "|					|					|					|					|\n"
				+ "+================================================================================+",
				seat_122,
				seat_121,
				seat_120,
				seat_226,
				seat_225,
				seat_224,
				seat_223);

		System.out.println(blockView);
	}
}