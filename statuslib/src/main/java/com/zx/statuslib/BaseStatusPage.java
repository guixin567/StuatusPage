package com.zx.statuslib;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author zxKueen 2018/1/18 22:37
 *         email 4994766@qq.com
 */
public abstract class BaseStatusPage extends AppCompatDialogFragment {
    protected int mStatusLayout;
    private OnStatusListener mListener;
    private View mContainerView;
    protected int mType;
    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置无透明度
        lp.dimAmount = 0;
        Display display = window.getWindowManager().getDefaultDisplay();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置宽度为全屏
        lp.width = display.getWidth();
        window.setAttributes(lp);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置背景为透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ViewGroup view = null;
        if(mStatusLayout!=0){
             view = (ViewGroup) inflater.inflate(mStatusLayout, container);
            initView(view);
        }


        //当返回键时，要关闭当前Activity，如果不设置，当按返回键只会将dialog销毁，当前activity不会销毁
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK){
                    getActivity().finish();
                    return true;

                }else{
                    return false;
                }
            }
        });

        return view;
    }


    /**
     * 设置点击事件，控件的ID必须为click
     * 注意不能将ProgressBar的id设置为click
     * @param view
     */
    private void initView(View view) {
        if(getClickId()!=0){
            view.findViewById(getClickId()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onStatus(mType);
                    BaseStatusPage.this.dismiss();
                    //状态页替换的视图在点击后显示
                    if (mContainerView != null) {
                        mContainerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


    }

    /**
     * 拥有点击事件的控件ID
     * @return
     */
    protected abstract int getClickId();

    /**
     * 状态视图需要加载的布局
     * @return
     */
    public abstract BaseStatusPage getLayout();

    /**
     * 设置状态页类型，如网络错误，无数据
     * @param type
     * @return
     */
    public BaseStatusPage setType(int type){
        mType = type;
        //设置完状态直接调用获取相应的视图，不用再使用的时候再次调用
        getLayout();
        return this;
    }

    /**
     * 设置状态替换的视图
     * @param containerView
     * @return
     */
    public BaseStatusPage setContainer(View containerView){
        mContainerView = containerView;
        return this;
    };


    public BaseStatusPage show(FragmentManager manager) {
        //状态页替换的视图在显示状态页时隐藏
        if (mContainerView != null) {
            mContainerView.setVisibility(View.GONE);
        }
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        ft.add(this, String.valueOf(System.currentTimeMillis()));
        ft.commitAllowingStateLoss();
        return this;
    }

    /**
     * 设置监听事件
     * @param listener
     */
    public BaseStatusPage addStatusListener(OnStatusListener listener) {
        mListener = listener;
        return this;
    }

    public abstract static class OnStatusListener{
        /**
         * 确定按钮
         */
        public abstract void onStatus(int type);

    }


}
