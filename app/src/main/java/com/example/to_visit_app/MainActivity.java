package com.example.to_visit_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.to_visit_app.ViewModel.LoginViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::listener);
        TextView tv_username = findViewById(R.id.tv_logged_as);
        TextView tvLogInOut = findViewById(R.id.tv_log_in_out);
        ImageButton btnLogin = findViewById(R.id.img_btn_login);

        //default fragment
        loadFragment(new FragmentHome());
        bottomNavigationView.getMenu().getItem(1);

        // no logged in user
        tv_username.setVisibility(View.INVISIBLE);
        AtomicReference<Boolean> loggedIn = new AtomicReference<>(false);

        /*--------- view models ---------*/
        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        LoginViewModel loginVM = new ViewModelProvider(this).get(LoginViewModel.class);
        loginVM.getUser().observe(this, user -> {
            if (user != null) {
                tv_username.setVisibility(View.VISIBLE);
                String sUser = "Logged in as user: " + user.getUsername();
                tv_username.setText(sUser);
                tvLogInOut.setText("Logout");
                loggedIn.set(true);
            }
        });

        /*------- LISTENERS --------*/
        btnLogin.setOnClickListener(v -> {
            if (!loggedIn.get()) loadFragment(new FragmentLogin());
            else {
                loginVM.deleteUser();
                tvLogInOut.setText("Login");
                tv_username.setVisibility(View.INVISIBLE);
                loggedIn.set(false);
            }
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
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, fragment, "");
        fragmentTransaction.commit();
    }
}