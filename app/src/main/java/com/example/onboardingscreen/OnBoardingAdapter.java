package com.example.onboardingscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.ViewHolder> {

    private List<OnBoardingItem> onBoardingItemList;

    public OnBoardingAdapter(List<OnBoardingItem> onBoardingItemList) {
        this.onBoardingItemList = onBoardingItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboarding,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OnBoardingItem boardingItem = onBoardingItemList.get(position);
        int image = boardingItem.getImage();
        String title = boardingItem.getTitle();
        String description = boardingItem.getDescription();

        holder.imageOnBoarding.setImageResource(image);
        holder.textTitle.setText(title);
        holder.textDescription.setText(description);
    }

    @Override
    public int getItemCount() {
        return onBoardingItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageOnBoarding;
        private TextView textTitle,textDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageOnBoarding = itemView.findViewById(R.id.imageOnBoarding);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
        }
    }
}
