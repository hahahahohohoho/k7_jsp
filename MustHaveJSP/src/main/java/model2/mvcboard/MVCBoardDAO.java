package model2.mvcboard;

import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import common.JDBConnect;

public class MVCBoardDAO extends JDBConnect{
	public MVCBoardDAO() {
		super();
	}


	public int selectCount(Map<String, Object> map) {
		int totalCount = 0 ;

		// query
		String query = "select count(*) from mvcboard";
		//검색 조건이 있다면 where절 추가
		if(map.get("searchWord")!=null) {
			query += " where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%'";
		}
		try {
			Statement stmt = con.createStatement(); //쿼리문 생성
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}

	public List<MVCBoardDTO> selectListPage(Map<String, Object> map){
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		//query
		String query = "select * from mvcboard ";

		//검색 조건이 있다면 where절 추가
		if (map.get("searchWord") != null){
			query += " WHERE " + map.get("searchField")
			+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += " ORDER BY idx DESC limit ?,? ";
		try {
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, (int)map.get("start"));
			psmt.setInt(2, (int)map.get("pageSize"));
			ResultSet rs = psmt.executeQuery();


			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();

				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));

				board.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}

	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		try {
			String query = "insert into mvcboard("
							+ "name, title, content, ofile, sfile, pass)"
							+ " values ("
							+ "?, ?, ?, ?, ?, ?)";

			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			result = psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	public MVCBoardDTO selctView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		String query = "select * from mvcboard where idx = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(idx));
			ResultSet rs = psmt.executeQuery();

			if(rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
			}
		}catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}

	public void updateVisitCount(String idx) {
		String query = "update mvcboard set"
				+ " visitcount=visitcount+1 "
				+ " where idx = ?";

		try {
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(idx));
			psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외발생");
			e.printStackTrace();
		}
	}


	public void downCountPlus(String idx) {
		String sql = "update mvcboard set "
					+ " downcount = downcount+1 "
					+ " where idx = ?";
		try {
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(idx));
			psmt.executeUpdate();
		}catch(Exception e) {}
	}


	public boolean confirmPassword(String pass, String idx) {
		boolean isCorr = true;
		try {
			String sql = "select count(*) from mvcboard where pass=? and idx=?";
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setInt(2, Integer.parseInt(idx));
			ResultSet rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
				isCorr = false;
			}
		}catch (Exception e) {
			// TODO: handle exception
			isCorr = false;
			e.printStackTrace();
		}
		return isCorr;
	}

	public int deletePost(String idx) {
		int result = 0;
		try {
			String query = "delete from mvcboard where idx =?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(idx));
			result = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	public int updatePost(MVCBoardDTO dto) {
		int result = 0;
		try {
			String query = "update mvcboard "
							+ " set title=?, name=?, content=?, ofile=?, sfile=? "
							+ " where idx = ? and pass =? ";

			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setInt(6, Integer.parseInt(dto.getIdx()));
			psmt.setString(7, dto.getPass());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
}
