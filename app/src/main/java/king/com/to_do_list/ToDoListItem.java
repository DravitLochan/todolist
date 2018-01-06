package king.com.to_do_list;

import com.orm.SugarRecord;

/**
 * Created by DravitLochan on 06-01-2018.
 */

public class ToDoListItem extends SugarRecord {

    String title;
    String sample_text;
    String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSample_text() {
        return sample_text;
    }

    public void setSample_text(String sample_text) {
        this.sample_text = sample_text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ToDoListItem(String title, String sample_text, String date) {

        this.title = title;
        this.sample_text = sample_text;
        this.date = date;
    }

    public ToDoListItem() {

    }
}
