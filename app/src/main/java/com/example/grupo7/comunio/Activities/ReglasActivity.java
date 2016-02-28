package com.example.grupo7.comunio.Activities;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import com.example.grupo7.comunio.ExpandableListRules.MyCustomAdapter;
import com.example.grupo7.comunio.ExpandableListRules.MyDataProvider;
import com.example.grupo7.comunio.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*created using Android Studio (Beta) 0.8.14
* www.101apps.co.za
* */

public class ReglasActivity extends AppCompatActivity {

    HashMap<String, List<String>> rulesHashMap;
    List<String> rulesHashMapKeys;
    ExpandableListView expandableListView;
    MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expandableListView = (ExpandableListView) findViewById(R.id.myExpandableList);
        rulesHashMap = MyDataProvider.getDataHashMap();
        rulesHashMapKeys = new ArrayList<>(rulesHashMap.keySet());

        adapter = new MyCustomAdapter(this, rulesHashMap, rulesHashMapKeys);
        expandableListView.setAdapter(adapter);
    }
}
