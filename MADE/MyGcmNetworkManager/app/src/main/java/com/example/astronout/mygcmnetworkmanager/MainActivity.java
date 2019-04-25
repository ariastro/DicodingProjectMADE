package com.example.astronout.mygcmnetworkmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSetScheduler, btnCancelScheduler;
    private SchedulerTask mSchedulerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetScheduler = findViewById(R.id.btn_set_scheduler);
        btnCancelScheduler = findViewById(R.id.btn_cancel_scheduler);
        btnSetScheduler.setOnClickListener(this);
        btnCancelScheduler.setOnClickListener(this);
        mSchedulerTask = new SchedulerTask(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_set_scheduler:
                mSchedulerTask.createPeriodicTask();
                Toast.makeText(this, "Periodic Task Created", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancel_scheduler:
                mSchedulerTask.cancelPeriodicTask();
                Toast.makeText(this, "Periodic Task Canceled", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
