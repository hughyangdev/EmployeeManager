package jdbc_emp;
import java.sql.*;
import java.util.*;
/*DAO ==> Data Access Object
 * CRUD기능을 수행*/
public class EmpDAO {
   private Connection con;
   private PreparedStatement ps;
   private ResultSet rs;
   
   /**사원 정보를 등록하는 메소드-INSERT문을 수행
    * */
   public boolean insertEmp(EmpVO e) {
      try {
         con=DBUtil.getCon();
         
         String sql="insert into emp2(empno,ename,job,deptno,sal,comm,mgr,hiredate)";
         sql+=" values(emp2_seq.nextval,?,?,?,?,?,?,sysdate)";
         //System.out.println(sql);
         ps=con.prepareStatement(sql);
         ps.setString(1, e.getEname());
         ps.setString(2, e.getJob());
         ps.setInt(3, e.getDeptno());
         ps.setInt(4, e.getSal());
         ps.setInt(5, e.getComm());
         ps.setInt(6, e.getMgr());
         int n=ps.executeUpdate();
         boolean b=(n>0)? true:false;
         return b;
      } catch (SQLException ex) {
         ex.printStackTrace();
         return false;
      }finally {
         close();
      }
   }//---------------------
   
   /** 사원명으로 사원정보를 검색하는 메소드
    *  -where 절을 갖는 select문 이용 */
	public ArrayList<EmpVO> selectByEname(String ename)	{
		try {
			con = DBUtil.getCon();
			String sql = "SELECT * FROM EMP2 WHERE ENAME=UPPER(?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, ename);
			rs=ps.executeQuery();
			ArrayList arr = makeList(rs);
			return arr;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}
	
	private ArrayList<EmpVO> makeList(ResultSet rs)
	throws SQLException
	{
		ArrayList<EmpVO> arr = new ArrayList<>();
		while(rs.next()) {
			int empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			int mgr=rs.getInt("mgr");
			java.sql.Date hdate = rs.getDate("hiredate");
			int sal = rs.getInt("sal");
			int comm = rs.getInt("comm");
			int deptno = rs.getInt("deptno");
			String dname = rs.getString("dname");
			// VO 객체가 하나의 레코드
			EmpVO emp = new EmpVO(empno, ename, job, mgr, hdate, sal, comm, deptno, dname);
			arr.add(emp);
		}
		return arr;
	}
	
	/* 모든 사원 정보를 db에서 가져오는 메소드 */
	/*dept2와 emp2를 join해서 emp2의 모든 정보와 dept2의 dname 가져오는 sql문 작성해보기
	단, 사원이 없는 부서정보도 출력되도록 하세요. 이 경우 사원 정보는 null로 표현되도록 합니다.
	select d.deptno, d.dname, empno, ename, job, mgr, sal, comm, hiredate
	from dept2 d left outer join emp2 e
	on d.deptno=e.deptno order by d.deptno asc
     * */
	public ArrayList<EmpVO> selectEmpAll() {
		try	{
			con = DBUtil.getCon();
			//String sql = "SELECT * FROM EMP2 ORDER BY EMPNO ASC";
			String sql = "select d.deptno, d.dname, empno, ename, job, mgr, sal, comm, hiredate\r\n" + 
					" from dept2 d left outer join emp2 e\r\n" + 
					" on d.deptno=e.deptno order by d.deptno asc";
			
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			ArrayList<EmpVO> arr = makeList(rs);
			return arr;
		} catch(SQLException e)	{
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}
	
	/* 사번으로 사원정보를 DB에서 검색하는 메소드 
	public ArrayList<EmpVO> selectByEmpno(int empno) {
		try {
			con = DBUtil.getCon();
			String sql = "SELECT * FROM EMP2 WHERE EMPNO=UPPER(?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, empno);
			rs=ps.executeQuery();
			ArrayList<EmpVO> arr = makeList(rs);
			return arr;
		} catch(SQLException e)	{
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	} //-------------*/
	/**특정 사원 정보를 DB에서 수정처리하는 메소드*/
	public boolean updateEmp(EmpVO emp) {
		try {
			con = DBUtil.getCon();
			// 이름, 부서번호, 업무, 보너스, 급여
			String sql = "UPDATE EMP2 SET ename=?, deptno=?, job=?, comm=?, sal=?"
					+ " WHERE empno=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, emp.getEname());
			ps.setInt(2, emp.getDeptno());
			ps.setString(3, emp.getJob());
			ps.setInt(4, emp.getComm());
			ps.setInt(5, emp.getSal());
			ps.setInt(6, emp.getEmpno());
			int n = ps.executeUpdate();
			return (n>0)? true: false;
			
		} catch(SQLException e)	{
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	} //-------------
	
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

   /**사번(PK)으로 사원정보 가져오는 메소드*/
/*   public ArrayList<EmpVO> selectByEmpno(String empno) {
	   try {
		   con = DBUtil.getCon();
		   String sql = "select d.deptno, d.dname, e.empno, e.ename,"
		   		+ " e.job, e.mgr, e.hiredate, e.sal, e.comm";
		   sql += " from dept2 d left outer join emp2 e";
		   sql += " on d.deptno = e.deptno and e.empno=?";
		   System.out.println(sql);
		   ps=con.prepareStatement(sql);
		   ps.setString(1, empno);
		   rs=ps.executeQuery();
		   ArrayList<EmpVO> arr = makeList(rs);
		   return arr;
	   }catch(Exception e) {
		   e.printStackTrace();
		   return null;
	   }finally {
		   close();
	   }
	   
   }*/
	public EmpVO selectByEmpno(String empno) {
		   try {
			   con = DBUtil.getCon();
			   String sql = "select d.deptno, d.dname, e.empno, e.ename,"
			   		+ " e.job, e.mgr, e.hiredate, e.sal, e.comm";
			   sql += " from dept2 d left outer join emp2 e";
			   sql += " on d.deptno = e.deptno and e.empno=?";
			   System.out.println(sql);
			   ps=con.prepareStatement(sql);
			   ps.setString(1, empno);
			   rs=ps.executeQuery();
			   ArrayList<EmpVO> arr = makeList(rs);
			   if(rs != null) {
				   EmpVO emp = arr.get(0);
				   return emp;
			   }
			   return null;
		   }catch(Exception e) {
			   e.printStackTrace();
			   return null;
		   }finally {
			   close();
		   }
		   
	   }

   public ArrayList<EmpVO> findEmp(String fname) {
	   try {
		   con = DBUtil.getCon();
		   // like 절을 이용해 검색하는 select문, 단 dept2와 equi join 한다.
		   String sql = "select d.dname, e.* " + 
		   		" from emp2 e join dept2 d " + 
		   		" on e.deptno=d.deptno " + 
		   		" and e.ename like ?";
		   ps = con.prepareStatement(sql);
		   ps.setString(1, "%"+fname+"%");
		   rs = ps.executeQuery();
		   return makeList(rs);
	   }catch(Exception e) {
		   e.printStackTrace();
		   return null;
	   }finally {
		   close();
	   }
	   
   }
   
   /**사번으로 회원정보를 DB에서 삭제 처리하는 메소드*/
   public boolean deleteEmp(String empno) {
	   try {
		   con = DBUtil.getCon();
		   String sql = "delete * from emp2 where empno=?";
		   ps = con.prepareStatement(sql);
		   ps.setString(1, empno);
		   int n = ps.executeUpdate();
		   return (n>0)? true: false;
	   } catch (Exception e) {
		   e.printStackTrace();
		   return false;
	   } finally {
		   close();
	   }
   }
   
}/////////////////////////////////////