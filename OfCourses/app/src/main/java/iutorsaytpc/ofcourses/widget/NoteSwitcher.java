package iutorsaytpc.ofcourses.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import iutorsaytpc.ofcourses.MainActivity;
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
        if(noteTextView.getText().toString().compareTo(noteEditText.getText().toString()) != 0) {
            hadChanged = true;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    float note;
                    if(noteEditText.getText().toString().compareTo("") == 0) note = -1;
                    else note = Float.parseFloat(noteEditText.getText().toString());
                    BD.setNote(id_note, note);
                }
            }).start();
        }
        noteEditText.setEnabled(!noteEditText.isEnabled());
        if(noteTextView.getText().toString().compareTo("") == 0) noteEditText.setText("");
        else noteTextView.setText(noteEditText.getText());
        super.showNext();
    }
}
