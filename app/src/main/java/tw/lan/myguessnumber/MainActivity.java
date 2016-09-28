package tw.lan.myguessnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private String answer;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText)findViewById(R.id.numberInput);
        info = (TextView)findViewById(R.id.resultInfo);
        answer = CreateNumber(3);

    }

    public void GuessClick(View v) {
        String guess = input.getText().toString();
        String result = CheckNumber(answer, guess);
        info.append(result + "\n");
        input.setText("");
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
