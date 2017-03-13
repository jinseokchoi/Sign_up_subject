import java.sql.SQLException;
import java.util.Scanner;


public class RootMethod {
	static String studentId = Register_course.studentId;
	static String ProfessorId = Register_course.ProfessorId;
	static String id = Register_course.id;
	static Scanner sc = new Scanner(System.in);
	static String tmp;

	
	static void courseRegister(){//���� ��� �޽��
		System.out.println("<< ���� ��� >>");
    	
    	System.out.print("���� ID: ");
    	String courseId = sc.nextLine();
    	
    	while(courseId.isEmpty()) {
    		System.out.println("����� �Է����ּ���");
    		System.out.print("���� ID: ");
    		courseId = sc.nextLine();
    	}
    	
    	try {
			while(DB.checkCourse(courseId)) {
				System.out.println("����ID�� �ߺ� �˴ϴ� ");
				System.out.print("���� ID: ");
				courseId = sc.nextLine();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
        System.out.print("����� : ");
        String title = sc.nextLine();
        while(title.isEmpty()) {
        	System.out.print("����� : ");
        	title = sc.nextLine();
        }
        
        System.out.print("���� : ");
        int credits = sc.nextInt();
        sc.nextLine();
        while(credits <= 0) {
        	System.out.println("0 ���� �̻� �Է��ϼ���");
        	System.out.print("���� : ");
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
	
	static void studentRegister(){ //�л� ��� �޽��
		System.out.println("<< �л� ��� >>");
    	
    	System.out.print("�л� ID: ");
    	String s_Id = sc.nextLine();
    	
    	while(s_Id.isEmpty()) {
    		System.out.println("ID�� ����� �Է����ּ���");
    		System.out.print("�л� ID: ");
    		s_Id = sc.nextLine();
    	}
    	
    	try {
			while(DB.checkStudent(s_Id)) {
				System.out.println("ID�� �ߺ� �˴ϴ� ");
				System.out.print("�л� ID: ");
				s_Id = sc.nextLine();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    		
        System.out.print("�̸� : ");
        String studentName = sc.nextLine();
        while(studentName.isEmpty()) {
        	System.out.print("�̸� : ");
            studentName = sc.nextLine();
        }
        
        System.out.print("�ּ� : ");
        String studentAddress = sc.nextLine();
        while(studentAddress.isEmpty()) {
        	System.out.println("�ּҸ� ����� �Է��� �ּ���");
        	System.out.print("�ּ� : ");
            studentAddress = sc.nextLine();
        }
        				            				            
        System.out.print("��ȭ��ȣ : ");
        String studentPhone = sc.nextLine();
        while(studentPhone.isEmpty() || studentPhone.length() > 11 || studentPhone.length() < 10) {
        	System.out.println("-���� 10~11�ڸ��� �Է��ϼ���");
        	System.out.print("��ȭ��ȣ : ");
            studentPhone = sc.nextLine();
        }
        System.out.print("��й�ȣ : ");
        String Studentpassword = sc.nextLine();
        while(Studentpassword.isEmpty()) {
        	System.out.println("��й�ȣ�� ����� �Է��� �ּ���");
        	System.out.print("��й�ȣ : ");
        	Studentpassword = sc.nextLine();
        }
        try {
			DB.insertStudent(s_Id,studentName,studentAddress,studentPhone,Studentpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	static void instrutorRegister(){ //���� ��� �޼ҵ�
		System.out.println("<<Professor register>>");
    	
    	System.out.print("���� ID: ");
    	String i_Id = sc.nextLine();
    	
    	while(i_Id.isEmpty()) {
    		System.out.println("ID�� ����� �Է����ּ���");
    		System.out.print("�л� ID: ");
    		i_Id = sc.nextLine();
    	}
    	
    	try {
			while(DB.checkProfessor(i_Id)) {
				System.out.println("ID�� �ߺ� �˴ϴ� ");
				System.out.print("���� ID: ");
				i_Id = sc.nextLine();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    		
        System.out.print("�̸� : ");
        String ProfessorName = sc.nextLine();
        while(ProfessorName.isEmpty()) {
        	System.out.print("�̸� : ");
            ProfessorName = sc.nextLine();
        }
        
        System.out.print("�ּ� : ");
        String ProfessorAddress = sc.nextLine();
        while(ProfessorAddress.isEmpty()) {
        	System.out.println("�ּҸ� ����� �Է��� �ּ���");
        	System.out.print("�ּ� : ");
            ProfessorAddress = sc.nextLine();
        }
        				            				            
        System.out.print("��ȭ��ȣ : ");
        String ProfessorPhone = sc.nextLine();
        while(ProfessorPhone.isEmpty() || ProfessorPhone.length() > 11 || ProfessorPhone.length() < 10) {
        	System.out.println("-���� 10~11�ڸ��� �Է��ϼ���");
        	System.out.print("��ȭ��ȣ : ");
            ProfessorPhone = sc.nextLine();
        }
        
        System.out.print("���� : ");
        String ProfessorDept = sc.nextLine();
        
        System.out.print("��й�ȣ : ");
        String Professorpassword = sc.nextLine();
        while(Professorpassword.isEmpty()) {
        	System.out.println("��й�ȣ�� �ٽ� �Է��� �ּ���");
        	System.out.print("��й�ȣ : ");
        	Professorpassword = sc.nextLine();
        }
        try {
			DB.insertProfessor(i_Id,ProfessorName,ProfessorAddress,ProfessorPhone,ProfessorDept,Professorpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	static void courseDelete(){//���� ���� �޽��
		System.out.println("<< ���� ���� >>");
    	System.out.print("���� ID: ");
    	String courseId = sc.nextLine();
    	while(courseId.isEmpty()) {
    		System.out.println("�ٽ� �Է����ּ���");
    		System.out.print("���� ID: ");
    		courseId = sc.nextLine();
    	}
    	try {
			while(! DB.checkCourse(courseId)) {//�й� ����˻�
				System.out.println("�������� �ʴ� ID ");
				
				do{
		        	System.out.print("��������� ����Ͻðڽ��ϱ�?(y/n) : ");
		        	tmp = sc.nextLine();
		        
		        }while(!(tmp.equals("y") || tmp.equals("Y") || tmp.equals("n") || tmp.equals("N")));
		        

			if (tmp.equals("n") || tmp.equals("N"))
				break;
			System.out.print("���� ID: ");
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
	
	static void studentDelete(){//�л� ���� �޼ҵ�
		System.out.println("<<Student delete>>");
    	
    	//�л� ���� �Է½���
    	System.out.print("�л� ID: ");
    	String s_Id = sc.nextLine();
    	
    	while(s_Id.isEmpty()) {
    		System.out.println("�й��� ����� �Է����ּ���");
    		System.out.print("�л� ID: ");
    		s_Id = sc.nextLine();
    	}
    	
    	try {
			while(! DB.checkStudent(s_Id)) {//�й� �ߺ��˻�
				System.out.println("�������� �ʴ� ID ");
			
				do{
		        	System.out.print("�л������� ����Ͻðڽ��ϱ�?(y/n) : ");
		        	tmp = sc.nextLine();
		        
		        }while(!(tmp.equals("y") || tmp.equals("Y") || tmp.equals("n") || tmp.equals("N")));
		        

			if (tmp.equals("n") || tmp.equals("N"))
				break;
		
				System.out.print("�л� ID: ");
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
	
	static void ProfessorDelete(){//���� ���� �޼ҵ�
		System.out.println("<<Professor delete>>");
    	
    	//�л� ���� �Է½���
    	System.out.print("ID: ");
    	String i_Id = sc.nextLine();
    	
    	while(i_Id.isEmpty()) {
    		System.out.println("����ID�� ����� �Է����ּ���");
    		System.out.print("ID: ");
    		i_Id = sc.nextLine();
    	}
    	
    	try {
			while(! DB.checkProfessor(i_Id)) {//�й� �ߺ��˻�
				System.out.println("�������� �ʴ� ID ");
				do{
		        	System.out.print("���������� ����Ͻðڽ��ϱ�?(y/n) : ");
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
