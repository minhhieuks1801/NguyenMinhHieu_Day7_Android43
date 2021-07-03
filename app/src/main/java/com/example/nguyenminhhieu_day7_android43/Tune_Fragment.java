package com.example.nguyenminhhieu_day7_android43;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.nguyenminhhieu_day7_android43.databinding.ActivityMainBinding;

public class Tune_Fragment extends Fragment {
    Button btnfl, btnfd;


    public static Tune_Fragment newInstance() {

        Bundle args = new Bundle();
        Tune_Fragment fragment = new Tune_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tune_fragment, container,false);

        btnfl = view.findViewById(R.id.zoe);
        btnfd = view.findViewById((R.id.neeko));

        btnfd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] strings = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                boolean[] booleans = {false, false, false, false, false, false, false};

                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Chọn ngày để đi xa bờ!")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                Toast.makeText(getContext(), strings[i], Toast.LENGTH_LONG).show();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });


        return view;

    }
}
