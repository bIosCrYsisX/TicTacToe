package tk.dalpiazsolutions.tictactoe;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int playerOnTurn = 0;
    int randomNumber;
    List<Integer> ids = new LinkedList<>();
    List<Integer> allIds = new LinkedList<>();
    List<Integer> preferedIds = new LinkedList<>();
    Random id = new Random();
    ImageView counter;
    SharedPreferences preferences;
    public int[] gamestates = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningStates = {{0, 1, 2} , {3, 4, 5}, {6, 7, 8} , {0, 3, 6} , {1, 4, 7} , {2, 5, 8} , {0, 4, 8} , {2, 4, 6} };
    int tag;
    int x = 0;
    boolean alreadyBeen = false;
    boolean gameOver = false;
    boolean idGot = false;
    int mode = -1;
    int idView;
    int difficulty;
    String background;
    String colorOne;
    String colorTwo;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            newGame();
        }
    };

    public void alreadyBeen(int id)
    {
        alreadyBeen = false;
        int i = 0;
        while (i < ids.size())
        {
            if (ids.get(i) == id)
            {
                alreadyBeen = true;
                idGot = false;
            }
            i++;
        }
    }

    public void dropIn(View view)
    {
        counter = (ImageView) view;

        alreadyBeen(counter.getId());

        if(gameOver==false) {
            if (alreadyBeen == false) {
                if (mode == 0) {
                    ids.add(counter.getId());
                    tag = Integer.parseInt(counter.getTag().toString());
                    if (playerOnTurn == 0) {
                        gamestates[tag] = playerOnTurn;

                        if(colorOne.equals(getString(R.string.skinRed)))
                        {
                            counter.setImageResource(R.drawable.red_skin);
                        }

                        else if(colorOne.equals(getString(R.string.skinYellow)))
                        {
                            counter.setImageResource(R.drawable.yellow_skin);
                        }

                        else if(colorOne.equals(getString(R.string.skinBlack)))
                        {
                            counter.setImageResource(R.drawable.black_skin);
                        }

                        else if(colorOne.equals(getString(R.string.skinPurple)))
                        {
                            counter.setImageResource(R.drawable.purple_skin);
                        }

                        playerOnTurn = 1;
                    } else if (playerOnTurn == 1) {
                        gamestates[tag] = playerOnTurn;

                        if(colorTwo.equals(getString(R.string.skinRed)))
                        {
                            counter.setImageResource(R.drawable.red_skin);
                        }

                        else if(colorTwo.equals(getString(R.string.skinYellow)))
                        {
                            counter.setImageResource(R.drawable.yellow_skin);
                        }

                        else if(colorTwo.equals(getString(R.string.skinBlack)))
                        {
                            counter.setImageResource(R.drawable.black_skin);
                        }

                        else if(colorTwo.equals(getString(R.string.skinPurple)))
                        {
                            counter.setImageResource(R.drawable.purple_skin);
                        }

                        playerOnTurn = 0;
                    }

                    counter.setTranslationY(-1000f);
                    counter.animate().translationYBy(1000F).rotation(1800).setDuration(300);

                    checkWinning();
                } else if (mode == 1 || mode == 2) {
                    player1();
                    Log.i("tag", "player1");
                    for(int i = 0; i < gamestates.length; i++)
                    {
                        Log.i("tag", Integer.toString(gamestates[i]));
                    }
                    player2();
                    Log.i("tag", "player2");
                    for(int i = 0; i < gamestates.length; i++)
                    {
                        Log.i("tag", Integer.toString(gamestates[i]));
                    }
                }
            }
        }
    }

    public void newGame()
    {
        mode = preferences.getInt("mode", 0);
        difficulty = preferences.getInt("difficulty", 0);
        alreadyBeen = false;
        gameOver = false;
        idGot = false;
        x = 0;
        int i = 0;

        while(i <= 8)
        {
            gamestates[i] = 2;
            i++;
        }
        ids = new LinkedList<>();
        playerOnTurn = 0;
        setContentView(R.layout.activity_main);

        changeBackground();
        if (mode == 2)
        {
            player2();
        }
    }

    public void player1()
    {
        if(gameOver==false) {
            playerOnTurn = 0;
            ids.add(counter.getId());
            Log.e("player1Id", Integer.toString(counter.getId()));
            tag = Integer.parseInt(counter.getTag().toString());
            Log.e("player1Tag", counter.getTag().toString());
            gamestates[tag] = playerOnTurn;

            if(colorOne.equals(getString(R.string.skinRed)))
            {
                counter.setImageResource(R.drawable.red_skin);
            }

            else if(colorOne.equals(getString(R.string.skinYellow)))
            {
                counter.setImageResource(R.drawable.yellow_skin);
            }

            else if(colorOne.equals(getString(R.string.skinBlack)))
            {
                counter.setImageResource(R.drawable.black_skin);
            }

            else if(colorOne.equals(getString(R.string.skinPurple)))
            {
                counter.setImageResource(R.drawable.purple_skin);
            }

            counter.setTranslationY(-1000f);
            counter.animate().translationYBy(1000F).rotation(1800).setDuration(300);

            checkWinning();
        }
    }

    public void player2()
    {
        if(gameOver==false) {
            playerOnTurn = 1;
            setCounterObject();
            ids.add(counter.getId());

            final ImageView enemyView = counter;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(colorTwo.equals(getString(R.string.skinRed)))
                            {
                                enemyView.setImageResource(R.drawable.red_skin);
                            }

                            else if(colorTwo.equals(getString(R.string.skinYellow)))
                            {
                                enemyView.setImageResource(R.drawable.yellow_skin);
                            }

                            else if(colorTwo.equals(getString(R.string.skinBlack)))
                            {
                                enemyView.setImageResource(R.drawable.black_skin);
                            }

                            else if(colorTwo.equals(getString(R.string.skinPurple)))
                            {
                                enemyView.setImageResource(R.drawable.purple_skin);
                            }
                            enemyView.setTranslationY(-1000f);
                            enemyView
                                    .animate()
                                    .translationYBy(1000F)
                                    .rotation(1800)
                                    .setDuration(300);
                        }
                    });
                }
            }, 500);
            tag = Integer.parseInt(enemyView.getTag().toString());
            gamestates[tag] = playerOnTurn;
            checkWinning();
        }
    }

    public void setCounterObject()
    {
        idGot=false;
        alreadyBeen = true;
        while (alreadyBeen == true) {
            if(difficulty == 0) {
                idView = id.nextInt(allIds.size());
                alreadyBeen(allIds.get(idView));
            }

            else if(difficulty == 1)
            {

                if (mode == 1 && gamestates[4] == 2){
                    idGot = true;
                    idView = 4;
                }

                for (int[] winningPositions : winningStates) {
                    if (idGot == false) {
                        if (gamestates[winningPositions[0]] == 1 && gamestates[winningPositions[1]] == 1 && gamestates[winningPositions[2]] == 2) {
                            idView = winningPositions[2];
                            idGot = true;
                        } else if (gamestates[winningPositions[1]] == 1 && gamestates[winningPositions[2]] == 1 && gamestates[winningPositions[0]] == 2) {
                            idView = winningPositions[0];
                            idGot = true;
                        } else if (gamestates[winningPositions[0]] == 1 && gamestates[winningPositions[2]] == 1 && gamestates[winningPositions[1]] == 2) {
                            idView = winningPositions[1];
                            idGot = true;
                        }
                    }
                }

                if(idGot==false) {

                    for (int[] winningPositions : winningStates) {
                        if (idGot == false) {
                            if (gamestates[winningPositions[0]] == 0 && gamestates[winningPositions[1]] == 0 && gamestates[winningPositions[2]] == 2) {
                                idView = winningPositions[2];
                                idGot = true;
                            } else if (gamestates[winningPositions[1]] == 0 && gamestates[winningPositions[2]] == 0 && gamestates[winningPositions[0]] == 2) {
                                idView = winningPositions[0];
                                idGot = true;
                            } else if (gamestates[winningPositions[0]] == 0 && gamestates[winningPositions[2]] == 0 && gamestates[winningPositions[1]] == 2) {
                                idView = winningPositions[1];
                                idGot = true;
                            }
                        }
                    }
                }

                if(idGot == false) {
                    if(gamestates[0] == 2 || gamestates[2] == 2 || gamestates[6] == 2 || gamestates[8] == 2) {
                        randomNumber = id.nextInt(4);
                        ImageView imageView = findViewById(preferedIds.get(randomNumber));
                        idView = Integer.parseInt(imageView.getTag().toString());
                        idGot = true;
                    }
                }

                if(idGot==false) {
                    idView = id.nextInt(allIds.size());
                    idGot = true;
                }
                Log.e("tag", "idView: " + Integer.toString(idView));
                Log.e("allIDS", Integer.toString(allIds.get(idView)));
                Log.e("ids", ids.toString());
                alreadyBeen(allIds.get(idView));
            }
        }

        Field[] fields = R.id.class.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            try {
                int fieldValue = fields[i].getInt(null);
                if (fieldValue == allIds.get(idView)) {
                    counter = findViewById(fieldValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkWinning()
    {
        for (int[] winningPositions : winningStates) {
            if (gamestates[winningPositions[0]] == gamestates[winningPositions[1]] &&
                    gamestates[winningPositions[1]] == gamestates[winningPositions[2]] &&
                    gamestates[winningPositions[0]] != 2) {
                if (gamestates[winningPositions[0]] == 0) {
                    Toast.makeText(getApplicationContext(), colorOne + getString(R.string.wins) , Toast.LENGTH_LONG).show();
                } else if (gamestates[winningPositions[0]] == 1) {
                    Toast.makeText(getApplicationContext(), colorTwo + getString(R.string.wins), Toast.LENGTH_LONG).show();
                }
                gameOver = true;
                handler.postDelayed(runnable, 2000);
            }
        }

        if(gameOver==false) {

            while (x <= 8 && gamestates[x] != 2) {
                x++;
            }

            if (x == 9) {
                gameOver=true;
                Toast.makeText(getApplicationContext(), getString(R.string.nobody), Toast.LENGTH_LONG).show();
                handler.postDelayed(runnable, 2000);
            }
            x = 0;
        }
    }

    public void changeBackground()
    {
        ConstraintLayout constraintLayout = findViewById(R.id.game_layout);
        background = preferences.getString("background", getString(R.string.orange));
        colorOne = preferences.getString("colorOne", getString(R.string.skinRed));
        colorTwo = preferences.getString("colorTwo", getString(R.string.skinYellow));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (background.equals(getString(R.string.orange))) {
                constraintLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.background));
            } else if (background.equals(getString(R.string.green))) {
                constraintLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.background_green));
            } else if (background.equals(getString(R.string.blue))) {
                constraintLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.background_blue));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("modeFile", MODE_PRIVATE);
        difficulty = preferences.getInt("difficulty", 0);
        changeBackground();

        mode = preferences.getInt("mode", 0);

        allIds.add(R.id.imageView20);
        allIds.add(R.id.imageView19);
        allIds.add(R.id.imageView18);
        allIds.add(R.id.imageView17);
        allIds.add(R.id.imageView16);
        allIds.add(R.id.imageView15);
        allIds.add(R.id.imageView14);
        allIds.add(R.id.imageView13);
        allIds.add(R.id.imageView12);

        preferedIds.add(R.id.imageView12);
        preferedIds.add(R.id.imageView14);
        preferedIds.add(R.id.imageView18);
        preferedIds.add(R.id.imageView20);

        if (mode == 2)
        {
            player2();
        }
    }
}
