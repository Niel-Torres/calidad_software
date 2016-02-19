package com.example.grupo7.comunio.Tabs;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.grupo7.comunio.R;
import com.example.grupo7.comunio.SourceCode.Httppostaux;
import com.example.grupo7.comunio.SourceCode.PuntosUsuario;
import com.example.grupo7.comunio.SourceCode.StaticElements;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tab1Liga extends Fragment {

    View view;

    ProgressDialog progressDialog;
    Httppostaux post;
    String IP_Server="adoptaunalien.esy.es";
    String URL_connect = "http://"+IP_Server+"/Comunio/resultados/tabla_clasif.php";

    ArrayList<String> arrayAux = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_1_liga,container,false);
        this.view = v;

        StaticElements.puntosUsuarios.clear();

        post=new Httppostaux();
        progressDialog = new ProgressDialog(getContext(), R.style.AppTheme_Dark_Dialog);
        new asyncclasif().execute();

        return v;
    }


    public void setLista (){
        // para mostrar la lista de opciones
        Object[] obj = arrayAux.toArray();
        String[] arrayEquiposClasificacion = new String[obj.length];
        for(int i=0;i<arrayEquiposClasificacion.length;i++) {
            arrayEquiposClasificacion[i]= (String) obj[i];
        }
        ArrayAdapter<String> AdaptadorClasificacion = new ArrayAdapter<>(this.getContext(), R.layout.text_list_view, arrayEquiposClasificacion);
        ListView lista = (ListView) view.findViewById(R.id.listViewClasificacion);
        lista.setAdapter(AdaptadorClasificacion);
    }


    class asyncclasif extends AsyncTask< String, String, String > {

        String user;
        protected void onPreExecute() {
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Cargando...");
            progressDialog.show();
        }

        protected String doInBackground(String... params) {
            if (clasifstatus()==true){
                return "ok"; //mercado cargado correctamente
            }else{
                return "err"; //mercado no cargado correctamente
            }
        }

        protected void onPostExecute(String result) {
            setLista();
            Tab2Liga.refresh();
            progressDialog.dismiss();
            Log.e("onPostExecute=",""+result);
        }

    }


    public boolean clasifstatus() {
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();

        //Realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata = post.getserverdata(postparameters2send, URL_connect);
        if (jdata != null && jdata.length() > 0) {

            JSONObject json_data; //creamos un objeto JSON
            try {
                for (int k = 0; k < jdata.length(); k++) {
                    json_data = jdata.getJSONObject(k); //leemos el primer segmento en nuestro caso el unico
                    String usuario = json_data.getString("usuario");//accedemos al valor
                    int numJornada = json_data.getInt("numJornada");//accedemos al valor
                    int puntosJornada = json_data.getInt("puntosJornada");//accedemos al valor

                    ArrayList<Integer> array = new ArrayList<>();
                    if (numJornada == 1) {
                        array.add(numJornada - 1, new Integer(puntosJornada));
                    }
                    PuntosUsuario puntosUser = new PuntosUsuario(usuario.substring(0, usuario.indexOf('@')), array);
                    if (!(StaticElements.puntosUsuarios.contains(puntosUser))){//No se ha incorporado ya el usuario "usuario"
                        StaticElements.puntosUsuarios.add(puntosUser);
                    } else{ //Ya estaba el usuario. Se añade una nueva jornada a su array de puntos
                        PuntosUsuario pAux = StaticElements.puntosUsuarios.get(StaticElements.puntosUsuarios.indexOf(puntosUser));
                        pAux.getPuntosPorJornada().add(numJornada - 1, new Integer(puntosJornada));
                    }
                }
                for (int k = 0; k < StaticElements.puntosUsuarios.size(); k++) {
                    String usuario = StaticElements.puntosUsuarios.get(k).getUsuario(); //Se coge la forma reducida del usuraio
                    String espacio = "";
                    for (int i = usuario.length(); i <= 10; i++){
                        espacio = espacio + " ";
                    }
                    this.arrayAux.add("\n" + (k+1) + "º  -  " + usuario + espacio + StaticElements.puntosUsuarios.get(k).getSumaTotal() + " puntos" + "\n");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        } else {    //json obtenido invalido verificar parte WEB.
            Log.e("JSON  ", "ERROR");
            return false;
        }
    }
}