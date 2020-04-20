package com.nguyenminhngan.baitaprenluyenmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;

import com.nguyenminhngan.adapter.NhanVienAdapter;
import com.nguyenminhngan.baitaprenluyenmenu.databinding.ActivityMainBinding;
import com.nguyenminhngan.model.NhanVien;

import java.net.NoRouteToHostException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvNhanVien;
    NhanVienAdapter nhanVienAdapter;
    ActivityMainBinding binding;
    NhanVien selectedNV = null;
    ArrayList<NhanVien> dsNguon = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addControl();
        addEvents();
    }

    private void addEvents() {
        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNV = nhanVienAdapter.getItem(position);
            }
        });
        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNV=nhanVienAdapter.getItem(position);
                return false;
            }
        });
    }

    private void addControl() {
        lvNhanVien = binding.lvNhanVien;
        nhanVienAdapter = new NhanVienAdapter(MainActivity.this,R.layout.nhanvien_item);
        lvNhanVien.setAdapter(nhanVienAdapter);
        registerForContextMenu(lvNhanVien);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem mnuSearch =menu.findItem(R.id.mnuSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(mnuSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()){
                    nhanVienAdapter.clear();
                    nhanVienAdapter.addAll(dsNguon);
                }
                else{
                ArrayList<NhanVien> dsTim = new ArrayList<NhanVien>();
                for (NhanVien nv: dsNguon){
                    if (nv.getMa().contains(newText)||nv.getTen().contains(newText))
                        dsTim.add(nv);
                }
                nhanVienAdapter.clear();
                nhanVienAdapter.addAll(dsTim);
            }
                return false;}
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnuSua){
            hienThiManHinhEdit();
        }
        else xuLyhXoa();
        return super.onContextItemSelected(item);
    }

    private void xuLyhXoa() {
        nhanVienAdapter.remove(selectedNV);
    }

    private void hienThiManHinhEdit() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.detail_layout);
        final EditText ma = (EditText) dialog.findViewById(R.id.edtMa);
        final EditText ten = dialog.findViewById(R.id.edtTen);
        RadioButton radMale = dialog.findViewById(R.id.radNam);
        final RadioButton radFemale = dialog.findViewById(R.id.radNu);
        Button btnLuu = dialog.findViewById(R.id.btnLuu);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien=new NhanVien();
                nhanVien.setMa(ma.getText().toString());
                nhanVien.setTen(ten.getText().toString());
                nhanVien.setGioitinh(radFemale.isChecked());
                nhanVienAdapter.insert(nhanVien,nhanVienAdapter.getPosition(selectedNV));
                nhanVienAdapter.remove(selectedNV);
                dialog.dismiss();
                dsNguon.clear();
                for (int i=0;i<nhanVienAdapter.getCount();i++){
                    dsNguon.add(nhanVienAdapter.getItem(i));
                }
            }
        });
        ma.setText(selectedNV.getMa());
        ten.setText(selectedNV.getTen());
        if (selectedNV.getGioitinh())
            radFemale.setChecked(true);
        else radMale.setChecked(true);
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuAbout:
                break;
            case R.id.mnuHelp:
                break;
            case R.id.mnuNew:
                hienThiManHinhNhapNhanVien();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hienThiManHinhNhapNhanVien() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.detail_layout);
        final EditText ma = (EditText) dialog.findViewById(R.id.edtMa);
        final EditText ten = dialog.findViewById(R.id.edtTen);
        RadioButton radMale = dialog.findViewById(R.id.radNam);
        final RadioButton radFemale = dialog.findViewById(R.id.radNu);
        Button btnLuu = dialog.findViewById(R.id.btnLuu);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien=new NhanVien();
                nhanVien.setMa(ma.getText().toString());
                nhanVien.setTen(ten.getText().toString());
                nhanVien.setGioitinh(radFemale.isChecked());
                nhanVienAdapter.add(nhanVien);
                dsNguon.clear();
                dialog.dismiss();
                for (int i=0;i<nhanVienAdapter.getCount();i++){
                    dsNguon.add(nhanVienAdapter.getItem(i));
                }

            }
        });
        dialog.show();
    }
}
