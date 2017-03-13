import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DB {
   static  Connection con = null;

   // JDBC 드라이버 로드 및 연결, 연경 성공이면 true, 실패면 false 반환
   public static boolean loadConnect()  {
      try {
         // 드라이버 로딩
         Class.forName("org.gjt.mm.mysql.Driver");

      } catch (java.lang.ClassNotFoundException e ) {
         System.err.println("**Driver loaderror in loadConnect: " + e.getMessage() );
         e.printStackTrace();

      }

      try {
         // 연결하기 - 데이터베이스와 연결
         String URL = "jdbc:mysql://localhost:3306/project";
         con  = DriverManager.getConnection(URL, "root", "83192750");  
         System.out.println("연결 성공: "+URL+"에 연결됨");

         return true;
      } catch( SQLException ex ) 
      {
         System.err.println("** connection error in loadConnect: " + ex.getMessage() );
         ex.printStackTrace();
      }      

      return false;
   }
   
    // searchINSERT

    static void insertStudent(String studentId, String studentName, String studentAddress, String studentPhone, String password) throws SQLException {
       
 
        if(studentPhone.length() == 10 )
           studentPhone = studentPhone.substring(0,3)+")"+studentPhone.substring(3,6)+"-"+studentPhone.substring(6);
        else if (studentPhone.length() == 11 )
           studentPhone = studentPhone.substring(0,3)+"-"+studentPhone.substring(3,7)+"-"+studentPhone.substring(7);

        
        try {
           PreparedStatement stmt = con.prepareStatement("INSERT INTO student VALUES(?,?,?,?,null,HEX(AES_ENCRYPT(?, \"abcd\")));");
           
           stmt.setString(1, studentId);
           stmt.setString(2, studentName);
           stmt.setString(3, studentAddress);
           stmt.setString(4, studentPhone);
           stmt.setString(5, password);
           
           stmt.execute();
   
           stmt.close();
          
        } catch (SQLException e) {
           System.out.println("insertStudents SQL ERROR");
        }
    }
    
           
   static void insertCourse(String courseId,String title, int credits) throws SQLException {
      
      
      try {
         PreparedStatement stmt = con.prepareStatement("INSERT INTO course VALUES(?,?,?);");
           
           stmt.setString(1, courseId);
           stmt.setString(2, title);
           stmt.setInt(3, credits);
     
           stmt.execute();
   
           stmt.close();
           
        } catch (SQLException e) {
           System.out.println("insertStudents SQL ERROR");
        }
    }
    
   
   static void insertTakes(String studentId, String courseId) throws SQLException {
       
       
       try {
          Statement stmt1 = con.createStatement();
          Statement stmt2 = con.createStatement();

          int tot_cred;
          int credits;
          
          ResultSet rset1 = stmt1.executeQuery("SELECT tot_cred FROM student WHERE student_id = '" + studentId +"';");
          if(rset1.next()){
             tot_cred = rset1.getInt(1) ;
             
             ResultSet rset2 = stmt2.executeQuery("SELECT credits FROM course WHERE course_id = '" + courseId +"';");
              if(rset2.next()){
                 
                 credits = rset2.getInt(1);
              
                    if((tot_cred + credits) <= 20){ // 20 학점 이하만 수강할수 있도록 제한
                 
                       PreparedStatement stmt = con.prepareStatement("INSERT INTO takes VALUES(?,?,null);");
                       
                       stmt.setString(1, studentId);
                       stmt.setString(2, courseId);
                       stmt.execute();
                       stmt.close();
                   System.out.println("신청되었습니다.\n");
                    }
                    else{
                   System.out.println("수강 가능 학점을 초과했습니다 (최대 20학점)\n");
                    }
              }
          }
          
          stmt1.close();
          stmt2.close();
         
       } catch (SQLException e) {
          System.out.println("insertTakes SQL ERROR");
       }
   }
   

    static void insertTeaches(String ProfessorId, String courseId) throws SQLException {
        try {
           PreparedStatement stmt = con.prepareStatement("INSERT INTO teaches VALUES(?,?);");
                        
           stmt.setString(1, ProfessorId);
           stmt.setString(2, courseId);
           stmt.execute();
           stmt.close();
           System.out.println("신청되었습니다.\n");
           
           
           } catch (SQLException e) {
              System.out.println("insertTakes SQL ERROR");
           }
       }
   

    
    
    static void insertProfessor(String ProfessorId, String ProfessorName, String ProfessorAddress, String ProfessorPhone, String ProfessorDept, String password) throws SQLException {
        
 
        if(ProfessorPhone.length() == 10 )
           ProfessorPhone = ProfessorPhone.substring(0,3)+")"+ProfessorPhone.substring(3,6)+"-"+ProfessorPhone.substring(6);
        else if (ProfessorPhone.length() == 11 )
           ProfessorPhone = ProfessorPhone.substring(0,3)+"-"+ProfessorPhone.substring(3,7)+"-"+ProfessorPhone.substring(7);

        
        try {
           PreparedStatement stmt = con.prepareStatement("INSERT INTO Professor VALUES(?,?,?,?,?,HEX(AES_ENCRYPT(?, \"abcd\")));");
           
           stmt.setString(1, ProfessorId);
           stmt.setString(2, ProfessorName);
           stmt.setString(3, ProfessorAddress);
           stmt.setString(4, ProfessorPhone);
           stmt.setString(5, ProfessorDept);
           stmt.setString(6, password);
           
           stmt.execute();
   
           stmt.close();
          
        } catch (SQLException e) {
           System.out.println("insertProfessor SQL ERROR");
        }
    }
    
    
    
    
    
    
    // searchUPDATE
    
    static void updateStudent(String studentId, String studentName, String studentAddress, String studentPhone, String password) throws SQLException {        
        
        if(studentPhone.length() == 10 )
           studentPhone = studentPhone.substring(0,3)+")"+studentPhone.substring(3,6)+"-"+studentPhone.substring(6);
        else if (studentPhone.length() == 11 )
           studentPhone = studentPhone.substring(0,3)+"-"+studentPhone.substring(3,7)+"-"+studentPhone.substring(7);
        
        try {
           PreparedStatement stmt = con.prepareStatement("UPDATE student SET student_name = ?, student_address = ?, student_phone = ?," +
                 "password = HEX(AES_ENCRYPT(?, \"abcd\")) WHERE student_id = '"+ studentId + "';");
           
           stmt.setString(1, studentName);
           stmt.setString(2, studentAddress);
           stmt.setString(3, studentPhone);
           stmt.setString(4, password);
     
           stmt.execute();
   
           stmt.close();
        } catch (SQLException e) {
           System.out.println("updateStudents SQL ERROR");
        }
    }
    
    static void updateProfessor(String ProfessorId, String ProfessorName, String ProfessorAddress, String ProfessorPhone, String ProfessorDept, String password) throws SQLException {        
        
        if(ProfessorPhone.length() == 10 )
           ProfessorPhone = ProfessorPhone.substring(0,3)+")"+ProfessorPhone.substring(3,6)+"-"+ProfessorPhone.substring(6);
        else if (ProfessorPhone.length() == 11 )
           ProfessorPhone = ProfessorPhone.substring(0,3)+"-"+ProfessorPhone.substring(3,7)+"-"+ProfessorPhone.substring(7);
        
        try {
           PreparedStatement stmt = con.prepareStatement("UPDATE Professor SET Professor_name = ?, Professor_address = ?, Professor_phone = ?," +
                 " Professor_dept = ?, password = HEX(AES_ENCRYPT(?, \"abcd\")) WHERE Professor_id = '"+ ProfessorId + "';");
           
           stmt.setString(1, ProfessorName);
           stmt.setString(2, ProfessorAddress);
           stmt.setString(3, ProfessorPhone);
           stmt.setString(4, ProfessorDept);
           stmt.setString(5, password);
     
           stmt.execute();
   
           stmt.close();
        } catch (SQLException e) {
           System.out.println("updateProfessor SQL ERROR");
        }
    }
    
    
    
    static void updateScore(String studentId, String courseId, String score) throws SQLException {
        //con.setAutoCommit(false);

            try {
               PreparedStatement stmt = con.prepareStatement("UPDATE takes SET score = ? WHERE student_id = '"+ studentId + "' and course_id = '" + courseId + "';");
               
               stmt.setString(1, score);
               
               stmt.execute();
       
               stmt.close();
            } catch (SQLException e) {
               System.out.println("updateStudents SQL ERROR");
            }
        }
    
    
    // courseID 를 매개 변수로 해서 해당 수업을 듣는 학생들의 tot_cred 값을 갱신
    static void updateTotcredCourse(String courseId) throws SQLException {        

        try {
           int tot_cred=0;
           Statement stmt1 = con.createStatement();
           Statement stmt2 = con.createStatement();
           
           ResultSet rset1 = stmt1.executeQuery("SELECT takes.student_id FROM student, takes, course WHERE takes.student_id = student.student_id and" +
                 " course.course_id = takes.course_id and course.course_id = '" + courseId +"';");

           if(rset1.next()) {
              do {
                 String studentId=rset1.getString(1); 
                 tot_cred=0;
                 
                 ResultSet rset2 = stmt2.executeQuery("SELECT course.credits FROM student, takes, course WHERE takes.student_id = student.student_id and" +
                         " course.course_id = takes.course_id and student.student_id = '" + studentId +"';");
                 
                 if(rset2.next()) {
                      do { //수강생당 모든 수강강좌 출력
                         tot_cred = tot_cred + rset2.getInt(1);
                      } while(rset2.next());
                   }
                 PreparedStatement stmt3 = con.prepareStatement("UPDATE student SET tot_cred = ? WHERE student_id = '"+ studentId + "';");
                   stmt3.setInt(1, tot_cred);
                   stmt3.execute();
                   stmt3.close();               
              } while(rset1.next());
           }
           stmt1.close();
           stmt2.close();
           
        }catch (SQLException e) {
           System.out.println("updateStudents SQL ERROR");
        }
    }
    
    // studentID 를 매개 변수로 해서 해당 학생의 tot_cred 값을 갱신
    static void updateTotcred(String studentid) throws SQLException {        
        
        try {
           int tot_cred=0;
           Statement stmt1 = con.createStatement();
           
           ResultSet rset = stmt1.executeQuery("SELECT course.credits FROM student, takes, course WHERE takes.student_id = student.student_id and" +
                 " course.course_id = takes.course_id and student.student_id = '" + studentid +"';");
           
           if(rset.next()) {
              do { //수강생당 모든 수강강좌 출력
                 tot_cred = tot_cred + rset.getInt(1);
                 } while(rset.next());
              }
           PreparedStatement stmt2 = con.prepareStatement("UPDATE student SET tot_cred = ? WHERE student_id = '"+ studentid + "';");
           stmt2.setInt(1, tot_cred);
           stmt2.execute();
           
           stmt1.close();
           stmt2.close();
        }catch (SQLException e) {
           System.out.println("updateStudents SQL ERROR");
        }
    }
    
    
    static void updateCourse(String courseId, String title, int credits) throws SQLException {        
        
        try {
           PreparedStatement stmt = con.prepareStatement("UPDATE course SET title = ?, credits = ? WHERE course_id = '"+ courseId + "';");

           stmt.setString(1, title);
           stmt.setInt(2, credits);

           stmt.execute();
           stmt.close();
        } catch (SQLException e) {
           System.out.println("updateStudents SQL ERROR");
        }
    }    
    
    // CHECK
    static boolean checkStudent(String studentId) throws SQLException {
       Statement stmt = con.createStatement();
       ResultSet rset = stmt.executeQuery("SELECT student_id FROM student WHERE student_id = '" + studentId +"';");
       boolean result = false;
       
       
       if(rset.next()) {
          result = true;
        }

        stmt.close();
        return result;
    }

    static boolean checkStudentPassword(String id, String password) throws SQLException {
       Statement stmt = con.createStatement();
       ResultSet rset = stmt.executeQuery("SELECT AES_DECRYPT(UNHEX(password), \"abcd\") FROM student WHERE student_id = '" + id +"';");
       
       boolean result = false;
       
       if(rset.next()) {
          if(rset.getString(1).equals(password)){
             result = true;
          }
        }

        stmt.close();
        return result;
    }

    static boolean checkProfessorPassword(String id, String password) throws SQLException {
       Statement stmt = con.createStatement();
       ResultSet rset = stmt.executeQuery("SELECT AES_DECRYPT(UNHEX(password), \"abcd\") FROM Professor WHERE Professor_id = '" + id +"';");
       boolean result = false;
       
       if(rset.next()) {
          if(rset.getString(1).equals(password)){
             result = true;
          }
        }

        stmt.close();
        return result;
    }

    static boolean checkCourse(String courseId) throws SQLException {
       Statement stmt = con.createStatement();
       ResultSet rset = stmt.executeQuery("SELECT course_id FROM course WHERE course_id = '" + courseId +"';");
       boolean result = false;
       
       if(rset.next()) {
          result = true;
        }
        stmt.close();
        return result;
    }
    
    static boolean checkTakes(String studentId, String courseId) throws SQLException {
       Statement stmt = con.createStatement();
       ResultSet rset = stmt.executeQuery("SELECT course_Id FROM takes WHERE student_id = '" + studentId +"';");
       boolean result = false;
       
       
       while(rset.next()) {
          if(rset.getString(1).equals(courseId))
             result = true;
        }

        stmt.close();
        return result;
    }      
    
    // 해당 수업을 가르치는 교수가 있는지 확인한다. 있으면 참 없으면 거짓
    static boolean checkCourseTeaches(String courseId) throws SQLException {
       Statement stmt = con.createStatement();
       ResultSet rset = stmt.executeQuery("SELECT Professor_Id FROM teaches WHERE course_id = '" + courseId +"';");
       boolean result = false;
       
       
       while(rset.next()) {
             result = true;
        }

        stmt.close();
        return result;
    }  
    
    // 해당 교수가 해당 수업을 가르치는지 확인한다. 맞으면 참 아니면 거짓
    static boolean checkProfessorTeaches(String ProfessorId, String courseId) throws SQLException {
       Statement stmt = con.createStatement();
       ResultSet rset = stmt.executeQuery("SELECT course_Id FROM teaches WHERE Professor_id = '" + ProfessorId +"';");
       boolean result = false;
       
       
       while(rset.next()) {
          if(rset.getString(1).equals(courseId))
             result = true;
        }

        stmt.close();
        return result;
    }  
    
    
    
    static boolean checkProfessor(String ProfessorId) throws SQLException {
       Statement stmt = con.createStatement();
       ResultSet rset = stmt.executeQuery("SELECT Professor_id FROM Professor WHERE Professor_id = '" + ProfessorId +"';");
       boolean result = false;
      
       if(rset.next()) {
          result = true;
        }

        stmt.close();
        return result;
    }
    
    
    
    
    // searchSelect

    // 학생 정보와 학생의 수강 과목 보여준다
    static void selectStudents(String studentId) throws SQLException {
       Statement stmt = con.createStatement();
       Statement stmt_takes = con.createStatement();
       //student table 에서 정보 가져오는 쿼리
       ResultSet rset = stmt.executeQuery("SELECT * FROM student WHERE student_id = '" + studentId +"';");
       
       //Takes table과 student table, course table 의 조인 후 원하는 정보 가져오는 쿼리
       ResultSet rset_takes = stmt_takes.executeQuery("SELECT takes.course_id, title, score FROM student, takes, course WHERE takes.student_id = student.student_id and" +
             " course.course_id = takes.course_id and student.student_id = '" + studentId +"';");
       
       if(rset.next()) { //result set에 투플을 모두 출력
          do {  //모든 수강생 정보 출력
          System.out.println("----------------------------------Student Information----------------------------------");
          System.out.print("Student ID : ");
          System.out.println(rset.getString(1));
          System.out.print("Student Name : ");
          System.out.println(rset.getString(2));
          System.out.print("Student Address : ");
          System.out.println(rset.getString(3));
          System.out.print("Student Phone : ");
          System.out.println(rset.getString(4));
          System.out.print("Student Total Credits : ");
          System.out.println(rset.getString(5));
          
          if(rset_takes.next()) { //Takes result set에 튜플을 모두 출력
             System.out.print("Takes Course :\n");
             do { //수강생당 모든 수강강좌 출력
                 System.out.print("\t courseID : ");
                 System.out.print(rset_takes.getString(1));
                 System.out.print("\t Title : ");
                 System.out.print(rset_takes.getString(2));
                System.out.print("\t Score : ");
                String tmpScore = rset_takes.getString(3);
                  if(tmpScore == null)
                     tmpScore = "미입력";
                 System.out.print(tmpScore);
                 System.out.print("\n");
             } while(rset_takes.next());
          } else {
             System.out.println("수강강좌가 없습니다");
          }
          System.out.println("--------------------------------------------------------------------");
          
          //System.
          } while(rset.next()); 
        } else {
           System.out.println("해당 수강생의 정보가 존재하지 않습니다");          
       }

        stmt.close();
        stmt_takes.close();
    }


    static void selectProfessors(String ProfessorId) throws SQLException {
       Statement stmt = con.createStatement();
       Statement stmt_teaches = con.createStatement();
       
       ResultSet rset = stmt.executeQuery("SELECT * FROM Professor WHERE Professor_id = '" + ProfessorId +"';");
       
       ResultSet rset_teaches = stmt_teaches.executeQuery("SELECT course.course_id, title FROM Professor, teaches, course WHERE teaches.Professor_id = Professor.Professor_id and" +
             " course.course_id = teaches.course_id and Professor.Professor_id = '" + ProfessorId +"';");
       
       if(rset.next()) {
          do {
          System.out.println("----------------------------------Professor Information----------------------------------");
          System.out.print("Professor ID : ");
          System.out.println(rset.getString(1));
          System.out.print("Professor Name : ");
          System.out.println(rset.getString(2));
          System.out.print("Professor Address : ");
          System.out.println(rset.getString(3));
          System.out.print("Professor Phone : ");
          System.out.println(rset.getString(4));
          System.out.print("Professor Salary : ");
          System.out.println(rset.getString(5));
          
          if(rset_teaches.next()) {
             System.out.print("Teaching Course :\n");
             do { //수강생당 모든 수강강좌 출력
                 System.out.print("\t courseID : ");
                 System.out.print(rset_teaches.getString(1));
                 System.out.print("\t Title : ");
                 System.out.print(rset_teaches.getString(2));
                 System.out.print("\n");
             } while(rset_teaches.next());
          } else {
             System.out.println("수업강좌가 없습니다");
          }
          System.out.println("--------------------------------------------------------------------");
          
          //System.
          } while(rset.next()); 
        } else {
           System.out.println("해당 수강생의 정보가 존재하지 않습니다");          
       }

        stmt.close();
        stmt_teaches.close();
    }
    
    
    
    
    static void selectCourse(String courseId) throws SQLException {
       Statement stmt = con.createStatement();
       Statement stmt_takes = con.createStatement();
       //student table 에서 정보 가져오는 쿼리
       ResultSet rset = stmt.executeQuery("SELECT * FROM course WHERE course.course_id = '" + courseId +"';");
       
       ResultSet rset_takes = stmt_takes.executeQuery("SELECT student.student_id, student.student_name, score FROM student, takes, course WHERE takes.student_id = student.student_id and" +
             " course.course_id = takes.course_id and course.course_id = '" + courseId +"';");

       
       if(rset.next()) { //result set에 투플을 모두 출력
          do {  //모든 수강생 정보 출력
          System.out.println("----------------------------------Course Information----------------------------------");
          System.out.print("Course ID : ");
          System.out.println(rset.getString(1));
          System.out.print("Title : ");
          System.out.println(rset.getString(2));
          System.out.print("Credits : ");
          System.out.println(rset.getString(3));
          
          if(rset_takes.next()) { //Takes result set에 튜플을 모두 출력
            System.out.print("takes Professor :\n");
             do {
                System.out.print("\t Student ID : ");
                 System.out.print(rset_takes.getString(1));
                System.out.print("\t Student Name : ");
                 System.out.print(rset_takes.getString(2));
                System.out.print("\t Score : ");
                String tmpScore = rset_takes.getString(3);
                  if(tmpScore == null)
                     tmpScore = "미입력";
                 System.out.print(tmpScore);
                System.out.print("\n");
                 } while(rset_takes.next());
          } else {
             System.out.println("해당 과목을 수강하는 학생이 없습니다.");
          }
          System.out.println("--------------------------------------------------------------------");
          
          //System.
          } while(rset.next()); 
        } else {
           System.out.println("존재하지 않습니다");          
       }

        stmt.close();
        stmt_takes.close();
    }
    
    
    private static void selectTakes(String studentId) throws SQLException {
       Statement stmt_takes = con.createStatement();
       //Takes table과 student table, class table 의 조인 후 원하는 정보 가져오는 쿼리
       ResultSet rset_takes = stmt_takes.executeQuery("SELECT takes.course_id, title FROM student, takes, course WHERE takes.student_id = student.student_id and" +
             " course.course_id = takes.course_id and student.student_id = '" + studentId + "';");
    
       if(rset_takes.next()) { //Takes result set에 튜플을 모두 출력
          do { //수강생당 모든 수강강좌 출력
             System.out.println("----------------------------------");
             System.out.print("Course ID : ");
              System.out.println(rset_takes.getString(1));
              System.out.print("Title : ");
              System.out.println(rset_takes.getString(2));
              System.out.print("----------------------------------");
          } while(rset_takes.next());
       } else {
          System.out.println("*수강강좌가 없습니다");
       }
       stmt_takes.close();
    }
    
    
    
    
    //////////////////  searchDISPLAY
    
    static void displayCourse() throws SQLException {
       
       Statement stmt = con.createStatement();
       ResultSet rset = stmt.executeQuery("SELECT course_id, title, credits FROM course ");
       
       if(rset.next()){

         System.out.println("----------------------------------");
          do { //수강생당 모든 수강강좌 출력
             System.out.print("Course ID : ");
             System.out.print(rset.getString(1));
             System.out.print("\t Title : ");
             System.out.print(rset.getString(2));
             System.out.print("\t Credits : ");
             System.out.print(rset.getString(3));
             System.out.print("\n");
             } while(rset.next());
       } else {
           System.out.println("과목이 존재하지 않습니다");          
       }
      System.out.println("----------------------------------");
    
    }
    

    static void displayStudentCourse(String studentId) throws SQLException {
       
       Statement stmt = con.createStatement();
       
       ResultSet rset = stmt.executeQuery("SELECT course.course_id, course.title, course.credits FROM student, takes, course WHERE takes.student_id = student.student_id and" +
             " course.course_id = takes.course_id and student.student_id = '" + studentId +"';");
       
       if(rset.next()){

         System.out.println("----------------------------------");
          do { //수강생당 모든 수강강좌 출력
             System.out.print("Course ID : ");
             System.out.print(rset.getString(1));
             System.out.print("\t Title : ");
             System.out.print(rset.getString(2));
             System.out.print("\t Credits : ");
             System.out.print(rset.getString(3));
             System.out.print("\n");
             } while(rset.next());
       } else {
           System.out.println("과목이 존재하지 않습니다");          
       }
      System.out.println("----------------------------------");
    
    }
    

    static void displayProfessorCourse(String ProfessorId) throws SQLException {
       
       Statement stmt = con.createStatement();
       
       ResultSet rset = stmt.executeQuery("SELECT course.course_id, course.title FROM Professor, teaches, course WHERE teaches.Professor_id = Professor.Professor_id and" +
             " course.course_id = teaches.course_id and Professor.Professor_id = '" + ProfessorId +"';");
       
       if(rset.next()){
         System.out.println("----------------------------------");
          do {
             System.out.print("Course ID : ");
             System.out.print(rset.getString(1));
             System.out.print("\t Title : ");
             System.out.print(rset.getString(2));
             System.out.print("\n");
             } while(rset.next());
       } else {
           System.out.println("과목이 존재하지 않습니다");          
       }
      System.out.println("----------------------------------");
    
    }
    
    
    
    
    
    ///////// searchDELETE
   static void deleteStudent(String studentId) throws SQLException {
       Statement stmt = con.createStatement();
       Statement stmt_takes = con.createStatement();
       
       stmt.execute("DELETE FROM student WHERE student.student_id = '" + studentId +"';");
       stmt.execute("DELETE FROM takes WHERE takes.student_id = '" + studentId +"';");
       
        stmt.close();
        stmt_takes.close();
    }
    
    static void deleteProfessor(String ProfessorId) throws SQLException {
       Statement stmt = con.createStatement();
       Statement stmt_takes = con.createStatement();
       
       stmt.execute("DELETE FROM Professor WHERE Professor.Professor_id = '" + ProfessorId +"';");
       stmt.execute("DELETE FROM teaches WHERE teaches.Professor_id = '" + ProfessorId +"';");
       
        stmt.close();
        stmt_takes.close();
    }
    
    static void deleteCourse(String courseId) throws SQLException {
       Statement stmt = con.createStatement();
       Statement stmt_takes = con.createStatement();
       
       stmt.execute("DELETE FROM course WHERE course_id = '" + courseId +"';");
       stmt.execute("DELETE FROM takes WHERE takes.course_id = '" + courseId +"';");
       
        stmt.close();
        stmt_takes.close();
    }

    
    static void deleteTakes(String studentId, String courseId) throws SQLException {
       Statement stmt = con.createStatement();
       Statement stmt_takes = con.createStatement();
       
       stmt.execute("DELETE FROM takes WHERE takes.course_id = '" + courseId +"' and takes.student_id = '" + studentId + "';");
       
        stmt.close();
        stmt_takes.close();
    }
    
    static void deleteTeaches(String ProfessorId, String courseId) throws SQLException {
       Statement stmt = con.createStatement();
       Statement stmt_takes = con.createStatement();
       
       stmt.execute("DELETE FROM teaches WHERE teaches.course_id = '" + courseId +"' and teaches.Professor_id = '" + ProfessorId + "';");
       
        stmt.close();
        stmt_takes.close();
    }
}