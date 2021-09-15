package com.example.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private List<OnBoardingItem> onBoardingItemList;
    private ViewPager2 onBoardingViewPager;
    private LinearLayout layoutOnBoardingIndicators;
    private MaterialButton buttonOnBoardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBoardingViewPager = findViewById(R.id.onBoardingViewPager);
        buttonOnBoardingAction = findViewById(R.id.buttonOnBoardingAction);
        layoutOnBoardingIndicators = findViewById(R.id.layoutOnBoardingIndicators);

        setUpOnBoardingItems();
        onBoardingViewPager.setAdapter(onBoardingAdapter);
        setUpOnBoardingIndicators();
        setCurrentOnBoardingIndicator(0);
        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicator(position);
            }
        });
        buttonOnBoardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBoardingViewPager.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()) {
                    onBoardingViewPager.setCurrentItem(onBoardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
            }
        });
    }

    private void setUpOnBoardingItems() {
        onBoardingItemList = new ArrayList<>();
        onBoardingItemList.add(new OnBoardingItem(R.drawable.eating, "Eat Food",
                "Enjoy Real Taste Of Life With Us!"));
        onBoardingItemList.add(new OnBoardingItem(R.drawable.fast_food, "Eat Fast Food",
                "Enjoy Real Taste Of Life With Us!"));
        onBoardingItemList.add(new OnBoardingItem(R.drawable.food_blogger, "Eat Food",
                "Enjoy Real Taste Of Life With Us!"));
        onBoardingItemList.add(new OnBoardingItem(R.drawable.hamburger, "Eat Hamburger",
                "Enjoy Real Taste Of Life With Us!"));
        onBoardingItemList.add(new OnBoardingItem(R.drawable.healthy_eating, "Eat Healthy Food",
                "Enjoy Real Taste Of Life With Us!"));
        onBoardingItemList.add(new OnBoardingItem(R.drawable.pizza, "Eat Pizza",
                "Enjoy Real Taste Of Life With Us!"));
        onBoardingAdapter = new OnBoardingAdapter(onBoardingItemList);
    }

    private void setUpOnBoardingIndicators() {
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicators.addView(indicators[i]);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentOnBoardingIndicator(int index) {
        int childCount = layoutOnBoardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnBoardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.onboarding_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.onboarding_indicator_inactive
                ));
            }
        }

        if(index == onBoardingAdapter.getItemCount() - 1){
            buttonOnBoardingAction.setText("Start");
        }else{
            buttonOnBoardingAction.setText("Next");
        }
    }
}