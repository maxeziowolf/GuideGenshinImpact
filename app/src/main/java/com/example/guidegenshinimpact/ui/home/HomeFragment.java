package com.example.guidegenshinimpact.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guidegenshinimpact.R;
import com.example.guidegenshinimpact.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvListCharacter;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private CharacterViewAdapter characterViewAdapter;
    private ArrayList<String> characterList;
    private ArrayList<String> characterSerch;
    private String [] arrayCharactersAutoComplete;
    private AutoCompleteTextView autoCompleteETBuscar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        initRecycleView();

        initModel(root);

        initComponent(root);

        initAutoComplete();

        return root;
    }

    private void initComponent(View root) {
        autoCompleteETBuscar = root.findViewById(R.id.etBuscarCharacter);
        autoCompleteETBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(autoCompleteETBuscar.getText().toString() != null){
                    findCharacter(autoCompleteETBuscar.getText().toString());
                }
            }
        });
        autoCompleteETBuscar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(autoCompleteETBuscar.getText().toString() != null){
                    findCharacter(autoCompleteETBuscar.getText().toString());
                }
            }
        });
    }

    private void initModel(View root) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getCharactersList().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                if(strings != null){
                    characterList = strings;
                    characterViewAdapter.setData(characterList);
                    initAutoComplete();
                }
            }
        });
        homeViewModel.getAllCharactersNames();
    }

    private void initAutoComplete() {
        arrayCharactersAutoComplete = new String[characterList.size()];
        int i = 0;
        for (String name : characterList) {
            arrayCharactersAutoComplete[i]= CharacterViewHolder.upperCaseFirst(name);
            i++;
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,  arrayCharactersAutoComplete);
        autoCompleteETBuscar.setAdapter(adaptador);
    }

    private void initRecycleView() {
        rvListCharacter = binding.rvListCharacters;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        rvListCharacter.setLayoutManager(gridLayoutManager);
        characterViewAdapter = new CharacterViewAdapter();
        characterList = new ArrayList<>();
        rvListCharacter.setAdapter(characterViewAdapter);
    }

    public void findCharacter(String name){
        characterSerch = new ArrayList<>();
        if(name.trim().equals("")){
            characterViewAdapter.setData(characterList);
        }else{
            for (String characterName : characterList) {
                if (characterName.contains(name.toLowerCase())){
                    characterSerch.add(characterName);
                }
                characterViewAdapter.setData(characterSerch);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}