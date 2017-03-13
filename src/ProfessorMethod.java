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
						System.out.println("�������� �ʴ� courseID �Դϴ�");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else if (DB.checkCourseTeaches(courseId)) {
						System.out.println("�̹� ������ ���� �������ִ� courseID �Դϴ�");
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
		        	System.out.print("������û�� ����Ͻðڽ��ϱ�?(y/n) : ");
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
						System.out.println("�������� �ʴ� courseID �Դϴ�");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else if (!DB.checkProfessorTeaches(
							ProfessorId, courseId)) {
						System.out
								.println("������ ���� ���� ���� courseID �Դϴ�");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else {
						DB.deleteTeaches(ProfessorId, courseId);
						System.out.println("��� �Ϸ�Ǿ����ϴ�\n");
						break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			 do{
		        	System.out.print("������Ҹ� ����Ͻðڽ��ϱ�?(y/n) : ");
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
			System.out.println("������ �Է��� ������ �����ϼ���");
			System.out.print("select courseID : ");
			String courseId = sc.nextLine();

			while (true) {
				try {
					if (!DB.checkCourse(courseId)) {
						System.out.println("�������� �ʴ� courseID �Դϴ�");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else if (!DB.checkProfessorTeaches(
							ProfessorId, courseId)) {
						System.out.println("�ش� ������ ���� ���� �ʽ��ϴ�");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else
						break;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// �ش� ������ ������ ���������� ���
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
								.println("�ش������ �����ϰ����� ���� StudentID �Դϴ�");
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
					System.out.println("���� �Է��� �߸��ƽ��ϴ�.");
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
			System.out.println("������ �Է� �Ǿ����ϴ�");

			 do{
		        	System.out.print("���� �Է��� ����Ͻðڽ��ϱ�?(y/n) : ");
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
		while (ProfessorName.isEmpty()) { // �̸� ����˻�
			System.out.print("Professor Name : ");
			ProfessorName = sc.nextLine();
		}

		System.out.print("Professor Address : ");
		String ProfessorAddr = sc.nextLine();
		while (ProfessorAddr.isEmpty()) { // �ּ� ����˻�
			System.out.println("�ּҸ� ����� �Է��� �ּ���");
			System.out.print("Professor Address : ");
			ProfessorAddr = sc.nextLine();
		}

		System.out.print("Professor Phone : ");
		String ProfessorPhone = sc.nextLine();
		while (ProfessorPhone.isEmpty()
				|| ProfessorPhone.length() > 11
				|| ProfessorPhone.length() < 10) {
			System.out
					.println("��ȭ��ȣ�� ������Է����ּ���(-���� 10~11�ڸ��� �ڵ��� ��ȣ)");
			System.out.print("Professor Phone : ");
			ProfessorPhone = sc.nextLine();
		}

		System.out.print("Professor Dept : ");
		String ProfessorDept = sc.nextLine();

		System.out.print("Professor Password : ");
		String password = sc.nextLine();
		while (password.isEmpty()) { // �̸� ����˻�
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
		System.out.println("�����Ǿ����ϴ�");
	}
}
