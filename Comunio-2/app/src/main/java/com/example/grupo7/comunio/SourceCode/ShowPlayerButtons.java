package com.example.grupo7.comunio.SourceCode;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.grupo7.comunio.R;

/**
 * Created by Pablo on 15/11/2015.
 */
public class ShowPlayerButtons {

    View v;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30, b31, b32, b33;

    RelativeLayout alineacion442;
    RelativeLayout alineacion433;
    RelativeLayout alineacion343;

    public ShowPlayerButtons (View v){
        this.v = v;

        b1 = (Button) v.findViewById(R.id.button1);
        b2 = (Button) v.findViewById(R.id.button2);
        b3 = (Button) v.findViewById(R.id.button3);
        b4 = (Button) v.findViewById(R.id.button4);
        b5 = (Button) v.findViewById(R.id.button5);
        b6 = (Button) v.findViewById(R.id.button6);
        b7 = (Button) v.findViewById(R.id.button7);
        b8 = (Button) v.findViewById(R.id.button8);
        b9 = (Button) v.findViewById(R.id.button9);
        b10 = (Button) v.findViewById(R.id.button10);
        b11= (Button) v.findViewById(R.id.button11);
        b12 = (Button) v.findViewById(R.id.button12);
        b13 = (Button) v.findViewById(R.id.button13);
        b14 = (Button) v.findViewById(R.id.button14);
        b15 = (Button) v.findViewById(R.id.button15);
        b16 = (Button) v.findViewById(R.id.button16);
        b17 = (Button) v.findViewById(R.id.button17);
        b18 = (Button) v.findViewById(R.id.button18);
        b19 = (Button) v.findViewById(R.id.button19);
        b20= (Button) v.findViewById(R.id.button20);
        b21 = (Button) v.findViewById(R.id.button21);
        b22 = (Button) v.findViewById(R.id.button22);
        b23= (Button) v.findViewById(R.id.button23);
        b24= (Button) v.findViewById(R.id.button24);
        b25= (Button) v.findViewById(R.id.button25);
        b26= (Button) v.findViewById(R.id.button26);
        b27= (Button) v.findViewById(R.id.button27);
        b28 = (Button) v.findViewById(R.id.button28);
        b29 = (Button) v.findViewById(R.id.button29);
        b30 = (Button) v.findViewById(R.id.button30);
        b31 = (Button) v.findViewById(R.id.button31);
        b32 = (Button) v.findViewById(R.id.button32);
        b33 = (Button) v.findViewById(R.id.button33);

        alineacion442 = (RelativeLayout) v.findViewById(R.id.alineacion_442);
        alineacion433 = (RelativeLayout) v.findViewById(R.id.alineacion_433);
        alineacion343 = (RelativeLayout) v.findViewById(R.id.alineacion_343);
    }

    public void setAlineacionVisible(int alin){
        switch (alin) {
            case 442:
                alineacion442.setVisibility(View.VISIBLE);
                alineacion433.setVisibility(View.INVISIBLE);
                alineacion343.setVisibility(View.INVISIBLE);
                b1.setText(StaticElements.getAlineacion442()[0].getNombre());
                b2.setText(StaticElements.getAlineacion442()[1].getNombre());
                b3.setText(StaticElements.getAlineacion442()[2].getNombre());
                b4.setText(StaticElements.getAlineacion442()[4].getNombre());
                b5.setText(StaticElements.getAlineacion442()[5].getNombre());
                b6.setText(StaticElements.getAlineacion442()[3].getNombre());
                b7.setText(StaticElements.getAlineacion442()[6].getNombre());
                b8.setText(StaticElements.getAlineacion442()[8].getNombre());
                b9.setText(StaticElements.getAlineacion442()[9].getNombre());
                b10.setText(StaticElements.getAlineacion442()[7].getNombre());
                b11.setText(StaticElements.getAlineacion442()[10].getNombre());
                break;
            case 433:
                alineacion442.setVisibility(View.INVISIBLE);
                alineacion433.setVisibility(View.VISIBLE);
                alineacion343.setVisibility(View.INVISIBLE);
                b12.setText(StaticElements.getAlineacion433()[0].getNombre());
                b13.setText(StaticElements.getAlineacion433()[1].getNombre());
                b14.setText(StaticElements.getAlineacion433()[2].getNombre());
                b15.setText(StaticElements.getAlineacion433()[3].getNombre());
                b16.setText(StaticElements.getAlineacion433()[4].getNombre());
                b17.setText(StaticElements.getAlineacion433()[5].getNombre());
                b18.setText(StaticElements.getAlineacion433()[6].getNombre());
                b19.setText(StaticElements.getAlineacion433()[7].getNombre());
                b20.setText(StaticElements.getAlineacion433()[8].getNombre());
                b21.setText(StaticElements.getAlineacion433()[9].getNombre());
                b22.setText(StaticElements.getAlineacion433()[10].getNombre());
                break;
            case 343:
                alineacion442.setVisibility(View.INVISIBLE);
                alineacion433.setVisibility(View.INVISIBLE);
                alineacion343.setVisibility(View.VISIBLE);
                b23.setText(StaticElements.getAlineacion343()[0].getNombre());
                b24.setText(StaticElements.getAlineacion343()[1].getNombre());
                b25.setText(StaticElements.getAlineacion343()[2].getNombre());
                b26.setText(StaticElements.getAlineacion343()[3].getNombre());
                b27.setText(StaticElements.getAlineacion343()[4].getNombre());
                b28.setText(StaticElements.getAlineacion343()[5].getNombre());
                b29.setText(StaticElements.getAlineacion343()[6].getNombre());
                b30.setText(StaticElements.getAlineacion343()[7].getNombre());
                b31.setText(StaticElements.getAlineacion343()[8].getNombre());
                b32.setText(StaticElements.getAlineacion343()[9].getNombre());
                b33.setText(StaticElements.getAlineacion343()[10].getNombre());
                break;
        }
    }
}
