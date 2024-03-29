package com.example.grupo7.comunio.PopUpMenus;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.grupo7.comunio.R;
import com.example.grupo7.comunio.SourceCode.StaticElements;

public class PopUpMenuEventHandle4 implements PopupMenu.OnMenuItemClickListener {
    Context context;
    View button;

    public PopUpMenuEventHandle4(Context context, View button){
        this.context =context;
        this.button = button;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ButtonToAlin bta = new ButtonToAlin();
        if (item.getItemId()== R.id.primerItem4) {
            Button b = (Button) this.button;
            CharSequence nombre = item.getTitle();
            b.setText(nombre);
            int ident = button.getId();
            bta.buttonToAlin(ident, nombre.toString());
            StaticElements.cambiadaAlin = true;

            Toast.makeText(context, item.getTitle() + " en juego", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId()==R.id.segundoItem4){
            Button b = (Button) this.button;
            CharSequence nombre = item.getTitle();
            b.setText(nombre);
            int ident = button.getId();
            bta.buttonToAlin(ident, nombre.toString());
            StaticElements.cambiadaAlin = true;

            Toast.makeText(context, item.getTitle() + " en juego", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId()==R.id.tercerItem4){
            Button b = (Button) this.button;
            CharSequence nombre = item.getTitle();
            b.setText(nombre);
            int ident = button.getId();
            bta.buttonToAlin(ident, nombre.toString());
            StaticElements.cambiadaAlin = true;

            Toast.makeText(context, item.getTitle() + " en juego", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId()==R.id.cuartoItem4){
            Button b = (Button) this.button;
            CharSequence nombre = item.getTitle();
            b.setText(nombre);
            int ident = button.getId();
            bta.buttonToAlin(ident, nombre.toString());
            StaticElements.cambiadaAlin = true;

            Toast.makeText(context, item.getTitle() + " en juego", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}