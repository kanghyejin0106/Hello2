package android.develop.hello;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(TAG, "onReceive called");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages != null && messages.length>0){
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG, "Sender: "+sender);

            String body = messages[0].getMessageBody();
            Log.i(TAG,"Body: "+body);

            Date receivedData = new Date(messages[0].getTimestampMillis());
            Log.i(TAG,"Date: "+receivedData.toString());
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for(int i=0;i<smsCount;i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i]=SmsMessage.createFromPdu((byte[])objs[i],format);
            }else{
                messages[i]=SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }

        return messages;
    }
}