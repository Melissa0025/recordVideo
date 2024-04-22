package com.example.recordvideo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{
    ImageView vid;
    Uri videoUri;
    Intent vidIntent;
    int REQUEST_CODE_VIDEO_CAPTURE = 2607;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit = (Button) findViewById(R.id.button);
        EditText txt = (EditText) findViewById(R.id.name);
        EditText txt1 = (EditText) findViewById(R.id.place);

        vid = (ImageView) findViewById(R.id.vid);

        vid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                if(intent.resolveActivity(getPackageManager()) != null)
                {
                    startActivityForResult(intent,REQUEST_CODE_VIDEO_CAPTURE);
                }

            }
        });

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name = txt.getText().toString().trim();
                String place = txt1.getText().toString().trim();

                dataholder obj = new dataholder(name,place);

//                FirebaseDatabase db = FirebaseDatabase.getInstance();

                //DatabaseReference node = db.getReference("students");
                DatabaseReference node;

                node = FirebaseDatabase.getInstance().getReference();

                String userId = node.push().getKey();

                node.child("students").child(userId).child("name").setValue(name);
                node.child("students").child(userId).child("place").setValue(place);
                txt.setText("");
                txt1.setText("");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_VIDEO_CAPTURE && resultCode ==RESULT_OK)
        {
            Uri videoUri = data.getData();

            Intent vidIntent=new Intent(this,MainActivity2.class);

            vidIntent.putExtra("uri",videoUri.toString());

            startActivity(vidIntent);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}