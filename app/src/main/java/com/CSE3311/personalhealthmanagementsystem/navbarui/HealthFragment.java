package com.CSE3311.personalhealthmanagementsystem.navbarui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.navbarui.notes.NotesFragment;

public class HealthFragment extends Fragment implements View.OnClickListener {

    public HealthFragment() {
        // Required empty public constructor
    }
     private Button viewNotes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_health, container, false);


        viewNotes=v.findViewById(R.id.view_notes);
        viewNotes.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == viewNotes.getId()){
            final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, new NotesFragment()).addToBackStack(null);
            ft.commit();
        }
    }
}