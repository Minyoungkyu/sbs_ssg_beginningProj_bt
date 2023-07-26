package com.baseballtonight.dao.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.baseballtonight.dao.DAO;
import com.baseballtonight.dto.Article;
import com.baseballtonight.dto.ArticleReply;

public class ArticleDAO {
	DAO dao = new DAO();
	private ResultSet resultSet;
	private int parkId;
	private ArrayList<Article> articles;
	private ArrayList<Article> searchedArticles;
	private ArrayList<ArticleReply> replys;
	private String articleOrderSql; // 07/26 추가됨

	public ArticleDAO(int parkId) {
		this.parkId = parkId;
		this.articleOrderSql = "select * from article where parkId = " + parkId + " ORDER BY id DESC;"; // 07/26 추가됨.
		try {
			this.articles = new ArrayList<>();
			resultSet = dao.select(articleOrderSql); // 07/26 ( select 괄호 안이 바뀜 )
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String regDate = resultSet.getString("regDate");
				String title = resultSet.getString("title");
				String body = resultSet.getString("body");
				String memberId = resultSet.getString("memberId"); // 나중에 query문 join 으로 바꾸고 String 으로 작성자 닉네임 새로 받아야함.
				int hit = resultSet.getInt("hit");
				int recommend = resultSet.getInt("recommend");

				articles.add(new Article(id, regDate, title, body, memberId, hit, recommend));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//07/26 시작!!!!!!!!!!!!!
	public void setArticleOrderByRecommend() {
		this.articleOrderSql = "select * from article where parkId = " + parkId + " ORDER BY recommend DESC;";
	}
	
	public void setArticleOrderByHit() {
		this.articleOrderSql = "select * from article where parkId = " + parkId + " ORDER BY hit DESC;";
	}
	
	public void setArticleOrderById() {
		this.articleOrderSql = "select * from article where parkId = " + parkId + " ORDER BY id DESC;";
	}
	
	//07/26 끝!!!!!!!!!!!!!

	public ArrayList<Article> getArticleList() {
		reSet();
		return articles;
	}

	public ArrayList<Article> getSearchedArticle(String searchKey) {
		reSet();
		this.searchedArticles = new ArrayList<>();
		for(Article a : articles) {
			if(a.title.contains(searchKey))
				searchedArticles.add(a);
			else if(a.body.contains(searchKey))
				searchedArticles.add(a);
		}
		return searchedArticles;
	}

	public Article getArticle(String articleTitle) {
		reSet();
		for(int i = 0; i < articles.size(); i++) {
			if(articles.get(i).title.equals(articleTitle)) {
//				increaseHit(articles.get(i).id);
				return articles.get(i);
			}
		}
		return null;
	}

	public String getArticleTitle(int articleId) { // 07/26 시작
		reSet();
		for(int i = 0; i < articles.size(); i++) {
			if(articles.get(i).id == articleId) {
				return articles.get(i).title;
			}
		}
		return null;
	} // 07/26 끝
	
	public void doArticleWrite(String title, String body, int parkId, String mem_id) {
		dao.update("INSERT INTO article\n" +
			"SET regDate = NOW(),\n" +
			"title = '" + title + "',\n" +
			"`body` = '" + body + "',\n" +
			"memberId = '" + mem_id + "',\n" + // 회원기능 추가 후 변경
			"hit = 0,\n" +
			"recommend = 0,\n" +
			"parkId = " + parkId + ";");
	}

	public void doArticleModify(String title, String body, int articleId) {
		dao.update("UPDATE article\n" +
			"SET title = '" + title + "',\n" +
			"`body` = '" + body + "'\n" +
			"WHERE id = " + articleId + ";");
	}

	public void doArticleDelete(int articleId) {
		dao.update("DELETE FROM article WHERE id = " + articleId + ";");
	}

	public void doArticleReplyWrite(String body, int articleId, String mem_id) { // memberId 고쳐야함!!!

		dao.update("INSERT INTO article_reply\r\n"
			+ "SET regDate = NOW(),\r\n"
			+ "`body` = '" + body + "',\r\n"
			+ "memberId = '" + mem_id + "',\r\n" // memberId 수정
			+ "articleId = " + articleId + ",\r\n"
			+ "recommend = 0;");
	}

	public void increaseHit(int id) {
		try {
			dao.update("UPDATE article SET `hit` = `hit` + 1 WHERE id = " + id);
		} catch(Exception e) {
		}
	} // 조회수 증가 함수

	public void increaseRecommend(int id) {
		try {
			dao.update("UPDATE article SET recommend = recommend + 1 WHERE id = " + id);
		} catch(Exception e) {
		}
	} // 추천수 증가 함수

	public void decreaseRecommend(int id) {
		try {
			dao.update("UPDATE article SET recommend = recommend - 1 WHERE id = " + id);
		} catch(Exception e) {
		}
	} // 추천수 감소 함수

	public ArrayList<ArticleReply> getArticleReplyList(int articleId) {
		try {
			this.replys = new ArrayList<>();
			resultSet = dao.select("SELECT \r\n"
				+ "AR.id,\r\n"
				+ "AR.regDate,\r\n"
				+ "AR.body,\r\n"
				+ "AR.memberId,\r\n"
				+ "AR.regDate\r\n"
				+ "FROM article AS A \r\n"
				+ "INNER JOIN article_reply AS AR\r\n"
				+ "ON A.id = AR.articleId\r\n"
				+ "WHERE A.id = " + articleId + ";");
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String regDate = resultSet.getString("regDate");
				String body = resultSet.getString("body");
				String memberId = resultSet.getString("memberId"); // 나중에 query문 join 으로 바꾸고 String 으로

				replys.add(new ArticleReply(id, regDate, body, memberId));

			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return replys;
	}

	private void reSet() {
		try {
			this.articles = new ArrayList<>();
			resultSet = dao.select(articleOrderSql); // 07/26 ( select 괄호 안이 바뀜 )
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String regDate = resultSet.getString("regDate");
				String title = resultSet.getString("title");
				String body = resultSet.getString("body");
				String memberId = resultSet.getString("memberId"); // 나중에 query문 join 으로 바꾸고 String 으로 작성자 닉네임 새로 받아야함.
				int hit = resultSet.getInt("hit");
				int recommend = resultSet.getInt("recommend");

				articles.add(new Article(id, regDate, title, body, memberId, hit, recommend));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	} // articles 새로 세팅.
}
