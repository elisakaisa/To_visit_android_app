package com.example.to_visit_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.to_visit_app.ViewModel.LoginViewModel;
import com.example.to_visit_app.ViewModel.VisitViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::listener);
        TextView tv_username = findViewById(R.id.tv_logged_as);

        //default fragment
        loadFragment(new FragmentHome());
        bottomNavigationView.getMenu().getItem(1);

        // view model
        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        VisitViewModel model = new ViewModelProvider(this).get(VisitViewModel.class);
        model.getVisits().observe(this, visits -> {
            Log.d("MainActivity -> VM", String.valueOf(visits.get(1).getWhat()));
        });
        LoginViewModel loginVM = new ViewModelProvider(this).get(LoginViewModel.class);
        loginVM.getUser().observe(this, user ->{
            String sUser = "Logged in as user: " + user;
            tv_username.setText(sUser);
        });
    }

    @SuppressLint("NonConstantResourceId")
    private boolean listener(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_add:
                loadFragment(new FragmentAdd());
                return true;
            case R.id.nav_home:
                loadFragment(new FragmentHome());
                return true;
            case R.id.nav_login:
                loadFragment(new FragmentLogin());
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment, "");
        fragmentTransaction.commit();
    }
}