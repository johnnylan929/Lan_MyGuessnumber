package tw.lan.myguessnumber;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private String answer;
    private TextView info;
    private int times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText)findViewById(R.id.numberInput);
        info = (TextView)findViewById(R.id.resultInfo);
        initGame();
        Log.d("Lan", answer);
    }

    public void GuessClick(View v) {
        String guess = input.getText().toString();
        String result = CheckNumber(answer, guess);
        times++;
        info.append(times + ":" + result + "\n");
        input.setText("");

        if(result.equals("3A0B")) {
            showMesgDialog(true);
        }else if(times == 10) {
            showMesgDialog(false);
        }
    }

    private void initGame() {
        answer = CreateNumber(3);
        times = 0;
        info.setText("");
    }

    private void showMesgDialog(Boolean isWin) {
        AlertDialog alert = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Info");
        if(isWin) {
            builder.setMessage("Win");
        }else {
            builder.setMessage("Loss");
        }
        builder.setCancelable(true);
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initGame();
            }
        });
        alert = builder.create();
        alert.show();

    }

    //Create a Number
    private String CreateNumber(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (set.size() < n) {
            set.add((int)(Math.random()*9));
        }
        StringBuffer sb = new StringBuffer();
        for(Integer i : set) {
            sb.append(i);
        }
        return sb.toString();
    }

    //Check the Number
    private String CheckNumber(String a, String g) {
        int A,B; A=B=0;
        for (int i =0 ;i<a.length();i++) {
            if(a.charAt(i) == g.charAt(i)) {
                A++;
            }else if(a.indexOf(g.charAt(i)) != -1) {
                B++;
            }
        }
        return A + "A" + B + "B";
    }
}
