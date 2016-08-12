package com.frostney.bluetoothstate;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

import android.bluetooth.BluetoothAdapter;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;

public class RNBluetoothStateModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNBluetoothStateModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNBluetoothState";
    }

    @ReactMethod
    public void initialize() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            sendStateEvent("centralManagerDidUpdateState", "unsupported");
        } else {
            try {
                sendStateEvent("centralManagerDidUpdateState", matchBluetoothStateToString(adapter.getState()));
                listenForBluetoothStateChanges();
            } catch (SecurityException e) {
                sendStateEvent("centralManagerDidUpdateState", "unauthorized");
            }
        }
    }

    private void sendStateEvent(String eventName, String bluetoothState) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, bluetoothState);
    }

    // see http://stackoverflow.com/a/9694138
    private void listenForBluetoothStateChanges() {
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        reactContext.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();
                if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                    sendStateEvent("centralManagerDidUpdateState",
                    matchBluetoothStateToString(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)));
                }
            }
        }, filter);
    }

    private String matchBluetoothStateToString(int state) {
        switch (state) {
        case BluetoothAdapter.STATE_OFF:
            return "off";
        case BluetoothAdapter.STATE_ON:
            return "on";
        default:
            return "unknown";
        }
    }
}
