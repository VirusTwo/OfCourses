package iutorsaytpc.ofcourses.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.controller.ConnexionController;
import iutorsaytpc.ofcourses.controller.ListeEleveController;
import iutorsaytpc.ofcourses.controller.ListeGroupesController;
import iutorsaytpc.ofcourses.popup.PopupAppreciation;
import iutorsaytpc.ofcourses.popup.PopupNote;
import iutorsaytpc.ofcourses.widget.StudentRow;

/**
 * Created by Cyril on 25/03/2016.
 */

public class ListeGroupesView extends LinearLayout {
    private Context context;

    private Spinner spinnerMatiere;
    private Spinner spinnerGroupe;
    private List<String> groupList = new ArrayList<String>();
    private List<String> matiereList = new ArrayList<String>();

    private Button buttonSuivant;
    private boolean editActiv = false;


    public ListeGroupesView(Context context) {
        super(context);
        this.context = context;

        inflate();
        bindViews();
    }

    private void inflate() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.content_liste_groupe, this, true);
    }

    private void bindViews(){
        ListeGroupesController controller = new ListeGroupesController(this);

        spinnerMatiere = (Spinner) findViewById(R.id.spinnerMatiere);
        spinnerGroupe = (Spinner) findViewById(R.id.spinnerGroupe);
        buttonSuivant = (Button) findViewById(R.id.buttonSuivant);
        ArrayAdapter<String> dataAdapterMatiere = new ArrayAdapter<String>(super.getContext(), android.R.layout.simple_spinner_item, matiereList);
        dataAdapterMatiere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMatiere.setAdapter(dataAdapterMatiere);


        ArrayAdapter<String> dataAdapterGroupe = new ArrayAdapter<String>(super.getContext(), android.R.layout.simple_spinner_item, groupList);
        dataAdapterGroupe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroupe.setAdapter(dataAdapterGroupe);

        buttonSuivant.setOnClickListener(controller);
    }

    public void setMatList(List<String> matiereList) {
        ArrayAdapter<String> dataAdapterMatiere = new ArrayAdapter<String>(super.getContext(), android.R.layout.simple_spinner_item, matiereList);
        dataAdapterMatiere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMatiere.setAdapter(dataAdapterMatiere);
    }
    public void setGroupeList(List<String> groupList){
        ArrayAdapter<String> dataAdapterGroupe = new ArrayAdapter<String>(super.getContext(), android.R.layout.simple_spinner_item, groupList);
        dataAdapterGroupe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroupe.setAdapter(dataAdapterGroupe);
    }

    public void demoListeGroupeMatiere(){
        List listTMP = new ArrayList<String>();
        listTMP.add("Développement Android");
        listTMP.add("Développement IOS");
        listTMP.add("Maths ingénieur");
        listTMP.add("Robotique");
        listTMP.add("Personal");
        setMatList(listTMP);

        List listTMP2 = new ArrayList<String>();
        listTMP2.add("A1");
        listTMP2.add("A2");
        listTMP2.add("B1");
        listTMP2.add("B2");
        listTMP2.add("C1");
        listTMP2.add("C2");
        setGroupeList(listTMP2);

    }
}