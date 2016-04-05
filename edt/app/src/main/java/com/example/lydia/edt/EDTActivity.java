package com.example.lydia.edt;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EDTActivity extends AppCompatActivity {

    private ArrayList<String> edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edt);

        //on récupère l'arraylist depuis la connexion
       edt = BD.getCours();


        //et on met à jour l'edt
            updateLabel(edt);
    }



    public void updateLabel(ArrayList<String> ar){

        ar = new ArrayList<>();
        String code;
        String nomat;
        String numTP;
        String salle;
        int taille = ar.size();
        int quadr;

        //sert pour éviter les messages d'erreurs du type "textView may no be initialized"
        TextView view = new TextView(this);

        //sert pour récupérer le textView à modifier
        Resources res = getResources();
        int id;

        //on parcourt l'arrayList
        for(int i=0; i<taille; i++){
            //modulo 4 pour savoir quel est l'élément pour parcourir le quadruplet d'informations sur le cours{
            quadr = i % 4;

            //pour savoir quelle action faire
            switch(quadr)
            {
                //si premier élément du quadruplet (le code jour/heure)
                case 0:
                    code=ar.get(i);
                    //sert à récupérer le code et à s'en servir pour identifier la textView à modifier
                    id = res.getIdentifier(code, "id", getApplicationContext().getPackageName());
                    view = (TextView) findViewById(id);
                    break;


                //si deuxième élément du quadruplet, nom de la matière
                case 1:
                    nomat=ar.get(i);
                    view.setText(nomat + "\n\tTP ");
                    couleurBackgroud(view, nomat);
                    break;


                //si troisième élément, numéro du tp
                case 2:
                    numTP=ar.get(i);
                    numTP=view.getText().toString()+numTP;
                    view.setText(numTP + "\n\t");
                    break;


                //si quatrième élément, salle
                case 3:
                    salle=ar.get(i);
                    salle=view.getText().toString()+salle;
                    view.setText( salle.toString());
                    break;
            }
        }
    }






    //changer la couleur du backgroud en fonction de la matière
    public void couleurBackgroud (TextView tv, String s){

        switch (s) {
            case "Codage" : tv.setBackgroundColor(Color.BLUE);
                break;

            case "Maths" : tv.setBackgroundColor(Color.RED);
                break;

            case "Poo" : tv.setBackgroundColor(Color.CYAN);
                break;


            case "Coo" : tv.setBackgroundColor(Color.GRAY);
                break;


            case "Android" : tv.setBackgroundColor(100);
                break;


            case "Web" : tv.setBackgroundColor(255);
                break;


            case "Ios" : tv.setBackgroundColor(100);
                break;


            case "Réseaux" : tv.setBackgroundColor(46);
                break;


            case "Archi" : tv.setBackgroundColor(33);
                break;

            default:tv.setBackgroundColor(Color.WHITE);
                break;

        }

    }

}
