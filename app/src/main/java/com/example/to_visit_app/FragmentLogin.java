package com.example.to_visit_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.to_visit_app.viewModel.LoginViewModel;
import com.example.to_visit_app.utils.AlertDial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLogin extends Fragment {

    LoginViewModel loginVM;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLogin newInstance(String param1, String param2) {
        FragmentLogin fragment = new FragmentLogin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextInputLayout tiPassword = view.findViewById(R.id.ti_password);
        TextInputLayout tiUsername = view.findViewById(R.id.ti_username);
        TextInputEditText etUsername = view.findViewById(R.id.et_username);
        TextInputEditText etPassword = view.findViewById(R.id.et_password);
        Button btnLogin = view.findViewById(R.id.btn_login);

        /*-------- VIEW MODEL ---------*/
        loginVM = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        /*-------- LISTENERS --------*/
        //etPassword.se

        btnLogin.setOnClickListener(v -> {
            if (!isFieldEmpty(etPassword.getText())) {
                tiPassword.setError("password required");
            } else {
                tiPassword.setError(null);
                loginVM.login(String.valueOf(etUsername.getText()), String.valueOf(etPassword.getText())) ;
            }
        });

        // TODO: fix this listener https://codelabs.developers.google.com/codelabs/mdc-101-java#4
        etPassword.setOnKeyListener((view1, i, keyEvent) -> {
            if(isFieldEmpty(etPassword.getText())) {
                tiPassword.setError(null);
            }
            return false;
        });

        loginVM.setLoginListener((loggedIn, errorMessage, user) -> {
            if (loggedIn) {
                String username = user.getUsername();
                String token = user.getToken();
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = sharedPref.edit();
                prefsEditor.putString("username", username);
                prefsEditor.putString("token", token);
                prefsEditor.apply();

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_container, new FragmentHome(), "");
                fragmentTransaction.commit();
                //TODO: fix bottom navigation highlighted icon
            } else {
                new AlertDial().createMsgDialog(getActivity(), "Login error", errorMessage).show();
            }
        });

        return view;
    }

    private Boolean isFieldEmpty(Editable text) {
        return text != null && text.length() > 1;
    }
}