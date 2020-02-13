package gr.hua.grigorisar.locationapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Check internet connection and call Toast
        if (networkInfo != null && networkInfo.isConnected()) {
//            Toast.makeText(context, "INTERNET SIGNAL RECEIVED", Toast.LENGTH_SHORT).show();

            Intent intent1 = new Intent(context,LocationService.class);
            context.startService(intent1);

            //TODO SERVICE HERE
        }
    }
}
