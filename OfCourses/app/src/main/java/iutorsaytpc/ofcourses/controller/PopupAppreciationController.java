package iutorsaytpc.ofcourses.controller;

import android.widget.RadioGroup;

import android.widget.RadioGroup;

import iutorsaytpc.ofcourses.popup.PopupAppreciation;

/**
 * Created by simon on 25/03/2016.
 */
public class PopupAppreciationController implements RadioGroup.OnCheckedChangeListener {

    private PopupAppreciation view;

    public PopupAppreciationController(PopupAppreciation view) {
        this.view = view;
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        view.getSomme();
    }
}
