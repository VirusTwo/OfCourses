package iutorsaytpc.ofcourses.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import iutorsaytpc.ofcourses.bd.BD;

/**
 * Created by Cyril on 18/03/2016.
 */
public class NoteSwitcher extends ViewSwitcher {
    private TextView noteTextView;
    private EditText noteEditText;
    private int id_note;
    private StudentRow eleve;
    private float note;
    private boolean hadChanged = false;

    public NoteSwitcher(Context context,float note,StudentRow eleve, int id_note){
        super(context);
        setLayoutParams(StudentRow.LPSWITCHTER);
        this.note = note;
        this.eleve = eleve;
        this.id_note = id_note;
        noteTextView = new TextView(super.getContext());
        if(note == -1) noteTextView.setText("");
        else noteTextView.setText(String.valueOf(note));
        noteTextView.setTextSize(15);
        noteTextView.setLayoutParams(StudentRow.LPTEXT);
        addView(noteTextView);
        noteEditText = new EditText(super.getContext());
        noteEditText.setText(String.valueOf(note));
        noteEditText.setTextSize(12);
        noteEditText.setPadding(5, 0, 0, -5);
        noteEditText.setBackgroundColor(Color.WHITE);
        noteEditText.setLayoutParams(StudentRow.LPEDITTEXT);
        noteEditText.setEnabled(false);
        noteEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        addView(noteEditText);
    }

    public void showNext(){
        System.out.println(noteTextView.getText().toString());
        System.out.println(noteEditText.getText().toString());
        if(noteTextView.getText().toString().compareTo(noteEditText.getText().toString()) != 0 && Float.parseFloat(noteEditText.getText().toString()) != -1) {
            hadChanged = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    BD.setNote(id_note, Float.parseFloat(noteEditText.getText().toString()));
                }
            }).start();
        }
        noteEditText.setEnabled(!noteEditText.isEnabled());
        if(noteTextView.getText().toString().compareTo("") == 0) noteTextView.setText("");
        else  noteTextView.setText(noteEditText.getText());
        super.showNext();
    }
}
