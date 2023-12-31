package com.example.wordapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Declaring Array of string
    String[] Words = new String[]{
            "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday","india","mexico","france","italy","africa","japan","australia","egypt","singapore","china","turkey"
    };
    //Declaring all other variables
    String word;
    Random random;
    TextView  txtRightAnswer, txtQuestionContainer;
    EditText edtUserInput;
    Button btnCheck, btnShow, btnSkip;
    TextView txtCorrect , txtIncorrect;
    private int i = 0;
    private int correct=0;
    private int incorrect = 0;
    private int skip = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        txtCorrect.setText("Correct: "+correct);
        txtIncorrect.setText("Incorrect: "+incorrect);
        //init random variable
        random = new Random();
        randomWord();


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = edtUserInput.getText().toString().replaceAll("\\s", "");
                if (userInput.equalsIgnoreCase(word)) {
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    setCorrect();
                    txtRightAnswer.setVisibility(View.INVISIBLE);
                    txtQuestionContainer.setVisibility(View.VISIBLE);
                    edtUserInput.setText("");
                    i = 0;
                    btnShow.setText("Show");
                    randomWord();
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                    setIncorrect();
                }
            }
        });



        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              txtCorrectAnswer.setVisibility(View.INVISIBLE);
                txtRightAnswer.setVisibility(View.INVISIBLE);
                txtQuestionContainer.setVisibility(View.VISIBLE);
                edtUserInput.setText("");
                i = 0;
                btnShow.setText("Show");
                if (skip!=0) {
                    skip--;
                    Toast.makeText(MainActivity.this, skip+"skip left", Toast.LENGTH_SHORT).show();
                    randomWord();
                }else{
                    Toast.makeText(MainActivity.this, "Cannot skip more than 3 times", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                txtCorrectAnswer.setVisibility(View.VISIBLE);
                if (i%2==0){
                    btnShow.setText("Show");
                    txtQuestionContainer.setVisibility(View.VISIBLE);
                    txtRightAnswer.setVisibility(View.INVISIBLE);
                    i++;
                }else {
                    btnShow.setText("Hide");
                    txtQuestionContainer.setVisibility(View.INVISIBLE);
                    txtRightAnswer.setVisibility(View.VISIBLE);
                    txtRightAnswer.setText(word);
                    i--;
                }
            }
        });
    }

    private void init(){
        txtQuestionContainer = findViewById(R.id.txtQuestionContainer);
//        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer);
        txtRightAnswer = findViewById(R.id.txtRightAnswer);
        edtUserInput = findViewById(R.id.edtUserInput);
        btnCheck = findViewById(R.id.btnCheck);
        btnSkip = findViewById(R.id.btnSkip);
        btnShow = findViewById(R.id.btnShow);
        txtCorrect = findViewById(R.id.txtCorrect);
        txtIncorrect = findViewById(R.id.txtIncorrect);

    }

    private String mixWords(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        StringBuilder mixed = new StringBuilder();
        for (String letter : letters) {
            mixed.append(letter);
        }
        return mixed.toString();
    }
    private void randomWord() {
        word = Words[random.nextInt(Words.length)];
        txtQuestionContainer.setText(mixWords(word));
    }


    private void setCorrect(){
        correct++;
        txtCorrect.setText("Correct: "+correct);
    }
    private void setIncorrect(){
        incorrect++;
        txtIncorrect.setText("Incorrect: "+incorrect);
    }
}