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

    //public static int MedPosition;
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
        //holder.mCardView.setTag(position);
        holder.titleMedItem.setText(currentMed.getNameOfMed());
        holder.titleTypeItem.setText(currentMed.getTypeOfMed()+" "+currentMed.getQuantity()+" "+currentMed.getStartTime()+" "+currentMed.getEndDate()+" "+currentMed.getFrequency()+" "+currentMed.isFrequencyUnit());
//        holder.mCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClickAction.onClickActionMethod(position);
//            }
//        });
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
        private TextView titleMedItem,titleTypeItem;
        private CardView mCardView;

        public MedicationHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cardViewMed);
            titleMedItem = itemView.findViewById(R.id.titleMedItem);
            titleTypeItem = itemView.findViewById(R.id.titleTypeItem);

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
