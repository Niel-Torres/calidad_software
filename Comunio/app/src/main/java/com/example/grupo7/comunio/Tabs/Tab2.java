package com.example.grupo7.comunio.Tabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grupo7.comunio.R;
import com.example.grupo7.comunio.SourceCode.Httppostaux;
import com.example.grupo7.comunio.SourceCode.Jugador;
import com.example.grupo7.comunio.SourceCode.MensajeInicio;
import com.example.grupo7.comunio.SourceCode.Posicion;
import com.example.grupo7.comunio.SourceCode.StaticElements;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import it.gmariotti.cardslib.library.internal.dismissanimation.SwipeDismissAnimation;
import it.gmariotti.cardslib.library.prototypes.CardSection;
import it.gmariotti.cardslib.library.prototypes.SectionedCardAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

import com.example.grupo7.comunio.SourceCode.asyncalineaciones;


public class Tab2 extends Fragment {

    ArrayList<Card> cards;
    SwipeDismissAnimation dismissAnimation;
    View v;

    CardListView listView;
    ProgressDialog progressDialog;
    Httppostaux post;
    String IP_Server="adoptaunalien.esy.es";//IP DE NUESTRO PC
    String URL_connect="http://"+IP_Server+"/Comunio/miequipo/miequipo.php";//ruta en donde estan nuestros archivos
    String URL_connect_2="http://"+IP_Server+"/Comunio/mercado/vender.php";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v =inflater.inflate(R.layout.tab_2,container, false);
        post=new Httppostaux();
        StaticElements.removeMiEquipo();
        progressDialog = new ProgressDialog(this.getContext(), R.style.AppTheme_Dark_Dialog);

        new asyncmiequipo().execute(StaticElements.getUser());

