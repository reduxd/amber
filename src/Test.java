import java.io.IOException;

/**
 * Created by reduxd on 12/9/15.
 */
public class Test {

    public static void main(String args[]) throws IOException {
        Amber amberItem = new Amber("home.html");
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
}
