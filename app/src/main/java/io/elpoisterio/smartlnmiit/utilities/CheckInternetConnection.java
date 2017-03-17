package io.elpoisterio.smartlnmiit.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AlertDialog;

/**
 * CheckInternetConnection Class is used to check Internet connection
 *
 * @author Rishabh Yadav
 */
public class CheckInternetConnection {

    private Context context;

    public CheckInternetConnection(Context mContext) {
        context = mContext;
    }

    /**
     * @return isConnectedToInternet?(), is used to check Internet Connectivity by using Connection Manager.
     * it will return true if it is connected and false if not connected
     */
    public boolean isConnectedToInternet() {
        try {
            if (context == null) {
                return false;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Network[] info = connectivityManager.getAllNetworks();
                NetworkInfo networkInfo;
                if (info != null) {
                    for (Network anInfo : info) {

                        networkInfo = connectivityManager.getNetworkInfo(anInfo);
                        if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }


                    }
                }
                return false;
            } else if (connectivityManager != null) {
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }


                    }

                }
                return false;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle("No Internet Connectivity Found");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        ((Activity) context).finish();
                        //dialog.dismiss();
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}