package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CSE3311.personalhealthmanagementsystem.Medication;
import com.CSE3311.personalhealthmanagementsystem.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.localDB;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.userId;


public class MedicineFragment extends Fragment implements MedicationAdapter.OnClickAction, View.OnClickListener {

    private List<Medication> medications;
    public MedicineFragment() {
        // Required empty public constructor
    }

    private FloatingActionButton addMed_button;
    private ImageView backToHealthFromMed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_medicine, container, false);
        medications = localDB.daointerface().getMedicationsById(userId);

        addMed_button = v.findViewById(R.id.addMed_button);
        backToHealthFromMed = v.findViewById(R.id.backToHealthFromMed);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);

        addMed_button.setOnClickListener(this);

        backToHealthFromMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        MedicationAdapter adapter = new MedicationAdapter(medications);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickAction(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //copy and paste any case to add other fragments
            case R.id.addMed_button:
                final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, new AddMedFragment()).addToBackStack(null);
                ft.commit();
                break;
        }
    }

    public void onClickActionMethod(int position) {
        Medication med = medications.get(position);

        final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, new MedViewFragment(med)).addToBackStack(null);
        ft.commit();
    }


}