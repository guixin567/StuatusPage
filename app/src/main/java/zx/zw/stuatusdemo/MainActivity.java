package zx.zw.stuatusdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zx.statuslib.BaseStatusPage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private View           mContainerView;
    private BaseStatusPage mStatusDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initStatus();
    }



    private void initView() {
        mContainerView = findViewById(R.id.container);
        findViewById(R.id.tv_loadding).setOnClickListener(this);
        findViewById(R.id.tv_error).setOnClickListener(this);
        findViewById(R.id.tv_nodata).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_loadding:
                initLoading();
                break;
            case R.id.tv_error:
                initError();
                break;
            case R.id.tv_nodata:
                initNodata();
                break;
        }
    }

    private void initNodata() {
        mStatusDialog.setType(StatusDialog.TYPE_NODATA) .show(getSupportFragmentManager());
    }

    private void initError() {
        mStatusDialog.setType(StatusDialog.TYPE_ERROR)
        .show(getSupportFragmentManager());

    }

    private void initLoading() {
        mStatusDialog.setType(StatusDialog.TYPE_LOADING) .show(getSupportFragmentManager());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mStatusDialog.dismiss();
                mContainerView.setVisibility(View.VISIBLE);
            }
        }, 2000);


    }

    /**
     * 在Activity中初始化状态页面
     * 设置状态页替换的View
     * 添加状态点击事件
     * 1，无数据
     * 2，网络错误
     * 3，无数据可能分无订单数据，无商品数据，无优惠券
     * 这个我们可以在getLayout方法中添加相应的类型，然后在状态点击事件里面列出需要的类型就可以了
     *
     */
    private void initStatus() {
        mStatusDialog = new StatusDialog()
                .setContainer(mContainerView)
                .addStatusListener(new BaseStatusPage.OnStatusListener() {
                    @Override
                    public void onStatus(int type) {
                        switch (type){
                            case StatusDialog.TYPE_ERROR:
                                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                                break;
                            case StatusDialog.TYPE_NODATA:
                                Toast.makeText(MainActivity.this, "NODATA", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });


    }
}
