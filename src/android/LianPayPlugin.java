package com.xxx.plugin;
// The native Toast API
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.widget.Toast;
// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lianlian.base.OnResultListener;
import com.lianlian.securepay.token.SecurePayService;

public class LianPayPlugin extends CordovaPlugin {
    public static final String ACTION_PAY = "startPay";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (ACTION_PAY.equals(action)) {
            final LianPayPlugin that = this;
            final String url = args.getString(0);
            this.mResultListener = new OnResultListener() {
                @Override
                public void onResult(JSONObject jsonObject) {
                    LOG.d("LLPAY", jsonObject.toString());
                    callbackContext.success(jsonObject.toString());
                }
            };
            securePay(url);
            return  true;
        }
        callbackContext.error("Unknow action");
        return  false;
    }

    private void securePay(String gatewayUrl) {
        final Context context=this.cordova.getActivity();

        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SecurePayService.securePay(context, gatewayUrl, 1, mResultListener, false);
            }
        });


    }

    OnResultListener mResultListener = null;

}
