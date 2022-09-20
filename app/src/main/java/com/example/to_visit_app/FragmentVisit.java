package com.example.to_visit_app;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.to_visit_app.databinding.FragmentVisitBinding;
import com.example.to_visit_app.viewModel.VisitViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentVisit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentVisit extends Fragment {

    private View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentVisit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentVisit.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentVisit newInstance(String param1, String param2) {
        FragmentVisit fragment = new FragmentVisit();
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
        FragmentVisitBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_visit, container, false);
        view = binding.getRoot();
        // Inflate the layout for this fragment

        TableLayout table = view.findViewById(R.id.tableLayout);
        table.setVisibility(View.GONE);
        TextView tvLoading = view.findViewById(R.id.tv_loading_ind);

        VisitViewModel model = new ViewModelProvider(requireActivity()).get(VisitViewModel.class);

        model.getSelectedVisits().observe(requireActivity(), visit -> {
            binding.setVisitVM(model);
            tvLoading.setVisibility(View.GONE);
            table.setVisibility(View.VISIBLE);
            // todo: make sure that actualPrice and TotWalkingDistance are invisible if -1
        });

        return view;
    }
}