package com.example.foodster_foodplanner.screen.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.screen.OnCardClickListener;
import com.example.foodster_foodplanner.screen.meal.MealActivity;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeFragment extends Fragment implements OnCardClickListener, HomeView {

    private ViewPager2 viewPager2;
    private List<Meal> daily;
    private List<Meal> aroundTheWorld;
    private PageViewerAdapter adapter;
    private Handler slider;
    private HomePresenterImplementation presenter;
    private String todayDate;
    private TextView userNameText;
    private TextView region;
    private RecyclerView countryRecycler;
    private RecyclerAdapter recyclerAdapter;

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
        aroundTheWorld = new ArrayList<>();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new HomePresenterImplementation(this, RepositoryImpl.getInstance(RetrofitClientImpl.getInstance(), LocalDatabaseSource.getInstance(this.requireContext())));
        viewPager2 = view.findViewById(R.id.viewPager);
        countryRecycler = view.findViewById(R.id.country_meals_menu);
        userNameText = view.findViewById(R.id.userLoginName);
        region = view.findViewById(R.id.countryTitle);

        slider = new Handler();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userName = user.getDisplayName();
        userNameText.setText(userName);

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

        setRecyclerAdapter();
        presenter.getCountryMeals();
    }

    @Override
    public void onCardClick(Meal meal) {
        presenter.getMealByID(meal.getIdMeal());
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

    @Override
    public void showFromCountry(List<Meal> countryList) {
        recyclerAdapter.setList(countryList);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setName(String CountryName) {
        region.setText(CountryName);
    }

    @Override
    public void goToMeal(Meal meal) {
        Intent i = new Intent(this.requireContext(), MealActivity.class);
        i.putExtra("meal", meal);
        startActivity(i);
    }

    public void setAdapter() {
        adapter = new PageViewerAdapter(daily, viewPager2, this, this.requireContext());
        viewPager2.setAdapter(adapter);
    }

    public void setRecyclerAdapter() {
        recyclerAdapter = new RecyclerAdapter(requireContext(), aroundTheWorld, this);
        countryRecycler.setAdapter(recyclerAdapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        countryRecycler.setLayoutManager(layoutManager);
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
        }
    }

}