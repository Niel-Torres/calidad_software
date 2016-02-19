package com.example.grupo7.comunio.Tabs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.grupo7.comunio.R;
import com.example.grupo7.comunio.SourceCode.Httppostaux;
import com.example.grupo7.comunio.SourceCode.ShowPlayerButtons;
import com.example.grupo7.comunio.SourceCode.StaticElements;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Tab1 extends Fragment {

    ProgressDialog progressDialog;
    Httppostaux post;
    String IP_Server="adoptaunalien.esy.es";
    String URL_connect = "http://"+IP_Server+"/Comunio/alineacion/crearAlineacion.php";
    ImageButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_1,container,false);
        StaticElements.setSpbStatic(new ShowPlayerButtons(v));
        StaticElements.cambiadaAlin = false;
        post=new Httppostaux();

        fab = (ImageButton) v.findViewById(R.id.imageButton);
        fab.bringToFront();

        final RelativeLayout alineacion442 = (RelativeLayout) v.findViewById(R.id.alineacion_442);
        final RelativeLayout alineacion433 = (RelativeLayout) v.findViewById(R.id.alineacion_433);
        final RelativeLayout alineacion343 = (RelativeLayout) v.findViewById(R.id.alineacion_343);

        alineacion442.setVisibility(View.INVISIBLE);
        alineacion433.setVisibility(View.INVISIBLE);
        alineacion343.setVisibility(View.INVISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharSequence alineaciones[] = new CharSequence[]{"4-4-2", "4-3-3", "3-4-3"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Alineación");
                builder.setItems(alineaciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean mostrarToast = true;
                        switch (which) {
                            case 0:
                                mostrarToast = StaticElements.getCurrentAlin() != 442;
                                StaticElements.setCurrentAlin(442);
                                StaticElements.getSpbStatic().setAlineacionVisible(442);
                                if (mostrarToast){
                                    Toast.makeText(getActivity().getBaseContext(), "Alineación cambiada a 4-4-2", Toast.LENGTH_SHORT).show();
                                    StaticElements.cambiadaAlin = true;
                                }
                                break;

                            case 1:
                                mostrarToast = StaticElements.getCurrentAlin() != 433;
                                StaticElements.setCurrentAlin(433);
                                StaticElements.getSpbStatic().setAlineacionVisible(433);
                                if (mostrarToast) {
                                    Toast.makeText(getActivity().getBaseContext(), "Alineación cambiada a 4-3-3", Toast.LENGTH_SHORT).show();
                                    StaticElements.cambiadaAlin = true;
                                }
                                break;

                            case 2:
                                mostrarToast = StaticElements.getCurrentAlin() != 343;
                                StaticElements.setCurrentAlin(343);
                                StaticElements.getSpbStatic().setAlineacionVisible(343);
                                if (mostrarToast){
                                    Toast.makeText(getActivity().getBaseContext(), "Alineación cambiada a 3-4-3", Toast.LENGTH_SHORT).show();
                                    StaticElements.cambiadaAlin = true;
                                }
                                break;
                        }
                    }
                });
                builder.show();

            }
        });

        //Solo si se ha cargado la alineación
        if (StaticElements.getAlineacion343()[10] != null){
            StaticElements.getSpbStatic().setAlineacionVisible(StaticElements.getCurrentAlin());
        }

        return v;
    }

    @Override
    public void onStop() {

        //Enviamos los cambios a la BBDD cuando se para el Fragment
        if (StaticElements.cambiadaAlin) {
            new asyncalinchange(StaticElements.getCurrentAlin()).execute();
        }
        super.onStop();
    }



    class asyncalinchange extends AsyncTask< String, String, String > {

        int alin;

        public asyncalinchange(int alin){
            this.alin = alin;
        }

        protected void onPreExecute() {

        }

        protected String doInBackground(String... params) {
            if (alinchangestatus(StaticElements.getUser(), alin) == true){
                return "ok"; //mercado cargado correctamente
            }else{
                return "err"; //mercado no cargado correctamente
            }
        }

        protected void onPostExecute(String result) {

        }

    }

    public boolean alinchangestatus(String user, int alin) {

    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
        postparameters2send.add(new BasicNameValuePair("nombre",user));
        postparameters2send.add(new BasicNameValuePair("alin",Integer.toString(alin)));
        postparameters2send.add(new BasicNameValuePair("jug0",StaticElements.getAlineacion442()[0].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug1",StaticElements.getAlineacion442()[1].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug2",StaticElements.getAlineacion442()[2].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug3",StaticElements.getAlineacion442()[3].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug4",StaticElements.getAlineacion442()[4].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug5",StaticElements.getAlineacion442()[5].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug6",StaticElements.getAlineacion442()[6].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug7",StaticElements.getAlineacion442()[7].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug8",StaticElements.getAlineacion442()[8].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug9",StaticElements.getAlineacion442()[9].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug10",StaticElements.getAlineacion442()[10].getNombre()));

        postparameters2send.add(new BasicNameValuePair("jug11",StaticElements.getAlineacion433()[0].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug12",StaticElements.getAlineacion433()[1].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug13",StaticElements.getAlineacion433()[2].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug14",StaticElements.getAlineacion433()[3].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug15",StaticElements.getAlineacion433()[4].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug16",StaticElements.getAlineacion433()[5].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug17",StaticElements.getAlineacion433()[6].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug18",StaticElements.getAlineacion433()[7].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug19",StaticElements.getAlineacion433()[8].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug20",StaticElements.getAlineacion433()[9].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug21",StaticElements.getAlineacion433()[10].getNombre()));

        postparameters2send.add(new BasicNameValuePair("jug22",StaticElements.getAlineacion343()[0].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug23",StaticElements.getAlineacion343()[1].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug24",StaticElements.getAlineacion343()[2].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug25",StaticElements.getAlineacion343()[3].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug26",StaticElements.getAlineacion343()[4].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug27",StaticElements.getAlineacion343()[5].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug28",StaticElements.getAlineacion343()[6].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug29",StaticElements.getAlineacion343()[7].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug30",StaticElements.getAlineacion343()[8].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug31",StaticElements.getAlineacion343()[9].getNombre()));
        postparameters2send.add(new BasicNameValuePair("jug32",StaticElements.getAlineacion343()[10].getNombre()));
        post.getserverdata(postparameters2send, URL_connect);

        return true;
    }
}