package com.CSE3311.personalhealthmanagementsystem.navbarui.notes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.UserID;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.localDB;

import com.CSE3311.personalhealthmanagementsystem.Note;
import com.CSE3311.personalhealthmanagementsystem.R;

import java.util.List;


public class NotesFragment extends Fragment implements RVAdapter.OnItemClicked, View.OnClickListener {

    private List<Note> mDataset;
    Button addNewNote;
    String returnString = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataset = localDB.daointerface().getNotesById(UserID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        addNewNote = rootView.findViewById(R.id.new_note);
        addNewNote.setOnClickListener(this);

        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RVAdapter mAdapter = new RVAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClick(this);
        return rootView;

    }

    @Override
    public void onItemClick(int position) {
        Log.d("Notes Fragment", "recycler view item clicked " + ((mDataset.get(position).getNoteId())));

        final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        Fragment noteview = new NoteViewFragment(mDataset.get(position));
        ft.replace(R.id.nav_host_fragment, noteview).addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onClick(View v) {

        final Note newNote = new Note();
        //The userID is very important!!!!!!!!!!!
        newNote.setAuthorId(UserID);
        newNote.setType("General");
        final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        Fragment noteview = new NoteViewFragment(newNote);
        ft.replace(R.id.nav_host_fragment, noteview).addToBackStack(null);
        ft.commit();
    }


}

