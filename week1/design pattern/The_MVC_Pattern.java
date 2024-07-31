// Define the Model class
class Student {
    private String studentId;
    private String studentName;
    private String studentGrade;

    public Student(String studentId, String studentName, String studentGrade) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }
}

// Define the View class
class StudentView {
    public void displayStudentInformation(String name, String id, String grade) {
        System.out.println("Student Information:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Grade: " + grade);
    }
}

// Define the Controller class
class StudentController {
    private Student studentModel;
    private StudentView studentView;

    public StudentController(Student studentModel, StudentView studentView) {
        this.studentModel = studentModel;
        this.studentView = studentView;
    }

    public void setStudentName(String name) {
        studentModel.setStudentName(name);
    }

    public String getStudentName() {
        return studentModel.getStudentName();
    }

    public void setStudentId(String id) {
        studentModel.setStudentId(id);
    }

    public String getStudentId() {
        return studentModel.getStudentId();
    }

    public void setStudentGrade(String grade) {
        studentModel.setStudentGrade(grade);
    }

    public String getStudentGrade() {
        return studentModel.getStudentGrade();
    }

    public void updateStudentView() {
        studentView.displayStudentInformation(studentModel.getStudentName(), studentModel.getStudentId(), studentModel.getStudentGrade());
    }
}

// Test the MVC implementation
class MVCDemo {
    public static void main(String[] args) {
        // Create a Student model
        Student student = new Student("1", "John Doe", "A");

        // Create a Student view
        StudentView view = new StudentView();

        // Create a Student controller
        StudentController controller = new StudentController(student, view);

        // Display initial student information
        controller.updateStudentView();

        // Update student information
        controller.setStudentName("Jane Smith");
        controller.setStudentGrade("B");

        // Display updated student information
        controller.updateStudentView();
    }
}
