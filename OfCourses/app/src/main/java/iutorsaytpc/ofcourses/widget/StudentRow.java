package iutorsaytpc.ofcourses.widget;

import android.content.Context;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * Created by Cyril on 18/03/2016.
 */
public class StudentRow extends TableRow {
    private TextView textView;
    private EditText editText;
    private float note[];
    private boolean noteChanged = false;
    private String commentaire;
    private String nomEleve;
    private ArrayList<NoteSwitcher> noteSwitchers;
    public final static LayoutParams LPROW = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    public final static LayoutParams LPTEXT = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    public final static LayoutParams LPEDITTEXT = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    public final static LayoutParams LPSWITCHTER = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

    public StudentRow(Context context,String nomEleve, String commentaire, float note[]){
        super(context);
        this.noteSwitchers = new ArrayList<NoteSwitcher>();
        this.note = note.clone();
        this.nomEleve = nomEleve;
        this.commentaire = commentaire;
    }

    public void generateRow(){

        LPTEXT.setMargins(0,0,20,0);
        //Déclarer d'un switcher
        ViewSwitcher viewSwitcher;
        NoteSwitcher noteSwitcher;
        //Déclarer un TextView
        TextView tmpTxtView;
        //Déclarer un edit text
        EditText tmpEditText;

        //Création de la ligne
        TableRow row = new TableRow(super.getContext());
        setLayoutParams(LPROW);

        //Ajout du nom de l'élève
        tmpTxtView = new TextView(super.getContext());
        tmpTxtView.setText(nomEleve);
        tmpTxtView.setTextSize(15);
        tmpTxtView.setLayoutParams(LPTEXT);
        addView(tmpTxtView);

        for(int i = 0; i < note.length; i+=2){
            noteSwitcher =  new NoteSwitcher(super.getContext(),note[i + 1],this, (int) note[i]);
            addView(noteSwitcher);
            noteSwitchers.add(noteSwitcher);
        }

        /*
        for(float x:note){
            noteSwitcher =  new NoteSwitcher(super.getContext(),x,this, id_note);
            addView(noteSwitcher);
            noteSwitchers.add(noteSwitcher);
        }*/

        tmpTxtView = new TextView(super.getContext());
        tmpTxtView.setText(commentaire);
        tmpTxtView.setTextSize(15);
        tmpTxtView.setLayoutParams(LPTEXT);
        addView(tmpTxtView);

        tmpTxtView = new TextView(super.getContext());
        tmpTxtView.setText(String.valueOf(10));
        tmpTxtView.setTextSize(15);
        tmpTxtView.setLayoutParams(LPTEXT);
        addView(tmpTxtView);
    }
    public void switchView(){
        for(NoteSwitcher v : noteSwitchers){
            v.showNext();
        }
    }
}
