package com.example.foodster_foodplanner.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodster_foodplanner.MealActivity;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeFragment extends Fragment implements OnCardClickListener, HomeView {

    private ViewPager2 viewPager2;
    private List<Meal> daily;
    private PageViewerAdapter adapter;
    private Handler slider;
    private HomePresenterImplementation presenter;
    private String todayDate;

    static Integer LOGGED_FLAG;
    String TAG = "here";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        daily = new ArrayList<>();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            presenter = new HomePresenterImplementation(this, RepositoryImpl.getInstance(RetrofitClientImpl.getInstance(), LocalDatabaseSource.getInstance(this.requireContext())));
            viewPager2 = view.findViewById(R.id.viewPager);
            slider = new Handler();

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            Date today = new Date();

            todayDate = formatter.format(today);

            setAdapter();

            presenter.getDailyFromDb(todayDate);

            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    slider.removeCallbacks(sliderRunnable);
                    slider.postDelayed(sliderRunnable, 3000);
                }
            });

    }

    @Override
    public void onCardClick(Meal meal) {
      
        Intent i = new Intent(this.requireContext(), MealActivity.class);
        i.putExtra("meal",meal);
        startActivity(i);

    }

    @Override
    public void showDailyMeals(List<Meal> dailyTen) {
        adapter.setList(dailyTen);
        adapter.notifyDataSetChanged();
        addToDatabase(dailyTen);
    }


    @Override
    public void showError(String errMsg) {
        Toast.makeText(getActivity(), errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFromDataBase(List<Meal> dailyTen) {
        adapter.setList(dailyTen);
        adapter.notifyDataSetChanged();
    }

    public void setAdapter() {
        adapter = new PageViewerAdapter(daily, viewPager2, this, this.requireContext());
        viewPager2.setAdapter(adapter);
        Log.i(TAG, "ana f set l adapter");
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            int current = viewPager2.getCurrentItem();
            viewPager2.setCurrentItem(current + 1);

        }
    };

    @Override
    public void onFavoriteClick(Meal meal) {
        Toast.makeText(this.requireContext(), "Meal is added: " + meal.getStrMeal(), Toast.LENGTH_SHORT).show();
        presenter.addToFavs(meal);
    }

    public void addToDatabase(List<Meal> dailyRecieved) {
        for (Meal m : dailyRecieved) {
            m.setDate(todayDate);
            presenter.addDailyToDb(m);
            Log.i(TAG, "meal added to db");
        }
    }

}