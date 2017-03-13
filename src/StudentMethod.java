import java.sql.SQLException;
import java.util.Scanner;

public class StudentMethod {
	static String studentId = Register_course.studentId;
	static String ProfessorId = Register_course.ProfessorId;
	static String id = Register_course.id;
	static Scanner sc = new Scanner(System.in);

	static void registerCourse() {// ������û
		System.out.println("<<Register Course>>");
		String tmp;
		while (true) {

			try {
				DB.displayCourse();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
	
				e.printStackTrace();
			}

			System.out.print("����ID  : ");
			String courseId = sc.nextLine();

			while (true) {
				try {
					if (!DB.checkCourse(courseId)) {
						System.out.println("�������� �ʴ� courseID �Դϴ�");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else if (DB.checkTakes(studentId, courseId)) {
						System.out.println("�̹� �����ϰ� �ִ� courseID �Դϴ�");
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
				System.out.print("����Ͻðڽ��ϱ�?(y/n) : ");
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
						System.out.println("�������� �ʴ� courseID �Դϴ�");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else if (!DB.checkTakes(studentId, courseId)) {
						System.out
								.println("�����ϰ� ���� ���� courseID �Դϴ�");
						System.out.print("select courseId : ");
						courseId = sc.nextLine();
					} else {
						DB.deleteTakes(studentId, courseId);
						System.out.println("��� �Ϸ�Ǿ����ϴ�\n");
						break;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			 do{
		        	System.out.print("����Ͻðڽ��ϱ�?(y/n) : ");
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
		while (studentName.isEmpty()) { // �̸� ����˻�
			System.out.print("Student Name : ");
			studentName = sc.nextLine();
		}

		System.out.print("Student Address : ");
		String studentAddr = sc.nextLine();
		while (studentAddr.isEmpty()) { // �ּ� ����˻�
			System.out.println("�ּҸ� ����� �Է��� �ּ���");
			System.out.print("Student Address : ");
			studentAddr = sc.nextLine();
		}

		System.out.print("Student Phone : ");
		String studentPhone = sc.nextLine();
		while (studentPhone.isEmpty()) { // ���� ����˻�
			System.out.println("��ȭ��ȣ�� ������Է����ּ���");
			System.out.print("Student Phone : ");
			studentPhone = sc.nextLine();
		}
		while (studentPhone.length() > 11) { // ���� ���İ˻�+����˻�
			System.out.println("�߸� �Է��ϼ̽��ϴ�. ");
			System.out.print("Student Phone : ");
			studentPhone = sc.nextLine();
		}

		System.out.print("Student Password : ");
		String password = sc.nextLine();
		while (password.isEmpty()) { // �̸� ����˻�
			System.out.print("Student Password : ");
			password = sc.nextLine();
		}

		// �л� ���� update
		try {
			DB.updateStudent(studentId, studentName, studentAddr,
					studentPhone, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�����Ǿ����ϴ�");

	}
}
