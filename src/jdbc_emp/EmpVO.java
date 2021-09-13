package jdbc_emp;
import java.sql.Date;
//Domain 객체-> 값을 담고 있는 객체.(Value Object)
public class EmpVO {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;
	private int sal;
	private int comm;
	private int deptno;
	private String dname;
	//멤버변수
	
	public EmpVO() {
		this(0,"김사원",null,0,null,0,0,0,null);
	}	
	public EmpVO(int empno, String ename, String job, int mgr, Date hdate, 
			int sal, int comm, int deptno, String dname) { //오버로드 ,지역변수
		this.empno=empno; this.ename=ename; this.job=job; 
		this.mgr=mgr; hiredate=hdate; this.sal=sal; 
		this.comm=comm; this.deptno=deptno; this.dname=dname;
	}
	//setter, getter--------------
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
}