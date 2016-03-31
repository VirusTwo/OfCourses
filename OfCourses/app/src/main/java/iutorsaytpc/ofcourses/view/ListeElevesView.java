package iutorsaytpc.ofcourses.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.bd.BD;
import iutorsaytpc.ofcourses.controller.ConnexionController;
import iutorsaytpc.ofcourses.controller.ListeEleveController;
import iutorsaytpc.ofcourses.popup.PopupAppreciation;
import iutorsaytpc.ofcourses.popup.PopupNote;
import iutorsaytpc.ofcourses.widget.StudentRow;

/**
 * Created by Cyril on 25/03/2016.
 */

public class ListeElevesView extends LinearLayout {

    private Context context;
    private Button btnAddAppreciation;
    private Button btnAddNote;
    private Button btnModifier;
    private Button btnSauvergarder;

    private TableLayout tableEleve;
    private ArrayList<StudentRow> studentRows = new ArrayList<StudentRow>();

    private AlertDialog participationDialog;
    private AlertDialog marksDialog;

    private boolean editActiv = false;

    ArrayList<Object>  res;

    public ListeElevesView(Context context) {
        super(context);
        this.context = context;

        inflate();
        bindViews();

        createDemoDataListeEleve();
    }

    private void inflate() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.content_listeeleve, this, true);
    }

    private void bindViews(){
        ListeEleveController controller = new ListeEleveController(this);
        btnAddAppreciation = (Button) findViewById(R.id.addAppreciation);
        btnAddNote = (Button) findViewById(R.id.addNote);
        btnModifier = (Button) findViewById(R.id.modifierButton);
        btnSauvergarder = (Button) findViewById(R.id.sauvegarderButton);

        tableEleve = (TableLayout) findViewById(R.id.tableEleve);

        btnAddAppreciation.setOnClickListener(controller);
        btnAddNote.setOnClickListener(controller);
        btnModifier.setOnClickListener(controller);
        btnSauvergarder.setOnClickListener(controller);
    }

    public void createHeadTable(String header[]) {
        TableRow headRow = new TableRow(super.getContext());
        headRow.setBackground(getResources().getDrawable(R.drawable.border));
        headRow.setClickable(true);

        TableRow.LayoutParams lpRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        headRow.setLayoutParams(lpRow);

        TableRow.LayoutParams lpText = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        lpText.setMargins(0, 0, 20, 0);
        TextView tmpTxtView;
        for (String x : header) {
            tmpTxtView = new TextView(super.getContext());
            tmpTxtView.setText(x);
            tmpTxtView.setTypeface(null, Typeface.BOLD);
            tmpTxtView.setTextSize(15);
            tmpTxtView.setTextColor(Color.parseColor("#040404"));
            tmpTxtView.setLayoutParams(lpText);
            headRow.addView(tmpTxtView);
        }
        tableEleve.addView(headRow);
    }
    public void switchallSwitch(){

        for(StudentRow a:studentRows){
            a.switchView();
        }
    }
    public void participationDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(super.getContext());
        builder.setTitle(R.string.add_appreciation_popup_title);
        builder.setIcon(R.drawable.logo);

        final PopupAppreciation pop = new PopupAppreciation(super.getContext(),null); //Null car après il faudra communiquer l'array list des élèves

        builder.setView(pop);

        builder.setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                System.out.println(pop.getSomme());
                System.out.println(pop.getCommentaire());
                System.out.println(pop.getEtudiant());

                participationDialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        participationDialog = builder.show();
    }

    public void marksDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(super.getContext());
        builder.setTitle(R.string.add_mark_popup_title);
        builder.setIcon(R.drawable.logo);
        final PopupNote noteDialog = new PopupNote(super.getContext());
        builder.setView(noteDialog);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                System.out.print(noteDialog.getEtudiant());
                System.out.print(noteDialog.getNote());
                System.out.print(noteDialog.getTypeEval());
                //Toast.makeText(getApplicationContext(), "Vous avez ajouter une note à " + noteDialog.getEtudiant() + "\n et il a eu  " + noteDialog.getNote() + " à un " + noteDialog.getTypeEval(), Toast.LENGTH_SHORT).show();
                marksDialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        marksDialog = builder.show();
    }

    public void createDemoDataListeEleve() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                res = BD.getNotes();

                ((Activity) getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        int maxCC = (int) res.get(0);
                        int maxDS = (int) res.get(1);
                        ArrayList<Object> students = (ArrayList<Object>) res.get(2);
                        ArrayList<Object> notesCC = (ArrayList<Object>) res.get(3);
                        ArrayList<Object> notesDS = (ArrayList<Object>) res.get(4);
                        StudentRow tmpRow;

                        //HEADER
                        String header[] = new String[3 + maxCC + maxDS];
                        header[0] = "Élèves";//,"CC1","DS1", "Commentaire","Moyenne"};
                        //Colonnes CC
                        for (int i = 1; i <= maxCC; i++) {
                            header[i] = "CC" + i;
                        }
                        //Colonnes DS
                        for (int i = 1; i <= maxDS; i++) {
                            header[i + maxCC] = "DS" + i;
                        }
                        header[1 + maxCC + maxDS] = "Commentaire";
                        header[2 + maxCC + maxDS] = "Moyenne";

                        createHeadTable(header);

                        //NOTES
                        for (int i = 0; i < students.size(); i += 2) {
                            int id_personne = (int) students.get(i);
                            String nom_personne = (String) students.get(i + 1);
                            //String commentaire = (String) students.get(i+2);

                            float[] notes = new float[2*(maxCC + maxDS)];
                            int cpt = 0;
                            for(int j = i ; j < i + maxCC * 2; j += 2) {
                                int id_note = (int) notesCC.get(j);
                                float note = (float) notesCC.get(j + 1);
                                notes[cpt] = id_note;
                                notes[cpt + 1] = note;
                                cpt+=2;
                            }

                            for(int j = i ; j < i + maxDS * 2; j += 2) {
                                int id_note = (int) notesDS.get(j);
                                float note = (float) notesDS.get(j + 1);
                                notes[cpt] = id_note;
                                notes[cpt + 1] = note;
                                cpt+=2;
                            }


                            tmpRow = new StudentRow(getContext(), nom_personne, "je sais pas encore", notes);
                            tmpRow.generateRow();
                            tableEleve.addView(tmpRow);
                            studentRows.add(tmpRow);
                        }
/*
                        float note[] = {12,15};
                        tmpRow = new StudentRow(getContext(),"Michelle", "Gros nul", note);
                        tmpRow.generateRow();
                        tableEleve.addView(tmpRow);
                        studentRows.add(tmpRow);

                        float note2[] = {19,20};
                        tmpRow = new StudentRow(getContext(),"SuperMan", "Gros nul", note2);
                        tmpRow.generateRow();
                        studentRows.add(tmpRow);
                        tableEleve.addView(tmpRow);*/
                    }
                });
            }
        }).start();



    }
}