package com.example.grupo7.comunio.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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


public class MercadoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SwipeDismissAnimation dismissAnimation;
    ArrayList<Card> cards;

    CardListView listView;
    ProgressDialog progressDialog;
    Httppostaux post;
    String IP_Server="adoptaunalien.esy.es";//IP DE NUESTRO PC
    String URL_connect="http://"+IP_Server+"/Comunio/mercado/mercado.php";//ruta en donde estan nuestros archivos
    String URL_connect_2="http://"+IP_Server+"/Comunio/mercado/fichar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado);
        post=new Httppostaux();
        progressDialog = new ProgressDialog(MercadoActivity.this, R.style.AppTheme_Dark_Dialog);
        StaticElements.removeMercado();

        new asyncmarket().execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_mercado);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_mercado);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_item_mercado);

        //Se pone el nombre de usuario en el nav_header
        TextView campoNombreUsuario = (TextView) findViewById(R.id.textViewUsuarioMenu);
        String nombreUsuario = StaticElements.getUser();
        campoNombreUsuario.setText((nombreUsuario.substring(0, nombreUsuario.indexOf('@'))));
        //Se pone los puntos actuales en el nav_header
        TextView campoPuntosUsuario = (TextView) findViewById(R.id.textViewPuntosMenu);
        String puntosUsuario = Integer.toString(StaticElements.puntosUsuario);
        campoPuntosUsuario.setText(puntosUsuario + " puntos");
        //Se pone el saldo actual en el nav_header
        TextView campoSaldoUsuario = (TextView) findViewById(R.id.textViewSaldoMenu);
        String saldoUsuario = Integer.toString(StaticElements.getSaldo());
        campoSaldoUsuario.setText("   -   " + StaticElements.customFormat(StaticElements.getSaldo()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cerrarsesion) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¿Desea cerrar la sesión?");
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    onCerrarSesionPressed();
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //TODO
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void onCerrarSesionPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        System.exit(0); //Limpiamos la pila de memoria
    }


    private class CustomExpandCard extends CardExpand {

        private Button b;
        private ArrayList<Card> array;

        //Use your resource ID for your inner layout
        public CustomExpandCard(Context context, ArrayList<Card> a, boolean t) {
            super(context, R.layout.inner_expand);
            if (!t){
                this.setInnerLayout(R.layout.inner_expand_naranja);
            }
            this.array = a;
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            if (view == null) return;

            b = (Button) view.findViewById(R.id.boton_expand_mercado);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Card c = getParentCard();
                    int precioJ = getPrecioJugador(c.getCardHeader().getTitle());
                    int ficharRecuperar = 1;
                    if (c.getInnerLayout() == R.layout.card_inner_naranja) { //Si el jugador está en una tarjeta de recuperar se reduce su coste
                        precioJ = Jugador.getLowPrice(precioJ);
                        ficharRecuperar = 0;
                    }
                    if (StaticElements.getSaldo() >= precioJ) {

                        dismissAnimation.animateDismiss(c);
                        ArrayList<Card> newArray = new ArrayList<Card>(array); //para dar a la animación tiempo para finalizar
                        newArray.remove(c);
                        array = newArray;
                        //Se resta el precio del jugador comprado al saldo del usuario
                        StaticElements.setSaldo(StaticElements.getSaldo() - precioJ);

                        new asyncbuymarket().execute(StaticElements.getUser(), c.getCardHeader().getTitle(), Integer.toString(ficharRecuperar));
                        eliminarJugador(c.getCardHeader().getTitle());
                        Toast.makeText(getContext(), "Jugador Comprado", Toast.LENGTH_SHORT).show();

                    } else {
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(100);
                        Toast.makeText(getContext(), "No hay suficientes fondos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_item_inicio) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            finish();
        } else if (id == R.id.nav_item_equipo) {
            Intent intent = new Intent(this, EquipoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            finish();
        /*} else if (id == R.id.nav_item_mercado) {
            Intent intent = new Intent(this, MercadoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);*/
        } else if (id == R.id.nav_item_liga) {
            Intent intent = new Intent(this, LigaActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            finish();
        }
        else if (id == R.id.nav_item_micuenta) {
            Intent intent = new Intent(this, MiCuentaActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            finish();
        }
        else if (id == R.id.nav_item_reglas) {
            Intent intent = new Intent(this, ReglasActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_item_politicaprivacidad) {
            Intent intent = new Intent(this, PoliticaPrivacidadActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_mercado);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //Elimina el jugador del array estático del mercado dado su nombre
    public void eliminarJugador(String nombre){
        boolean encontrado = false;
        int i = 0;
        Jugador j;
        while (i < StaticElements.getArrayMercado().size() && !encontrado) {
            j = StaticElements.getArrayMercado().get(i);
            if (j.getNombre().equals(nombre)){
                encontrado = true;
                StaticElements.getArrayMercado().remove(j);
            }
            else i++;
        }
    }

    //Devuelve el precio de un jugador dado su nombre
    public int getPrecioJugador (String nombre){
        boolean encontrado = false;
        int i = 0;
        int precio = 0;
        Jugador j;
        while (i < StaticElements.getArrayMercado().size() && !encontrado) {
            j = StaticElements.getArrayMercado().get(i);
            if (j.getNombre().equals(nombre)){
                encontrado = true;
                precio = j.getPrecio();
            }
            else i++;
        }
        return precio;
    }




    public boolean marketstatus() {

    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();

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
                    String miequipo = json_data.getString("miequipo");

                    //Se crea el jugador y se añade al mercado estático
                    Jugador j = new Jugador(nombre, edad, posicion, precio, new ArrayList<Integer>(), equipo, miequipo);
                    StaticElements.getArrayMercado().add(j);

                    Log.e("Nombre: ", nombre);//muestro por log que obtuvimos
                }

                //Se ejecuta en el thread principal
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        cards = new ArrayList<>();
                        for (int i = 0; i < StaticElements.getArrayMercado().size(); i++) {

                            Jugador j = StaticElements.getArrayMercado().get(i);

                            Card card;
                            boolean b;
                            if (j.getMiequipo().equals(StaticElements.getUser())){
                                card = new Card(getBaseContext(), R.layout.card_inner_naranja);
                                //Desripción del jugador
                                card.setTitle(j.toStringNoPointsLowPrice());
                                b = false;
                            }
                            else {
                                card = new Card(getBaseContext(), R.layout.card_inner);
                                //Desripción del jugador
                                card.setTitle(j.toStringNoPoints());
                                b = true;
                            }

                            //Create a CardHeader
                            CardHeader header = new CardHeader(getBaseContext());
                            header.setTitle(j.getNombre());

                            //Expandable de botón comprar
                            final CustomExpandCard expand = new CustomExpandCard(getBaseContext(), cards, b);
                            card.addCardExpand(expand);
                            ViewToClickToExpand viewToClickToExpand =
                                    ViewToClickToExpand.builder()
                                            .highlightView(false)
                                            .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);
                            card.setViewToClickToExpand(viewToClickToExpand);

                            //Título de la tarjeta
                            card.addCardHeader(header);

                            //Foto del jugador
                            CardThumbnail thumb = new CardThumbnail(getBaseContext());
                            String nombreFoto = StaticElements.stripAccents(j.getNombre().toLowerCase().replace(' ', '_'));
                            int photoID = getResources().getIdentifier("foto_" + nombreFoto, "string", getBaseContext().getPackageName());
                            Log.i("EN MERCADO: ", "Nombre string: " + "foto_" + nombreFoto);
                            String urlFoto = getResources().getString(photoID);

                            thumb.setUrlResource(urlFoto);
                            card.addCardThumbnail(thumb);

                            card.setCardElevation(100);
                            cards.add(card);
                        }

                        final CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getBaseContext(), cards);
                        dismissAnimation = (SwipeDismissAnimation) new SwipeDismissAnimation(getBaseContext()).setup(mCardArrayAdapter);

                        // Define your sections
                        List<CardSection> sections = new ArrayList<>();
                        /*sections.add(new CardSection(numDelanteros(cards), "Delanteros"));
                        sections.add(new CardSection(numMedios(cards),"Medios"));
                        sections.add(new CardSection(numDefensas(cards),"Defensas"));
                        sections.add(new CardSection(numPorteros(cards),"Porteros"));*/
                        CardSection[] dummy = new CardSection[sections.size()];

                        //Define your Sectioned adapter
                        final SectionedCardAdapter mAdapter = new SectionedCardAdapter(getBaseContext(), mCardArrayAdapter);
                        mAdapter.setCardSections(sections.toArray(dummy));

                        listView = (CardListView) findViewById(R.id.carddemo_list_gplaycard);

                        listView = (CardListView) findViewById(R.id.carddemo_list_gplaycard);

                        if (listView != null) {
                            listView.setExternalAdapter(mAdapter, mCardArrayAdapter);
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        } else {    //json obtenido invalido verificar parte WEB.
            Log.e("JSON  ", "ERROR");
            return false;
        }
    }


    class asyncmarket extends AsyncTask< String, String, String > {

        String user;
        protected void onPreExecute() {
            //para el progress dialog
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Cargando...");
            progressDialog.show();
        }

        protected String doInBackground(String... params) {
            //enviamos, recibimos y analizamos los datos en segundo plano.
            if (marketstatus()==true){
                return "ok"; //mercado cargado correctamente
            }else{
                return "err"; //mercado no cargado correctamente
            }

        }

        /*Una vez terminado doInBackground segun lo que haya ocurrido
        pasamos a la sig. activity
        o mostramos error*/
        protected void onPostExecute(String result) {

            progressDialog.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=",""+result);

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





    class asyncbuymarket extends AsyncTask< String, String, String > {

        String user;
        String player;
        String ficharRecuperar;

        protected void onPreExecute() {}

        protected String doInBackground(String... params) {
            //obtnemos usr y pass
            user = params[0];
            player = params[1];
            ficharRecuperar = params[2];

            //enviamos, recibimos y analizamos los datos en segundo plano.
            marketbuystatus(user, player, ficharRecuperar);

            return "ok";
        }

        protected void onPostExecute(String result) {
            Log.e("onPostExecute=",""+result);
        }

    }

    public boolean marketbuystatus(String user, String player, String ficharRecuperar) {

    	/*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
        postparameters2send.add(new BasicNameValuePair("usuario",user));
        postparameters2send.add(new BasicNameValuePair("jugador",player));
        postparameters2send.add(new BasicNameValuePair("fichar",ficharRecuperar));

        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata = post.getserverdata(postparameters2send, URL_connect_2);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy - HH:mm");
        String currentDateandTime = sdf.format(new Date());
        StaticElements.mensajesCambiados.add(new MensajeInicio(1, currentDateandTime + "\n\n" + user.substring(0, user.indexOf('@')) + " ha COMPRADO a " + player));

        return true;
    }
}
