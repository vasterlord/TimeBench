package gmailivanrudyk1996.com.timebench.timer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import gmailivanrudyk1996.com.timebench.R;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class Timer extends Fragment {

    Button btnStart, btnStop;
    TextView textViewTime;
    LinearLayout pinkers;
    FrameLayout frameTime;


    private static int sHour;
    private static int sMinute;
    private static int sSecond;
    private static long time;
    private static long timePause;

    public static void setTimePause(long timePause) {
        Timer.timePause = timePause;
    }


    CounterClass timer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.timer, null);

        btnStart = (Button) v.findViewById(R.id.btnStart);
        btnStop = (Button) v.findViewById(R.id.btnStop);
        textViewTime = (TextView) v.findViewById(R.id.textViewTime);
        pinkers = (LinearLayout) v.findViewById(R.id.pincers);
        frameTime = (FrameLayout) v.findViewById(R.id.frameTime);

        final NumberPicker npHours = (NumberPicker) v.findViewById(R.id.npHours);
        final NumberPicker npMinutes = (NumberPicker) v.findViewById(R.id.npMinute);
        final NumberPicker npSeconds = (NumberPicker) v.findViewById(R.id.npSecond);

        npHours.setMaxValue(23);
        npHours.setMinValue(0);
        npHours.setWrapSelectorWheel(false);
        npMinutes.setMaxValue(59);
        npMinutes.setMinValue(0);
        npMinutes.setWrapSelectorWheel(false);
        npSeconds.setMaxValue(59);
        npSeconds.setMinValue(0);
        npSeconds.setWrapSelectorWheel(false);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnStart.getText().toString().equals("Start")) {
                    Timer.sHour = npHours.getValue();
                    Timer.sMinute = npMinutes.getValue();
                    Timer.sSecond = npSeconds.getValue();

                    Timer.time = (1000 * (60 * (60 * Timer.sHour)) + 1000 * (60 * Timer.sMinute) + 1000 * (Timer.sSecond) + 500);
                    timer = new CounterClass(Timer.time, 1000);
                    timer.start();
                    btnStart.setText("Pause");
                    btnStop.setText("Censel");
                    pinkers.setVisibility(View.INVISIBLE);
                    frameTime.setVisibility(View.VISIBLE);

                } else if (btnStart.getText().toString().equals("Resume")) {
                    Timer.time = Timer.timePause;
                    timer = new CounterClass(Timer.time, 1000);
                    timer.start();
                    btnStart.setText("Pause");
                    btnStop.setText("Censel");
                    pinkers.setVisibility(View.INVISIBLE);
                    frameTime.setVisibility(View.VISIBLE);
                } else {
                    timer.cancel();
                    btnStart.setText("Resume");
                    btnStop.setText("Censel");
                }
            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnStop.getText().toString().equals("Censel")) {
                    timer.onFinish();
                    timer.cancel();
                    frameTime.setVisibility(View.INVISIBLE);
                    btnStart.setText("Start");
                    btnStop.setText("Reset");
                } else {
                    btnStart.setText("Start");
                    npHours.setValue(0);
                    npMinutes.setValue(0);
                    npSeconds.setValue(0);
                }
            }
        });
        return v;
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
     @SuppressLint("NewApi")
     public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            pinkers.setVisibility(View.VISIBLE);
            textViewTime.setText("Time finish");
            btnStart.setText("Start");
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            System.out.println(hms);
            textViewTime.setText(hms);
            Timer.setTimePause(millis);
        }
    }
}

