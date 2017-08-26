package com.xtagwgj.dbindingsample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xtagwgj.dbindingsample.databinding.ActivityEdittextBinding
import com.xtagwgj.dbindingsample.entity.Student

/**
 * 双向绑定
 */
class EditTextActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityEdittextBinding>(this, R.layout.activity_edittext)

        binding.student = Student(201)
        binding.hintText = "请输入学生姓名"
//        binding.textSize = 66F
        binding.textSize=resources.getDimension(R.dimen.textSize)
    }


}