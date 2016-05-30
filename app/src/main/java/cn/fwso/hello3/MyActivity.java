package cn.fwso.hello3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "cn.fwso.hello2.MESSAGE";
    public final static String KEY_PHONE_NUMBER = "cn.fwso.hello2.KEY_PHONE_NUMBER";

    private EditText editPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        editPhone = (EditText) findViewById(R.id.edit_phone);
        editPhone.setText(getCurrentPhone());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void savePhoneNumber(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editPhone = (EditText) findViewById(R.id.edit_phone);
        String message = editPhone.getText().toString();

        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_PHONE_NUMBER, message);
        editor.commit();

        intent.putExtra(EXTRA_MESSAGE, message + " Saved");
        startActivity(intent);
    }

    private String getCurrentPhone() {
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        String phone = pref.getString(KEY_PHONE_NUMBER, null);
        if (phone != null) {
            return phone;
        }
        return "";
    }
}
