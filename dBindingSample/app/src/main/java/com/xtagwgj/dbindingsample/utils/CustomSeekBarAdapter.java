package com.xtagwgj.dbindingsample.utils;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;

import com.xtagwgj.dbindingsample.widget.CustomSeekBar;

/**
 * 自定义拖动条的dataBinding设置
 * Created by xtagwgj on 2017/8/25.
 * <p>
 * InverseBindingMethods：反向绑定方法,用来确定怎么去监听view属性的变化和回调哪一个getter方法
 */
@InverseBindingMethods({
        @InverseBindingMethod(type = CustomSeekBar.class, attribute = "cs_selected_index", method = "getSelectedIndex")
})
public class CustomSeekBarAdapter {
    /**
     * 使用BindingAdapter注解设置与第三步中event值相同的回调函数的setter函数
     *
     * @param seekBar         控件的view
     * @param inverseListener 双向绑定的回调，告诉视图已经发生变化
     *                        <p>
     *                        <p>
     *                        NOTE：
     *                        双向绑定必须的
     *                        OnSeekChangeListener可直接在xml中设置，不需要写在attr中，
     */
    @BindingAdapter(value = {"OnSeekChangeListener", "cs_selected_indexAttrChanged"}, requireAll = false)
    public static void setOnSeekChangeListener(CustomSeekBar seekBar,
                                               final CustomSeekBar.OnSeekChangeListener onSeekChangeListener,
                                               final InverseBindingListener inverseListener) {

        seekBar.setOnSeekChangeListener(
                new CustomSeekBar.OnSeekChangeListener() {
                    @Override
                    public void onChange(CustomSeekBar seekBar1, int pos) {

                        if (onSeekChangeListener != null)
                            onSeekChangeListener.onChange(seekBar1, pos);

                        if (inverseListener != null)
                            inverseListener.onChange();

                    }
                });
    }

    /**
     * 使用BindingAdapter注解设置cs_selected_index属性的setter函数，注解参数value是在xml中对应的属性名
     *
     * @param seekBar       控件的view
     * @param selectedIndex 需要设置的属性值。
     */
    @BindingAdapter(value = "cs_selected_index")
    public static void setIndex(CustomSeekBar seekBar, int selectedIndex) {
        if (seekBar.getSelectedIndex() != selectedIndex)//为了防止设置数据时的死循环
            seekBar.setSelectedIndex(selectedIndex);
    }
}