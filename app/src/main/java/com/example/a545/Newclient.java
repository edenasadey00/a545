package com.example.a545;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Newclient extends AppCompatActivity {

    EditText editName, editPhone, editAge, editAddress;
    Button btnAdd;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newclient);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        editName = findViewById(R.id.textView4);
        editPhone = findViewById(R.id.textView6);
        editAge = findViewById(R.id.textView7);
        editAddress = findViewById(R.id.textView5);
        btnAdd = findViewById(R.id.button4);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    private void addData() {
        String name = editName.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String age = editAge.getText().toString().trim();
        String address = editAddress.getText().toString().trim();

        Map<String, Object> client = new HashMap<>();
        client.put("Name", name);
        client.put("Phone", phone);
        client.put("Age", age);
        client.put("Address", address);
        db.collection("Clients")
                .add(client)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Newclient.this, "Client added successfully!", Toast.LENGTH_SHORT).show();
                        editName.setText("");
                        editPhone.setText("");
                        editAge.setText("");
                        editAddress.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Newclient.this, "Error adding client: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
