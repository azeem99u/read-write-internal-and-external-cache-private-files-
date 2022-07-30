package com.example.android.workingwithinternalandexternalcachestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String FILE_NAME = "azeem.txt";
    TextView internalData,externalData;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internalData = findViewById(R.id.txtPrivateInternalCache);
         externalData = findViewById(R.id.txtPrivateExternalCache);
         editText = findViewById(R.id.et1);
    }

    public void createInternalPrivateCache(View view) {
        File file = new File(getFilesDir(),FILE_NAME);
        writeFile(file);
    }
    public void readInternalPrivateCache(View view) {
        File file = new File(getFilesDir(),FILE_NAME);
        String s = readFile(file);
        internalData.setText(s);
    }

    public void createExternalPrivateCache(View view) {
        File file = new File(getExternalCacheDir(),FILE_NAME);
        writeFile(file);
    }

    public void readExternalPrivateCache(View view) {
        File file = new File(getExternalCacheDir(),FILE_NAME);
        String s = readFile(file);
        externalData.setText(s);
    }

    private void writeFile(File file){
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(editText.getText().toString().getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readFile(File file){
        StringBuilder stringBuilder = null;
        FileInputStream fileInputStream = null;

        try {
            stringBuilder  = new StringBuilder();
            fileInputStream = new FileInputStream(file);
            int read;
            while ((read = fileInputStream.read()) != -1){
                stringBuilder.append((char) read);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}