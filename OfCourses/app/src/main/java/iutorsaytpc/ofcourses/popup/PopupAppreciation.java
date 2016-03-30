package iutorsaytpc.ofcourses.popup;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.controller.PopupAppreciationController;

/**
 * Created by simon on 24/03/2016.
 */
public class PopupAppreciation extends LinearLayout {

    private Spinner spinnerEtudiant;
    private Context context;
    private RadioGroup rg1;
    private RadioGroup rg2;
    private RadioGroup rg3;
    private RadioGroup rg4;
    private RadioGroup rg5;

    private EditText commentaire;
    private TextView txtTotal;
    private Integer sommeNote = 0;

    private ArrayList<String> listeEleves;
    public PopupAppreciation(Context context,ArrayList<String> listeEleves) {
        super(context);
        this.context = context;
        this.listeEleves = listeEleves;

        inflate();
        bindViews();
    }

    private void inflate() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.popup_appreciation, this, true);
    }

    private void bindViews() {
        rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
        rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
        rg3 = (RadioGroup) findViewById(R.id.radioGroup3);
        rg4 = (RadioGroup) findViewById(R.id.radioGroup4);
        rg5 = (RadioGroup) findViewById(R.id.radioGroup5);

        commentaire = (EditText) findViewById(R.id.editCommentaire);
        spinnerEtudiant = (Spinner) findViewById(R.id.spinner);

        ArrayList<String> lstEtudiant = new ArrayList<>();
        lstEtudiant.add("John");
        lstEtudiant.add("Paul");
        lstEtudiant.add("Rick");
        lstEtudiant.add("Carl");

        ArrayAdapter<String> dataApadaterEtudiant = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, lstEtudiant );
        dataApadaterEtudiant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEtudiant.setAdapter(dataApadaterEtudiant);

        PopupAppreciationController controller = new PopupAppreciationController(this);

        rg1.setOnCheckedChangeListener(controller);
        rg2.setOnCheckedChangeListener(controller);
        rg3.setOnCheckedChangeListener(controller);
        rg4.setOnCheckedChangeListener(controller);
        rg5.setOnCheckedChangeListener(controller);

        txtTotal = (TextView) findViewById(R.id.editNote);
    }

    public Integer getSomme(){
        sommeNote = rg1.indexOfChild(findViewById(rg1.getCheckedRadioButtonId())) + rg2.indexOfChild(findViewById(rg2.getCheckedRadioButtonId())) + rg3.indexOfChild(findViewById(rg3.getCheckedRadioButtonId())) + rg4.indexOfChild(findViewById(rg4.getCheckedRadioButtonId())) + rg5.indexOfChild(findViewById(rg5.getCheckedRadioButtonId()));
        txtTotal.setText(String.valueOf(sommeNote));
        return sommeNote;
    }

    public String getCommentaire(){
        return commentaire.getText().toString();
    }

    public String getEtudiant()
    {
        return spinnerEtudiant.getSelectedItem().toString();
    }
}



