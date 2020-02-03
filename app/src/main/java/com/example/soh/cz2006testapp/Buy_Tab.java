/**
 * Codes to create an expandable view of the result for
 * buying a house.
 * This java file is a fragment of the Tabs activity
 */

package com.example.soh.cz2006testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.round;

public class Buy_Tab extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    TextView recommend;

    DecimalFormat decimalFormatter = new DecimalFormat("###,###,###,###,###");

    /**
     * Returns the view for the Buy_Tab fragment to be displayed
     * on the Tabs activity.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return rootView
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buy__tab, container, false);

        recommend = (TextView) rootView.findViewById(R.id.text_view_result_TV);
        recommend.setText("It will be better to buy if you cannot find a rental lower than $" +
                decimalFormatter.format(round(Tabs.rentDbl)));

        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expandable_list_view);
        expandableListDetail = Expandable_List_Data_Pump_Buy.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        java.util.Collections.sort(expandableListTitle);
        expandableListAdapter = new Custom_Expandable_List_Adapter(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        return rootView;
    }
}
