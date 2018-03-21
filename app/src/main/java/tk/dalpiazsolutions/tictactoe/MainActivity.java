package tk.dalpiazsolutions.tictactoe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int playerOnTurn = 0;
    List<Integer> ids = new LinkedList<>();
    List<Integer> allIds = new LinkedList<>();
    Random id = new Random();
    ImageView counter;
    SharedPreferences preferences;
    public int[] gamestates = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningStates = {{0, 1, 2} , {3, 4, 5}, {6, 7, 8} , {0, 3, 6} , {1, 4, 7} , {2, 5, 8} , {0, 4, 8} , {2, 4, 6} };
    int tag;
    int x = 0;
    boolean alreadyBeen = false;
    boolean gameOver = false;
    int mode = -1;
    int idView;

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
                        counter.setImageResource(R.drawable.red_skin);
                        playerOnTurn = 1;
                    } else if (playerOnTurn == 1) {
                        gamestates[tag] = playerOnTurn;
                        counter.setImageResource(R.drawable.yellow_skin);
                        playerOnTurn = 0;
                    }

                    counter.setTranslationY(-1000f);
                    counter.animate().translationYBy(1000F).rotation(1800).setDuration(300);

                    checkWinning();
                } else if (mode == 1 || mode == 2) {
                    player1();
                    player2();
                }
            }
        }
    }

    public void newGame()
    {
        mode = preferences.getInt("mode", 0);
        alreadyBeen=false;
        gameOver = false;
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

        if (mode == 2)
        {
            setCounterObject();
            player2();
        }
    }

    public void player1()
    {
        if(gameOver==false) {
            playerOnTurn = 0;
            ids.add(counter.getId());
            tag = Integer.parseInt(counter.getTag().toString());
            gamestates[tag] = playerOnTurn;
            counter.setImageResource(R.drawable.red_skin);
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

            final ImageView enemyView = counter;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            enemyView.setImageResource(R.drawable.yellow_skin);
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
            ids.add(counter.getId());
            tag = Integer.parseInt(counter.getTag().toString());
            gamestates[tag] = playerOnTurn;
            checkWinning();
        }
    }

    public void setCounterObject()
    {
        alreadyBeen = true;
        while (alreadyBeen == true) {
            idView = id.nextInt(allIds.size());
            alreadyBeen(allIds.get(idView));
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
                    Toast.makeText(getApplicationContext(), getString(R.string.red), Toast.LENGTH_LONG).show();
                } else if (gamestates[winningPositions[0]] == 1) {
                    Toast.makeText(getApplicationContext(), getString(R.string.yellow), Toast.LENGTH_LONG).show();
                }
                gameOver = true;
                handler.postDelayed(runnable, 2000);
            }
        }

        while (x <= 8 && gamestates[x] != 2) {
            x++;
        }

        if (x == 9) {
            Toast.makeText(getApplicationContext(), getString(R.string.nobody), Toast.LENGTH_LONG).show();
            handler.postDelayed(runnable, 2000);
        }
        x = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("modeFile", MODE_PRIVATE);

        mode = preferences.getInt("mode", 0);

        allIds.add(R.id.imageView12);
        allIds.add(R.id.imageView13);
        allIds.add(R.id.imageView14);
        allIds.add(R.id.imageView15);
        allIds.add(R.id.imageView16);
        allIds.add(R.id.imageView17);
        allIds.add(R.id.imageView18);
        allIds.add(R.id.imageView19);
        allIds.add(R.id.imageView20);

        if (mode == 2)
        {
            setCounterObject();
            player2();
        }
    }
}
