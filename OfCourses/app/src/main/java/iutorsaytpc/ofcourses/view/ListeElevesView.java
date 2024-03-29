package iutorsaytpc.ofcourses.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

import iutorsaytpc.ofcourses.MainActivity;
import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.bd.BD;
import iutorsaytpc.ofcourses.controller.ConnexionController;
import iutorsaytpc.ofcourses.controller.ListeEleveController;
import iutorsaytpc.ofcourses.modele.ClasseSingleton;
import iutorsaytpc.ofcourses.modele.MatiereSingleton;
import iutorsaytpc.ofcourses.modele.SettingsSingleton;
import iutorsaytpc.ofcourses.popup.PopupAppreciation;
import iutorsaytpc.ofcourses.popup.PopupNote;
import iutorsaytpc.ofcourses.widget.StudentRow;

/**
 * Created by Cyril on 25/03/2016.
 */

public class ListeElevesView extends LinearLayout {

    private Context context;
    private ListeElevesView myInstance;
    private Button btnAddAppreciation;
    private Button btnAddNote;
    private Button btnModifier;

    private TextView txtViewNameClasse;
    private TextView txtViewNameMatiere;

    private TableLayout tableEleve;
    private ArrayList<StudentRow> studentRows = new ArrayList<StudentRow>();

    private AlertDialog participationDialog;
    private AlertDialog marksDialog;

    private boolean inEdit = false;
    private boolean editActiv = false;

    ArrayList<Object>  res;

    public ListeElevesView(Context context) {
        super(context);
        this.myInstance = this;
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


        txtViewNameClasse = (TextView) findViewById(R.id.textViewTP);
        txtViewNameMatiere = (TextView) findViewById(R.id.textViewMatiere);

        tableEleve = (TableLayout) findViewById(R.id.tableEleve);

        btnAddAppreciation.setOnClickListener(controller);
        btnAddNote.setOnClickListener(controller);
        btnModifier.setOnClickListener(controller);

        String tmpvar = MatiereSingleton.getName_matiere();
        txtViewNameMatiere.setText(MatiereSingleton.getName_matiere());
        tmpvar = ClasseSingleton.getName_classe();
        txtViewNameClasse.setText(ClasseSingleton.getName_classe());
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

        MainActivity.attachLoadingFragment();
        if(inEdit) {
            btnModifier.setText(this.getContext().getString(R.string.modifier));
            inEdit = false;
        } else {
            btnModifier.setText(super.getContext().getString(R.string.save));
            inEdit = true;
        }

        for(StudentRow a:studentRows){
            a.switchView();
        }

        MainActivity.detachLoadingFragment();
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

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BD.setPointBonus(pop.getIdEtudiant(), pop.getSomme(), pop.getCommentaire());
                    }
                }).start();
                createDemoDataListeEleve();
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
                System.out.print(noteDialog.getTypeEval());
                //Toast.makeText(getApplicationContext(), "Vous avez ajouter une note à " + noteDialog.getEtudiant() + "\n et il a eu  " + noteDialog.getNote() + " à un " + noteDialog.getTypeEval(), Toast.LENGTH_SHORT).show();

                // ajout des notes
                Runnable runnable = null;

                switch (noteDialog.getTypeEval()) {
                    case "DS" :
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                BD.addDS();
                            }
                        };
                        break;

                    case "CC" :
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                BD.addCC();
                            }
                        };
                        break;
                }
                new Thread(runnable).start();
                marksDialog.dismiss();
                createDemoDataListeEleve();

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
        tableEleve.removeAllViews();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!MainActivity.isLoadingFragmentAdded()) MainActivity.attachLoadingFragment();
                res = BD.getNotes();

                if (res != null) {

                    ((Activity) getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            int maxCC = (int) res.get(0);
                            int maxDS = (int) res.get(1);
                            float moyenneCC = 0;
                            float moyenneDS = 0;
                            ArrayList<Object> students = (ArrayList<Object>) res.get(2);
                            ArrayList<Object> notesCC = (ArrayList<Object>) res.get(3);
                            ArrayList<Object> notesDS = (ArrayList<Object>) res.get(4);
                            ArrayList<Object> pointBonus = (ArrayList<Object>) res.get(5);
                            StudentRow tmpRow;
                            System.out.println(notesCC);

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

                                float[] notes = new float[2 * (maxCC + maxDS) + 1];
                                int cpt = 0;
                                for (int j = 0; j < maxCC * 2; j += 2) {
                                    int id_note = (int) notesCC.get(j + maxCC * i);
                                    float note = (float) notesCC.get(j + maxCC * i + 1);
                                    notes[cpt] = id_note;
                                    notes[cpt + 1] = note;
                                    cpt += 2;
                                    moyenneCC += note;
                                }
                                moyenneCC /= maxCC;

                                for (int j = 0; j < maxDS * 2; j += 2) {
                                    int id_note = (int) notesDS.get(j + maxDS * i);
                                    float note = (float) notesDS.get(j + maxDS * i + 1);
                                    notes[cpt] = id_note;
                                    notes[cpt + 1] = note;
                                    cpt += 2;
                                    moyenneDS += note;
                                }
                                String lol = "";
                                String lolid = "";
                                for(int j = 1; j < notes.length; j+=2) {
                                    lol += notes[j] + ", ";
                                    lolid += notes[j - 1];
                                }
                                System.out.println(lol);
                                System.out.println(lolid);

                                moyenneDS /= maxDS;
                                notes[notes.length - 1] = (moyenneDS * (float) 0.6 + moyenneCC * (float) 0.4);


                                tmpRow = new StudentRow(getContext(), nom_personne, (String) pointBonus.get(i + 1), notes);
                                tmpRow.generateRow();
                                tableEleve.addView(tmpRow);
                                studentRows.add(tmpRow);
                            }
                            SettingsSingleton.getInstance().setAllFontInView(myInstance);
                        }
                    });
                }

                if(MainActivity.isLoadingFragmentAdded()) MainActivity.detachLoadingFragment();
            }
        }).start();
    }
}