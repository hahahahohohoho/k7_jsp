package fileupload;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import common.JDBConnect;

public class MyFileDAO extends JDBConnect{
	public static void main(String[] args) {
		MyFileDAO a = new MyFileDAO();

		MyFileDTO dto = new MyFileDTO();
		dto.setTitle("title");
		dto.setOfile("ofile");
		dto.setSfile("Sfile");
		dto.setCate("과제");
		a.insertFile(dto);

		System.out.println("test");
	}

	public int insertFile(MyFileDTO dto) {
		int applyResult = 0;
		PreparedStatement psmt = null;

		try {
			String query = "insert into myfile(title, cate, ofile, sfile)"
					+ " values (?, ?, ?, ?)";

			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getCate());
			psmt.setString(3, dto.getOfile());
			psmt.setString(4, dto.getSfile());

			applyResult = psmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("Insert 중 예외 발생");
			e.printStackTrace();
		}
		return applyResult;
	}

	public List<MyFileDTO> myFileList(){
		List<MyFileDTO> fileList = new Vector<MyFileDTO>();
		String query = "select * from myfile order by idx desc";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()) {
				MyFileDTO dto = new MyFileDTO();
				dto.setIdx(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setCate(rs.getString(3));
				dto.setOfile(rs.getString(4));
				dto.setSfile(rs.getString(5));
				dto.setPostdate(rs.getString(6));

				fileList.add(dto);
			}
		}catch(Exception e) {
			System.out.println("Select 시 예외발생");
			e.printStackTrace();
		}
		return fileList;
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

}
