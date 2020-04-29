package com.ihidea.as.citypicker.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ihidea.as.citypicker.R;
import com.lljjcoder.style.citythreelist.CityBean;
import com.lljjcoder.style.citythreelist.ProvinceActivity;

public class CitypickerThreeListActivity extends AppCompatActivity {
    TextView mListTv;
    
    TextView mResultTv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citypicker_three_list);
        findView();
    }
    
    private void findView() {
        mListTv = (TextView) findViewById(R.id.list_tv);
        mResultTv = (TextView) findViewById(R.id.result_tv);
        
        mListTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list();
            }
        });
    }
    
    public void list() {

        Intent intent = new Intent(CitypickerThreeListActivity.this, ProvinceActivity.class);
        startActivityForResult(intent, ProvinceActivity.RESULT_DATA);
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProvinceActivity.RESULT_DATA) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                
                CityBean area = data.getParcelableExtra("area");
                CityBean city = data.getParcelableExtra("city");
                CityBean province = data.getParcelableExtra("province");
                
                mResultTv.setText("所选省市区城市： " + province.getName() + " " + province.getId() + "\n" + city.getName()
                        + " " + city.getId() + "\n" + area.getName() + " " + area.getId() + "\n");
            }
        }
    }
}
