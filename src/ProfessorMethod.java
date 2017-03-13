import java.sql.SQLException;
import java.util.Scanner;


public class ProfessorMethod {
	static String studentId = Register_course.studentId;
	static String ProfessorId = Register_course.ProfessorId;
	static String id = Register_course.id;
	static Scanner sc = new Scanner(System.in);
	
	static void ProfessorCourse() {
		System.out.println("<<Register Course>>");
		String tmp = null;
		while (true) {

			try {
				DB.displayCourse();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.print("select courseID : ");
			String courseId = sc.nextLine();

			while (true) {
				try {
					if (!DB.checkCourse(courseId)) {
						System.out.println("존재하지 않는 courseID 입니다");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else if (DB.checkCourseTeaches(courseId)) {
						System.out.println("이미 수업을 맏은 교수가있는 courseID 입니다");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else {
						DB.insertTeaches(ProfessorId, courseId);
						break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			 do{
		        	System.out.print("수강신청을 계속하시겠습니까?(y/n) : ");
		        	tmp = sc.nextLine();
		        
		        }while(!(tmp.equals("y") || tmp.equals("Y") || tmp.equals("n") || tmp.equals("N")));
		        

			if (tmp.equals("n") || tmp.equals("N"))
				break;
		}
	}
	static void ProfessorCourseCancel(){
		System.out.println("<<Cancel Course>>");
		String tmp = null;
		while (true) {
			try {
				DB.displayProfessorCourse(ProfessorId);
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
					} else if (!DB.checkProfessorTeaches(
							ProfessorId, courseId)) {
						System.out
								.println("수업을 맏고 있지 않은 courseID 입니다");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else {
						DB.deleteTeaches(ProfessorId, courseId);
						System.out.println("취소 완료되었습니다\n");
						break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			 do{
		        	System.out.print("수강취소를 계속하시겠습니까?(y/n) : ");
		        	tmp = sc.nextLine();
		        
		        }while(!(tmp.equals("y") || tmp.equals("Y") || tmp.equals("n") || tmp.equals("N")));
		        

			if (tmp.equals("n") || tmp.equals("N"))
				break;
		}
	}
	static void ProfessorCourseOK(){
		System.out.println("<<All Course>>");
		try {
			DB.displayCourse();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void insertScore(){
		System.out.println("<<Insert SCORE>>");
		String tmp = null;
		while (true) {

			try {
				DB.displayProfessorCourse(ProfessorId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("성적을 입력할 수업을 선택하세요");
			System.out.print("select courseID : ");
			String courseId = sc.nextLine();

			while (true) {
				try {
					if (!DB.checkCourse(courseId)) {
						System.out.println("존재하지 않는 courseID 입니다");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else if (!DB.checkProfessorTeaches(
							ProfessorId, courseId)) {
						System.out.println("해당 수업을 맏고 있지 않습니다");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else
						break;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 해당 수업의 정보와 수강생들을 출력
			try {
				DB.selectCourse(courseId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.print("select studentID : ");
			String s_Id = sc.nextLine();

			while (true) {
				try {
					if (!DB.checkTakes(s_Id, courseId)) {
						System.out
								.println("해당수업을 수강하고있지 않은 StudentID 입니다");
						System.out.print("select studentId : ");
						courseId = sc.nextLine();
					} else
						break;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out
					.print("Insert Score(A(+),B(+),C(+),F ) : ");
			String score = sc.nextLine();

			while (true) {
				if (!(score.equals("A+") || score.equals("A")
						|| score.equals("B")
						|| score.equals("B+")
						|| score.equals("C")
						|| score.equals("C+") || score
							.equals("F"))) {
					System.out.println("점수 입력이 잘못됐습니다.");
					System.out
							.print("Insert Score(A(+),B(+),C(+),F ) : ");
					courseId = sc.nextLine();
				} else
					break;
			}
			try {
				DB.updateScore(s_Id, courseId, score);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("점수가 입력 되었습니다");

			 do{
		        	System.out.print("점수 입력을 계속하시겠습니까?(y/n) : ");
		        	tmp = sc.nextLine();
		        
		        }while(!(tmp.equals("y") || tmp.equals("Y") || tmp.equals("n") || tmp.equals("N")));
		        
			if (tmp.equals("n") || tmp.equals("N"))
				break;

		}
	}
	static void MyInfo(){
		System.out.println("<<My Information>>");
		try {
			DB.selectProfessors(ProfessorId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void MyInfoModify(){
		System.out.println("<<Modify My Information>>");
		try {
			DB.selectProfessors(ProfessorId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.print("Professor Name : ");
		String ProfessorName = sc.nextLine();
		while (ProfessorName.isEmpty()) { // 이름 공백검사
			System.out.print("Professor Name : ");
			ProfessorName = sc.nextLine();
		}

		System.out.print("Professor Address : ");
		String ProfessorAddr = sc.nextLine();
		while (ProfessorAddr.isEmpty()) { // 주소 공백검사
			System.out.println("주소를 제대로 입력해 주세요");
			System.out.print("Professor Address : ");
			ProfessorAddr = sc.nextLine();
		}

		System.out.print("Professor Phone : ");
		String ProfessorPhone = sc.nextLine();
		while (ProfessorPhone.isEmpty()
				|| ProfessorPhone.length() > 11
				|| ProfessorPhone.length() < 10) {
			System.out
					.println("전화번호를 제대로입력해주세요(-없이 10~11자리의 핸드폰 번호)");
			System.out.print("Professor Phone : ");
			ProfessorPhone = sc.nextLine();
		}

		System.out.print("Professor Dept : ");
		String ProfessorDept = sc.nextLine();

		System.out.print("Professor Password : ");
		String password = sc.nextLine();
		while (password.isEmpty()) { // 이름 공백검사
			System.out.print("Professor Password : ");
			password = sc.nextLine();
		}

		try {
			DB.updateProfessor(ProfessorId, ProfessorName,
					ProfessorAddr, ProfessorPhone, ProfessorDept,
					password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("수정되었습니다");
	}
}
