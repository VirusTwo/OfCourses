package iutorsaytpc.ofcourses.view;

import android.app.Activity;
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
import iutorsaytpc.ofcourses.bd.BD;
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
    private List<Integer> groupIdList = new ArrayList<Integer>();
    private List<Integer> matiereIdList = new ArrayList<Integer>();

    private Button buttonSuivant;
    private boolean editActiv = false;


    ArrayList<Object> res;

    public ListeGroupesView(Context context) {
        super(context);
        this.context = context;

        inflate();
        bindViews();
        demoListeGroupeMatiere();
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
        this.matiereList = matiereList;
        ArrayAdapter<String> dataAdapterMatiere = new ArrayAdapter<String>(super.getContext(), android.R.layout.simple_spinner_item, matiereList);
        dataAdapterMatiere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMatiere.setAdapter(dataAdapterMatiere);
    }
    public void setGroupeList(List<String> groupList){
        this.groupList = groupList;
        ArrayAdapter<String> dataAdapterGroupe = new ArrayAdapter<String>(super.getContext(), android.R.layout.simple_spinner_item, groupList);
        dataAdapterGroupe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroupe.setAdapter(dataAdapterGroupe);
    }

    public void demoListeGroupeMatiere(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                res = BD.getMatieresClasses();
                ((Activity) getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setMatList((List<String>) res.get(0));
                        setGroupeList((List<String>) res.get(1));
                        matiereIdList = (List<Integer>) res.get(2);
                        groupIdList = (List<Integer>) res.get(3);
                    }
                });
            }
        }).start();
    }

    public int getSelectedIdMatiere() {
        return matiereIdList.get(spinnerMatiere.getSelectedItemPosition());
    }
    public String getSelectedNameMatiere() {
        return matiereList.get(spinnerMatiere.getSelectedItemPosition());
    }
    public int getSelectedIdClasse() {
        return groupIdList.get(spinnerGroupe.getSelectedItemPosition());
    }

    public String getSelectedNameClasse() {
        return groupList.get(spinnerGroupe.getSelectedItemPosition());
    }
}