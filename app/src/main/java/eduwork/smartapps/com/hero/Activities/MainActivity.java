package eduwork.smartapps.com.hero.Activities;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import eduwork.smartapps.com.hero.Base.BaseActivity;
import eduwork.smartapps.com.hero.Helper.InputOutputFileOperation;
import eduwork.smartapps.com.hero.Helper.InputParseOperation;
import eduwork.smartapps.com.hero.Helper.MessageWriter;
import eduwork.smartapps.com.hero.Helper.OutputFileWriter;
import eduwork.smartapps.com.hero.Models.Hero;
import eduwork.smartapps.com.hero.R;
public class MainActivity extends BaseActivity {
    private InputParseOperation parser;
    private InputOutputFileOperation operation;
    private MessageWriter writer;
    OutputFileWriter outputFileWriter;
    TextView tv_game_result;
    ImageView img_result_game;
    Button bt_game_show;
    Hero hero;
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckFilePermision();
        initValues();
        initView();
        GameRun();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void GameRun(){
        String inputStr = operation.readInputFromFile();
        hero = parser.getHero(inputStr);
        hero.setWriter(writer);
        hero.start(parser.getRoute(inputStr));

    }
    public void initValues(){
        outputFileWriter=new OutputFileWriter(getApplicationContext());
        parser=new InputParseOperation();
        operation=new InputOutputFileOperation(getApplicationContext());
        writer=new MessageWriter(getApplicationContext());
    }

    public void initView(){
     tv_game_result=findViewById(R.id.txt_result_game);
     bt_game_show=findViewById(R.id.bt_result_show);
     img_result_game=findViewById(R.id.img_result);

    }

    public void HeroIsAlive(){
        img_result_game.setImageResource(R.drawable.winner_icon);
    }

    public void HeroIsDied(){
        img_result_game.setImageResource(R.drawable.lost_icon);
    }


    public void CheckFilePermision(){
        if(!AskPermision()){
            Want_To_Permision();

        }
    }

    public void onClickGameBtn(View v)
    {
        tv_game_result.setText(outputFileWriter.getOutputFile());
        bt_game_show.setVisibility(View.GONE);

        if(hero.isAlive()){

            HeroIsAlive();
        }
        else{
            HeroIsDied();
        }

    }
}
