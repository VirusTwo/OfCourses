package iutorsaytpc.ofcourses.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.controller.FragmentController;

/**
 * Created by Tilloman on 30/03/2016.
 */
public class FragmentView extends LinearLayout {
    private Context context;
    private Button edt;
    private Button listeGroupe;

    public FragmentView(Context context) {
        super(context);
        this.context = context;

        inflate();
        bindViews();
    }

    private void inflate() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.content_fragment, this, true);
    }

    private void bindViews(){
        FragmentController controller = new FragmentController(this);

        // Bind des objets de la page connexion
        edt = (Button) findViewById(R.id.edt);
        listeGroupe = (Button) findViewById(R.id.listeGroupe);

        edt.setOnClickListener(controller);
        listeGroupe.setOnClickListener(controller);
    }
}
