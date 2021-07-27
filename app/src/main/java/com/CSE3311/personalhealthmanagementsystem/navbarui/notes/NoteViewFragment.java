package com.CSE3311.personalhealthmanagementsystem.navbarui.notes;


import android.app.AlertDialog;
import android.content.Context;
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

        // Custom back button that detects if note has been changed.
        // If the user tries to press back without saving it will prompt the user to save the note
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

                // The below two if statements handle empty notes, otherwise an exception occurs
                if(curNote.getNoteTitle() == null) {
                    Log.e("NoteViewFragment",
                            "Note title was null, changing to the empty string. " +
                                    "If this is from a new note, this is intended behavior.");
                    curNote.setNoteTitle("");
                }
                if(curNote.getNoteContent() == null) {
                    Log.e("NoteViewFragment",
                            "Note content was null, changing to the empty string. " +
                                    "If this is from a new note, this is intended behavior.");
                    curNote.setNoteContent("");
                }

                // If the note title or contents have been changed
                if((!curNote.getNoteTitle().equals(title.getText().toString())) || (!curNote.getNoteContent().equals(content.getText().toString()))) {
                    saveNote(getContext(), true);
                }
                // if the note title or contents have not changed
                else {
                    postSelection();
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback); //boilerplate

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note_view, container, false);

        title = rootView.findViewById(R.id.note_title);
        content = rootView.findViewById(R.id.note_content);

        //set the note contents if viewing previously saved note, otherwise displays the empty string
        title.setText(curNote.getNoteTitle());
        content.setText(curNote.getNoteContent());

        return rootView;


    }

    // custom back press operation, otherwise the backstack acts buggy due
    // to moving fragments inside the navbar activity navhost fragment.
    // This returns to the NotesFragment
    public void postSelection() {
        final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, new NotesFragment()).addToBackStack(null);
        ft.commit();
    }

    // creates an alert dialog that prompts the user to save the note.
    // if the exit boolean is true, postSelection will run
    public void saveNote(Context context, final Boolean exit){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Save...");

        builder.setMessage("Would you like to save your changes?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                curNote.setNoteTitle(title.getText().toString());
                curNote.setNoteContent(content.getText().toString());
                HomePageActivity.localDB.daointerface().addNote(curNote);
                dialog.dismiss();
                if(exit)
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

}
