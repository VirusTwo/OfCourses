package iutorsaytpc.ofcourses;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import iutorsaytpc.ofcourses.fragment.LoadingFragment;
import iutorsaytpc.ofcourses.view.ConnexionView;
import iutorsaytpc.ofcourses.view.ListeElevesView;
public class MainActivity extends AppCompatActivity {

    //Fragment
    private static LoadingFragment loadingFragment = new LoadingFragment();
    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;

    private ViewGroup viewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        viewGroup = (ViewGroup) findViewById(R.id.layoutParent);

        ConnexionView connexionView = new ConnexionView(this);
        //setContentView(connexionView);
        initFragment();
        ListeElevesView listeElevesView = new ListeElevesView(this);
        listeElevesView.createDemoDataListeEleve();

        //viewGroup.addView(connexionView);
        viewGroup.addView(listeElevesView);


        //On peut retirer facilement la vue actuel de cette façon, les élèments de base de l'activity main restent
        // viewGroup.removeAllViews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

    private void initFragment() {
        fragmentManager = getFragmentManager();
    }

    public static void detachLoadingFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.remove(loadingFragment);
        fragmentTransaction.commit();
    }

    public static void attachLoadingFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.add(R.id.layoutParent, loadingFragment);
        fragmentTransaction.commit();
    }


}
