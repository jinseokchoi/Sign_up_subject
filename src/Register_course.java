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
					System.out.println("ID를 제대로 입력해주세요");
					System.out.print("ID : ");
					id = sc.nextLine();
				} else if (id.equals("root")) {
					System.out.print("password : ");
					String password = sc.nextLine();

					while (!password.equals("onlyroot")) {
						System.out.println("비밀번호가 틀렸습니다.");
						System.out.print("password : ");
						password = sc.nextLine();
					}
					userstate = 0;
					break;
				} else if (!DB.checkStudent(id)) {
					if (!DB.checkProfessor(id)) {
						System.out.println("존재하지 않는 ID 입니다");
						System.out.print("ID : ");
						id = sc.nextLine();
					} else { // 교수 아이디 일치
						System.out.print("Password : ");
						String password = sc.nextLine();

						while (!DB.checkProfessorPassword(id, password)) {
							System.out.println("비밀번호가 틀렸습니다.");
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
						System.out.println("비밀번호가 틀렸습니다.");
						System.out.print("Password : ");
						password = sc.nextLine();
					}

					userstate = 1;
					break;
				}
			}

			// DBA Management START !!

			if (userstate == 0) {
				System.out.println("<<관리자 Login>>");
				while (true) {
					System.out.println("----<<Root Menu>>---");
					System.out.println("1.과목,학생,교수 등록");
					System.out.println("2.과목,학생,교수 삭제");
					System.out.println("3.종료");
					System.out.println("--------------------");
					System.out.print("menu : ");
					select = sc.nextInt();
					sc.nextLine();

					if (select == 1) {
						while (true) {
							System.out.println("------<< 등록 >>------");
							System.out.println("1.과목 등록");
							System.out.println("2.학생 등록");
							System.out.println("3.교수 등록");
							System.out.println("4.종료");
							System.out.println("--------------------");
							System.out.print("menu : ");

							int tmp = sc.nextInt();
							sc.nextLine();

							if (tmp == 1) {// 과목 등록

								RootMethod.courseRegister();

							} else if (tmp == 2) {// 학생 등록

								RootMethod.studentRegister();

							} else if (tmp == 3) {// 교수 등록

								RootMethod.instrutorRegister();

							} else if (tmp == 4) {
								break;
							} else {
								System.out.println("menu를 다시 입력하세요 (1~4)");
							}
						}
					}

					else if (select == 2) { // 과목,학생,교수 삭제
						while (true) {
							System.out.println("------<< 삭제 >>------");
							System.out.println("1.과목 삭제");
							System.out.println("2.학생 삭제");
							System.out.println("3.교수 삭제");
							System.out.println("4.종료");
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
								System.out.println("menu를 다시 입력하세요 (1~4)");
							}
						}
					} else if (select == 3) {
						break;
					} else {
						System.out.println("menu를 다시 입력하세요 (1~4)");
					}
				}

			}

			// STUDENT Management START !!

			else if (userstate == 1) {
				studentId = id;
				System.out.println("== Student: "+studentId
						+ " 로그인 ==");
				while (true) {
					System.out.println("--<< Student Menu >>--");
					System.out.println("1.수강 신청");
					System.out.println("2.수강 취소");
					System.out.println("3.전체 과목 조회");
					System.out.println("4.자기정보확인");
					System.out.println("5.자기정보수정");
					System.out.println("6.종료");
					System.out.println("---------------------");
					System.out.print("menu : ");
					select = sc.nextInt();
					sc.nextLine();

					if (select == 1) { // 수강신청

						StudentMethod.registerCourse();
					
					} else if (select == 2) { // 수강취소
					
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
						System.out.println("menu를 다시 입력하세요 (1~4)");
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
					System.out.println("1.수업 과목 선택");
					System.out.println("2.수업 과목 취소");
					System.out.println("3.전체 과목 조회");
					System.out.println("4.성적 입력");
					System.out.println("5.자기정보확인");
					System.out.println("6.자기정보수정");
					System.out.println("7.종료");
					System.out.println("---------------------");
					System.out.print("select : ");
					select = sc.nextInt();
					sc.nextLine();

					if (select == 1) {//교수 수업 과목 선택
						ProfessorMethod.ProfessorCourse();
					} else if (select == 2) { // 교수 수업 과목 취소
						ProfessorMethod.ProfessorCourseCancel();
					} else if (select == 3) {// 교수 전체 수업 과목 조회
						ProfessorMethod.ProfessorCourseOK();
					} else if (select == 4) {// 성적 입력
						ProfessorMethod.insertScore();
					} else if (select == 5) { //정보 확인
						ProfessorMethod.MyInfo();
					} else if (select == 6) { //정보 수정
						ProfessorMethod.MyInfoModify();
					} else if (select == 7) {
						break;
					} else {
						System.out.println("menu를 다시 입력하세요 (1~7)");
					}
				}

			}
		}

	}

}