package com.example.grupo7.comunio.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.grupo7.comunio.R;
import com.example.grupo7.comunio.SourceCode.Httppostaux;
import com.example.grupo7.comunio.SourceCode.MensajeInicio;
import com.example.grupo7.comunio.SourceCode.StaticElements;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.prototypes.SectionedCardAdapter;
import it.gmariotti.cardslib.library.view.CardListView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<MensajeInicio> mensajes;
    ArrayList<Card> cards;
    ImageButton add;

    CardListView listView;
    ProgressDialog progressDialog;
    Httppostaux post;
    String IP_Server="adoptaunalien.esy.es";//IP DE NUESTRO PC
    String URL_connect="http://"+IP_Server+"/Comunio/mensaje/recibir_mensaje.php";//ruta en donde estan nuestros archivos
    String URL_connect_2="http://"+IP_Server+"/Comunio/mensaje/enviar_mensaje.php";

    public final static String EXTRA_MESSAGE = "com.grupo7.comunio.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mensajes = new ArrayList<>();
        post=new Httppostaux();
        progressDialog = new ProgressDialog(MainActivity.this, R.style.AppTheme_Dark_Dialog);

        add = (ImageButton) findViewById(R.id.imageButtonAdd);
        add.bringToFront();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Introduce el mensaje");

                // Set up the input
                final EditText input = new EditText(MainActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombreUsuario = StaticElements.getUser();
                        String nombreCorto = nombreUsuario.substring(0, nombreUsuario.indexOf('@'));
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy - HH:mm");
                        String currentDateandTime = sdf.format(new Date());

                        StaticElements.mensajesCambiados.add(new MensajeInicio(2, currentDateandTime + "\n\n" + nombreCorto + ":\n" + input.getText().toString()));

                        //Se recarga la actividad
                        new asyncenviarmensaje(true).execute();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        new asyncinicio().execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_item_inicio);

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
        campoSaldoUsuario.setText("   -   " + StaticElements.customFormat(StaticElements.getSaldo()));


    }
    @Override
    public void onBackPressed() {
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

    public void onCerrarSesionPressed() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
        System.exit(0); //Limpiamos la pila de memoria

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_item_inicio) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //animation
            overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
        } else*/ if (id == R.id.nav_item_equipo) {
            Intent intent = new Intent(this, EquipoActivity.class);
            startActivity(intent);
            //animation
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        } else if (id == R.id.nav_item_mercado) {
            Intent intent = new Intent(this, MercadoActivity.class);
            startActivity(intent);
            //animation
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        } else if (id == R.id.nav_item_liga) {
            Intent intent = new Intent(this, LigaActivity.class);
            startActivity(intent);
            //animation
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        }
        else if (id == R.id.nav_item_micuenta) {
            Intent intent = new Intent(this, MiCuentaActivity.class);
            startActivity(intent);
            //animation
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        }
        else if (id == R.id.nav_item_reglas) {
            Intent intent = new Intent(this, ReglasActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_item_politicaprivacidad) {
            Intent intent = new Intent(this, PoliticaPrivacidadActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStop() {

        //Enviamos los cambios a la BBDD cuando se para el Fragment

        if (!(StaticElements.mensajesCambiados.isEmpty())) {
            new asyncenviarmensaje(false).execute();
        }
        super.onStop();
    }

    public void reloadActiviy(){
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0,0);
        startActivity(intent);
    }


    class asyncinicio extends AsyncTask< String, String, String > {

        String user;
        protected void onPreExecute() {
            //para el progress dialog
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Cargando Mensajes...");
            progressDialog.show();
        }

        protected String doInBackground(String... params) {
            //obtnemos usr y pass
//            user=params[0];

            //enviamos, recibimos y analizamos los datos en segundo plano.
            if (iniciostatus()==true){
                return "ok"; //mercado cargado correctamente
            }else{
                return "err"; //mercado no cargado correctamente
            }

        }

        protected void onPostExecute(String result) {

            progressDialog.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=", "" + result);

        }

    }


    public boolean iniciostatus() {

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
                    String mensaje = json_data.getString("mensaje");//accedemos al valor
                    int tipo = Integer.parseInt(json_data.getString("tipo"));//accedemos al valor

                    //Se crea el jugador y se añade al mercado estático
                    MensajeInicio m = new MensajeInicio(tipo, mensaje);
                    this.mensajes.add(m);

                    Log.e("mensaje: ", mensaje);//muestro por log que obtuvimos
                }

                //Se ejecuta en el thread principal
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        cards = new ArrayList<>();
                        for (int i = 0; i < mensajes.size(); i++) {

                            MensajeInicio m = mensajes.get(i);

                            Card card = new Card(getBaseContext(), R.layout.card_inicio_compraventa);;

                            switch (m.getTipo()){
                                case 1: //Mensajes de compra-venta
                                    card = new Card(getBaseContext(), R.layout.card_inicio_compraventa);
                                    break;
                                case 2: //Mensajes de texto de usuarios
                                    card = new Card(getBaseContext(), R.layout.card_inicio_mensajeusuario);
                                    break;
                            }

                            //Desripción del jugador
                            card.setTitle(m.getMensaje());

                            card.setCardElevation(5);
                            cards.add(card);
                        }

                        final CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getBaseContext(), cards);
                        final SectionedCardAdapter mAdapter = new SectionedCardAdapter(getBaseContext(), mCardArrayAdapter);

                        listView = (CardListView) findViewById(R.id.carddemo_list_gplaycard);

                        listView = (CardListView) findViewById(R.id.carddemo_list_gplaycard);

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



    class asyncenviarmensaje extends AsyncTask< String, String, String > {

        boolean recargar;

        public asyncenviarmensaje (boolean b){
            this.recargar = b;
        }

        protected void onPreExecute() {}

        protected String doInBackground(String... params) {

            enviarmensajestatus();
            return "ok";

        }

        protected void onPostExecute(String result) {
            Log.e("onPostExecute=", ""+result);
            if (recargar) reloadActiviy();
        }

    }

    public boolean enviarmensajestatus() {

        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
        for (int i = 0; i < StaticElements.mensajesCambiados.size() ; i++){
            postparameters2send.add(new BasicNameValuePair("tipo"+i, Integer.toString(StaticElements.mensajesCambiados.get(i).getTipo())));
            postparameters2send.add(new BasicNameValuePair("mensaje"+i, StaticElements.mensajesCambiados.get(i).getMensaje()));
        }


        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata = post.getserverdata(postparameters2send, URL_connect_2);
        Log.i("ENVIADO:", postparameters2send.toString());
        StaticElements.mensajesCambiados.clear();

        return true;
    }

}
