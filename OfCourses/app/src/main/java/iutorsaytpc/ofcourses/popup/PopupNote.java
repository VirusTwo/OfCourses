package iutorsaytpc.ofcourses.popup;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

import iutorsaytpc.ofcourses.R;

/**
 * Created by johnathan on 26/03/2016.
 */
public class PopupNote extends LinearLayout {

    // Les spinners
    private Spinner spinnerTypeEval;

    // Le context
    private Context context;

    private Integer note = 0;


        public PopupNote(Context context) {
            super(context);
            this.context = context;
            inflate();
            bindViews();
        }

        private void inflate() {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutInflater.inflate(R.layout.popup_note, this, true);
        }

        private void bindViews() {

            // initialisation des composants
            spinnerTypeEval = (Spinner) findViewById(R.id.spinnerTypeTest);


            //-- la liste des catégories d'évaluation --
            ArrayList<String>  listeTypeEval = new ArrayList<String>();
            listeTypeEval.add("CC");
            listeTypeEval.add("DS");
            listeTypeEval.add("Projet");

            ArrayAdapter<String> dataApadaterTypeEval = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, listeTypeEval );
            dataApadaterTypeEval.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTypeEval.setAdapter(dataApadaterTypeEval);
        }


        public String getTypeEval()
         {
            return spinnerTypeEval.getSelectedItem().toString();
         }

    }



