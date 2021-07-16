package com.CSE3311.personalhealthmanagementsystem.navbarui.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.UserID;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.localDB;

import com.CSE3311.personalhealthmanagementsystem.Note;
import com.CSE3311.personalhealthmanagementsystem.R;

import java.util.List;


public class NotesFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected RVAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new RVAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    private void initDataset() {
        List<Note> userNoteList = localDB.daointerface().getNotesById(UserID);
        mDataset = new String[userNoteList.size()];
        for (int i = 0; i < userNoteList.size(); i++) {
            mDataset[i] = userNoteList.get(i).getNoteTitle();
        }
    }
}
