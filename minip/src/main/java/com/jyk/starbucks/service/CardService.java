package com.jyk.starbucks.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jyk.starbucks.dao.DataSource;
import com.jyk.starbucks.vo.CardFace;
import com.jyk.starbucks.vo.CardInfo;

public class CardService {

	private DataSource dataSource = DataSource.getInstance();
	private Connection conn = dataSource.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;
	CardInfo card = new CardInfo();
	MembershipManager m = new MembershipManager();

	// 카드 넘버 생성
	public static String genCardNo() {  //랜덤카드번호 생성을 위한 단순 함수 용도뿐이므로 정적 메소드로 정의
		int[] arr = new int[16];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 9) + 1; //(매스랜덤*a)+b일 때, b<=출력값범위<=a+b-1
		}
		String cn = Arrays.toString(arr).replaceAll("\\[|\\]|,|\\s", ""); //배열→String형변환(Arrays.toString). 기본 양식에 쓰이는 문자들을([],공백) 정규식으로 제거
		return cn.substring(0,4)+"-"+cn.substring(4,8)+"-"+cn.substring(8,12)+"-"+cn.substring(12,16); //4자리씩 분할 및 하이픈 추가
	}

	
	// 카드 상품 목록
	public List<CardFace> cardDisplay() { //CARDSFACE테이블의 VO로서 CardFace타입으로 ArrayList에 담음
		List<CardFace> display = new ArrayList<CardFace>();
		CardFace vo;
		String sql = "SELECT CARD_NAME FROM CARDSFACE WHERE CARD_AVL = 'Y' ORDER BY CARD_NAME";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 1; //카드목록 넘버링 초기값
			while(rs.next()) {
				vo = new CardFace();
				vo.setCardorder(i++); //초기값 1을 먼저 담고 1을 증가(++) → 다음루프반복
				vo.setCard_name(rs.getString("card_name"));
				display.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return display;

	}

	// 카드 등록
	public int cardInsert(CardInfo vo) {
		int n = 0;
		String sql = "INSERT INTO CARDS VALUES(?,?,?,DEFAULT,?,0,DEFAULT,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getCard_no());
			psmt.setString(2, vo.getCard_name());
			psmt.setDate(3, (Date) vo.getCard_regdate());
			psmt.setInt(4, vo.getCard_in());
			psmt.setString(5, vo.getId());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;

	}
	
	// 카드 목록 조회
	public List<CardInfo> cardList() { //CARDS테이블의 VO로서 CardInfo타입으로 ArrayList에 담음
		List<CardInfo> cardList = new ArrayList<CardInfo>();

		CardInfo vo;
		String sql = "SELECT CARD_NAME, CARD_BAL, CARD_NO, CARD_REGDATE, CARD_EXPDATE FROM CARDS WHERE ID = ? ORDER BY CARD_BAL DESC";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, m.accSession());
			rs = psmt.executeQuery();
			int i = 1; //카드목록 넘버링 초기값
			while(rs.next()) {
				vo = new CardInfo();
				vo.setCardorder(i++); //초기값 1을 먼저 담고 1을 증가(++) → 다음루프반복
				vo.setCard_name(rs.getString("card_name"));
				vo.setCard_bal(rs.getInt("card_bal"));
				vo.setCard_no(rs.getString("card_no"));
				vo.setCard_regdate(rs.getDate("card_regdate"));
				vo.setCard_expdate(rs.getDate("card_expdate"));
				cardList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cardList;

	}
	
	public void test() {
		System.out.println(m.accSession());
	}
	
		

}