import org.jsoup.nodes.*;
import org.jsoup.select.*;

/**
 * Created by reduxd on 12/9/15.
 */
public class Course {

    private String teacherName;
    private String teacherEmail;
    private String notesUrl;
    private String courseName;
    private String nickName;

    private double period;
    private gradeCycle cycleOne;
    private gradeCycle cycleTwo;
    private gradeCycle cycleThree;
    private generalCycle examOne;
    private generalCycle semesterOne;
    private gradeCycle cycleFour;
    private gradeCycle cycleFive;
    private gradeCycle cycleSix;
    private generalCycle examTwo;
    private generalCycle semesterTwo;

    public Course(Element source) {
        Elements rowColumns = source.children();

        teacherName = rowColumns.select(".EmailLink").text();
        teacherEmail = rowColumns.select(".EmailLink").attr("href").substring(7);
        notesUrl = rowColumns.get(1).select("a").attr("href");
        courseName = rowColumns.get(2).text();
        period = Double.valueOf(rowColumns.get(3).text());
        cycleOne = new gradeCycle(rowColumns.get(4));
        cycleTwo = new gradeCycle(rowColumns.get(5));
        cycleThree = new gradeCycle(rowColumns.get(6));
        examOne = new generalCycle(rowColumns.get(7));
        semesterOne = new generalCycle(rowColumns.get(8));
        cycleFour = new gradeCycle(rowColumns.get(9));
        cycleFive = new gradeCycle(rowColumns.get(10));
        cycleSix = new gradeCycle(rowColumns.get(11));
        examTwo = new generalCycle(rowColumns.get(12));
        semesterTwo = new generalCycle(rowColumns.get(13));
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public String getNotesUrl() {
        return notesUrl;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getNickName() {
        return nickName;
    }

    public double getPeriod() {
        return period;
    }

    public gradeCycle getCycleOne() {
        return cycleOne;
    }

    public gradeCycle getCycleTwo() {
        return cycleTwo;
    }

    public gradeCycle getCycleThree() {
        return cycleThree;
    }

    public generalCycle getExamOne() {
        return examOne;
    }

    public generalCycle getSemesterOne() {
        return semesterOne;
    }

    public gradeCycle getCycleFour() {
        return cycleFour;
    }

    public gradeCycle getCycleFive() {
        return cycleFive;
    }

    public gradeCycle getCycleSix() {
        return cycleSix;
    }

    public generalCycle getExamTwo() {
        return examTwo;
    }

    public generalCycle getSemesterTwo() {
        return semesterTwo;
    }
}

class gradeCycle {

    private String cycleGrade;
    private String cycleUrl;
    private String missingUrl;

    private CourseData cycleData;

    public gradeCycle(Element cycleData) {
        if (!(cycleData.children().size() == 0)) {

            if(cycleData.children().size() == 2) {
                missingUrl = cycleData.child(1).attr("href");
            }

            cycleGrade = cycleData.child(0).text();
            cycleUrl = cycleData.child(0).attr("href");
        }
    }

    public void setCycleData(String rawData) {
        cycleData = new CourseData(rawData);
    }

    public String getCycleGrade() {
        return cycleGrade;
    }

    public String getCycleUrl() {
        return cycleUrl;
    }

    public String getMissingUrl() {
        return missingUrl;
    }

    public CourseData getCycleData() {
        return cycleData;
    }

}

class generalCycle {
    private String cycleGrade;

    public generalCycle(Element cycleData) {
        if (cycleData.text().length() == 0) {
            cycleGrade = cycleData.text();
        }
    }
}
