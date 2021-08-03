package com.CSE3311.personalhealthmanagementsystem.navbarui.notes;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.CSE3311.personalhealthmanagementsystem.Note;
import com.CSE3311.personalhealthmanagementsystem.R;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.localDB;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.notesAdapter;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.notesDataset;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.userId;


public class NoteViewFragment extends Fragment {
    Note curNote;

    public NoteViewFragment(Note note){
        curNote = note;
    }

    EditText title;
    EditText content;
    ImageView backButton;
    ImageView checkButton;
    ImageView deleteButton;

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
        backButton = rootView.findViewById(R.id.back_to_note_from_view);
        checkButton = rootView.findViewById(R.id.confirm_note);
        deleteButton = rootView.findViewById(R.id.delete_note);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getView().getWindowToken(), 0);
                saveNote(getContext(), false);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getView().getWindowToken(), 0);
                getActivity().onBackPressed();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getView().getWindowToken(), 0);
                localDB.daointerface().deleteNote(curNote);
                postSelection();
            }
        });


        //set the note contents if viewing previously saved note, otherwise displays the empty string
        title.setText(curNote.getNoteTitle());
        content.setText(curNote.getNoteContent());

        return rootView;


    }

    // custom back press operation, otherwise the backstack acts buggy due
    // to moving fragments inside the navbar activity navhost fragment.
    // This returns to the NotesFragment
    public void postSelection() {
        notesDataset = localDB.daointerface().getNotesById(userId);
        notesAdapter.notifyDataSetChanged();
        getParentFragmentManager().popBackStackImmediate();
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
                localDB.daointerface().addNote(curNote);
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
