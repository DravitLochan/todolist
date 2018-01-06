package king.com.to_do_list;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewToDo extends AppCompatActivity {

    EditText title, text;
    Button save, reset;
    ToDoListItem toDoListItemFromDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);
        /*
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        */
        init();
        Bundle bundle = getIntent().getExtras();
        int id = Integer.parseInt(bundle.getString("itemNumber", -1 + ""));
        if (id != -1) {
            toDoListItemFromDb = ToDoListItem.findById(ToDoListItem.class, id);
            title.setText(toDoListItemFromDb.getTitle());
            text.setText(toDoListItemFromDb.getSample_text());
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().trim().length() == 0) {
                    title.setError("Title cannot be empty");
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String currentDateandTime = sdf.format(new Date());
                    if(toDoListItemFromDb==null) {
                        ToDoListItem toDoListItem = new ToDoListItem(title.getText().toString(), text.getText().toString(), currentDateandTime);
                        toDoListItem.save();
                        Toast.makeText(NewToDo.this, "New Item added to list!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        toDoListItemFromDb.date = simpleDateFormat + "";
                        toDoListItemFromDb.title = title.getText().toString();
                        toDoListItemFromDb.sample_text = text.getText().toString();
                        toDoListItemFromDb.save();
                        Toast.makeText(NewToDo.this, "Item Updated!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private void init() {
        title = findViewById(R.id.title);
        text = findViewById(R.id.text);
        save = findViewById(R.id.save);
        reset = findViewById(R.id.reset);
    }

}
