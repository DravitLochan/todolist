package king.com.to_do_list;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NewToDo extends AppCompatActivity {

    private DatePicker datePicker;
    private EditText title, text;
    private Button save, reset;
    private ToDoListItem toDoListItemFromDb;
    private ImageButton toogle;
    private Calendar myCalendar;
    static final int DATE_PICKER_ID = 1111;

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
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String currentDateandTime = sdf.format(new Date());
                    if (toDoListItemFromDb == null) {
                        ToDoListItem toDoListItem = new ToDoListItem(title.getText().toString(), text.getText().toString(), String.valueOf(myCalendar.getTime()));
                        toDoListItem.save();
                        Toast.makeText(NewToDo.this, "New Item added to list!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        toDoListItemFromDb.date = String.valueOf(myCalendar.getTime());
                        toDoListItemFromDb.title = title.getText().toString();
                        toDoListItemFromDb.sample_text = text.getText().toString();
                        toDoListItemFromDb.save();
                        Toast.makeText(NewToDo.this, "Item Updated!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("");
                text.setText("");
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }

        };

        toogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(datePicker.getVisibility() == View.VISIBLE)
                    datePicker.setVisibility(View.GONE);
                else
                    datePicker.setVisibility(View.VISIBLE);*/
                new DatePickerDialog(NewToDo.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                updateDate();
            }
        });
    }

    private void updateDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        // edittext.setText(sdf.format(myCalendar.getTime()));
    }

    private void init() {
        title = findViewById(R.id.title);
        text = findViewById(R.id.text);
        save = findViewById(R.id.save);
        reset = findViewById(R.id.reset);
        datePicker = findViewById(R.id.datePicker);
        datePicker.setVisibility(View.GONE);
        toogle = findViewById(R.id.button_date);
        myCalendar = Calendar.getInstance();
    }

}
