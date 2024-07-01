package model1.board;

import jakarta.servlet.ServletContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class BoardDAO extends JDBConnect{
	public BoardDAO(ServletContext application) {
		super(application);
	}

	public int selectCount(Map<String, Object> map) {
		int totalCount =0;

		String query = "select count(*) from board";
		if(map.get("searchWord")!=null) {
			query += " where " + map.get("searchField") + " " + " like '%" + map.get("searchWord") + "%'";
		}
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) stmt.close();
				if(rs !=null) rs.close();
			}
			catch(SQLException e) {
				System.out.println("게시물 수를 구하는 중 예외 발생");
				e.printStackTrace();
			}
		}


		return totalCount;
	}

	public List<BoardDTO> selectList(Map<String, Object> map){
		List<BoardDTO> bbs = new Vector<BoardDTO>();

		String query = "select * from board";
		if(map.get("searchWord")!=null) {
			query += " where " + map.get("searchField") + " " + " like '%" + map.get("searchWord") + "%'";
			query += " order by num desc ";
		}
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while(rs.next()) {
				BoardDTO dto = new BoardDTO();

				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postDate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));

				bbs.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) stmt.close();
				if(rs !=null) rs.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return bbs;
	}


}
