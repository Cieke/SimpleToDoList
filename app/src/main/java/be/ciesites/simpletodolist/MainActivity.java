package be.ciesites.simpletodolist;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText item;
    private ImageButton add;
    private ListView simpleToDoList;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = (EditText) findViewById(R.id.itemEditText);

        add = (ImageButton) findViewById(R.id.addItemButton);
        simpleToDoList = (ListView) findViewById(R.id.itemsListView);

        list = new ArrayList<String>();


        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);

        simpleToDoList.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toDoItem = item.getText().toString();
                if (item.length() > 0){
                    list.add(item.getText().toString());
                    adapter.notifyDataSetChanged();
                    item.setText("");
                }

            }
        });
        simpleToDoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                list.remove(position);

                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        loadList();

    }

    @Override
    protected void onStop() {
        super.onStop();
        saveList();
    }

    private void loadList(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        list.clear();
        list.addAll(preferences.getStringSet("list", new HashSet<String>()));
    }

    private void saveList(){


        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> values = new HashSet<>();
        values.addAll(list);
        editor.putStringSet("list", values);
        editor.commit();
    }


}
