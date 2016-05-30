package cn.fwso.smsfw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SettingActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "cn.fwso.hello2.MESSAGE";

    private EditText editPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editPhone = (EditText) findViewById(R.id.edit_phone);
        editPhone.setText(getCurrentPhone());
    }

    public void savePhoneNumber(View view) {
        Intent intent = new Intent(this, SavedActivity.class);
        //EditText editPhone = (EditText) findViewById(R.id.edit_phone);
        String message = editPhone.getText().toString().trim();

        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(getString(R.string.preference_key_phone_number), message);
        editor.commit();
        Log.d("SMSFWD", "Setting Saved: " + message);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private String getCurrentPhone() {
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        String phone = pref.getString(getString(R.string.preference_key_phone_number), null);
        if (phone != null) {
            return phone;
        }
        return "";
    }
}
