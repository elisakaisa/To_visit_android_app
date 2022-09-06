package com.example.to_visit_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.to_visit_app.ViewModel.LoginViewModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLogin extends Fragment {

    private RequestQueue mRequestQueue;
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
        EditText etUsername = view.findViewById(R.id.et_username);
        EditText etPassword = view.findViewById(R.id.et_password);
        Button btnLogin = view.findViewById(R.id.btn_login);

        // Volley
        mRequestQueue = Volley.newRequestQueue(getActivity());

        /*-------- VIEW MODEL ---------*/
        loginVM = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        /*-------- LISTENERS --------*/
        btnLogin.setOnClickListener(v ->
                login(String.valueOf(etUsername.getText()), String.valueOf(etPassword.getText())));

        return view;
    }

    private void login(String username, String password) {
        // asynchronous operation to fetch visits
        String mUrl = "https://pacific-spire-62523.herokuapp.com/api/login";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, mUrl, jsonBody, response -> {
            try {
                Log.d("POST request response", String.valueOf(response));
                // TODO: save token
                loginVM.setUser(response.getString("username"));

            } catch (Exception e){
                Log.i("error whilst parsing", e.toString());
            }
        }, error -> {
            Log.i("Volley error", error.toString());
            //TODO: handle 401 wrong credentials
        }){
            @Override
            public byte[] getBody(){
                String jsonString = jsonBody.toString();
                return jsonString.getBytes();
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }};

        loginRequest.setTag(this);
        mRequestQueue.add(loginRequest);

        // go to home fragment
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, new FragmentHome(), "");
        fragmentTransaction.commit();
        //TODO: fix bottom navigation highlighted icon
    }
}