package com.algonquincollege.king0410.guessanumber;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //setting varibles and generating random numbers
    java.util.Random rand = new java.util.Random();
    int randomNum = rand.nextInt(1000) + 1;
    int guessesNum = 1;
    int Wins = 0;
    int losses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button guessButton = (Button) findViewById(R.id.Button);
        final Button newGame = (Button) findViewById(R.id.newGame);
        newGame.setVisibility(View.GONE);
        guessButton.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
               //setting up all of the id's
            EditText userGuessScore = (EditText) findViewById(R.id.guesses);
            userGuessScore.setText("" + guessesNum);
            EditText userWins = (EditText) findViewById(R.id.win);
            EditText userGuessField = (EditText) findViewById(R.id.userGuess);
            Log.d("NumTag", "Random Number Is: " + randomNum);
            Log.d("GuessTag", "Guesses: " + guessesNum);
            Log.d("wins", "Wins: " + Wins);
            Log.d("losses", "Losses: " + losses);
                //main loop
                if(userGuessField.length() == 0){
                    userGuessField.setError("please enter a number");
                    return;
                }
            while (guessesNum < 11) {
                while (userGuessField.length() != 0) {
                    String userGuessbox = userGuessField.getText().toString();
                    int userGuessnum = Integer.parseInt(userGuessbox);
                    if (userGuessbox.isEmpty()) {
                        userGuessField.setError("Enter a number between 1-1000");
                        userGuessField.requestFocus();
                        return;
                    }

                    if (userGuessnum > 1000) {
                        userGuessField.setError("Number can't be bigger then 1000");
                        userGuessField.requestFocus();
                        userGuessField.setText("");
                        return;
                    }

                    if (userGuessnum < 1) {
                        userGuessField.setError("Number can't be smaller then 1");
                        userGuessField.requestFocus();
                        userGuessField.setText("");
                        return;
                    }

                    if (userGuessnum < randomNum) {
                        userGuessField.setError("Guess is too low");
                        guessesNum = guessesNum + 1;
                        userGuessField.requestFocus();
                        userGuessField.setText("");
                        return;
                    }

                    if (userGuessnum > randomNum) {
                        userGuessField.setError("Guess is too high");
                        guessesNum = guessesNum + 1;
                        userGuessField.requestFocus();
                        userGuessField.setText("");
                        return;
                    }

                    if (userGuessnum == randomNum) {
                        userGuessField.setText("");
                        if (guessesNum < 5) {
                            userGuessField.setHint("Superior win!");
                        }
                        if (guessesNum > 9) {
                            userGuessField.setHint("Excellent win!");
                        }
                        Wins = Wins + 1;
                        guessButton.setVisibility(View.GONE);
                        newGame.setVisibility(View.VISIBLE);
                        userGuessField.requestFocus();
                        userGuessField.setEnabled(false);
                        userWins.setText("" + Wins);
                        return;
                    }
                }
            }

            if (guessesNum < 13) {
                userGuessField.setEnabled(false);
                userGuessField.setHint("Play Agian");
                userGuessField.setError("Please Reset , Correct Answer Was " + randomNum);
                guessButton.setVisibility(View.GONE);
                newGame.setVisibility(View.VISIBLE);
                losses = losses + 1;
                EditText userloseScore = (EditText) findViewById(R.id.losses);
                userloseScore.setText("" + losses);
            }
        }

        });
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) {
            EditText userGuessField = (EditText) findViewById(R.id.userGuess);
            userGuessField.setEnabled(true);
            guessButton.setVisibility(View.VISIBLE);
            newGame.setVisibility(View.GONE);
            randomNum = rand.nextInt(1000) + 1;
            guessesNum = 1;
                userGuessField.setText("");
            EditText userGuessScore = (EditText) findViewById(R.id.guesses);
            userGuessScore.setText("0");
            EditText userloseScore = (EditText) findViewById(R.id.losses);
            userloseScore.setText("" + losses);
        }
        });


        guessButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Answer: " + randomNum, Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}