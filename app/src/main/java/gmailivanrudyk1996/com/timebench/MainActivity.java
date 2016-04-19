package gmailivanrudyk1996.com.timebench;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {



    Button bOpenStopwach, bOpenTimer, bOpenAlarm, bOpenSportTimer;
    Stopwatch stopwatch;
    Timer timer;
    Alarm alarm;
    SportTimer sportTimer;
    FragmentTransaction fTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bOpenStopwach = (Button) findViewById(R.id.btOpenStopwach);
        bOpenTimer = (Button) findViewById(R.id.btOpenTimer);
        bOpenAlarm = (Button) findViewById(R.id.btOpenAlarm);
        bOpenSportTimer = (Button) findViewById(R.id.btOpenSportTimer);
    }

    public void onClick(View view) {
        stopwatch = new Stopwatch();
        timer = new Timer();
        alarm = new Alarm();
        sportTimer = new SportTimer();
        fTrans = getFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.btOpenStopwach:
                fTrans.replace(R.id.frag1, stopwatch);
                bOpenStopwach.setTextSize(20);
                bOpenTimer.setTextSize(15);
                bOpenAlarm.setTextSize(15);
                bOpenSportTimer.setTextSize(15);
                break;
            case R.id.btOpenTimer:
                fTrans.replace(R.id.frag1, timer);
                bOpenStopwach.setTextSize(15);
                bOpenTimer.setTextSize(20);
                bOpenAlarm.setTextSize(15);
                bOpenSportTimer.setTextSize(15);
                break;
            case R.id.btOpenAlarm:
                fTrans.replace(R.id.frag1, alarm);
                bOpenStopwach.setTextSize(15);
                bOpenTimer.setTextSize(15);
                bOpenAlarm.setTextSize(20);
                bOpenSportTimer.setTextSize(15);
                break;
            case R.id.btOpenSportTimer:
                fTrans.replace(R.id.frag1, sportTimer);
                bOpenStopwach.setTextSize(16);
                bOpenTimer.setTextSize(15);
                bOpenAlarm.setTextSize(15);
                bOpenSportTimer.setTextSize(20);
            default:
                break;
        }
        fTrans.commit();
    }
}
