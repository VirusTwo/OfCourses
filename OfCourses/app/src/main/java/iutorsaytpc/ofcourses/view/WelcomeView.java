package iutorsaytpc.ofcourses.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.modele.EnseignantSingleton;

/**
 * Created by guillaumemartinez on 06/04/2016
 */
public class WelcomeView extends RelativeLayout {
    public WelcomeView(Context context) {
        super(context);

        inflate();
        bindViews();
    }

    private void inflate() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.welcome_content, this, true);
    }

    private void bindViews() {
        TextView nom = (TextView) findViewById(R.id.nomEnseignantText);
        nom.setText(EnseignantSingleton.getNom());
    }
}
