package android.lifeistech.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int[] PL_IMGS = {R.drawable.batsu, R.drawable.maru};
    public int turn;
    public int[] gameBoard;
    public ImageButton[] imgBtns;
    public TextView plTxtView;
    public TextView winnerTxtView;
    public int[] imgBtnRes = {
            R.id.imageButton1,
            R.id.imageButton2,
            R.id.imageButton3,
            R.id.imageButton4,
            R.id.imageButton5,
            R.id.imageButton6,
            R.id.imageButton7,
            R.id.imageButton8,
            R.id.imageButton9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plTxtView = findViewById(R.id.pltxt);
        winnerTxtView = findViewById(R.id.winnerTxt);

        imgBtns = new ImageButton[9];

        for(int i = 0; i < imgBtnRes.length; i++){
            imgBtns[i] = findViewById(imgBtnRes[i]);
        }

        init();
        setPlayer();
    }

    public void init(){
        turn = 1;
        gameBoard = new int[imgBtns.length];
        for(int i = 0; i < imgBtns.length; i++){
            gameBoard[i] = -1;
            imgBtns[i].setImageBitmap(null);
        }
        winnerTxtView.setVisibility(View.GONE);
    }

    public void setPlayer(){
        if(turn %2 == 0){
            plTxtView.setText("Player:×(2)");
        }else{
            plTxtView.setText("Player:○(1)");
        }
    }

    public void tapImgBtn(View v){
        if(winnerTxtView.getVisibility() == View.VISIBLE) return;

        int tappedBtnPos;
        int viewId = v.getId();

        if(viewId == R.id.imageButton1){
            tappedBtnPos = 0;
        }else if(viewId == R.id.imageButton2){
            tappedBtnPos = 1;
        }else if(viewId == R.id.imageButton3){
            tappedBtnPos = 2;
        }else if(viewId == R.id.imageButton4){
            tappedBtnPos = 3;
        }else if(viewId == R.id.imageButton5){
            tappedBtnPos = 4;
        }else if(viewId == R.id.imageButton6){
            tappedBtnPos = 5;
        }else if(viewId == R.id.imageButton7){
            tappedBtnPos = 6;
        }else if(viewId == R.id.imageButton8){
            tappedBtnPos = 7;
        }else{
            tappedBtnPos = 8;
        }

        if(gameBoard[tappedBtnPos] == -1){
            imgBtns[tappedBtnPos].setImageResource(PL_IMGS[turn % 2]);
            gameBoard[tappedBtnPos] = turn % 2;
            int jud = judGame();

            if(jud != -1){
                if(jud == 0){
                    winnerTxtView.setText("Game End\nPlayer:×(2)\n win");
                    winnerTxtView.setTextColor(Color.BLUE);
                }else{
                    winnerTxtView.setText("Game End\nPlayer:○(1)\n win");
                    winnerTxtView.setTextColor(Color.RED);
                }
                winnerTxtView.setVisibility(View.VISIBLE);
            } else{
                if(turn >= gameBoard.length){
                    winnerTxtView.setText("Game End\nDraw");
                    winnerTxtView.setTextColor(Color.YELLOW);
                    winnerTxtView.setVisibility(View.VISIBLE);
                }
            }

            turn++;
            setPlayer();
        }
    }

    public int judGame(){
        for (int i = 0; i < 3; i++){
            if(isMarkedHorizontal(i)){
                return gameBoard[i * 3];
            }

            if(isMarkedVertical(i)){
                return gameBoard[i];
            }
        }

        if(isMarkedDiagonal()){
            return gameBoard[4];
        }

        return -1;
    }

    public boolean isMarkedHorizontal(int i){
        if(gameBoard[i*3] != -1 && gameBoard[i*3] == gameBoard[i * 3 + 1] && gameBoard[i * 3] == gameBoard[i * 3 + 2]){
            return true;
        }else {
            return false;
        }
    }

    public boolean isMarkedVertical(int i){
        if(gameBoard[i] != -1 && gameBoard[i] == gameBoard[i + 3] && gameBoard[i] == gameBoard[i + 6]){
            return true;
        }else{
            return false;
        }
    }

    public boolean isMarkedDiagonal(){
        if(gameBoard[0] != -1 && gameBoard[0] == gameBoard[4] && gameBoard[0] == gameBoard[8]){
            return true;
        }else if(gameBoard[2] != -1 && gameBoard[2] == gameBoard[4] && gameBoard[2] == gameBoard[6]){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_menu_reset){
            init();
            setPlayer();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
