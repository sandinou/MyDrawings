package com.mydrawings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyPathDrawColor myPathDrawColor;
    private Context mContext;
    private PopupWindow mPopupWindow;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.myRL);

        myPathDrawColor = (MyPathDrawColor)findViewById(R.id.view);

        ((ImageView)findViewById(R.id.orangeIV)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.blueIV)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.blackIV)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.redIV)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.greenIV)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.clearIV)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.eraseIV)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.purpleIV)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.plusIV)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.minusIV)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.questionIV)).setOnClickListener(this);

        // Use MyService to show toast message at application's launch and all every minute
        startService(new Intent(this,MyService.class)); // use to start the service
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.redIV:
                myPathDrawColor.setPathColor(Color.RED);
                break;
            case R.id.blueIV:
                myPathDrawColor.setPathColor(Color.BLUE);
                break;
            case R.id.blackIV:
                myPathDrawColor.setPathColor(Color.BLACK);
                break;
            case R.id.orangeIV:
                myPathDrawColor.setPathColor(Color.rgb(255,153,0));
                break;
            case R.id.greenIV:
                myPathDrawColor.setPathColor(Color.GREEN);
                break;
            case R.id.purpleIV:
                myPathDrawColor.setPathColor(Color.rgb(179, 0, 179));
                break;
            case R.id.eraseIV:
                myPathDrawColor.erase();
                break;
            case R.id.clearIV:
                myPathDrawColor.clear();
                Toast.makeText(this,R.string.screen_clear,Toast.LENGTH_SHORT).show();
                break;

            case R.id.minusIV:
                myPathDrawColor.decreaseLine();
                break;
            case R.id.plusIV:
                myPathDrawColor.increaseLine();
                break;
            case R.id.questionIV:
                // Initialize a new instance of LayoutInflater service
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                View customView = inflater.inflate(R.layout.help_layout,null);

                // Initialize a new instance of popup window
                mPopupWindow = new PopupWindow(
                        customView,
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                // Set an elevation value for popup window
                // Call requires API level 21
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }

                // Get a reference for the custom view close button
                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Kill the service
        stopService(new Intent(this,MyService.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this,MyService.class));
    }
}
