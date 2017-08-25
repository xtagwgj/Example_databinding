package com.xtagwgj.dbindingsample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xtagwgj.dbindingsample.databinding.ActivitySimpleBinding
import com.xtagwgj.dbindingsample.entity.Student
import com.xtagwgj.dbindingsample.entity.Teacher

/**
 * 最简单的DataBinding的使用
 */
class SimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //使用DataBindingUtil.setContentView方法替代setContentView
        //binding的类型为布局文件的名称去掉下划线，首字母大写+Binding，是有系统自动生成的。所以写完布局文件最好Rebuild一次项目
        val binding = DataBindingUtil.setContentView<ActivitySimpleBinding>(this, R.layout.activity_simple)

        //初始化数据并绑定
        binding.student = Student(2)
        //绑定数据的另外一种方式
        //binding.setVariable(BR.student,Student(3))

        binding.teacher = Teacher(0)
    }
}