import java.util.ArrayList;
import java.util.Scanner;
import mypackage.EmpClass;
public class Main {
    public static void executeFunction(int option, EmpClass emp) {
        Scanner sc = new Scanner(System.in);
        switch (option) {
            case 1:
                ArrayList<String> empData = new ArrayList<String>();
                System.out.print("Enter the Employee ID:- ");
                int empId = sc.nextInt();
                System.out.print("Enter the Employee Name:- ");
                String empName = sc.next();
                System.out.print("Enter the Employee Email:- ");
                String empEmail = sc.next();
                System.out.print("Enter the Employee Salary:- ");
                int empSalary = sc.nextInt();
                empData = emp.createData(empId, empName, empEmail, empSalary);
                System.out.println(empData);
                main(null);
                break;
            case 2:
                ArrayList<String> eData = new ArrayList<String>();
                System.out.print("Enter the EmpID:- ");
                int eid = sc.nextInt();
                System.out.print("Enter the Ename:- ");
                String ename = sc.next();
                eData = emp.updateData(eid, ename);
                System.out.println(eData);
                main(null);
                break;
            case 3:
                ArrayList<ArrayList<String>> readEmpData = new ArrayList<ArrayList<String>>();
                readEmpData = emp.readData();
                System.out.println(readEmpData);
                main(null);
                break;
            case 4:
                System.out.print("Enter the EmpID:- ");
                int id = sc.nextInt();
                emp.deleteData(id);
                ArrayList<ArrayList<String>> readEmpDataUpdated = new ArrayList<ArrayList<String>>();
                readEmpDataUpdated = emp.readData();
                System.out.println(readEmpDataUpdated);
                main(null);
                break;
            case -1:
                System.out.println("Software Closed...");
                break;
        }
        sc.close();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Employee Management Software");
        EmpClass emp = new EmpClass();
        // emp.createDatabase();
        // emp.createTable();
        System.out.println("Chose any of the options:");
        Scanner sc = new Scanner(System.in);
        System.out.print("1.Create the Employee Data" + " ");
        System.out.print("2.Update the Employee Data" + " ");
        System.out.print("3.Read the Employee Data" + " ");
        System.out.println("4.Delete the Employee Data" + " ");

        int option = sc.nextInt();
        if (option < 1 || option > 4)
            System.out.println("Invalid Choice...");
        executeFunction(option, emp);
        sc.close();
    }
}
