package com.xtagwgj.dbindingsample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xtagwgj.dbindingsample.databinding.ActivityCustomBinding
import com.xtagwgj.dbindingsample.widget.CustomSeekBar
import org.jetbrains.anko.toast

/**
 *
 * Created by xtagwgj on 2017/8/26.
 */
class CustomActivity : AppCompatActivity() {

    //seekBar的监听器
    private val seekListener = CustomSeekBar.OnSeekChangeListener { seekBar, index ->
        toast("$index")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityCustomBinding>(this, R.layout.activity_custom)

        var seekIndex = 1

        binding.seekIndex = seekIndex
        binding.seekListener = seekListener
    }

}