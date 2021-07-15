package com.project.doctorapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DoctorViewProfile extends AppCompatActivity {

    TextView Name,ContactNo,Email,DOB,Address,Gender;
    Button edit,delete;
    Dialog dialog;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Doctor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_profile);
        edit = findViewById(R.id.docProfileEdit);
        delete = findViewById(R.id.docProfileDelete);

        dialog = new Dialog(DoctorViewProfile.this);
        dialog.setContentView(R.layout.doctor_delete_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_dialog));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button confirm = dialog.findViewById(R.id.okdoctorbutton);
        Button cancel = dialog.findViewById(R.id.canceldoctorbutton);


        Name = findViewById(R.id.docName);
        ContactNo=findViewById(R.id.docPhone);
        Email = findViewById(R.id.docEmail);
        DOB = findViewById(R.id.docDOB);
        Address = findViewById(R.id.docAddress);
        Gender = findViewById(R.id.docGender);

        Intent profileIntent = getIntent();

        String DoctorName = profileIntent.getStringExtra("_docName");
        String DoctorContactNo = profileIntent.getStringExtra("_contactNo");
        String DoctorEmail = profileIntent.getStringExtra("_email");
        String DoctorDOB = profileIntent.getStringExtra("_dob");
        String DoctorAddress = profileIntent.getStringExtra("_address");
        String DoctorGender = profileIntent.getStringExtra("_gender");

        Name.setText(DoctorName);
        ContactNo.setText(DoctorContactNo);
        Email.setText(DoctorEmail);
        DOB.setText(DoctorDOB);
        Address.setText(DoctorAddress);
        Gender.setText(DoctorGender);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(DoctorName).removeValue();
                Toast.makeText(DoctorViewProfile.this, "Doctor profile Deleted!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Intent deleteintent = new Intent(DoctorViewProfile.this,app1page.class);
                startActivity(deleteintent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DoctorViewProfile.this, "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editintent = new Intent(DoctorViewProfile.this,doctorEditProfile.class);
                editintent.putExtra("_doctorName",DoctorName);
                editintent.putExtra("_contactNo",DoctorContactNo);
                editintent.putExtra("_email",DoctorEmail);
                editintent.putExtra("_dob",DoctorDOB);
                editintent.putExtra("_address",DoctorAddress);
                editintent.putExtra("_gender",DoctorGender);
                startActivity(editintent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
}