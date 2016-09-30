package com.fahmee.braintrainer;

import android.os.CountDownTimer;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button startButton;
    Button button ;
    Button button1 ;
    Button button2;
    Button button3;
    ArrayList<Integer> result;
    int locationAnswer;
    TextView resultTextview;
    TextView resultText;
    int score=0;
    int questions = 0;
    TextView secText;
    Button playAgain;
    RelativeLayout gameRelativeLayout;


    public void playAgain(final View view){
        button.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        score=0;
        questions=0;

        secText.setText("30s");
        resultTextview.setText("");
        resultText.setText("0/0");
        playAgain.setVisibility(view.INVISIBLE);


        generateQuestions();
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                secText.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {

                playAgain.setVisibility(view.VISIBLE);
                secText.setText("0s");

                resultTextview.setText("Your Score: " + Integer.toString(score) + "/"
                        + Integer.toString(questions));
                resultText.setText("0/0");
                button.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);



            }

        }.start();





    }



    public void generateQuestions(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        textView = (TextView)findViewById(R.id.sumtextview);

        textView.setText(String.valueOf(a) + "+" + String.valueOf(b));

        result = new ArrayList<Integer>();

        locationAnswer = rand.nextInt(4);

        result.clear();
        int incorrectAns;

        for (int i=0; i <4; i++){

            if (i==locationAnswer){
                result.add(a+b);

            }else{
                incorrectAns = rand.nextInt(41);
                while (incorrectAns==a+b){
                    incorrectAns=rand.nextInt(41);
                }
                result.add(incorrectAns);
            }
        }
        button.setText((Integer.toString(result.get(0))));
        button1.setText((Integer.toString(result.get(1))));
        button2.setText((Integer.toString(result.get(2))));
        button3.setText((Integer.toString(result.get(3))));

    }

    public void chooseAnswer(View view){

        if (view.getTag().toString().equals(Integer.toString(locationAnswer))){

            score++;

            resultTextview.setText("Correct!");

        }else{
            resultTextview.setText("Wrong!");

        }
        questions++;

        resultText.setText(Integer.toString(score) + "/" + Integer.toString(questions));
        generateQuestions();

    }




    public void startButton(View view){



        startButton.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainBtn));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startButton = (Button)findViewById(R.id.startButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
        resultTextview = (TextView)findViewById(R.id.resultTextview);
        resultText = (TextView)findViewById(R.id.resultText);
        secText = (TextView)findViewById(R.id.secText);
        button = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgain = (Button)findViewById(R.id.playAgainBtn);



        }




    }

