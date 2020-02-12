package com.example.desafiomobile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.BitSet;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

public class ReceiptActivity extends AppCompatActivity  {

    Activity thisActivity;

    Button btn_confirm;
    TextView textViewPayment;
    TextView textViewValue;
    TextView textViewDate;

    private static final int STORAGE_PERMISSION_CODE = 101;
    private Bitmap bmp;
    private Object pad;
    private BitSet stream;
    private File fileImagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        thisActivity = this;

        Bundle extras = getIntent().getExtras();;

        textViewPayment = findViewById(R.id.textViewPayment);
        textViewPayment.setText(extras.getString("paymentMethod"));

        textViewValue = findViewById(R.id.textViewValue);
        textViewValue.setText(extras.getString("value"));

        textViewDate = findViewById(R.id.textViewDate);
        textViewDate.setText(extras.getString("date"));

        btn_confirm = (Button) findViewById(R.id.btn_confirm);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                getSupportActionBar().setTitle("PAGAMENTO REALIZADO COM SUCESSO");
                screenshoot();
            }
        });
    }

    private void screenshoot(){
        Date date = new Date();
        CharSequence now = android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", date);
        String filename = Environment.getExternalStorageDirectory() + "/Screenshooter/" + now + ".jpg";

        View root = getWindow().getDecorView();
        root.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(root.getDrawingCache());
        root.setDrawingCacheEnabled(false);

        File file = new File(filename);
        file.getParentFile().mkdirs();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            Uri uri = FileProvider.getUriForFile(ReceiptActivity.this, BuildConfig.APPLICATION_ID + ".provider",file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "image/*");

            startActivity(intent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }

}
