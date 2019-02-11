package com.pranav.hiname;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FetchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        final TextView t1 = findViewById(R.id.textView3);
        final EditText editText = (EditText) findViewById(R.id.editText);


        Button submitbutton = (Button) findViewById(R.id.submitbutton2);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //this will log the page number that was click
                String rs = editText.getText().toString();
                t1.setText(rs);

            }
        });


    }
}