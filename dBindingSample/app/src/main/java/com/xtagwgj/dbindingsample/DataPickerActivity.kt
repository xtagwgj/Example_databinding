package com.xtagwgj.dbindingsample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xtagwgj.dbindingsample.databinding.DataPickerActivityBinding

/**
 * 日期选择
 * Created by xtagwgj on 2017/8/26.
 */
class DataPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<DataPickerActivityBinding>(this, R.layout.data_picker_activity)

        var day = 26
        var month = 8
        var year = 2017
        var hour = 14
        var minute = 52


        binding.year = year
        binding.month = month
        binding.day = day
        binding.hour = hour
        binding.minute = minute

    }

}