package com.wiktor.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewNumbers;
    private SeekBar seekBar;

    private ArrayList<Integer> numbers;

    // Максимальное и минимальное значение SeekBar-a
    private int max = 20;
    private int min = 1;
    // Значение колличества эл-тов выводимое в таблице
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNumbers = findViewById(R.id.listViewNumbers);
        seekBar = findViewById(R.id.seekBar);

        //Установить максимальное значение SeekBar-a
        seekBar.setMax(max);


        numbers = new ArrayList<>();

        final ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1,
                numbers
        );

        listViewNumbers.setAdapter(arrayAdapter);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // вызывается когда изменен прогресс в seekBar-е
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                // отчистка списка перед очередным заполнением
                numbers.clear();
                // что бы не выводить таблицу умножения на 0, введем проверку
/*                if (i < min){
                    // если меньше минимального значения (1), то устанавливаем минимальное значение
                    seekBar.setProgress(min); // ползунок не доходит до 0
                }*/

                for (int ii = min; ii <= count; ii++) {
                    //Добавляем значения в массив  numbers
                    //seekBar.getProgress() - Получить значения из seekBar-а
                    numbers.add(seekBar.getProgress() * ii);
                }
                // Обновить данные в ListView. т.к. arrayAdapter изначально пустой( "numbers = new ArrayList<>()" ), ему нужно явно указать что массив изменился
                arrayAdapter.notifyDataSetChanged();
            }

            // вызывается когда пользователь только начанает двигать кружок
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            // вызывается когда пользователь отпускает кружок
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // Установить начальное значение seekBar на 10
        seekBar.setProgress(10);
    }
}
