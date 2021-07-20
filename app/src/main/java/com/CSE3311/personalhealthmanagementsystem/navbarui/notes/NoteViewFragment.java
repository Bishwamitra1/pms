package com.CSE3311.personalhealthmanagementsystem.navbarui.notes;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.CSE3311.personalhealthmanagementsystem.HomePageActivity;
import com.CSE3311.personalhealthmanagementsystem.Note;
import com.CSE3311.personalhealthmanagementsystem.R;


public class NoteViewFragment extends Fragment {
    Note curNote;

    public NoteViewFragment(Note note){
        curNote = note;
    }

    EditText title;
    EditText content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if(curNote.getNoteTitle() == null) {
                    Log.d("NoteViewFragment", "Note title was null, changing to the empty string. If this is from a new note, this is intended behavior.");
                    curNote.setNoteTitle("");
                }
                if(curNote.getNoteContent() == null) {
                    Log.d("NoteViewFragment", "Note content was null, changing to the empty string. If this is from a new note, this is intended behavior.");
                    curNote.setNoteContent("");

                }
                if((!curNote.getNoteTitle().equals(title.getText().toString())) || (!curNote.getNoteContent().equals(content.getText().toString()))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle("Save...");

                    builder.setMessage("Would you like to save your changes?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            curNote.setNoteTitle(title.getText().toString());
                            curNote.setNoteContent(content.getText().toString());
                            HomePageActivity.localDB.daointerface().addNote(curNote);
                            dialog.dismiss();
                            postSelection();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            postSelection();
                        }
                    });
                    builder.show();
                }
                else{
                    postSelection();
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note_view, container, false);
        title = rootView.findViewById(R.id.note_title);
        content = rootView.findViewById(R.id.note_content);
        title.setText(curNote.getNoteTitle());
        content.setText(curNote.getNoteContent());
        return rootView;


    }

    public void postSelection() {
        final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, new NotesFragment()).addToBackStack(null);
        ft.commit();
    }

}
