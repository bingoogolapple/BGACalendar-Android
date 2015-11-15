package cn.bingoogolapple.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/11/15 下午1:43
 * 描述:
 */
public class BGACalendarLayout extends LinearLayout implements View.OnClickListener {
    private TextView mYearTv;
    private TextView mMonthTv;
    private BGAMonthView mMonthView;

    public BGACalendarLayout(Context context) {
        this(context, null);
    }

    public BGACalendarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.layout_calandar, this);

        initView();
        setListener();
        initAttrs(context, attrs);
        processLogic();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BGACalendarLayout);
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    protected void initView() {
        mYearTv = getViewById(R.id.tv_layout_calandar_year);
        mMonthTv = getViewById(R.id.tv_layout_calandar_month);
        mMonthView = getViewById(R.id.mv_layout_calandar_month);
    }

    protected void setListener() {
        getViewById(R.id.tv_layout_calandar_last_month).setOnClickListener(this);
        getViewById(R.id.tv_layout_calandar_next_month).setOnClickListener(this);
    }

    protected void initAttr(int attr, TypedArray typedArray) {

    }

    protected void processLogic() {
        mYearTv.setText("2015年");
        mMonthTv.setText("十一月");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_layout_calandar_last_month) {
            Toast.makeText(getContext(), "上一个月", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.tv_layout_calandar_next_month) {
            Toast.makeText(getContext(), "下一个月", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }
}