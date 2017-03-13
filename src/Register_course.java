import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Register_course {
	static Connection con;
	static Scanner sc = new Scanner(System.in);
	static int select = 0;
	static int userstate = -1;
	static String studentId;
	static String ProfessorId;
	static String id;

	public static void main(String[] argv) throws SQLException {
		// Connect to the database
		if (DB.loadConnect() == true) {
			System.out.println("=====  Login  =====");
			System.out.print("ID : ");
			id = sc.nextLine();

			while (true) {
				if (id.isEmpty()) {
					System.out.println("ID�� ����� �Է����ּ���");
					System.out.print("ID : ");
					id = sc.nextLine();
				} else if (id.equals("root")) {
					System.out.print("password : ");
					String password = sc.nextLine();

					while (!password.equals("onlyroot")) {
						System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
						System.out.print("password : ");
						password = sc.nextLine();
					}
					userstate = 0;
					break;
				} else if (!DB.checkStudent(id)) {
					if (!DB.checkProfessor(id)) {
						System.out.println("�������� �ʴ� ID �Դϴ�");
						System.out.print("ID : ");
						id = sc.nextLine();
					} else { // ���� ���̵� ��ġ
						System.out.print("Password : ");
						String password = sc.nextLine();

						while (!DB.checkProfessorPassword(id, password)) {
							System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
							System.out.print("Password : ");
							password = sc.nextLine();
						}

						userstate = 2;
						break;
					}
				} else {
					System.out.print("Password : ");
					String password = sc.nextLine();

					while (!DB.checkStudentPassword(id, password)) {
						System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
						System.out.print("Password : ");
						password = sc.nextLine();
					}

					userstate = 1;
					break;
				}
			}

			// DBA Management START !!

			if (userstate == 0) {
				System.out.println("<<������ Login>>");
				while (true) {
					System.out.println("----<<Root Menu>>---");
					System.out.println("1.����,�л�,���� ���");
					System.out.println("2.����,�л�,���� ����");
					System.out.println("3.����");
					System.out.println("--------------------");
					System.out.print("menu : ");
					select = sc.nextInt();
					sc.nextLine();

					if (select == 1) {
						while (true) {
							System.out.println("------<< ��� >>------");
							System.out.println("1.���� ���");
							System.out.println("2.�л� ���");
							System.out.println("3.���� ���");
							System.out.println("4.����");
							System.out.println("--------------------");
							System.out.print("menu : ");

							int tmp = sc.nextInt();
							sc.nextLine();

							if (tmp == 1) {// ���� ���

								RootMethod.courseRegister();

							} else if (tmp == 2) {// �л� ���

								RootMethod.studentRegister();

							} else if (tmp == 3) {// ���� ���

								RootMethod.instrutorRegister();

							} else if (tmp == 4) {
								break;
							} else {
								System.out.println("menu�� �ٽ� �Է��ϼ��� (1~4)");
							}
						}
					}

					else if (select == 2) { // ����,�л�,���� ����
						while (true) {
							System.out.println("------<< ���� >>------");
							System.out.println("1.���� ����");
							System.out.println("2.�л� ����");
							System.out.println("3.���� ����");
							System.out.println("4.����");
							System.out.println("--------------------");
							System.out.print("menu : ");

							int tmp = sc.nextInt();
							sc.nextLine();

							if (tmp == 1) {

								RootMethod.courseDelete();

							} else if (tmp == 2) {

								RootMethod.studentDelete();

							} else if (tmp == 3) {// ///////////////////////////////////////////////////////////////

								RootMethod.ProfessorDelete();

							} else if (tmp == 4) {
								break;
							} else {
								System.out.println("menu�� �ٽ� �Է��ϼ��� (1~4)");
							}
						}
					} else if (select == 3) {
						break;
					} else {
						System.out.println("menu�� �ٽ� �Է��ϼ��� (1~4)");
					}
				}

			}

			// STUDENT Management START !!

			else if (userstate == 1) {
				studentId = id;
				System.out.println("== Student: "+studentId
						+ " �α��� ==");
				while (true) {
					System.out.println("--<< Student Menu >>--");
					System.out.println("1.���� ��û");
					System.out.println("2.���� ���");
					System.out.println("3.��ü ���� ��ȸ");
					System.out.println("4.�ڱ�����Ȯ��");
					System.out.println("5.�ڱ���������");
					System.out.println("6.����");
					System.out.println("---------------------");
					System.out.print("menu : ");
					select = sc.nextInt();
					sc.nextLine();

					if (select == 1) { // ������û

						StudentMethod.registerCourse();
					
					} else if (select == 2) { // �������
					
						StudentMethod.cancelCourse();
						
					} else if (select == 3) {
						StudentMethod.allCourse();
					}

					else if (select == 4) {
						StudentMethod.MyInfo();
					}

					else if (select == 5) {
						StudentMethod.MyInfoModify();
					}

					else if (select == 6) {
						break;
					} else {
						System.out.println("menu�� �ٽ� �Է��ϼ��� (1~4)");
					}
				}

			}

			// Professor Start !! //

			else if (userstate == 2) {
				ProfessorId = id;
				System.out.println("== Professor: " + ProfessorId
						+ " Login ==");
				while (true) {
					System.out.println("---<< Professor >>---");
					System.out.println("1.���� ���� ����");
					System.out.println("2.���� ���� ���");
					System.out.println("3.��ü ���� ��ȸ");
					System.out.println("4.���� �Է�");
					System.out.println("5.�ڱ�����Ȯ��");
					System.out.println("6.�ڱ���������");
					System.out.println("7.����");
					System.out.println("---------------------");
					System.out.print("select : ");
					select = sc.nextInt();
					sc.nextLine();

					if (select == 1) {//���� ���� ���� ����
						ProfessorMethod.ProfessorCourse();
					} else if (select == 2) { // ���� ���� ���� ���
						ProfessorMethod.ProfessorCourseCancel();
					} else if (select == 3) {// ���� ��ü ���� ���� ��ȸ
						ProfessorMethod.ProfessorCourseOK();
					} else if (select == 4) {// ���� �Է�
						ProfessorMethod.insertScore();
					} else if (select == 5) { //���� Ȯ��
						ProfessorMethod.MyInfo();
					} else if (select == 6) { //���� ����
						ProfessorMethod.MyInfoModify();
					} else if (select == 7) {
						break;
					} else {
						System.out.println("menu�� �ٽ� �Է��ϼ��� (1~7)");
					}
				}

			}
		}

	}

}