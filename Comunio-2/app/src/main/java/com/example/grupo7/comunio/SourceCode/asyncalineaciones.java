package com.example.grupo7.comunio.SourceCode;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class asyncalineaciones extends AsyncTask< String, String, String > {

    Httppostaux post;
    String IP_Server="adoptaunalien.esy.es";//IP DE NUESTRO PC
    String URL_connect="http://"+IP_Server+"/Comunio/alineacion/consultarAlineacion.php";//ruta en donde estan nuestros archivos
    ProgressDialog progressDialog;

    public asyncalineaciones (ProgressDialog p){
        this.progressDialog = p;
    }

    String usuario;
    protected void onPreExecute() {

    }

    protected String doInBackground(String... params) {
        //obtnemos usr y pass
        usuario=params[0];

        //enviamos, recibimos y analizamos los datos en segundo plano.
        if (myalinstatus(usuario)==true){
            return "ok"; //equipo cargado correctamente
        }else{
            return "err"; //equipo no cargado correctamente
        }

    }

    /*Una vez terminado doInBackground segun lo que haya ocurrido
    pasamos a la sig. activity
    o mostramos error*/
    protected void onPostExecute(String result) {

        //TO DO
        //Mostramos los jugadores en los botones a partir de la alineación construida en doInBackground
        StaticElements.getSpbStatic().setAlineacionVisible(StaticElements.getCurrentAlin());
        //TO DO

        progressDialog.dismiss();//ocultamos el progress dialog que venía de Tab2.
        Log.e("POST asyncalineaciones=", "" + result);

    }

    private boolean myalinstatus(String usuario) {

        post=new Httppostaux();


        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();

        postparameters2send.add(new BasicNameValuePair("usuario", usuario));

        JSONArray jdata = post.getserverdata(postparameters2send, URL_connect);

        if (jdata != null && jdata.length() > 0) {

            JSONObject json_data;
            try {
                for (int k = 0; k < jdata.length(); k++) {
                    json_data = jdata.getJSONObject(k);
                    if (json_data.getString("tipo").equals("442")) {
                        String nomJug0 = json_data.getString("jug0");
                        StaticElements.getAlineacion442()[0] = getFromMiEquipo(nomJug0);
                        String nomJug1 = json_data.getString("jug1");
                        StaticElements.getAlineacion442()[1] = getFromMiEquipo(nomJug1);
                        String nomJug2 = json_data.getString("jug2");
                        StaticElements.getAlineacion442()[2] = getFromMiEquipo(nomJug2);
                        String nomJug3 = json_data.getString("jug3");
                        StaticElements.getAlineacion442()[3] = getFromMiEquipo(nomJug3);
                        String nomJug4 = json_data.getString("jug4");
                        StaticElements.getAlineacion442()[4] = getFromMiEquipo(nomJug4);
                        String nomJug5 = json_data.getString("jug5");
                        StaticElements.getAlineacion442()[5] = getFromMiEquipo(nomJug5);
                        String nomJug6 = json_data.getString("jug6");
                        StaticElements.getAlineacion442()[6] = getFromMiEquipo(nomJug6);
                        String nomJug7 = json_data.getString("jug7");
                        StaticElements.getAlineacion442()[7] = getFromMiEquipo(nomJug7);
                        String nomJug8 = json_data.getString("jug8");
                        StaticElements.getAlineacion442()[8] = getFromMiEquipo(nomJug8);
                        String nomJug9 = json_data.getString("jug9");
                        StaticElements.getAlineacion442()[9] = getFromMiEquipo(nomJug9);
                        String nomJug10 = json_data.getString("jug10");
                        StaticElements.getAlineacion442()[10] = getFromMiEquipo(nomJug10);
                        if(json_data.getInt("activa") == 1){
                            StaticElements.setCurrentAlin(442);
                        }
                    }else if(json_data.getString("tipo").equals("433")){
                        String nomJug0 = json_data.getString("jug0");
                        StaticElements.getAlineacion433()[0] = getFromMiEquipo(nomJug0);
                        String nomJug1 = json_data.getString("jug1");
                        StaticElements.getAlineacion433()[1] = getFromMiEquipo(nomJug1);
                        String nomJug2 = json_data.getString("jug2");
                        StaticElements.getAlineacion433()[2] = getFromMiEquipo(nomJug2);
                        String nomJug3 = json_data.getString("jug3");
                        StaticElements.getAlineacion433()[3] = getFromMiEquipo(nomJug3);
                        String nomJug4 = json_data.getString("jug4");
                        StaticElements.getAlineacion433()[4] = getFromMiEquipo(nomJug4);
                        String nomJug5 = json_data.getString("jug5");
                        StaticElements.getAlineacion433()[5] = getFromMiEquipo(nomJug5);
                        String nomJug6 = json_data.getString("jug6");
                        StaticElements.getAlineacion433()[6] = getFromMiEquipo(nomJug6);
                        String nomJug7 = json_data.getString("jug7");
                        StaticElements.getAlineacion433()[7] = getFromMiEquipo(nomJug7);
                        String nomJug8 = json_data.getString("jug8");
                        StaticElements.getAlineacion433()[8] = getFromMiEquipo(nomJug8);
                        String nomJug9 = json_data.getString("jug9");
                        StaticElements.getAlineacion433()[9] = getFromMiEquipo(nomJug9);
                        String nomJug10 = json_data.getString("jug10");
                        StaticElements.getAlineacion433()[10] = getFromMiEquipo(nomJug10);
                        if(json_data.getInt("activa") == 1){
                            StaticElements.setCurrentAlin(433);
                        }
                    }else if(json_data.getString("tipo").equals("343")){
                        String nomJug0 = json_data.getString("jug0");
                        StaticElements.getAlineacion343()[0] = getFromMiEquipo(nomJug0);
                        String nomJug1 = json_data.getString("jug1");
                        StaticElements.getAlineacion343()[1] = getFromMiEquipo(nomJug1);
                        String nomJug2 = json_data.getString("jug2");
                        StaticElements.getAlineacion343()[2] = getFromMiEquipo(nomJug2);
                        String nomJug3 = json_data.getString("jug3");
                        StaticElements.getAlineacion343()[3] = getFromMiEquipo(nomJug3);
                        String nomJug4 = json_data.getString("jug4");
                        StaticElements.getAlineacion343()[4] = getFromMiEquipo(nomJug4);
                        String nomJug5 = json_data.getString("jug5");
                        StaticElements.getAlineacion343()[5] = getFromMiEquipo(nomJug5);
                        String nomJug6 = json_data.getString("jug6");
                        StaticElements.getAlineacion343()[6] = getFromMiEquipo(nomJug6);
                        String nomJug7 = json_data.getString("jug7");
                        StaticElements.getAlineacion343()[7] = getFromMiEquipo(nomJug7);
                        String nomJug8 = json_data.getString("jug8");
                        StaticElements.getAlineacion343()[8] = getFromMiEquipo(nomJug8);
                        String nomJug9 = json_data.getString("jug9");
                        StaticElements.getAlineacion343()[9] = getFromMiEquipo(nomJug9);
                        String nomJug10 = json_data.getString("jug10");
                        StaticElements.getAlineacion343()[10] = getFromMiEquipo(nomJug10);
                        if(json_data.getInt("activa") == 1){
                            StaticElements.setCurrentAlin(343);
                        }
                    }

                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;

        } else {    //json obtenido invalido verificar parte WEB.
            Log.e("JSON  ", "ERROR");
            return false;
        }
    }

    private Jugador getFromMiEquipo(String n){
        Jugador j = StaticElements.JUGADORVACIO;
        ArrayList<Jugador> jugs = new ArrayList<>(StaticElements.getArrayMiEquipo());
        for (int i = 0; i < jugs.size(); i++){
            j = jugs.get(i);
            if (j.getNombre().equals(n)){
                break;
            } else{
                j = StaticElements.JUGADORVACIO;
            }
        }
        return j;
    }
}