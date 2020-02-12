package com.example.desafiomobile;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;

    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_0;
    Button btn_backspace;
    Button btn_comma;
    Button btn_dot_purple;
    Button btn_dot_gray;

    ImageButton button_money;
    ImageButton button_credit;
    ImageButton button_debit;
    ImageButton button_vr;
    ImageButton button_cupom;

    TextView tv;

    HorizontalScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // Para o layout preencher toda tela do cel (remover a barra de tit.)
        getSupportActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_backspace = (Button) findViewById(R.id.btn_backspace);
        btn_comma = (Button) findViewById(R.id.btn_comma);
        btn_dot_purple = (Button) findViewById(R.id.btn_purple);
        btn_dot_gray = (Button) findViewById(R.id.btn_gray);
        button_money = (ImageButton) findViewById(R.id.button_money);
        button_money.setOnClickListener((View.OnClickListener) this);
        button_credit = (ImageButton) findViewById(R.id.button_credit);
        button_credit.setOnClickListener((View.OnClickListener) this);
        button_debit = (ImageButton) findViewById(R.id.button_debit);
        button_debit.setOnClickListener((View.OnClickListener) this);
        button_vr = (ImageButton) findViewById(R.id.button_vr);
        button_vr.setOnClickListener((View.OnClickListener) this);
        button_cupom = (ImageButton) findViewById(R.id.button_cupom);
        button_cupom.setOnClickListener((View.OnClickListener) this);
        tv = (TextView) findViewById(R.id.tv);
        scrollView = (HorizontalScrollView) findViewById(R.id.horizon);

        buttonWritesOnTextView(btn_1, tv, "1");
        buttonWritesOnTextView(btn_2, tv, "2");
        buttonWritesOnTextView(btn_3, tv, "3");
        buttonWritesOnTextView(btn_4, tv, "4");
        buttonWritesOnTextView(btn_5, tv, "5");
        buttonWritesOnTextView(btn_6, tv, "6");
        buttonWritesOnTextView(btn_7, tv, "7");
        buttonWritesOnTextView(btn_8, tv, "8");
        buttonWritesOnTextView(btn_9, tv, "9");
        buttonWritesOnTextView(btn_0, tv, "0");
        buttonWritesOnTextView(btn_comma, tv, ",");
        buttonClearsTextView(btn_backspace, tv);



        scrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_dot_purple.setBackgroundResource(R.drawable.dotgray);
                    btn_dot_gray.setBackgroundResource(R.drawable.dotpurple);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_dot_purple.setBackgroundResource(R.drawable.dotpurple);
                    btn_dot_gray.setBackgroundResource(R.drawable.dotgray);
                }
                int topDetector = scrollView.getScrollX();
                int bottomDetector = scrollView.getBottom() - (scrollView.getWidth() + scrollView.getScrollX()) - 10;
                if (bottomDetector == 0) {
                    btn_dot_purple.setBackgroundResource(R.drawable.dotpurple);
                    btn_dot_gray.setBackgroundResource(R.drawable.dotgray);
                }
                if (topDetector <= 0) {
                    btn_dot_purple.setBackgroundResource(R.drawable.dotgray);
                    btn_dot_gray.setBackgroundResource(R.drawable.dotpurple);
                }
                return false;
            }
        });
    }

    public static void buttonWritesOnTextView(Button btn, final TextView tv, final String text) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv.getText().length() <= 6) {
                    tv.setText(tv.getText() + text);
                }

            }
        });
    }

    public static void buttonClearsTextView(Button btn, final TextView tv) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        Date date = new Date();
        String dateFormat = simpleDateFormat.format(date);

        Intent intent = new Intent(this, ReceiptActivity.class);
        intent.putExtra("value", tv.getText());
        intent.putExtra("date", dateFormat);

        if(v.getId() == R.id.button_money){
            intent.putExtra("paymentMethod", "Dinheiro");
        } else if
            (v.getId() == R.id.button_credit){
            intent.putExtra("paymentMethod", "Crédito");
        } else if
            (v.getId() == R.id.button_debit){
            intent.putExtra("paymentMethod", "Débito");
        }else if
            (v.getId() == R.id.button_vr){
            intent.putExtra("paymentMethod", "V.R.");
        } else if
            (v.getId() == R.id.button_cupom){
            intent.putExtra("paymentMethod", "Cupom");

        }
        startActivity(intent);
    }


}

