package com.example.to_visit_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.to_visit_app.RecyclerView.VisitAdapter;
import com.example.to_visit_app.RecyclerView.VisitRecycler;
import com.example.to_visit_app.RecyclerView.VisitRecyclerInterface;
import com.example.to_visit_app.ViewModel.VisitViewModel;
import com.example.to_visit_app.model.VisitList;
import com.example.to_visit_app.model.VisitModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment implements VisitRecyclerInterface {

    private View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);

        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        RecyclerView visit_rv = view.findViewById(R.id.rv_visits);

        VisitViewModel model = new ViewModelProvider(requireActivity()).get(VisitViewModel.class);
        model.getVisits().observe(requireActivity(), visits -> {

            ArrayList<VisitList> itemList = new ArrayList<>();
            for (VisitModel instantVisit : visits) {
                String what = instantVisit.getWhat();
                String id = instantVisit.getId();
                itemList.add(new VisitRecycler(what, id));
            }
            RecyclerView.Adapter<VisitAdapter.ViewHolder> adapter = new VisitAdapter(itemList, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            visit_rv.setLayoutManager(layoutManager);
            visit_rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
                    model.loadVisits();
                    swipeRefreshLayout.setRefreshing(false);
                });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemClick(String id) {
        // set selected visit in VM
        VisitViewModel model = new ViewModelProvider(requireActivity()).get(VisitViewModel.class);
        model.setSelectId(id);
        // change fragment
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, new FragmentVisit(), "");
        fragmentTransaction.commit();
    }
}