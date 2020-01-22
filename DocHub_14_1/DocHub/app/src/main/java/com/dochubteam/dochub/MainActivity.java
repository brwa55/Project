package com.dochubteam.dochub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dochubteam.dochub.Fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    private static View backgroundBlack;
    private static LinearLayout loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        // If savedinstnacestate is null then replace login fragment.
        if (savedInstanceState == null) {
            fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, new LoginFragment(), Utils.LoginFragment).commit();
        }

        // On close icon click finish activity
        findViewById(R.id.close_activity).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    finish();
                }
            }
        );

    }


    public static void loadingBar(Boolean status, Activity activity) {
        backgroundBlack = activity.findViewById(R.id.background_black);
        loadingBar = activity.findViewById(R.id.loading_bar);

        if (status) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            loadingBar.setVisibility(View.VISIBLE);
            backgroundBlack.setVisibility(View.VISIBLE);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            loadingBar.setVisibility(View.INVISIBLE);
            backgroundBlack.setVisibility(View.INVISIBLE);
        }
    }

    // Replace Login Fragment with animation
    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new LoginFragment(),
                        Utils.LoginFragment).commit();
    }

    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment
        Fragment SignUp_Fragment = fragmentManager
                .findFragmentByTag(Utils.SignupFragment);
        Fragment ForgotPassword_Fragment = fragmentManager
                .findFragmentByTag(Utils.ForgotpasswordFragment);

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task

        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }
}
