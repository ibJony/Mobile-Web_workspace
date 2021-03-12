package com.jon.test.gcm;

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities {
	
	// give your server registration url here
    static final String SERVER_URL = "http://www.masterclass.co.ke/projects/mypoll/register.php"; 

    // Google project id
    static final String SENDER_ID = "425080491248"; 

    /**
     * Tag used on log messages.
     */
    static final String TAG = "AndroidHive GCM";

    static final String DISPLAY_MESSAGE_ACTION =
            "com.jon.test.gcm.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "message";

   
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
