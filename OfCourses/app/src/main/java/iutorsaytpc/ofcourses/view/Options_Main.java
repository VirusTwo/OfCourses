package iutorsaytpc.ofcourses.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.modele.SettingsSingleton;

public class Options_Main extends AppCompatActivity {
    Boolean bool;
    Boolean bool2;
    private Button btnQuitS;
    String fontLabel;
    String languageToLoad;
    String nomFont;
    SettingsSingleton settings;
    private Spinner spin1;
    private Spinner spin2;
    private CheckBox checkBoxNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options__main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialiser();
        checkBoxNotif = (CheckBox) findViewById(R.id.checkBox4);


        settings = SettingsSingleton.getInstance();

        bool = settings.getBooool();
        bool2 = settings.getBoolL();
        if (bool == true) {
            setFont();
        }
        if (bool2 == true) {
            setLangue();
            settings.boolFalse();
        }
        checkBoxNotif.setChecked(settings.getCheckedNotif());

        btnQuitS = (Button) findViewById(R.id.btnQuit);
        btnQuitS.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxNotif.isChecked()){
                    settings.setCheckedNotif(true);
                }
                else {
                    settings.setCheckedNotif(false);
                }
                settings.setNomFont(nomFont);
                settings.setFontSingleton(fontLabel);
                settings.savedBool();
                settings.savedBoolL();
                settings.setLangue(languageToLoad);
                setLangue();
                SettingsSingleton.getInstance().setSettingsHasChanged(true);
                finish();
            }
        });

    }
    
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options__main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void addItemToSpinner() {
        List<String> langue = new ArrayList();
        langue.add("Français");
        langue.add("Anglais");

        List<String> police = new ArrayList();
        police.add("Arial");
        police.add("Comic Sans MS");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, langue);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, police);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(dataAdapter2);
    }

    public void addListenerToSpinner() {
        spin1 = (Spinner) findViewById(R.id.spinnerLangue);
        spin1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spin2 = (Spinner) findViewById(R.id.spinnerPolice);
        spin2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void initialiser() {
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setLogo(R.mipmap.logo);
        addListenerToSpinner();
        addItemToSpinner();

    }

    public void setFont() {
        TextView un = (TextView) findViewById(R.id.textView);
        TextView deux = (TextView) findViewById(R.id.textView2);
        TextView trois = (TextView) findViewById(R.id.textView3);
        TextView quatre = (TextView) findViewById(R.id.textView4);
        TextView cinq = (TextView) findViewById(R.id.textView5);
        TextView six = (TextView) findViewById(R.id.textView6);
        TextView sept = (TextView) findViewById(R.id.textView7);
        TextView huit = (TextView) findViewById(R.id.textView8);
        TextView neuf = (TextView) findViewById(R.id.textView9);
        CheckBox c2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox c3 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox c = (CheckBox) findViewById(R.id.checkBox);
        Button b = (Button) findViewById(R.id.btnQuit);
        String n = this.settings.getNomFont();
        Typeface font = this.settings.getFont(this);
        un.setTypeface(font);
        deux.setTypeface(font);
        trois.setTypeface(font);
        quatre.setTypeface(font);
        cinq.setTypeface(font);
        six.setTypeface(font);
        sept.setTypeface(font);
        huit.setTypeface(font);
        neuf.setTypeface(font);
        neuf.setText(n);
        c.setTypeface(font);
        c2.setTypeface(font);
        c3.setTypeface(font);
        checkBoxNotif.setTypeface(font);
        b.setTypeface(font);
    }

    public void setLangue() {
        Locale locale = new Locale(settings.getLangue());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }


    public class CustomOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView parent, View view, int pos, long id) {
            Boolean Checked;
            Checked = settings.getCheckedNotif();
            if (Checked == true) {
                Toast.makeText(parent.getContext(), "Choix  : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
            }
            if (parent == findViewById(R.id.spinnerLangue) && pos == 0) {
                languageToLoad = "fr";
            } else if (parent == findViewById(R.id.spinnerLangue) && pos == 1) {
                languageToLoad = "en";
            }
            if (parent == findViewById(R.id.spinnerPolice) && pos == 1) {
                fontLabel = "comic.ttf";
                nomFont = "Comic Sans MS";
            } else if (parent == findViewById(R.id.spinnerPolice) && pos == 0) {
                fontLabel = "arial.ttf";
                nomFont = "Arial";
            }

        }

        public void onNothingSelected(AdapterView parent) {
        }
    }


}
