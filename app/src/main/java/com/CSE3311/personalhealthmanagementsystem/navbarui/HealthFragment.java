package com.CSE3311.personalhealthmanagementsystem.navbarui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.UserAccount;
import com.CSE3311.personalhealthmanagementsystem.navbarui.medication.MedicineFragment;
import com.CSE3311.personalhealthmanagementsystem.navbarui.notes.NoteViewFragment;
import com.CSE3311.personalhealthmanagementsystem.navbarui.notes.NotesFragment;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.checkPin;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.userId;
import static com.CSE3311.personalhealthmanagementsystem.MainActivity.localDB;

public class HealthFragment extends Fragment implements View.OnClickListener {

    public HealthFragment() {
        // Required empty public constructor
    }

    private CardView medication, vitalSigns, nutrition, notes, exercises, contact;
    private TextView NameOfCustomer;
    UserAccount userAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_health, container, false);
        userAccount = localDB.daointerface().getUserById(userId);

        NameOfCustomer = v.findViewById(R.id.NameOfCustomer);
        NameOfCustomer.setText(userAccount.getFirstName()+"!");

        medication = v.findViewById(R.id.medButton);
        medication.setOnClickListener(this);//page made

        vitalSigns = v.findViewById(R.id.vitalSignsButton);
        vitalSigns.setOnClickListener(this);//Holliday- working on page

        nutrition = v.findViewById(R.id.nutritionButton);
        nutrition.setOnClickListener(this);//page made

        notes = v.findViewById(R.id.notesButton);
        notes.setOnClickListener(this);//done by Wells

        exercises = v.findViewById(R.id.exercisesButton);
        exercises.setOnClickListener(this);//page made

        contact = v.findViewById(R.id.contactButton);
        contact.setOnClickListener(this);//page made

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //copy and paste any case to add other fragments
            case R.id.notesButton:
                Runnable r = new Runnable() {
                    public void run() {
                        final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                        ft.replace(R.id.nav_host_fragment, new NotesFragment()).addToBackStack(null);
                        ft.commit();
                    }
                };
                checkPin(userId, getContext(), r);
                break;
            case R.id.medButton:
                final FragmentTransaction ft0 = getParentFragmentManager().beginTransaction();
                ft0.replace(R.id.nav_host_fragment, new MedicineFragment()).addToBackStack(null);
                ft0.commit();
                break;
            case R.id.vitalSignsButton:
                final FragmentTransaction ft1 = getParentFragmentManager().beginTransaction();
                ft1.replace(R.id.nav_host_fragment, new VitalSignsFragment()).addToBackStack(null);
                ft1.commit();
                break;
            case R.id.nutritionButton:
                final FragmentTransaction ft2 = getParentFragmentManager().beginTransaction();
                ft2.replace(R.id.nav_host_fragment, new NutritionFragment()).addToBackStack(null);
                ft2.commit();
                break;
            case R.id.exercisesButton:
                final FragmentTransaction ft3 = getParentFragmentManager().beginTransaction();
                ft3.replace(R.id.nav_host_fragment, new ExercisesFragment()).addToBackStack(null);
                ft3.commit();
                break;
            case R.id.contactButton:
                final FragmentTransaction ft4 = getParentFragmentManager().beginTransaction();
                ft4.replace(R.id.nav_host_fragment, new ContactFragment()).addToBackStack(null);
                ft4.commit();
                break;

        }
        /* commented this out to turn it into a switch
        if(v.getId() == notes.getId()){
            final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, new NotesFragment()).addToBackStack(null);
            ft.commit();*/
    }
}


