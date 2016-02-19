package com.example.grupo7.comunio.ExpandableListRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*created using Android Studio (Beta) 0.8.14
* www.101apps.co.za
* */
public class MyDataProvider {

    public static HashMap<String, List<String>> getDataHashMap() {
        HashMap<String, List<String>> countriesHashMap = new HashMap<String, List<String>>();

        List<String> puntos = new ArrayList<String>();
        List<String> jornadas = new ArrayList<String>();
        List<String> compraVenta = new ArrayList<String>();
        List<String> dinero = new ArrayList<String>();

        for (int i = 0; i < MyDataArrays.puntosArray.length; i++) {
            puntos.add(MyDataArrays.puntosArray[i]);
        }

        for (int i = 0; i < MyDataArrays.jornadasArray.length; i++) {
            jornadas.add(MyDataArrays.jornadasArray[i]);
        }

        for (int i = 0; i < MyDataArrays.compraVentaArray.length; i++) {
            compraVenta.add(MyDataArrays.compraVentaArray[i]);
        }

        for (int i = 0; i < MyDataArrays.dineroArray.length; i++) {
            dinero.add(MyDataArrays.dineroArray[i]);
        }

        countriesHashMap.put("Puntos", puntos);
        countriesHashMap.put("Jornadas", jornadas);
        countriesHashMap.put("Compra - Venta", compraVenta);
        countriesHashMap.put("Dinero", dinero);

        return countriesHashMap;
    }
}
