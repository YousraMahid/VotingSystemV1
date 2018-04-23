package com.example.hp.votingsystemv1;

import android.app.Application;

import com.example.hp.votingsystemv1.Models.OnNotificationOpenHandler;
import com.onesignal.OneSignal;

public class YourAppClass extends Application {
   @Override
   public void onCreate() {
      super.onCreate();
     
      // OneSignal Initialization
      OneSignal.startInit(this)
        .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
        .unsubscribeWhenNotificationsAreDisabled(true)
              .setNotificationOpenedHandler(new OnNotificationOpenHandler() {
              })
        .init();
   }
}