        return v;
    }

    private class CustomExpandCard2 extends CardExpand {

        private Button b;
        private ArrayList<Card> array;
        private Jugador j;

        //Use your resource ID for your inner layout
        public CustomExpandCard2(Context context, ArrayList<Card> a, Jugador j) {
            super(context, R.layout.inner_expand2);
            this.array = a;
            this.j = j;
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, final View view) {

            if (view == null) return;

            TextView textoPuntosJornada = (TextView) view.findViewById(R.id.textViewPuntosJornada);
            textoPuntosJornada.setText(j.toStringJornadas());

            b = (Button) view.findViewById(R.id.boton_expand_jugadores);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Card c = getParentCard();
                    dismissAnimation.animateDismiss(c);
                    ArrayList<Card> newArray = new ArrayList<Card>(array); //para dar a la animación tiempo para finalizar
                    newArray.remove(c);
                    array = newArray;

                    new asyncsellteam().execute(StaticElements.getUser(), c.getCardHeader().getTitle());
                    eliminarJugador(c.getCardHeader().getTitle());
                    Toast.makeText(getContext(), "Jugador Puesto en Venta", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    //A CAMBIAR CON LA BBDD
    //Elimina el jugador del array estático de la lista de jugadores dado su nombre
    public void eliminarJugador(String nombre){
        boolean encontrado = false;
        int i = 0;
        Jugador j;
        while (i < StaticElements.getArrayMercado().size() && !encontrado) {
            j = StaticElements.getArrayMiEquipo().get(i);
            if (j.getNombre().equals(nombre)){
                encontrado = true;
                StaticElements.getArrayMiEquipo().remove(j);
                eliminarJugadorAlineaciones(j);
            }
            else i++;
        }
    }

    private void eliminarJugadorAlineaciones(Jugador j){
        boolean encontrado = false;
        int i = 0;
        while (i < 11 && !encontrado) {
            if (StaticElements.getAlineacion442()[i] == (j)) {
                StaticElements.getAlineacion442()[i] = StaticElements.JUGADORVACIO;
                encontrado = true;
            }
            else{
                i++;
            }
        }
        while (i < 11 && !encontrado) {
            if (StaticElements.getAlineacion433()[i] == (j)) {
                StaticElements.getAlineacion433()[i] = StaticElements.JUGADORVACIO;
                encontrado = true;
            }
            else{
                i++;
            }
        }
        while (i < 11 && !encontrado) {
            if (StaticElements.getAlineacion343()[i].equals(j)) {
                StaticElements.getAlineacion343()[i] = StaticElements.JUGADORVACIO;
                encontrado = true;
            }
            else{
                i++;
            }
        }
    }



    //////////////////PARTE EN RED//////////////////////

    public boolean myteamstatus(String usuario) {

    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();

        postparameters2send.add(new BasicNameValuePair("usuario", usuario));

        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata = post.getserverdata(postparameters2send, URL_connect);

        //si lo que obtuvimos no es null
        if (jdata != null && jdata.length() > 0) {

            JSONObject json_data; //creamos un objeto JSON
            try {
                for (int k = 0; k < jdata.length(); k++) {
                    json_data = jdata.getJSONObject(k); //leemos el primer segmento en nuestro caso el unico
                    String nombre = json_data.getString("nombre");//accedemos al valor
                    Integer edad = (Integer)json_data.getInt("edad");//accedemos al valor
                    Posicion posicion = stringToPosicion(json_data.getString("posicion"));//accedemos al valor
                    Integer precio = (Integer)json_data.getInt("precio");//accedemos al valor
                    String equipo = json_data.getString("equipo");//accedemos al valor

                    Integer puntos1 = new Integer(0);
                    Integer puntos2 = new Integer(0);
                    Integer puntos3 = new Integer(0);
                    if (!json_data.isNull("puntos_j1")) {
                        puntos1 = (Integer) json_data.getInt("puntos_j1");
                    }
                    if (!json_data.isNull("puntos_j2")) {
                        puntos2 = (Integer) json_data.getInt("puntos_j2");
                    }
                    if (!json_data.isNull("puntos_j3")) {
                        puntos3 = (Integer) json_data.getInt("puntos_j3");
                    }

                    //Se crea el jugador y se añade al equipo estático
                    ArrayList<Integer> puntos = new ArrayList<>();
                    puntos.add(puntos1);
                    puntos.add(puntos2);
                    puntos.add(puntos3);
                    Jugador j = new Jugador(nombre, edad, posicion, precio, puntos, equipo);
                    StaticElements.getArrayMiEquipo().add(j);
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        cards = new ArrayList<>();
                        for (int i = 0; i < StaticElements.getArrayMiEquipo().size(); i++) {

                            Jugador j = StaticElements.getArrayMiEquipo().get(i);

                            Card card = new Card(getContext(), R.layout.card_inner);

                            //Create a CardHeader
                            CardHeader header = new CardHeader(getContext());
                            header.setTitle(j.getNombre());

                            //Expandable de Puntos por jornada - Boton vender
                            final CustomExpandCard2 expand = new CustomExpandCard2(getActivity(), cards, j);
                            card.addCardExpand(expand);
                            ViewToClickToExpand viewToClickToExpand =
                                    ViewToClickToExpand.builder()
                                            .highlightView(false)
                                            .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);
                            card.setViewToClickToExpand(viewToClickToExpand);

                            //Título de la tarjeta
                            card.addCardHeader(header);

                            //Desripción del jugador
                            card.setTitle(j.toString());

                            //Foto del jugador
                            CardThumbnail thumb = new CardThumbnail(getContext());
                            String nombreFoto = StaticElements.stripAccents(j.getNombre().toLowerCase().replace(' ', '_'));
                            int photoID = getResources().getIdentifier("foto_" + nombreFoto, "string", getContext().getPackageName());
                            String urlFoto = getResources().getString(photoID);
                            thumb.setUrlResource(urlFoto);
                            card.addCardThumbnail(thumb);

                            card.setCardElevation(100);
                            cards.add(card);
                        }

                        final CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getContext(), cards);
                        dismissAnimation = (SwipeDismissAnimation) new SwipeDismissAnimation(getContext()).setup(mCardArrayAdapter);

                        // Define your sections
                        List<CardSection> sections = new ArrayList<>();
                        /*sections.add(new CardSection(numDelanteros(cards), "Delanteros"));
                        sections.add(new CardSection(numMedios(cards),"Medios"));
                        sections.add(new CardSection(numDefensas(cards),"Defensas"));
                        sections.add(new CardSection(numPorteros(cards),"Porteros"));*/
                        CardSection[] dummy = new CardSection[sections.size()];

                        //Define your Sectioned adapter
                        final SectionedCardAdapter mAdapter = new SectionedCardAdapter(getContext(), mCardArrayAdapter);
                        mAdapter.setCardSections(sections.toArray(dummy));

                        listView = (CardListView) v.findViewById(R.id.carddemo_list_gplaycard);

                        listView = (CardListView) v.findViewById(R.id.carddemo_list_gplaycard);

                        if (listView != null) {
                            listView.setExternalAdapter(mAdapter, mCardArrayAdapter);
                        }
                    }
                });
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



    class asyncmiequipo extends AsyncTask< String, String, String > {

        String usuario;
        protected void onPreExecute() {
            //para el progress dialog
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Cargando...");
            progressDialog.show();
        }

        protected String doInBackground(String... params) {
            //obtnemos usr
            usuario=params[0];

            //enviamos, recibimos y analizamos los datos en segundo plano.
            if (myteamstatus(usuario)==true){
                return "ok"; //equipo cargado correctamente
            }else{
                return "err"; //equipo no cargado correctamente
            }

        }

        protected void onPostExecute(String result) {

            //INICIAMOS UN NUEVO THREAD EN BACKGROUND PARA OBTENER LAS ALINEACIONES
            new asyncalineaciones(progressDialog).execute(StaticElements.getUser());
            Log.e("POST Tab2=", "" + result);

        }

    }

    public Posicion stringToPosicion (String s){
        switch (s){
            case "PORTERO":
                return Posicion.PORTERO;
            case "DEFENSA":
                return Posicion.DEFENSA;
            case "MEDIO":
                return Posicion.MEDIO;
            case "DELANTERO":
                return Posicion.DELANTERO;
            default: return Posicion.DELANTERO;
        }
    }


    class asyncsellteam extends AsyncTask< String, String, String > {

        String user;
        String player;

        protected void onPreExecute() {}

        protected String doInBackground(String... params) {
            //obtnemos usr y pass
            user=params[0];
            player=params[1];

            //enviamos, recibimos y analizamos los datos en segundo plano.
            teamsellstatus(user, player);
            return "ok";

        }

        protected void onPostExecute(String result) {

            StaticElements.getUser();
            Log.e("onPostExecute=",""+result);


        }

    }

    public boolean teamsellstatus(String user, String player) {

    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
        postparameters2send.add(new BasicNameValuePair("usuario",user));
        postparameters2send.add(new BasicNameValuePair("jugador",player));

        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata = post.getserverdata(postparameters2send, URL_connect_2);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy - HH:mm");
        String currentDateandTime = sdf.format(new Date());
        StaticElements.mensajesCambiados.add(new MensajeInicio(1, currentDateandTime + "\n\n" + user.substring(0, user.indexOf('@')) + " ha PUESTO A LA VENTA a " + player));

        return true;
    }


}