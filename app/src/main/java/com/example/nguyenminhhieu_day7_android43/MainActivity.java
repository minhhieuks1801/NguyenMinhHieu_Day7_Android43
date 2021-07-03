package com.example.nguyenminhhieu_day7_android43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nguyenminhhieu_day7_android43.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ISave {
    ActivityMainBinding binding;
    SavePresenter savePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        savePresenter = new SavePresenter(this);

        binding.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Thông báo: ")
                        .setMessage("Bạn có muốn lưu không?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                savePresenter.onSave(1);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                savePresenter.onSave(2);
                            }
                        })
                        .create();
                alertDialog.show();

            }
        });

        spTyPe();

        binding.btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getBaseContext(), view);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.show();
            }
        });

        binding.tvGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHours();
            }
        });

        binding.tvNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });

        binding.tvMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] strings = {"Suning", "NAVI", "G2 Esport", "SBTC Esport", "GAM Esport"};
                boolean[] booleans = {false, false, false, false, false};

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Chọn một đội để đi xa bờ!")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                Toast.makeText(MainActivity.this, strings[i], Toast.LENGTH_LONG).show();
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

        binding.tvThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] strings = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                boolean[] booleans = {false, false, false, false, false, false, false};

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Chọn ngày để đi xa bờ!")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                Toast.makeText(MainActivity.this, strings[i], Toast.LENGTH_LONG).show();
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

//        getFragment(Tune_Fragment.newInstance());

        binding.btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragment(Tune_Fragment.newInstance());
            }
        });
    }

    private void getFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentMain, fragment).commit();
    }

    private void spTyPe (){
        ArrayList<String> arrayListType = new ArrayList<String>();
        arrayListType.add("Nhảy cầu!");
        arrayListType.add("Ra đảo!");
        arrayListType.add(("Về bờ"));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spType.setAdapter(arrayAdapter);
    }

    private void setHours(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                binding.tvGio.setText(i + ":"+ i1);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                timeSetListener, 24, 60, true);

        timePickerDialog.show();
    }

    private void setDate(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                binding.tvNgay.setText(i2 +"/" + i1 +"/"+i);
            }

        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, 2000, 12, 31 );
        datePickerDialog.show();
    }

    @Override
    public void onSaveSuccessful(String mess) {
        Toast.makeText(this,mess,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNotSave(String mess) {
        Toast.makeText(this,mess,Toast.LENGTH_LONG).show();
    }
}