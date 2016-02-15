package com.example.grupo7.comunio.Tabs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.grupo7.comunio.ExpandableListJornadas.ExpandableListViewAdapter;
import com.example.grupo7.comunio.R;
import com.example.grupo7.comunio.SourceCode.StaticElements;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Tab2Liga extends Fragment {

    public static View staticView;
    public static Context context;
    ImageButton grafico;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_2_liga, container, false);
        staticView = v;
        context = getContext();
        refresh();
        grafico = (ImageButton) v.findViewById(R.id.imageButtonAdd);
        grafico.bringToFront();
        grafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupGraph(v);
            }
        });
        return v;
    }

    public static void refresh(){
        if (StaticElements.puntosUsuarios.size() > 0) {
            ArrayList<String> parentHeaderInformation;
            ExpandableListView expandableListView;
            parentHeaderInformation = new ArrayList<>();
            for (int i = 0; i < StaticElements.puntosUsuarios.get(0).getPuntosPorJornada().size(); i++) {
                parentHeaderInformation.add("Jornada " + (i + 1));
            }

            HashMap<String, List<String>> childContent = new HashMap<String, List<String>>();
            for (int i = 0; i < parentHeaderInformation.size(); i++){
                List<String> puntosEnJornada = new ArrayList<String>();
                for (int j = 0; j < StaticElements.puntosUsuarios.size(); j++){
                    puntosEnJornada.add(StaticElements.puntosUsuarios.get(j).getUsuario() + "  -  " + StaticElements.puntosUsuarios.get(j).getPuntosPorJornada().get(i) + " puntos");
                }
                childContent.put(parentHeaderInformation.get(i), puntosEnJornada);
            }

            expandableListView = (ExpandableListView) staticView.findViewById(R.id.expandableListView);

            ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(context, parentHeaderInformation, childContent);
            expandableListView.setAdapter(expandableListViewAdapter);
        }
    }

    public void showPopupGraph(View anchorView) {

        View popupView = getActivity().getLayoutInflater().inflate(R.layout.graph, null);
        PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setElevation(50);
        popupWindow.setAnimationStyle(R.style.AnimationPopup);

        GraphView graph = (GraphView) popupView.findViewById(R.id.graphic);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);

        graph.setTitle("PUNTOS - JORNADA");
        int[] arrayColores = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.CYAN, Color.GRAY, Color.YELLOW};

        for (int i = 0; i < StaticElements.puntosUsuarios.size(); i++){
            DataPoint[] puntosGraph = new DataPoint[1 + StaticElements.puntosUsuarios.get(0).getPuntosPorJornada().size()];
            puntosGraph[0] = new DataPoint(0,0);
            int puntosAux = 0;
            for (int j = 0; j < StaticElements.puntosUsuarios.get(i).getPuntosPorJornada().size(); j++){
                int puntosAct = StaticElements.puntosUsuarios.get(i).getPuntosPorJornada().get(j);
                puntosAux+= puntosAct;
                puntosGraph[j+1] = new DataPoint(j+1, puntosAux);
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(puntosGraph);
            series.setTitle(StaticElements.puntosUsuarios.get(i).getUsuario());
            series.setColor(arrayColores[i]);
            series.setThickness(8);
            series.setDrawDataPoints(true);
            graph.addSeries(series);
        }

        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryClearNoAlpha)));

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);

    }
}