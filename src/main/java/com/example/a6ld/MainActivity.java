package com.example.a6ld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TextView textView = (TextView) findViewById(R.id.textView);
        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.my_menu2, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_1:

                //id
                TextView symbols = (TextView) findViewById(R.id.textView);
                TextView output = (TextView) findViewById(R.id.textView2);

                output.setText("Tekste yra " + String.valueOf(symbols.length()) + " simbolių");
                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                builder2.setMessage("Tekste yra " + String.valueOf(symbols.length()) + " simbolių");
                builder2.create().show();
                return true;

            case R.id.option_2:
                //
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.finish) {
            finish();
        }

        if (id == R.id.time2) {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    Calendar rightNow = Calendar.getInstance();
                    int hour_current = rightNow.get(Calendar.HOUR_OF_DAY);
                    int minutes_current = rightNow.get(Calendar.MINUTE);
                    int time_sum = ((hour * 60 + minute) - (hour_current * 60 + minutes_current));
                    int time_hour = time_sum / 60;
                    int time_minute = time_sum % 60;

                    TextView textView2 = (TextView) findViewById(R.id.textView);
                    textView2.setText("Laiko skirtumas yra: " + Math.abs(time_hour) + "val. ir " + Math.abs(time_minute) + " min.");
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("Laiko skirtumas yra: " + Math.abs(time_hour) + "val. ir " + Math.abs(time_minute) + " min.");
                    builder1.create().show();
                }
            };
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
            timePickerDialog.setTitle("Select Time");
            timePickerDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}