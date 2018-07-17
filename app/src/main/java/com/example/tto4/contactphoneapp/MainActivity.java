package com.example.tto4.contactphoneapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tto4.contactphoneapp.adapter.ContactAdapter;
import com.example.tto4.contactphoneapp.adapter.DBManager;
import com.example.tto4.contactphoneapp.model.Contact;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Contact> arrayContacts;
    private ContactAdapter adapter;
    private EditText edtName, edtNumber;
    private RadioButton rbIsMale, rbIsFemale;
    private Button btnAddContact;
    private ListView lvContacts;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        btnAddContact.setOnClickListener(this);
        arrayContacts = new ArrayList<Contact>();
        //getAllContact();
        adapter = new ContactAdapter(this, R.layout.item_contact_listview, arrayContacts);
        lvContacts.setAdapter(adapter);
        checkAndRequestPermission();
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                showDialogConfirm(position);
            }
        });
    }

    private void getWidget() {
        edtName = (EditText) findViewById(R.id.edt_name);
        edtNumber = (EditText) findViewById(R.id.edt_number);
        rbIsMale = (RadioButton) findViewById(R.id.rb_male);
        rbIsFemale = (RadioButton) findViewById(R.id.rb_female);
        btnAddContact = (Button) findViewById(R.id.btn_add_contact);
        lvContacts = (ListView) findViewById(R.id.lv_contact);
    }

    public void checkAndRequestPermission() {
        String[] permissions = new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS};
        List<String> listPermissions = new ArrayList<>();
        for (String per : permissions) {
            if (ContextCompat.checkSelfPermission(this, per) != PackageManager.PERMISSION_GRANTED) {
                listPermissions.add(per);
            }
        }
        if (!listPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(MainActivity.this, listPermissions.toArray(new String[listPermissions.size()]), 1);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_contact:
                addContact();
                break;
        }
    }

    private void addContact(){
        dbManager = new DBManager(this);
        String name = edtName.getText().toString().trim();
        String number = edtNumber.getText().toString().trim();
        boolean isMale = true;
        if (rbIsMale.isChecked()) {
            isMale = true;
        } else {
            isMale = false;
        }
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number)) {
            Contact contact = new Contact( name, number, isMale);
            //dbManager.addContact(contact);
            arrayContacts.add(contact);
            //arrayContacts = dbManager.getAllContact();
            Toast.makeText(this, "Add Successfully!", Toast.LENGTH_SHORT).show();
            edtName.setText("");
            edtNumber.setText("");
            rbIsMale.setChecked(true);
        } else {
            Toast.makeText(this, "Please input Name or Number!", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }

    private void getAllContact(){
        dbManager = new DBManager(this);
        arrayContacts = dbManager.getAllContact();
        //adapter.notifyDataSetChanged();
    }

    public void showDialogConfirm(final int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
        Button btnSendMess = (Button) dialog.findViewById(R.id.btn_send_mess);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Call", Toast.LENGTH_SHORT).show();
                intentCall(position);
            }
        });
        btnSendMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Send Message", Toast.LENGTH_SHORT).show();
                intentSendMess(position);
            }
        });
        dialog.show();
    }

    public void intentCall(int position) {

        Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("tel: " + arrayContacts.get(position).getmNumber()));
        startActivity(i);

//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_DIAL);
//        //Log.d("TAG", "intentCall: " + arrayContacts.get(position).getmNumber());
//        intent.setData(Uri.parse("Tel:" + arrayContacts.get(position).getmNumber()));
//        //intent.setData(Uri.parse("Tel:" +"01687067898"));
//        startActivity(intent);

//        String contact_number = "123456789";
//        Intent callIntent = new Intent(Intent.ACTION_DIAL);
//        callIntent.setPackage("com.android.phone");
//        callIntent.setData(Uri.parse("tel:" + contact_number));
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
        //startActivity(callIntent);
    }
    public void intentSendMess(int position) {
//        Intent intent = new Intent(android.content.Intent.ACTION_SEND,Uri.parse("Tel:" + arrayContacts.get(position).getmNumber()));
//        startActivity(intent);

        Intent i = new Intent(android.content.Intent.ACTION_SEND, Uri.parse("tel: " + arrayContacts.get(position).getmNumber()));
        startActivity(i);
    }
}
