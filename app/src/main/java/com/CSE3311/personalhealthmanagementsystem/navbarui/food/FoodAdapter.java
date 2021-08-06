package com.CSE3311.personalhealthmanagementsystem.navbarui.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.CSE3311.personalhealthmanagementsystem.Exercise;
import com.CSE3311.personalhealthmanagementsystem.Food;
import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.navbarui.exercise.ExerciseAdapter;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder> {
    private OnClickAction onClickAction;
    List<Food> foods = new ArrayList<>();


    public interface OnClickAction {
        void onClickActionMethod(int position);
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicine_item,parent,false);
        return new FoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, final int position) {
        Food currentFood = foods.get(position);

        holder.titleMedItem.setText(currentFood.getFavoriteFood());
        holder.titleDescriptionItem.setText("You have taken "+ currentFood.getCalorieCount() + " calories");

        holder.getmCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAction.onClickActionMethod(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class FoodHolder extends RecyclerView.ViewHolder {
        private TextView titleMedItem, titleDescriptionItem;
        private CardView mCardView;

        public FoodHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cardViewMed);
            titleMedItem = itemView.findViewById(R.id.titleMedItem);
            titleDescriptionItem = itemView.findViewById(R.id.titleDescriptionItem);

        }

        public CardView getmCardView() {
            return mCardView;
        }
    }

    public FoodAdapter(List<Food> food)
    {
        this.foods = food;
    }

    public void setOnClickAction(OnClickAction onClickAction) { this.onClickAction = onClickAction; }
}
