package iutorsaytpc.ofcourses;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.io.LineNumberReader;

import iutorsaytpc.ofcourses.controller.ListeGroupesController;
import iutorsaytpc.ofcourses.fragment.ListeGroupeFragment;
import iutorsaytpc.ofcourses.fragment.LoadingFragment;
import iutorsaytpc.ofcourses.view.ConnexionView;
import iutorsaytpc.ofcourses.view.FragmentView;
import iutorsaytpc.ofcourses.view.ListeElevesView;
import iutorsaytpc.ofcourses.view.ListeGroupesView;
import iutorsaytpc.ofcourses.view.LoadingView;

public class MainActivity extends AppCompatActivity {

    //Fragment
    private static LoadingFragment loadingFragment;
    private static LoadingView loadingView;
    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;

    //Views
    private static FragmentView fragmentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);

        //Réglage des onglets
        fragmentView = new FragmentView(this);

        //Réglage du fragment loading;
        loadingView = new LoadingView(this);
        loadingFragment = new LoadingFragment(loadingView);
        initFragment();

        //On lance la page de connexion
        ConnexionView connexionView = new ConnexionView(this);
        ((RelativeLayout) findViewById(R.id.layoutParent)).addView(connexionView);
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

    public static boolean isLoadingFragmentAdded() {
        return loadingFragment.isAdded();
    }

    public static void errorConnexion () {
        loadingView.errorConnnexion();
    }

    public static void errorLogin() {
        loadingView.errorLogin();
    }

    public static void resetLoading() {
        loadingView.reset();
    }

    @Override
    public void onBackPressed() {
        FrameLayout onglets = (FrameLayout) findViewById(R.id.frameLayoutFragment);
        System.out.println(onglets.getContentDescription());
        if(onglets.getContentDescription() == "ListeEleveView"){
            ListeGroupesView lgv = new ListeGroupesView(this);
            onglets.removeAllViews();
            onglets.addView(lgv);
            onglets.setContentDescription("ListeGroupView");
        }else{
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                    .setMessage("Are you sure?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("no", null).show();
        }
    }
}
