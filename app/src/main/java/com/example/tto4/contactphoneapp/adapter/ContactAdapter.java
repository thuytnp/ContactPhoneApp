package com.example.tto4.contactphoneapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tto4.contactphoneapp.R;
import com.example.tto4.contactphoneapp.model.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact>{

    private Context context;
    private int resource;
    private List<Contact> arrayContacts;

    public ContactAdapter(@NonNull Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayContacts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHoilder;
        if(convertView == null){
            viewHoilder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact_listview, parent, false);
            viewHoilder.ivAvatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            viewHoilder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHoilder.tvNumber = (TextView) convertView.findViewById(R.id.tv_phone);

            convertView.setTag(viewHoilder);
        }else{
            viewHoilder = (ViewHolder) convertView.getTag();
        }
        Contact contact = arrayContacts.get(position);
        viewHoilder.tvName.setText(contact.getmName());
        viewHoilder.tvNumber.setText(contact.getmNumber());

        if(contact.isMale()){
            viewHoilder.ivAvatar.setBackgroundResource(R.drawable.ic_male);
        }else{
            viewHoilder.ivAvatar.setBackgroundResource(R.drawable.ic_female);
        }

        return convertView;
    }

    public class ViewHolder{
        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvNumber;

    }
}
