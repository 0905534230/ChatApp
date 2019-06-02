package Notification;


import com.google.firebase.iid.FirebaseInstanceIdReceiver;

public class MyFirebaseService extends FirebaseInstanceIdReceiver {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }

}
