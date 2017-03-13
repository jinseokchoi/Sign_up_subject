import java.sql.SQLException;
import java.util.Scanner;


public class RootMethod {
	static String studentId = Register_course.studentId;
	static String ProfessorId = Register_course.ProfessorId;
	static String id = Register_course.id;
	static Scanner sc = new Scanner(System.in);
	static String tmp;

	
	static void courseRegister(){//과목 등록 메쏘드
		System.out.println("<< 과목 등록 >>");
    	
    	System.out.print("과목 ID: ");
    	String courseId = sc.nextLine();
    	
    	while(courseId.isEmpty()) {
    		System.out.println("제대로 입력해주세요");
    		System.out.print("과목 ID: ");
    		courseId = sc.nextLine();
    	}
    	
    	try {
			while(DB.checkCourse(courseId)) {
				System.out.println("과목ID가 중복 됩니다 ");
				System.out.print("과목 ID: ");
				courseId = sc.nextLine();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
        System.out.print("과목명 : ");
        String title = sc.nextLine();
        while(title.isEmpty()) {
        	System.out.print("과목명 : ");
        	title = sc.nextLine();
        }
        
        System.out.print("학점 : ");
        int credits = sc.nextInt();
        sc.nextLine();
        while(credits <= 0) {
        	System.out.println("0 학점 이상 입력하세요");
        	System.out.print("학점 : ");
            credits = sc.nextInt();
            sc.nextLine();
        }
        try {
			DB.insertCourse(courseId,title,credits);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void studentRegister(){ //학생 등록 메쏘드
		System.out.println("<< 학생 등록 >>");
    	
    	System.out.print("학생 ID: ");
    	String s_Id = sc.nextLine();
    	
    	while(s_Id.isEmpty()) {
    		System.out.println("ID를 제대로 입력해주세요");
    		System.out.print("학생 ID: ");
    		s_Id = sc.nextLine();
    	}
    	
    	try {
			while(DB.checkStudent(s_Id)) {
				System.out.println("ID가 중복 됩니다 ");
				System.out.print("학생 ID: ");
				s_Id = sc.nextLine();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    		
        System.out.print("이름 : ");
        String studentName = sc.nextLine();
        while(studentName.isEmpty()) {
        	System.out.print("이름 : ");
            studentName = sc.nextLine();
        }
        
        System.out.print("주소 : ");
        String studentAddress = sc.nextLine();
        while(studentAddress.isEmpty()) {
        	System.out.println("주소를 제대로 입력해 주세요");
        	System.out.print("주소 : ");
            studentAddress = sc.nextLine();
        }
        				            				            
        System.out.print("전화번호 : ");
        String studentPhone = sc.nextLine();
        while(studentPhone.isEmpty() || studentPhone.length() > 11 || studentPhone.length() < 10) {
        	System.out.println("-없이 10~11자리를 입력하세요");
        	System.out.print("전화번호 : ");
            studentPhone = sc.nextLine();
        }
        System.out.print("비밀번호 : ");
        String Studentpassword = sc.nextLine();
        while(Studentpassword.isEmpty()) {
        	System.out.println("비밀번호를 제대로 입력해 주세요");
        	System.out.print("비밀번호 : ");
        	Studentpassword = sc.nextLine();
        }
        try {
			DB.insertStudent(s_Id,studentName,studentAddress,studentPhone,Studentpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	static void instrutorRegister(){ //교수 등록 메소드
		System.out.println("<<Professor register>>");
    	
    	System.out.print("교수 ID: ");
    	String i_Id = sc.nextLine();
    	
    	while(i_Id.isEmpty()) {
    		System.out.println("ID를 제대로 입력해주세요");
    		System.out.print("학생 ID: ");
    		i_Id = sc.nextLine();
    	}
    	
    	try {
			while(DB.checkProfessor(i_Id)) {
				System.out.println("ID가 중복 됩니다 ");
				System.out.print("교수 ID: ");
				i_Id = sc.nextLine();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    		
        System.out.print("이름 : ");
        String ProfessorName = sc.nextLine();
        while(ProfessorName.isEmpty()) {
        	System.out.print("이름 : ");
            ProfessorName = sc.nextLine();
        }
        
        System.out.print("주소 : ");
        String ProfessorAddress = sc.nextLine();
        while(ProfessorAddress.isEmpty()) {
        	System.out.println("주소를 제대로 입력해 주세요");
        	System.out.print("주소 : ");
            ProfessorAddress = sc.nextLine();
        }
        				            				            
        System.out.print("전화번호 : ");
        String ProfessorPhone = sc.nextLine();
        while(ProfessorPhone.isEmpty() || ProfessorPhone.length() > 11 || ProfessorPhone.length() < 10) {
        	System.out.println("-없이 10~11자리를 입력하세요");
        	System.out.print("전화번호 : ");
            ProfessorPhone = sc.nextLine();
        }
        
        System.out.print("전공 : ");
        String ProfessorDept = sc.nextLine();
        
        System.out.print("비밀번호 : ");
        String Professorpassword = sc.nextLine();
        while(Professorpassword.isEmpty()) {
        	System.out.println("비밀번호를 다시 입력해 주세요");
        	System.out.print("비밀번호 : ");
        	Professorpassword = sc.nextLine();
        }
        try {
			DB.insertProfessor(i_Id,ProfessorName,ProfessorAddress,ProfessorPhone,ProfessorDept,Professorpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	static void courseDelete(){//과목 삭제 메쏘드
		System.out.println("<< 과목 삭제 >>");
    	System.out.print("과목 ID: ");
    	String courseId = sc.nextLine();
    	while(courseId.isEmpty()) {
    		System.out.println("다시 입력해주세요");
    		System.out.print("과목 ID: ");
    		courseId = sc.nextLine();
    	}
    	try {
			while(! DB.checkCourse(courseId)) {//학번 존재검사
				System.out.println("존재하지 않는 ID ");
				
				do{
		        	System.out.print("과목삭제를 계속하시겠습니까?(y/n) : ");
		        	tmp = sc.nextLine();
		        
		        }while(!(tmp.equals("y") || tmp.equals("Y") || tmp.equals("n") || tmp.equals("N")));
		        

			if (tmp.equals("n") || tmp.equals("N"))
				break;
			System.out.print("과목 ID: ");
				courseId = sc.nextLine();
		}
			
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

    	 try {
			DB.deleteCourse(courseId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 try {
			DB.updateTotcredCourse(courseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void studentDelete(){//학생 삭제 메소드
		System.out.println("<<Student delete>>");
    	
    	//학생 정보 입력시작
    	System.out.print("학생 ID: ");
    	String s_Id = sc.nextLine();
    	
    	while(s_Id.isEmpty()) {
    		System.out.println("학번을 제대로 입력해주세요");
    		System.out.print("학생 ID: ");
    		s_Id = sc.nextLine();
    	}
    	
    	try {
			while(! DB.checkStudent(s_Id)) {//학번 중복검사
				System.out.println("존재하지 않는 ID ");
			
				do{
		        	System.out.print("학생삭제를 계속하시겠습니까?(y/n) : ");
		        	tmp = sc.nextLine();
		        
		        }while(!(tmp.equals("y") || tmp.equals("Y") || tmp.equals("n") || tmp.equals("N")));
		        

			if (tmp.equals("n") || tmp.equals("N"))
				break;
		
				System.out.print("학생 ID: ");
				s_Id = sc.nextLine();}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	 try {
			DB.deleteStudent(s_Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void ProfessorDelete(){//교수 삭제 메소드
		System.out.println("<<Professor delete>>");
    	
    	//학생 정보 입력시작
    	System.out.print("ID: ");
    	String i_Id = sc.nextLine();
    	
    	while(i_Id.isEmpty()) {
    		System.out.println("교수ID를 제대로 입력해주세요");
    		System.out.print("ID: ");
    		i_Id = sc.nextLine();
    	}
    	
    	try {
			while(! DB.checkProfessor(i_Id)) {//학번 중복검사
				System.out.println("존재하지 않는 ID ");
				do{
		        	System.out.print("교수삭제를 계속하시겠습니까?(y/n) : ");
		        	tmp = sc.nextLine();
		        
		        }while(!(tmp.equals("y") || tmp.equals("Y") || tmp.equals("n") || tmp.equals("N")));
		        

			if (tmp.equals("n") || tmp.equals("N"))
				break;
		
				System.out.print("ID: ");
				i_Id = sc.nextLine();}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	 try {
			DB.deleteProfessor(i_Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
