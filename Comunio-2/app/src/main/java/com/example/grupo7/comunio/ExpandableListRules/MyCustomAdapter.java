package com.example.grupo7.comunio.ExpandableListRules;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.grupo7.comunio.R;

import java.util.HashMap;
import java.util.List;

/*created using Android Studio (Beta) 0.8.14
* www.101apps.co.za
* */
public class MyCustomAdapter extends BaseExpandableListAdapter {

    private Context context;
    private HashMap<String, List<String>> rulesHashMap;
    private List<String> rulesList;

    public MyCustomAdapter(Context context,
                           HashMap<String, List<String>> hashMap,
                           List<String> list) {
        rulesHashMap = hashMap;
        this.context = context;
        this.rulesHashMap = hashMap;
        this.rulesList = list;
    }

    @Override
    public int getGroupCount() {
        return rulesHashMap.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return rulesHashMap.get(rulesList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return rulesList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return rulesHashMap.get(rulesList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_expandable_list, parent, false);
        }
        TextView parentTextView = (TextView) convertView.findViewById(R.id.textViewParent);
        parentTextView.setText(groupTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition,
                             int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        Log.i("test", "parent view: " + parent.getTag());

        String childTitle = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_expandable_list, parent, false);
        }
        TextView childTextView = (TextView) convertView.findViewById(R.id.textViewChild);
        childTextView.setText(childTitle);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
