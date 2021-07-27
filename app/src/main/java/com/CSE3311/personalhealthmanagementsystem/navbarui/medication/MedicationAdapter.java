//package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.CSE3311.personalhealthmanagementsystem.Medication;
//import com.CSE3311.personalhealthmanagementsystem.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationHolder> {
//
//    List<Medication> medications = new ArrayList<>();
//
//    @Override
//    public MedicationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.medicine_item,parent,false);
//        return new MedicationHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(MedicationHolder holder, int position) {
//        Medication currentMed = medications.get(position);
//        holder.titleMedItem.setText(currentMed.getNameOfMed());
//        holder.titleTypeItem.setText(currentMed.getTypeOfMed());
//    }
//
//    @Override
//    public int getItemCount() {
//        return medications.size();
//    }
//
//    public static class MedicationHolder extends RecyclerView.ViewHolder {
//        private TextView titleMedItem,titleTypeItem;
//
//        public MedicationHolder(View itemView) {
//            super(itemView);
//            titleMedItem = itemView.findViewById(R.id.titleMedItem);
//            titleTypeItem = itemView.findViewById(R.id.titleTypeItem);
//        }
//    }
//
//    public MedicationAdapter(List<Medication> Mmedications)
//    {
//        medications = Mmedications;
//    }
//}
