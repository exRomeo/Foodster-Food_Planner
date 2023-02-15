package com.example.foodster_foodplanner.home;

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
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeFragment extends Fragment implements OnCardClickListener, HomeView {

    private ViewPager2 viewPager2;
    private List<Meal> daily;
    private PageViewerAdapter adapter;
    private Handler slider;
    private HomePresenterImplementation presenter;
    private Date todayDate;
    int flag=0;
    String TAG ="here";

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
        todayDate = new Date(System.currentTimeMillis());

        if (flag==1) {
            presenter.getDailyFromDb(todayDate);
            daily = presenter.dailyFromDb;
            setAdapter();
            Log.i(TAG, "l2a meals w 3ml adapter");
        } else {
            presenter.getMeals();
            setAdapter();
            Log.i(TAG, "ml2ash f ra7 presenter w da b3d adapter");
        }
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
        NavHostFragment.findNavController(this)
                .navigate(HomeFragmentDirections.actionHomeFragmentToMealFragment(meal));
    }

    @Override
    public void showDailyMeals(List<Meal> dailyTen) {
        daily.add(dailyTen.get(0));
        adapter.notifyDataSetChanged();
        addToDatabase(dailyTen);
        Log.i(TAG, "successffulll w show");
    }


    @Override
    public void showError(String errMsg) {
        Toast.makeText(getActivity(), errMsg, Toast.LENGTH_SHORT).show();

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
        flag=1;
        for (Meal m : dailyRecieved) {
            m.setDate(todayDate);
            presenter.addDailyToDb(m);
            Log.i(TAG, "meal added to db");
        }
        
    }

}