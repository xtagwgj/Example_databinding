package com.xtagwgj.dbindingsample.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * DataBinding的适配器
 * Created by xtagwgj on 2017/8/26.
 */

public class BindingUtil {

    /**
     * @param view          imageView
     * @param url           图片地址
     * @param errorDrawable 错误时候的drawable
     */
    @BindingAdapter({"imageUrl", "errorDrawable"})
    public static void loadImage(ImageView view, String url, Drawable errorDrawable) {
        Picasso.with(view.getContext()).load(url).error(errorDrawable).into(view);
    }

}
