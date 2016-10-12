package be.ciesites.simpletodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText item;
    private Button add;
    private ListView simpleToDoList;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = (EditText) findViewById(R.id.itemEditText);

        add = (Button) findViewById(R.id.addItemButton);
        simpleToDoList = (ListView) findViewById(R.id.itemsListView);

        list = new ArrayList<String>();
        list.add("To do 1");

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
}
