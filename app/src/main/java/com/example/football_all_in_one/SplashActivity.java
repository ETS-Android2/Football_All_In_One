package com.example.football_all_in_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {
  ProgressBar splashProgress;
  ImageView football;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    football = findViewById(R.id.football);
    splashProgress = findViewById(R.id.splash_progress);

    Glide.with(this).load(R.drawable.hexagonal_football).into(football);

    Progress progress = new Progress();
    new Thread(progress).start();
  }

  private class Progress implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i <= 100; i++) {
        splashProgress.setProgress(i);
        try {
          Thread.sleep(25);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      Intent intent = new Intent(getApplicationContext(), MainActivity.class);
      startActivity(intent);
      finish();
    }
  }
}