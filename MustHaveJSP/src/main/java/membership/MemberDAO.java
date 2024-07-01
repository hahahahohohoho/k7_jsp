package membership;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBConnect;

public class MemberDAO extends JDBConnect{
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}

	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "select * from member where id=? and pass=?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt!=null) psmt.close();
				if(rs !=null) rs.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return dto;
	}

}
