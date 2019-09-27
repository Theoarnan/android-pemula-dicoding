package com.example.barvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtWidth;
    private EditText edtLength;
    private EditText edtHeight;
    private Button btnCalculate;
    private TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

        if(savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate){
            String inputLength = edtLength.getText().toString().trim();
            String inputHeigth = edtHeight.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble  = false;

            if (TextUtils.isEmpty(inputLength)){
                isEmptyFields = true;
                edtLength.setError("Ini tidak boleh Kosong!!");
            }

            if (TextUtils.isEmpty(inputHeigth)){
                isEmptyFields = true;
                edtHeight.setError("Ini tidak boleh Kosong!!");
            }

            if (TextUtils.isEmpty(inputWidth)){
                isEmptyFields = true;
                edtWidth.setError("Ini tidak boleh Kosong!!");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double heigth = toDouble(inputHeigth);

            if (length == null){
                isInvalidDouble = true;
                edtLength.setError("Harus nomer yang Valid!!");
            }

            if (width == null){
                isInvalidDouble = true;
                edtWidth.setError("Harus nomer Valid!!");
            }

            if (heigth == null){
                isInvalidDouble = true;
                edtHeight.setError("Nomer Harus Valid!!");
            }

            if (!isEmptyFields && !isInvalidDouble){

                double volume = length * width * heigth;
                tvResult.setText(String.valueOf(volume));
            }

        }
    }

    private Double toDouble(String str){
        try {
            return Double.valueOf(str);
        }catch (NumberFormatException e){
            return null;
        }
    }
}
