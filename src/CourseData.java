import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reduxd on 12/22/15.
 */
public class CourseData {

    private Document courseDataDocument;
    private List<classCategory> categories = new ArrayList<classCategory>();

    public CourseData(String rawData) {
        courseDataDocument = Jsoup.parse(rawData);

        Elements courseDetails = courseDataDocument.select(".CategoryName");
        Elements courseData = courseDataDocument.select(".DataTable");

        courseData.remove(0);

        for(int x = 0; x < courseDetails.size(); x++) {
            categories.add(new classCategory(courseDetails.get(x), courseData.get(x)));
        }

    }

    public List<classCategory> getCategories() {
        return categories;
    }

}

class classCategory {

    private String categoryName;
    private double categoryValue;
    private String categoryAverage;
    private List<generalAssignment> assignments = new ArrayList<generalAssignment>();

    public classCategory(Element categoryDetail, Element categoryData) {

        categoryName = categoryDetail.text().substring(0, categoryDetail.text().indexOf(" - ") - 1);
        categoryValue = Double.valueOf(categoryDetail.text().substring(categoryDetail.text().indexOf(" - ") + 3, categoryDetail.text().length() - 1));

        Elements assignmentRows = categoryData.child(0).children();
        assignmentRows.remove(0);

        String averageRow = assignmentRows.remove(assignmentRows.size() - 1).text();
        categoryAverage = averageRow.substring(averageRow.indexOf("Average") + 8, averageRow.length() - 1);

        for(Element assignment: assignmentRows) {
            assignments.add(new generalAssignment(assignment));
        }

    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getCategoryValue() {
        return categoryValue;
    }

    public List<generalAssignment> getAssignments() {
        return assignments;
    }

}

class generalAssignment {

    private String assignmentName;
    private String assignmentGiven;
    private String assignmentDue;
    private String assignmentNote;
    private String assignmentGrade;

    public generalAssignment(Element assignmentData) {

        assignmentName = assignmentData.select(".AssignmentName").text();
        assignmentGiven = assignmentData.select(".DateAssigned").text();
        assignmentDue = assignmentData.select(".DateDue").text();
        assignmentNote = assignmentData.select(".AssignmentNote").text();
        assignmentGrade = assignmentData.select(".AssignmentGrade").text();

    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public String getAssignmentGiven() {
        return assignmentGiven;
    }

    public String getAssignmentDue() {
        return assignmentDue;
    }

    public String getAssignmentNote() {
        return assignmentNote;
    }

    public String getAssignmentGrade() {
        return assignmentGrade;
    }

}