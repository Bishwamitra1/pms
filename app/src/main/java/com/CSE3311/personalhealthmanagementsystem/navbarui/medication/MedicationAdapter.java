package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.CSE3311.personalhealthmanagementsystem.Medication;
import com.CSE3311.personalhealthmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;


public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationHolder> {

    private OnClickAction onClickAction;
    List<Medication> medications = new ArrayList<>();


    public interface OnClickAction {
        void onClickActionMethod(int position);
    }

    @Override
    public MedicationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicine_item,parent,false);
        return new MedicationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MedicationHolder holder, final int position) {
        Medication currentMed = medications.get(position);

        holder.titleMedItem.setText(currentMed.getNameOfMed());
        holder.titleDescriptionItem.setText("Take "+currentMed.getQuantity()+" "+currentMed.getTypeOfMed()+" every "+currentMed.getFrequency()+((currentMed.isFrequencyUnit())?" Hour(s)":" Minute(s)")+" for "+currentMed.getDescriptionOfMed());

        holder.getmCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAction.onClickActionMethod(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    public static class MedicationHolder extends RecyclerView.ViewHolder {
        private TextView titleMedItem, titleDescriptionItem;
        private CardView mCardView;

        public MedicationHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cardViewMed);
            titleMedItem = itemView.findViewById(R.id.titleMedItem);
            titleDescriptionItem = itemView.findViewById(R.id.titleDescriptionItem);

        }

        public CardView getmCardView() {
            return mCardView;
        }
    }

    public MedicationAdapter(List<Medication> Mmedications)
    {
        medications = Mmedications;
    }

    public void setOnClickAction(OnClickAction onClickAction) { this.onClickAction = onClickAction; }
}
