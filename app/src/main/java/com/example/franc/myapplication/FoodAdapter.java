package com.example.franc.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter {

    private LayoutInflater mInflater;
    private ArrayList<Food> data = new ArrayList<>();

    private OnQuantitaChange onQuantitaChange;

    public void setData(ArrayList<Food> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public interface OnQuantitaChange {
        public void onItemAdded(float prezzo);

        public void onItemRemoved(float prezzo);


    }

    public void setOnQuantitaChange(OnQuantitaChange onQuantitaChange) {
        this.onQuantitaChange = onQuantitaChange;
    }

    public FoodAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    public FoodAdapter(Context context, ArrayList<Food> data) {
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.row, viewGroup, false);
        return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FoodViewHolder foodViewHolder = (FoodViewHolder) viewHolder;
        Food currentFood = data.get(i);
        foodViewHolder.productName.setText(currentFood.getName());
        foodViewHolder.productPrice.setText(String.valueOf(currentFood.getPrezzo()));


        foodViewHolder.productQty.setText(String.valueOf(currentFood.getQuantita()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void addItem() {

    }

    private void removeItem() {


    }


    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView productName, productPrice, productQty;
        public Button addBtn, removeBtn;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.item_price);
            productQty = itemView.findViewById(R.id.item_quantity);
            addBtn = itemView.findViewById(R.id.plus_btn);
            removeBtn = itemView.findViewById(R.id.minus_btn);
            addBtn.setOnClickListener(this);
            removeBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.plus_btn) {
                Food food = data.get(getAdapterPosition());
                food.increaseQuantita();
                notifyItemChanged(getAdapterPosition());

                onQuantitaChange.onItemAdded(food.getPrezzo());


            } else if (view.getId() == R.id.minus_btn) {
                Food food = data.get(getAdapterPosition());
                Integer itemNum = food.getQuantita();
                if (itemNum > 0) {
                    food.decreaseQuantita();
                    notifyItemChanged(getAdapterPosition());

                    onQuantitaChange.onItemRemoved(food.getPrezzo());


                }
            }
        }
    }
}