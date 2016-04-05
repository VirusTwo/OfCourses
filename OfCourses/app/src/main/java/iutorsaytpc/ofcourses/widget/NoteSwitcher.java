package iutorsaytpc.ofcourses.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * Created by Cyril on 18/03/2016.
 */
public class NoteSwitcher extends ViewSwitcher {
    private TextView noteTextView;
    private EditText noteEditText;
    private StudentRow eleve;
    private float note;
    private boolean hadChanged = false;

    public NoteSwitcher(Context context,float note,StudentRow eleve){
        super(context);
        setLayoutParams(StudentRow.LPSWITCHTER);
        this.note = note;
        this.eleve = eleve;
        noteTextView = new TextView(super.getContext());
        noteTextView.setText(String.valueOf(note));
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
        if(noteTextView.getText() != noteEditText.getText()){
            hadChanged = true;
        }
        noteEditText.setEnabled(!noteEditText.isEnabled());
        noteTextView.setText(noteEditText.getText());
        super.showNext();
    }
}
