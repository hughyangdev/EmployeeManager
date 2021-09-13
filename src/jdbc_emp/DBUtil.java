package jdbc_emp;
import java.sql.*;
//공통적으로 사용되는 코드 모듈화함 >> 공통모듈
public class DBUtil {
   
   private static String url="jdbc:oracle:thin:@localhost:1521:XE"; // XE:전역 데이터베이스 이름
   private static String user="scott", pwd="tiger";
   //static 블럭은 클래스 로딩 타임에 가장 먼저 메모리에 올라가는 영역
   //main()메소드보다도 먼저 올라간다.
   static {
      try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      System.out.println("Driver Loading Success...");
      }catch(ClassNotFoundException e) {
         e.printStackTrace();
      }
   }//static initializer
   
   public static Connection getCon() throws SQLException {
      Connection con=DriverManager.getConnection(url,user,pwd);
      return con;
   }
}