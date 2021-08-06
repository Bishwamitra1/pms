package com.CSE3311.personalhealthmanagementsystem.navbarui.exercise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.CSE3311.personalhealthmanagementsystem.Exercise;
import com.CSE3311.personalhealthmanagementsystem.Medication;
import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.navbarui.medication.MedicationAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseHolder> {
    private OnClickAction onClickAction;
    List<Exercise> exercises = new ArrayList<>();


    public interface OnClickAction {
        void onClickActionMethod(int position);
    }

    @Override
    public ExerciseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicine_item,parent,false);
        return new ExerciseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseHolder holder, final int position) {
        Exercise currentExer = exercises.get(position);

        holder.titleMedItem.setText(currentExer.getNameOfExercise());
        holder.titleDescriptionItem.setText(currentExer.getDescriptionOfExercise());

        holder.getmCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAction.onClickActionMethod(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ExerciseHolder extends RecyclerView.ViewHolder {
        private TextView titleMedItem, titleDescriptionItem;
        private CardView mCardView;

        public ExerciseHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cardViewMed);
            titleMedItem = itemView.findViewById(R.id.titleMedItem);
            titleDescriptionItem = itemView.findViewById(R.id.titleDescriptionItem);

        }

        public CardView getmCardView() {
            return mCardView;
        }
    }

    public ExerciseAdapter(List<Exercise> exercises)
    {
        this.exercises = exercises;
    }

    public void setOnClickAction(OnClickAction onClickAction) { this.onClickAction = onClickAction; }
}
