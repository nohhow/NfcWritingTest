package com.example.nfcTest2;
        import java.io.IOException;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.PendingIntent;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.nfc.NdefMessage;
        import android.nfc.NdefRecord;
        import android.nfc.NfcAdapter;
        import android.nfc.Tag;
        import android.nfc.tech.Ndef;
        import android.nfc.tech.NdefFormatable;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

/**
 * Activity to write NFC tags with own mimetype and ID
 * Based on the excellent tutorial by Jesse Chen
 * http://www.jessechen.net/blog/how-to-nfc-on-the-android-platform/
 */
public class subActivity extends Activity {

    boolean mWriteMode = false;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mNfcPendingIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mNfcAdapter = NfcAdapter.getDefaultAdapter(subActivity.this);
                mNfcPendingIntent = PendingIntent.getActivity(subActivity.this, 0,
                        new Intent(subActivity.this, subActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

//                enableTagWriteMode();

                new AlertDialog.Builder(subActivity.this).setTitle("Touch tag to write")
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
//                                disableTagWriteMode();
                            }

                        }).create().show();
            }
        });

    }

}
