package com.nguyenminhngan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenminhngan.baitaprenluyenmenu.R;
import com.nguyenminhngan.model.NhanVien;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int resource;
    public NhanVienAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View custom = this.context.getLayoutInflater().inflate(this.resource,null);
        ImageView imgAva= (ImageView) custom.findViewById(R.id.imgAva);
        TextView txtTen=(TextView) custom.findViewById(R.id.txtMa);
        TextView txtMa=(TextView) custom.findViewById(R.id.txtTen);
        NhanVien nhanVien=getItem(position);
        if (nhanVien.getGioitinh())
            imgAva.setImageResource(R.drawable.female);
        else
            imgAva.setImageResource(R.drawable.male);
        txtMa.setText(nhanVien.getMa());
        txtTen.setText(nhanVien.getTen());
        return custom;
    }
}
