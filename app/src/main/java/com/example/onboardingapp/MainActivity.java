package com.example.onboardingapp;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager nslideViewPager;
    private LinearLayout ndotsLayout;

    private TextView[] nDots;

    private SliderAdapter sliderAdapter;

    private Button nNextButton;
    private Button nPreviousButton;

    private int nCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nslideViewPager = findViewById(R.id.slideViewPager);
        ndotsLayout = findViewById(R.id.dotsLayout);

        nNextButton = findViewById(R.id.next_Button);
        nPreviousButton = findViewById(R.id.previous_Button);

        sliderAdapter = new SliderAdapter(this);
        nslideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        nslideViewPager.addOnPageChangeListener(viewListener);

        nNextButton.setOnClickListener(view -> nslideViewPager.setCurrentItem(nCurrentPage+1));

        nPreviousButton.setOnClickListener(view -> nslideViewPager.setCurrentItem(nCurrentPage-1));





    }
    public void addDotsIndicator(int position){
        nDots = new TextView[3];
        ndotsLayout.removeAllViews();

        for(int i = 0; i<nDots.length; i++){
            nDots[i] = new TextView(this);
            nDots[i].setText(Html.fromHtml("&#8226;"));
            nDots[i].setTextSize(35);
            nDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            ndotsLayout.addView(nDots[i]);
        }

        if(nDots.length > 0){
            nDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
                addDotsIndicator(position);
                nCurrentPage = position;

                if(position==0){
                    nNextButton.setEnabled(true);
                    nPreviousButton.setEnabled(false);
                    nPreviousButton.setVisibility(View.INVISIBLE);

                    nNextButton.setText("Next");
                    nPreviousButton.setText("");

                }else if(position==nDots.length-1){
                    nNextButton.setEnabled(true);
                    nPreviousButton.setEnabled(true);
                    nPreviousButton.setVisibility(View.VISIBLE);

                    nNextButton.setText("Finish");
                    nPreviousButton.setText("Back");
            }else{
                    nNextButton.setEnabled(true);
                    nPreviousButton.setEnabled(true);
                    nPreviousButton.setVisibility(View.VISIBLE);

                    nNextButton.setText("Next");
                    nPreviousButton.setText("Back");
                }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



}