package com.xtagwgj.dbindingsample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.xtagwgj.dbindingsample.databinding.ActivityImageBinding
import org.jetbrains.anko.toast

/**
 * 图片
 * Created by xtagwgj on 2017/8/26.
 */
class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityImageBinding>(this, R.layout.activity_image)
        binding.url = "http://img.ivsky.com/img/tupian/pre/201707/27/shenqideyanjingtupian-001.jpg"
        //将activity传递给view
        binding.activity = this
    }

    //定义一个点击回调的方法,点击图片显示图片的地址信息
    //有view返回的
    fun imageClickWithView(view: View, url: String) {
        toast("$view $url")
    }

    //无view返回的
    fun imageClick(url: String) {
        toast(url)
    }

}