package com.example.mybingo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    ボタンID:register_max_number 最大値テキストフィールドID:max_number

    //    最大値
    private int maxNumber = 75;
//    数字の履歴
    private ArrayList<String> history = new ArrayList<>();
    //    最大値入力欄
    private EditText maxNumberEditText;
    //    最大値の設定ボタン
    private Button registerMaxNumberButton;
    //    次の数字を出すボタン
    private Button nextNumberButton;
//    現在の数字を表示するTextView
    private TextView currentNumberTextView;
//    履歴を表示する
    private TextView historyTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ビューの変数を初期化する
        maxNumberEditText = findViewById(R.id.max_number);
        registerMaxNumberButton = findViewById(R.id.register_max_number);
        nextNumberButton = findViewById(R.id.next_number);
        currentNumberTextView = findViewById(R.id.current_number);
        historyTextView = findViewById(R.id.history);

//        最大値の初期値をEditTextにセットする
        maxNumberEditText.setText("" + maxNumber);

//        最大値を更新する
        registerMaxNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                入力値を文字列で取り出す
                String maxNumberString = maxNumberEditText.getText().toString();
//                int型に変換してから代入する
                maxNumber = Integer.valueOf(maxNumberString);

                Log.d("MainActivity", "maxNumber: " + maxNumber);

            }
        });

//        表示中の数字を更新する
        nextNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNextNumber();
            }
        });
    }

//    nextNumberButtonがタップされたときの処理
    private void onClickNextNumber() {

        Log.d("MainActivity", "onClickNextNumber");

//        maxNumberを考慮したランダムな数値
        int nextNumber = createRandomNUmber();

//        重複してる場合は数値の生成をやり直す
        while (history.contains("" + nextNumber)) {
            Log.d("MainActivity", "重複したので再生成");
            nextNumber = createRandomNUmber();
        }

//        nextNumberを文字列に変換する
        String nextNumberStr = "" + nextNumber;

//        nextNumberを画面に表示する
        currentNumberTextView.setText("" + nextNumber);

//        履歴を残す
        history.add(nextNumberStr);
        Log.d("MainActivity", history.toString());

//        履歴を表示する
        historyTextView.setText(history.toString());
    }

//    maxNumberを考慮したランダムな数値を生成する
    private int createRandomNUmber() {
//    0.0～74.0(最大値が初期値の場合)の数値を生成する
        double randomNumber = Math.random() * (maxNumber - 1);
//    1～75(最大値が初期値の場合)の整数値を生成する
        return  (int) randomNumber + 1;
    }

}
