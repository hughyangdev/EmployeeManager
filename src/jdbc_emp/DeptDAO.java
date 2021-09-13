package jdbc_emp;

import java.sql.*;
import java.util.*;
/**부서정보를 등록, 삭제, 조회하는
 * biz logic을 담고 있는 클래스
 * */

public class DeptDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/** 부서정보를 등록하는 메소드*/
	public boolean insertDept(DeptVO dvo) {
		try {
			con = DBUtil.getCon();
			String sql = "insert into dept2(deptno, dname, loc)"
					+ " values(dept2_seq.nextval, ?, ?)";
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			ps.setString(1, dvo.getDname());
			ps.setString(2, dvo.getLoc());
			int n = ps.executeUpdate();
			return(n>0)? true: false;			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	} //--------------------
	
	public ArrayList<DeptVO> allDept(){
		try {
			con = DBUtil.getCon();
			String sql = "SELECT * FROM DEPT2 ORDER BY DEPTNO ASC";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			ArrayList<DeptVO> arr = new ArrayList<>();
			while(rs.next()) {
				int deptno=rs.getInt(1);
				String dname=rs.getString(2);
				String loc=rs.getString(3);
				DeptVO dvo = new DeptVO(deptno, dname, loc); //하나의 행
				arr.add(dvo);
			}//while-----------
			return arr;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	} //----------------------
	
	/**부서 정보를 DB에서 삭제 처리하는 메소드*/
	public boolean deleteDept(String deptno) {
		try {
			con = DBUtil.getCon();
			String sql = "DELETE FROM dept2 WHERE deptno=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, deptno);
			int n = ps.executeUpdate();
			return (n>0)? true:false;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	} //----------------------
	
	/**DB연결 자원을 반납하는 메소드*/
	public void close() {
		try {
			if(rs!=null) rs.close(); //null이 아닐때 close해
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}//-----------
}
