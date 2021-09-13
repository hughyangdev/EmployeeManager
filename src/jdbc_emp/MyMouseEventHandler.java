package jdbc_emp;
/**JTable의 마우스 이벤트를 처리하는 클래스
 * 1)MouseListener 인터페이스를 상속받던지
 * 2)MouseAdapter 추상클래스를 상속받는다.
 * 이벤트 핸들러를 외부 클래스로 별도 작성하는 경우
 * 유의 사항
 *  - 인자 생성자를 구성한다.
 * */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
public class MyMouseEventHandler extends MouseAdapter {

	EmpManagerGui gui;
	public MyMouseEventHandler(EmpManagerGui gui) {
		this.gui = gui;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// gui.setTitle("MousePress");
		Object obj = e.getSource();
		// JTable에서 선택한 행의 인덱스값 받아오기
		JTable table = (JTable)obj;
		int row = table.getSelectedRow(); //선택한 행의 인덱스 반환
		gui.setTitle("row="+row);
		// 선택한 행이 없으면 -1을 반환한다.
		if(obj==gui.tableDept) {
			getSelectedDeptData(row);
		} else if(obj==gui.tableEmp) {
			getSelectedEmpData(row);
		}
	}

	private void getSelectedEmpData(int row) {
		if(row>-1) {
			// 선택한 사원의 사번 추출
			String empno = gui.tableEmp.getValueAt(row, 0).toString();
			//String ename = gui.tableEmp.getValueAt(row, 1).toString();
			//String deptno = gui.tableEmp.getValueAt(row, 2).toString();
			//String dname = gui.tableEmp.getValueAt(row, 3).toString();
			//String job = gui.tableEmp.getValueAt(row, 4).toString();
			//String hdate = gui.tableEmp.getValueAt(row, 5).toString();
			
			gui.tempno.setText(empno);
			//gui.tename.setText(ename);
			//gui.tdeptno.setText(deptno);
			//gui.tdname.setText(dname);
			//gui.tjob.setText(job);
			//gui.thiredate.setText(hdate);
			
//			ArrayList<EmpVO> emp = gui.empDao.selectByEmpno(empno); // PK
			EmpVO emp = gui.empDao.selectByEmpno(empno); // PK
			// 선택한 사번으로 DB에서 사원정보 가져오기
			if(emp!=null) {
//				EmpVO empValue = emp.get(0);
				gui.tename.setText(emp.getEname() != null? emp.getEname(): "");
				gui.tjob.setText(emp.getJob() != null? emp.getJob(): "");
				gui.thiredate.setText(emp.getHiredate() != null? emp.getHiredate().toString(): "");
				//gui.tdeptno.setText(emp.getDeptno()+"");
				// 부서정보는 comboBox
//				String info=(empValue.getDname() != null? empValue.getDname(): "")+":"+(empValue.getDname() != null? empValue.getDname(): "");
//				String info = emp.getDeptno()+":"+emp.getDname().trim();
//				gui.cdname.setSelectedItem(info);
				int index = 0;
				switch(emp.getDeptno()) {
				case 10 : index = 0; break;
				case 20 : index = 1; break;
				case 30 : index = 2; break;
				case 40 : index = 3; break;
				case 50 : index = 4; break;
				case 51 : index = 5; break;
				case 52 : index = 6; break;
				case 55 : index = 7; break;
				}
				gui.cdname.setSelectedIndex(index);
				gui.tdname.setText(emp.getDname() != null? emp.getDname(): "");
				gui.tsal.setText(String.valueOf(emp.getSal() != 0? emp.getSal(): 0));
				gui.tcomm.setText(String.valueOf(emp.getComm() != 0? emp.getComm(): 0));
			}
			/*
			if(emp!=null) {
				gui.tename.setText(emp.getEname());
				gui.tjob.setText(emp.getJob());
				gui.thiredate.setText(emp.getHiredate().toString());
				// 부서정보는 comboBox
//				String info=(emp.getDeptno())+":"+(emp.getDname());
//				gui.cdname.setSelectedItem("20:RESEARCH");
				int index = 0;
				switch(emp.getDeptno()) {
				case 10 : index = 0; break;
				case 20 : index = 1; break;
				case 30 : index = 2; break;
				case 40 : index = 3; break;
				case 50 : index = 4; break;
				case 51 : index = 5; break;
				case 52 : index = 6; break;
				case 55 : index = 7; break;
				}
				gui.cdname.setSelectedIndex(index);
				gui.tdname.setText(emp.getDname());
				gui.tsal.setText(String.valueOf(emp.getSal()));
				gui.tcomm.setText(String.valueOf(emp.getComm()));
			}
			*/
		}
		
	}

	private void getSelectedDeptData(int row) {
		if(row>=0) { // 선택한 행이 있다면
			// 선택한 행의 각 열의 정보를 추출하자.
			String deptno = gui.tableDept.getValueAt(row, 0).toString();
			String dname = gui.tableDept.getValueAt(row, 1).toString();
			String loc = gui.tableDept.getValueAt(row, 2).toString();
			gui.tdeptno.setText(deptno);
			gui.tdname.setText(dname);
			gui.tloc.setText(loc);
		} // if-----
		
	} //-----------------------
}
