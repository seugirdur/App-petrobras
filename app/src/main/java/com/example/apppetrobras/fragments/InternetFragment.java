package com.example.apppetrobras.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apppetrobras.DadosLista;
import com.example.apppetrobras.ProblemActivity;
import com.example.apppetrobras.R;
import com.example.apppetrobras.RecyclerViewAdapter;

import java.util.ArrayList;

public class InternetFragment extends Fragment implements RecyclerViewInteface{

    private ArrayList<DadosLista> dataArrayList;
    private String[] titulosProblemas;
    private int[] imagensProblemas, idProblemas;
    private RecyclerView recyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_internet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),dataArrayList, this);
        recyclerview.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {

        dataArrayList = new ArrayList<>();

        titulosProblemas = new String[]{
                getString(R.string.internet_1),
                getString(R.string.internet_2),
                getString(R.string.internet_3),
                getString(R.string.internet_4),
                getString(R.string.internet_5),
                getString(R.string.internet_6),
        };

        idProblemas = new int[]{
                1, 2, 3, 4, 5, 6
        };

        imagensProblemas = new int[]{
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
        };

        for(int i = 0; i < titulosProblemas.length; i++){
            DadosLista data = new DadosLista(titulosProblemas[i], idProblemas[i], imagensProblemas[i]);
            dataArrayList.add(data);
        }

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ProblemActivity.class);
        intent.putExtra("TIPO",2);
        intent.putExtra("ID", dataArrayList.get(position).getId());
        startActivity(intent);
    }

}
