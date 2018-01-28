package com.miuty.slowgit.ui.screen;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.miuty.slowgit.util.InputHelper;
import com.miuty.slowgit.util.SchemeParser;


/**
 * Created by Kosh on 09 Dec 2016, 12:31 PM
 */

public class LinksParserActivity extends Activity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (Login.getUser() == null) {
            Toast.makeText(SlowGitApplication.getInstance(), R.string.please_login, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginChooserActivity.class));
            finish();
            return;
        }*/
        onCreate(getIntent());
    }

    @Override protected void onStart() {
        super.onStart();
        setVisible(true);
    }

    private void onCreate(@Nullable Intent intent) {
        if (intent == null || intent.getAction() == null) {
            finish();
            return;
        }
        if (Intent.ACTION_SEND.equals(intent.getAction())) {
            try {
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (!InputHelper.isEmpty(sharedText)) {
                    Uri uri = Uri.parse(sharedText);
                    onUriReceived(uri);
                }
            } catch (Exception ignored) {}
        } else if (intent.getAction().equals(Intent.ACTION_VIEW)) {
            if (intent.getData() != null) {
                onUriReceived(intent.getData());
            }
        }
        finish();
    }

    private void onUriReceived(@NonNull Uri uri) {
        SchemeParser.launchUri(this, uri, false, true);
    }
}
