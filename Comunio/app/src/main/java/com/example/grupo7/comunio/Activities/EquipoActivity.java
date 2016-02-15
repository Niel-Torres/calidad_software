package com.example.grupo7.comunio.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.grupo7.comunio.PopUpMenus.PopUpMenuEventHandle1;
import com.example.grupo7.comunio.PopUpMenus.PopUpMenuEventHandle2;
import com.example.grupo7.comunio.PopUpMenus.PopUpMenuEventHandle3;
import com.example.grupo7.comunio.PopUpMenus.PopUpMenuEventHandle4;
import com.example.grupo7.comunio.PopUpMenus.PopUpMenuEventHandle5;
import com.example.grupo7.comunio.R;
import com.example.grupo7.comunio.SourceCode.Jugador;
import com.example.grupo7.comunio.SourceCode.StaticElements;
import com.example.grupo7.comunio.Tabs.SlidingTabLayout;
import com.example.grupo7.comunio.Tabs.ViewPagerAdapter;

import java.util.ArrayList;

public class EquipoActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = new CharSequence[2]; // = {getResources().getString(R.string.equipo_alineacion), getResources().getString(R.string.equipo_jugadores), getResources().getString(R.string.equipo_misfichajes)};
    int Numboftabs = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        Titles[0] = getString(R.string.equipo_alineacion);
        Titles[1] = getString(R.string.equipo_jugadores);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_equipo);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_equipo);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_item_equipo);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager_equipo);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs_equipo);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_item_inicio) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //animation
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        /*if (id == R.id.nav_item_equipo) {
            Intent intent = new Intent(this, EquipoActivity.class);
            startActivity(intent);
            //animation
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);*/
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_equipo);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    public void showPopUpDelanteros(View view){
        PopupMenu popupMenu=new PopupMenu(this,view);
        MenuInflater menuInflater=popupMenu.getMenuInflater();
        ArrayList<Jugador> misJugadoresBanquillo = new ArrayList<>();
        switch(StaticElements.getCurrentAlin()){
            case 442:
                misJugadoresBanquillo = StaticElements.getDelanterosBanquillo442();
                break;
            case 433:
                misJugadoresBanquillo = StaticElements.getDelanterosBanquillo433();
                break;
            case 343:
                misJugadoresBanquillo = StaticElements.getDelanterosBanquillo343();
                break;
        }
        this.showPopUpBanquillo(misJugadoresBanquillo, popupMenu, menuInflater, view);
    }

    public void showPopUpMedios(View view){
        PopupMenu popupMenu=new PopupMenu(this,view);
        MenuInflater menuInflater=popupMenu.getMenuInflater();
        ArrayList<Jugador> misJugadoresBanquillo = new ArrayList<>();
        switch(StaticElements.getCurrentAlin()){
            case 442:
                misJugadoresBanquillo = StaticElements.getMediosBanquillo442();
                break;
            case 433:
                misJugadoresBanquillo = StaticElements.getMediosBanquillo433();
                break;
            case 343:
                misJugadoresBanquillo = StaticElements.getMediosBanquillo343();
                break;
        }
        this.showPopUpBanquillo(misJugadoresBanquillo, popupMenu, menuInflater, view);
    }

    public void showPopUpDefensas(View view){
        PopupMenu popupMenu=new PopupMenu(this,view);
        MenuInflater menuInflater=popupMenu.getMenuInflater();
        ArrayList<Jugador> misJugadoresBanquillo = new ArrayList<>();
        switch(StaticElements.getCurrentAlin()){
            case 442:
                misJugadoresBanquillo = StaticElements.getDefensasBanquillo442();
                break;
            case 433:
                misJugadoresBanquillo = StaticElements.getDefensasBanquillo433();
                break;
            case 343:
                misJugadoresBanquillo = StaticElements.getDefensasBanquillo343();
                break;
        }
        this.showPopUpBanquillo(misJugadoresBanquillo, popupMenu, menuInflater, view);
    }

    public void showPopUpPorteros(View view){
        PopupMenu popupMenu=new PopupMenu(this,view);
        MenuInflater menuInflater=popupMenu.getMenuInflater();
        ArrayList<Jugador> misJugadoresBanquillo = new ArrayList<>();
        switch(StaticElements.getCurrentAlin()){
            case 442:
                misJugadoresBanquillo = StaticElements.getPorterosBanquillo442();
                break;
            case 433:
                misJugadoresBanquillo = StaticElements.getPorterosBanquillo433();
                break;
            case 343:
                misJugadoresBanquillo = StaticElements.getPorterosBanquillo343();
                break;
        }
        this.showPopUpBanquillo(misJugadoresBanquillo, popupMenu, menuInflater, view);
    }



    public void showPopUpBanquillo(ArrayList<Jugador> misJugadoresBanquillo, PopupMenu popupMenu, MenuInflater menuInflater, View button){

        if (misJugadoresBanquillo.size() == 1) {
            PopUpMenuEventHandle1 popUpMenuEventHandle = new PopUpMenuEventHandle1(this, button);
            popupMenu.setOnMenuItemClickListener(popUpMenuEventHandle);
            menuInflater.inflate(R.menu.pop_up_1, popupMenu.getMenu());
            popupMenu.show();
            MenuItem item = popupMenu.getMenu().findItem(R.id.primerItem1);
            item.setTitle(misJugadoresBanquillo.get(0).getNombre());
        }
        else if (misJugadoresBanquillo.size() == 2) {
            PopUpMenuEventHandle2 popUpMenuEventHandle = new PopUpMenuEventHandle2(this, button);
            popupMenu.setOnMenuItemClickListener(popUpMenuEventHandle);
            menuInflater.inflate(R.menu.pop_up_2, popupMenu.getMenu());
            popupMenu.show();
            MenuItem item1 = popupMenu.getMenu().findItem(R.id.primerItem2);
            item1.setTitle(misJugadoresBanquillo.get(0).getNombre());
            MenuItem item2 = popupMenu.getMenu().findItem(R.id.segundoItem2);
            item2.setTitle(misJugadoresBanquillo.get(1).getNombre());
        }
        else if (misJugadoresBanquillo.size() == 3) {
            PopUpMenuEventHandle3 popUpMenuEventHandle = new PopUpMenuEventHandle3(this, button);
            popupMenu.setOnMenuItemClickListener(popUpMenuEventHandle);
            menuInflater.inflate(R.menu.pop_up_3, popupMenu.getMenu());
            popupMenu.show();
            MenuItem item1 = popupMenu.getMenu().findItem(R.id.primerItem3);
            item1.setTitle(misJugadoresBanquillo.get(0).getNombre());
            MenuItem item2 = popupMenu.getMenu().findItem(R.id.segundoItem3);
            item2.setTitle(misJugadoresBanquillo.get(1).getNombre());
            MenuItem item3 = popupMenu.getMenu().findItem(R.id.tercerItem3);
            item3.setTitle(misJugadoresBanquillo.get(2).getNombre());

        }
        else if (misJugadoresBanquillo.size() == 4) {
            PopUpMenuEventHandle4 popUpMenuEventHandle = new PopUpMenuEventHandle4(this, button);
            popupMenu.setOnMenuItemClickListener(popUpMenuEventHandle);
            menuInflater.inflate(R.menu.pop_up_4, popupMenu.getMenu());
            popupMenu.show();
            MenuItem item1 = popupMenu.getMenu().findItem(R.id.primerItem4);
            item1.setTitle(misJugadoresBanquillo.get(0).getNombre());
            MenuItem item2 = popupMenu.getMenu().findItem(R.id.segundoItem4);
            item2.setTitle(misJugadoresBanquillo.get(1).getNombre());
            MenuItem item3 = popupMenu.getMenu().findItem(R.id.tercerItem4);
            item3.setTitle(misJugadoresBanquillo.get(2).getNombre());
            MenuItem item4 = popupMenu.getMenu().findItem(R.id.cuartoItem4);
            item4.setTitle(misJugadoresBanquillo.get(3).getNombre());
        }
        else if (misJugadoresBanquillo.size() >= 5) {
            PopUpMenuEventHandle5 popUpMenuEventHandle = new PopUpMenuEventHandle5(this, button);
            popupMenu.setOnMenuItemClickListener(popUpMenuEventHandle);
            menuInflater.inflate(R.menu.pop_up_5, popupMenu.getMenu());
            popupMenu.show();
            MenuItem item1 = popupMenu.getMenu().findItem(R.id.primerItem5);
            item1.setTitle(misJugadoresBanquillo.get(0).getNombre());
            MenuItem item2 = popupMenu.getMenu().findItem(R.id.segundoItem5);
            item2.setTitle(misJugadoresBanquillo.get(1).getNombre());
            MenuItem item3 = popupMenu.getMenu().findItem(R.id.tercerItem5);
            item3.setTitle(misJugadoresBanquillo.get(2).getNombre());
            MenuItem item4 = popupMenu.getMenu().findItem(R.id.cuartoItem5);
            item4.setTitle(misJugadoresBanquillo.get(3).getNombre());
            MenuItem item5 = popupMenu.getMenu().findItem(R.id.quintoItem5);
            item5.setTitle(misJugadoresBanquillo.get(4).getNombre());
        }
    }
}
