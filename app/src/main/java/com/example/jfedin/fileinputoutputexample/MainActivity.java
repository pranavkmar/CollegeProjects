package com.example.jfedin.fileinputoutputexample;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText readView;
    EditText writeView;

    FileOutputStream outputStream;
    FileInputStream inputStream;
    String filename = "temp.txt";

    File myExternalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readView = (EditText) findViewById(R.id.editTextRead);
        writeView = (EditText) findViewById(R.id.editTextWrite);

        Button externalWrite = (Button) findViewById(R.id.WriteExternalButton);
        Button externalRead = (Button) findViewById(R.id.ReadExternalButton);
        Button externalDelete = (Button) findViewById(R.id.DeleteExternalButton);

        // if not able to write to external storage, disable buttons
        if (!isExternalStorageWritable() && isExternalStorageReadable()) {
            externalWrite.setEnabled(false);
            externalRead.setEnabled(false);
            externalDelete.setEnabled(false);
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    12);
        }

        myExternalFile = getDocumentDir("temp.txt");
    }

    public void WriteInternalOnClick(View view) {
        String data = writeView.getText().toString();

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ReadInternalOnClick(View view) {

        try {
            InputStream instream = this.openFileInput(filename);
            ReadData(instream);

        } catch (java.io.FileNotFoundException e) {
            // do something if the filename does not exits
        }
    }

    public void DeleteInternalOnClick(View view) {
        this.deleteFile("temp.txt");
    }

    public void WriteExternalOnClick(View view) {
        String data = writeView.getText().toString();

            try {
                outputStream = new FileOutputStream(myExternalFile);
                outputStream.write(data.getBytes());
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void ReadExternalOnClick(View view) {
        try {
            InputStream instream = new FileInputStream(myExternalFile);
            ReadData(instream);

        } catch (java.io.FileNotFoundException e) {

            // do something if the filename does not exits
        }
    }

    public void DeleteExternalOnClick(View view) {
        myExternalFile.delete();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 12: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do something
                }
                else
                {
                    // not granted
                }
                return;
            }
        }
    }

     /* Helper Methods */

    public File getDocumentDir(String name) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), name);

        return file;
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public void ReadData(InputStream instream)
    {
        try {
            InputStreamReader inputreader = new InputStreamReader(instream);

            BufferedReader buffreader = new BufferedReader(inputreader);

            String line = new String();
            String allLines = new String();

            // read every line of the file into the line-variable, on line at the time

            while ((line = buffreader.readLine()) != null) {
                allLines += line;
            }

            readView.setText(allLines);

            // close the file again
            instream.close();

        } catch (Exception e) {

            // do something if the filename does not exits
        }
    }
}
