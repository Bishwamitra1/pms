package com.CSE3311.personalhealthmanagementsystem.navbarui.notes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.checkPin;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.userId;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.localDB;

import com.CSE3311.personalhealthmanagementsystem.Note;
import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.navbarui.HealthFragment;

import java.util.List;


public class NotesFragment extends Fragment implements RVAdapter.OnItemClicked, View.OnClickListener {

    private List<Note> mDataset;
    Button addNewNote;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //retrieve all notes made by the current user
        mDataset = localDB.daointerface().getNotesById(userId);


        // This creates a custom operation when the back button is pressed;
        // specifically, this fixes the backstack bug caused by the user
        // going into a new fragment from a fragment in the navHost fragment
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                getParentFragmentManager().popBackStackImmediate();
            }
        };requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        // assign the "Add new note" button an on click
        addNewNote = rootView.findViewById(R.id.new_note);
        addNewNote.setOnClickListener(this);

        // find the recycler view by id
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // assign the layout to be displayed ion the recycler view
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // provide a dataset to the recycler view through an adapter
        RVAdapter mAdapter = new RVAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        // enable on click for recycler view items
        mAdapter.setOnClick(this);

        return rootView;

    }

    @Override
    public void onItemClick(final int position) {

        // goto the NoteViewFragment when a note is clicked on
                final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                Fragment noteview = new NoteViewFragment(mDataset.get(position));
                ft.replace(R.id.nav_host_fragment, noteview).addToBackStack(null);
                ft.commit();
    }


    @Override
    public void onClick(View v) {

        // create a new note for the user to modify
        final Note newNote = new Note();
        newNote.setAuthorId(userId); //The userID is very important! it is what allows the "findnotesbyID" function to work
        newNote.setType("General");  // TODO: assign note types based on note(IE is it a recipe, a general note, a vital sign, etc

        // goto NoteViewFragment, passing in a new note
        final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        Fragment noteview = new NoteViewFragment(newNote);
        ft.replace(R.id.nav_host_fragment, noteview).addToBackStack(null);
        ft.commit();
    }


}

