import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by reduxd on 12/9/15.
 */
public class Test {

    public static void main(String args[]) {
        try {
            // test home page (classes and periods overview)
            amberTest("home.html");
            amberTest("home2.html");

            // test class page (assignments)
            courseDataTest("class.html");
            courseDataTest("class2.html");
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    public static void amberTest(String file) throws IOException{
        Amber amberItem = new Amber(file);
        for(Course c : amberItem.getCourseItems()) {
            System.out.println(c.getCourseName());
            System.out.println("    Teacher: " + c.getTeacherName());
            System.out.println("    Email: " + c.getTeacherEmail());
            System.out.println("    Period: " + c.getPeriod());
            System.out.println("");
            System.out.println("    Cycle 1: " + c.getCycleOne().getCycleGrade());
            System.out.println("    Cycle 2: " + c.getCycleTwo().getCycleGrade());
            System.out.println("    Cycle 3: " + c.getCycleThree().getCycleGrade());
            System.out.println("    Cycle 4: " + c.getCycleFour().getCycleGrade());
            System.out.println("    Cycle 5: " + c.getCycleFive().getCycleGrade());
            System.out.println("    Cycle 6: " + c.getCycleSix().getCycleGrade());
        }
    }

    public static void courseDataTest(String file) throws IOException{

        String rawData = new String(Files.readAllBytes(Paths.get(file)));

        CourseData d = new CourseData(rawData);

        List<classCategory> cats = d.getCategories();

        System.out.println("#: " + cats.get(0).getAssignments().size());

        for(generalAssignment a : cats.get(0).getAssignments()) {
            System.out.println("name: " + a.getAssignmentName());
            System.out.println("given: " + a.getAssignmentGiven());
            System.out.println("due: " + a.getAssignmentDue());
            System.out.println("grade: " + a.getAssignmentGrade());
            System.out.println("note: " + a.getAssignmentNote());
            System.out.println("");
        }

    }
}
