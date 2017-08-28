package com.xtagwgj.dbindingsample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewStub
import com.xtagwgj.dbindingsample.databinding.ActivityIncludeBinding
import com.xtagwgj.dbindingsample.databinding.StubViewBinding
import com.xtagwgj.dbindingsample.entity.Student

/**
 * Created by xtagwgj on 2017/8/28.
 */
class IncludeActivity : AppCompatActivity() {

    private val stu = Student(101)

    lateinit var binding: ActivityIncludeBinding

    private val listener = View.OnClickListener {
        stu.money.set(Math.random())

        if (!binding.viewStub.isInflated)
        //显示ViewStub的内容
            binding.viewStub.viewStub.inflate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_include)


        //在viewStub初始化的时候调用如下监听器
        binding.viewStub.setOnInflateListener(ViewStub.OnInflateListener { stub, inflated ->
            val viewBinding = DataBindingUtil.bind<StubViewBinding>(inflated)

            viewBinding.stu = Student(222)
        })

        binding.student = stu
        binding.listener = listener

        stu.money.set(100110239.0)
    }
}