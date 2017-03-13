import java.sql.SQLException;
import java.util.Scanner;

public class StudentMethod {
	static String studentId = Register_course.studentId;
	static String ProfessorId = Register_course.ProfessorId;
	static String id = Register_course.id;
	static Scanner sc = new Scanner(System.in);

	static void registerCourse() {// 수강신청
		System.out.println("<<Register Course>>");
		String tmp;
		while (true) {

			try {
				DB.displayCourse();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
	
				e.printStackTrace();
			}

			System.out.print("과목ID  : ");
			String courseId = sc.nextLine();

			while (true) {
				try {
					if (!DB.checkCourse(courseId)) {
						System.out.println("존재하지 않는 courseID 입니다");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else if (DB.checkTakes(studentId, courseId)) {
						System.out.println("이미 수강하고 있는 courseID 입니다");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} 
					
					else {
						DB.insertTakes(studentId, courseId);
						break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			do {
				System.out.print("계속하시겠습니까?(y/n) : ");
				tmp = sc.nextLine();

			} while (!(tmp.equals("y") || tmp.equals("Y") || tmp.equals("n") || tmp
					.equals("N")));

			if (tmp.equals("n") || tmp.equals("N"))
				break;
		}

		try {
			DB.updateTotcred(studentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void cancelCourse(){
		System.out.println("<<Cancel Course>>");
		String tmp = null;
		while (true) {
			try {
				DB.displayStudentCourse(studentId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.print("select courseID : ");
			String courseId = sc.nextLine();

			while (true) {
				try {
					if (!DB.checkCourse(courseId)) {
						System.out.println("존재하지 않는 courseID 입니다");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else if (!DB.checkTakes(studentId, courseId)) {
						System.out
								.println("수강하고 있지 않은 courseID 입니다");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else {
						DB.deleteTakes(studentId, courseId);
						System.out.println("취소 완료되었습니다\n");
						break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			 do{
		        	System.out.print("계속하시겠습니까?(y/n) : ");
		        	tmp = sc.nextLine();
		        
		        }while(!(tmp.equals("y") || tmp.equals("Y") || tmp.equals("n") || tmp.equals("N")));
		        

			if (tmp.equals("n") || tmp.equals("N"))
				break;
		}
		try {
			DB.updateTotcred(studentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void allCourse(){
		System.out.println("<<All Course>>");
		try {
			DB.displayCourse();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void MyInfo(){
		System.out.println("<<My Information>>");
		try {
			DB.selectStudents(studentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void MyInfoModify(){
		System.out.println("<<Modify My Information>>");
		try {
			DB.selectStudents(studentId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.print("Student Name : ");
		String studentName = sc.nextLine();
		while (studentName.isEmpty()) { // 이름 공백검사
			System.out.print("Student Name : ");
			studentName = sc.nextLine();
		}

		System.out.print("Student Address : ");
		String studentAddr = sc.nextLine();
		while (studentAddr.isEmpty()) { // 주소 공백검사
			System.out.println("주소를 제대로 입력해 주세요");
			System.out.print("Student Address : ");
			studentAddr = sc.nextLine();
		}

		System.out.print("Student Phone : ");
		String studentPhone = sc.nextLine();
		while (studentPhone.isEmpty()) { // 전번 공백검사
			System.out.println("전화번호를 제대로입력해주세요");
			System.out.print("Student Phone : ");
			studentPhone = sc.nextLine();
		}
		while (studentPhone.length() > 11) { // 전번 형식검사+공백검사
			System.out.println("잘못 입력하셨습니다. ");
			System.out.print("Student Phone : ");
			studentPhone = sc.nextLine();
		}

		System.out.print("Student Password : ");
		String password = sc.nextLine();
		while (password.isEmpty()) { // 이름 공백검사
			System.out.print("Student Password : ");
			password = sc.nextLine();
		}

		// 학생 정보 update
		try {
			DB.updateStudent(studentId, studentName, studentAddr,
					studentPhone, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("수정되었습니다");

	}
}
