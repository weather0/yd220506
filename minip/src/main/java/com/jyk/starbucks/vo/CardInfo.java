package com.jyk.starbucks.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.jyk.starbucks.app.MainMenu;

import lombok.Data;

@Data
public class CardInfo {
	int cardorder; // 고유필드
	String card_no;
	String card_name;
	Timestamp card_regdate;
	Timestamp card_expdate;
	int card_in;
	int card_out;
	int card_bal;
	String id;

//	
//	
//	
//	
//	
//	
//	
//	
	// 카드목록 양식
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(cardorder<10) {
		System.out.print("│  " + cardorder + ". " + card_name);
		MainMenu.korPrint(39, card_name);
		System.out.printf("│ %6d │ %s │", card_bal,
				card_no + " │ " + sdf.format(card_regdate) + " │ " + sdf.format(card_expdate));
		System.out.println();
		}else {
			System.out.print("│ " + cardorder + ". " + card_name);
			MainMenu.korPrint(39, card_name);
			System.out.printf("│ %6d │ %s │", card_bal,
					card_no + " │ " + sdf.format(card_regdate) + " │ " + sdf.format(card_expdate));
			System.out.println();
			
		}

		return null;
	}
	
//	
//	
//	
//	
//	
//	
//	
//	
	// 주문창 카드 양식
	public String oneCardString() {
		
		System.out.print(card_name);
		MainMenu.korPrint(39, card_name);
		System.out.printf(" %6d ", card_bal);
		System.out.println();

		return null;
	}

}
