package king.com.to_do_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView listItems;
    ListItemAdapter listItemAdapter;
    Context context;
    ArrayList<ToDoListItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init(MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listItems.setLayoutManager(layoutManager);
        listItems.setItemAnimator(new DefaultItemAnimator());
        listItems.setAdapter(listItemAdapter);
        listItemAdapter.notifyDataSetChanged();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
                 */
                Intent intent = new Intent(MainActivity.this, NewToDo.class);
                intent.putExtra("itemNumber", "-1");
                startActivity(intent);
            }
        });
    }

    private void init(Context context) {
        this.context = context;
        listItems = findViewById(R.id.list_item);
        items = new ArrayList<>();
        fetchFromDb();
        listItemAdapter = new ListItemAdapter(context, items);
    }

    public void fetchFromDb(){
        List<ToDoListItem> dbList = ToDoListItem.listAll(ToDoListItem.class);
        for (int i = 0; i < dbList.size(); i++) {
            items.add(dbList.get(i));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        items.clear();
        fetchFromDb();
        listItemAdapter.notifyDataSetChanged();
        Log.d("items size", items.size() + "");
    }
}
