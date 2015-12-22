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

    public Amber(String sourceDocument) throws IOException {
        File input = new File(sourceDocument);
        rootDocument = Jsoup.parse(input, "UTF-8");

        Element courseTable = rootDocument.select("tbody").last();
        courseTable.child(0).remove();
        Elements courseRows = courseTable.children();

        for(Element row : courseRows) {
            courseItems.add(new Course(row));
        }

    }

    public List<Course> getCourseItems() {
        return courseItems;
    }

}
