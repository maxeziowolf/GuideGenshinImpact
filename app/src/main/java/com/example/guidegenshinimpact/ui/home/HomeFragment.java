package com.example.guidegenshinimpact.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.guidegenshinimpact.models.CharacterGenshin;
import com.example.guidegenshinimpact.ui.home.components.CharacterViewAdapter;
import com.example.guidegenshinimpact.utils.StringFormatter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvListCharacter;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private CharacterViewAdapter characterViewAdapter;
    private ArrayList<String> characterListNames;
    private ArrayList<CharacterGenshin> characterList;
    private ArrayList<CharacterGenshin> characterSerch;
    private String [] arrayCharactersAutoComplete;
    private AutoCompleteTextView autoCompleteETBuscar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        initRecycleView();

        initModel(root);

        initComponent(root);


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
        homeViewModel.getCharactersListNames().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                if(strings != null){
                    characterListNames = strings;
                    initAutoComplete();
                }
            }
        });
        homeViewModel.getCharactersList().observe(getViewLifecycleOwner(), new Observer<ArrayList<CharacterGenshin>>() {
            @Override
            public void onChanged(ArrayList<CharacterGenshin> characterGenshins) {
                if(characterGenshins != null){
                    characterList = characterGenshins;
                    characterViewAdapter.setData(characterGenshins);
                }
            }
        });
        homeViewModel.getAllCharactersNames();
        homeViewModel.getAllCharacters();
    }

    private void initAutoComplete() {
        arrayCharactersAutoComplete = new String[characterListNames.size()];
        int i = 0;
        for (String name : characterListNames) {
            arrayCharactersAutoComplete[i]= StringFormatter.upperCaseFirst(name);
            i++;
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,  arrayCharactersAutoComplete);
        autoCompleteETBuscar.setAdapter(adaptador);
    }

    private void initRecycleView() {
        rvListCharacter = binding.rvListCharacters;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
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
            for (CharacterGenshin character : characterList) {
                if (character.getName().toLowerCase().contains(name.toLowerCase())){
                    characterSerch.add(character);
                }
                characterViewAdapter.setData(characterSerch);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        autoCompleteETBuscar.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}