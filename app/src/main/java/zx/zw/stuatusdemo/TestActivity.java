package zx.zw.stuatusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mTvEnter = (TextView) findViewById(R.id.tv_enter);

        mTvEnter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_enter:
                startActivity(new Intent(TestActivity.this,MainActivity.class));
                break;
        }
    }
}
