package com.codepath.queridacamara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText etDescription;
    Button btnTakePicture;
    ImageView ivPostPicture;
    Button btnPostPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDescription.findViewById(R.id.etDescription);
        btnTakePicture.findViewById(R.id.btnTakePicture);
        ivPostPicture.findViewById(R.id.ivPostPicture);
        btnPostPicture.findViewById(R.id.btnTakePicture);
    }
}
