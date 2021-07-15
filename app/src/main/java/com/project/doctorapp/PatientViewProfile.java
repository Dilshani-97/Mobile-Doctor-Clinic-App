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


public class PatientViewProfile extends AppCompatActivity {

    TextView Name,ContactNo,Email,DOB,Address,Gender;
    Button edit,delete;
    Dialog dialog;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Patient");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_profile);

        edit = findViewById(R.id.patientProfileEdit);
        delete = findViewById(R.id.patientProfileDelete);

        dialog = new Dialog(PatientViewProfile.this);
        dialog.setContentView(R.layout.patient_delete_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_dialog));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        Button confirm = dialog.findViewById(R.id.okpatientbutton);
        Button cancel = dialog.findViewById(R.id.cancelpatientbutton);

        Name = findViewById(R.id.patientName);
        ContactNo=findViewById(R.id.patientPhone);
        Email = findViewById(R.id.patientEmail);
        DOB = findViewById(R.id.patientDOB);
        Address = findViewById(R.id.patientAddress);
        Gender = findViewById(R.id.patientGender);

        Intent profileIntent = getIntent();

        String PatientName = profileIntent.getStringExtra("_patientName");
        String PatientContactNo = profileIntent.getStringExtra("_patientcontactNo");
        String PatientEmail = profileIntent.getStringExtra("_patientemail");
        String PatientDOB = profileIntent.getStringExtra("_patientdob");
        String PatientAddress = profileIntent.getStringExtra("_patientaddress");
        String PatientGender = profileIntent.getStringExtra("_patientgender");

        Name.setText(PatientName);
        ContactNo.setText(PatientContactNo);
        Email.setText(PatientEmail);
        DOB.setText(PatientDOB);
        Address.setText(PatientAddress);
        Gender.setText(PatientGender);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(PatientName).removeValue();
                Toast.makeText(PatientViewProfile.this, "Patient profile Deleted!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Intent deleteintent = new Intent(PatientViewProfile.this,app1page.class);
                startActivity(deleteintent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PatientViewProfile.this, "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editpatientintent = new Intent(PatientViewProfile.this,patientEditProfile.class);
                editpatientintent.putExtra("_patientName",PatientName);
                editpatientintent.putExtra("_patientcontactNo",PatientContactNo);
                editpatientintent.putExtra("_patientemail",PatientEmail);
                editpatientintent.putExtra("_patientdob",PatientDOB);
                editpatientintent.putExtra("_patientaddress",PatientAddress);
                editpatientintent.putExtra("_patientgender",PatientGender);
                startActivity(editpatientintent);
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