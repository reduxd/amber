import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reduxd on 12/9/15.
 */
public class Amber {

    private Document rootDocument;
    private List<Course> courseItems = new ArrayList<Course>();

    public Amber(String rawDocument) {

        rootDocument = Jsoup.parse(rawDocument);

        Element courseTable = rootDocument.select("tbody").last();
        courseTable.children().remove(0);
        Elements courseRows = courseTable.children();

        for(Element row : courseRows) {
            courseItems.add(new Course(row));
        }
    }

    public List<Course> getCourseItems() {
        return courseItems;
    }

}
