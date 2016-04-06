package iutorsaytpc.ofcourses.modele;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingsSingleton {
    private static SettingsSingleton ourInstance;
    private Boolean boolL;
    private Boolean booool;
    private String font;
    private String langue;
    private String nomFont;
    private int selectionLangue;
    private int selectionFont;
    private boolean settingsHasChanged = false;
    static {
        ourInstance = new SettingsSingleton();
    }

    public static SettingsSingleton getInstance() {
        return ourInstance;
    }

    private SettingsSingleton() {
        this.langue = "fr";
        this.font = "arial.ttf";
        this.nomFont = "Arial";
        this.booool = false;
        this.boolL = false;
        this.selectionLangue = 0;
        this.selectionFont = 0;
    }

    public Typeface getFont(Context a) {
        return Typeface.createFromAsset(a.getAssets(), "fonts/" + this.font);
    }

    public void setFontSingleton(String f) {
        this.font = f;
    }

    public String getNomFont() {
        return this.nomFont;
    }

    public void setNomFont(String n) {
        this.nomFont = n;
    }

    public Boolean getBooool() {
        return this.booool;
    }

    public void savedBool() {
        this.booool = true;
    }

    public String getLangue() {
        return this.langue;
    }

    public void setLangue(String l) {
        this.langue = l;
    }

    public void boolFalse() {
        this.boolL = false;
    }

    public Boolean getBoolL() {
        return this.boolL;
    }

    public void savedBoolL() {
        this.boolL = true;
    }

    public int getSelectionFont() {
        return selectionFont;
    }

    public int getSelectionLangue() {
        return selectionLangue;
    }

    public void setSelectionFont(int selectionFont) {
        this.selectionFont = selectionFont;
    }

    public void setSelectionLangue(int selectionLangue) {
        this.selectionLangue = selectionLangue;
    }

    public boolean getSettingsHasChanged() { return settingsHasChanged; }

    public void setSettingsHasChanged(boolean fontHasChanged) { this.settingsHasChanged = fontHasChanged; }

    public void setAllFontInView(View v){
        if (v instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {

                View child = viewGroup.getChildAt(i);
                if(child instanceof TextView)
                    ((TextView)child).setTypeface(SettingsSingleton.getInstance().getFont(child.getContext()));
                else
                    setAllFontInView(child);

            }
        }
    }
}
