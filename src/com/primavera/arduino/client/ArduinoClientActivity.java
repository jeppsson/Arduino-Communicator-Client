/*
 * Copyright (C) 2013 Mathias Jeppsson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.primavera.arduino.client;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ArduinoClientActivity extends Activity {

    private final static String DATA_RECEIVED_INTENT = "primavera.arduino.intent.action.DATA_RECEIVED";
    private final static String SEND_DATA_INTENT = "primavera.arduino.intent.action.SEND_DATA";
    private final static String DATA_EXTRA = "primavera.arduino.intent.extra.DATA";
    private final static String TAG = "ArduinoClientActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(DATA_RECEIVED_INTENT);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();
                Log.d(TAG, "onReceive() " + action);
                if (DATA_RECEIVED_INTENT.equals(action)) {
                    final byte[] data = intent.getByteArrayExtra(DATA_EXTRA);
                    Toast.makeText(context, new String(data), Toast.LENGTH_SHORT).show();
                }
            }
        }, filter);
    }

    public void sendDataPressed(View view) {
        Log.d(TAG, "onClick()");
        final byte[] data = {0x48,0x65,0x6c,0x6c,0x6f,0x20,0x77,0x6f,0x72,0x6c,0x64,0x21};
        Intent intent = new Intent(SEND_DATA_INTENT);
        intent.putExtra(DATA_EXTRA, data);
        sendBroadcast(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.help:
            startActivity(new Intent(this, Help.class));
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

}