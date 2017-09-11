/*
 * Copyright (C) 2017 MaBaoGW
 *
 * Source Link: https://github.com/MaBaoGW
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package blesample.mabaogw.sample.relaycontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class UploadStringActivity extends AbstractBleControlActivity {
    private final static String TAG = UploadStringActivity.class.getSimpleName();
    private Context context;

    private ImageButton buttonLED,buttonRELAY;
    private boolean ledOn = false;
    private boolean relayOn = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_upload_string);
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        activity = this;
        context = this;

        final Intent intent = getIntent();
        currDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        currDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(currDeviceName);

        buttonLED = (ImageButton) findViewById(R.id.buttonLED);
        buttonLED.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Log.d(TAG, "buttonLED onClick");

                if(ledOn){
                    buttonLED.setBackgroundResource(R.drawable.led_off);
                    ledOn = false;
                    UploadAsyncTask asyncTask = new UploadAsyncTask();
                    asyncTask.execute(0xA1);
                }
                else{
                    buttonLED.setBackgroundResource(R.drawable.led_on);
                    ledOn = true;
                    UploadAsyncTask asyncTask = new UploadAsyncTask();
                    asyncTask.execute(0xA2);
                }

            }
        });

        buttonRELAY = (ImageButton) findViewById(R.id.buttonRELAY);
        buttonRELAY.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Log.d(TAG, "buttonRELAY onClick");
                if(relayOn){
                    buttonRELAY.setBackgroundResource(R.drawable.relay_off);
                    relayOn = false;
                    UploadAsyncTask asyncTask = new UploadAsyncTask();
                    asyncTask.execute(0xB1);
                }
                else{
                    buttonRELAY.setBackgroundResource(R.drawable.relay_on);
                    relayOn = true;
                    UploadAsyncTask asyncTask = new UploadAsyncTask();
                    asyncTask.execute(0xB2);
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nothing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}