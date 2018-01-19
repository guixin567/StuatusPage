package zx.zw.stuatusdemo;

import com.zx.statuslib.BaseStatusPage;

/**
 * @author zxKueen 2018/1/18 23:03
 *         email 4994766@qq.com
 */
public class StatusDialog extends BaseStatusPage {

    /**
     * 加载中
     */
    public static final int TYPE_LOADING = 1;
    /**
     * 加载失败
     */
    public static final int TYPE_ERROR = 2;
    /**
     * 无数据
     */
    public static final int TYPE_NODATA = 3;


    /**
     * 每个状态页的布局文件中只能有一个可以点击的控件
     * 并且每个布局文件的可点击控件的ID必须为该ID
     * 指定该控件的ID
     * @return
     */
    @Override
    protected int getClickId() {
        return R.id.click;
    }

    @Override
    public BaseStatusPage getLayout() {
        switch (mType){
            case TYPE_LOADING:
                mStatusLayout = R.layout.dialog_loadding;
                break;
            case TYPE_ERROR:
                mStatusLayout = R.layout.dialog_error;
                break;
            case TYPE_NODATA:
                mStatusLayout = R.layout.dialog_nodata;
                break;
            default:
                break;
        }
        return this;
    }


}
