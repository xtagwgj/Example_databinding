package com.xtagwgj.dbindingsample

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xtagwgj.dbindingsample.databinding.ItemListBinding
import com.xtagwgj.dbindingsample.entity.Student
import kotlinx.android.synthetic.main.activity_list.*

/**
 * 示范RecyclerView的使用
 * Created by xtagwgj on 2017/8/28.
 */
class ListActivity : AppCompatActivity() {

    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //添加n个数据 只是id不一样
        (0..100).mapTo(studentList) { Student(it.toLong()) }

        //显示数据
        recycler.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)

            //其他代码

        }.adapter = MyAdapter(studentList)
    }

}

/**
 * 适配器
 */
open class MyAdapter(val datas: MutableList<Student>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    override fun getItemCount() = datas.size

    /**
     * 实现了数据的绑定
     * 和点击事件的监听
     */
    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {

        //这里只是最简单的使用 ，实际情况可能比较复杂
        holder.binding.stu = datas[position]
        //点击输出log
        holder.binding.listener = View.OnClickListener {
            Log.e("ClickAdapter", datas[position].toString())
        }

        //。。。其他逻辑
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemListBinding>(LayoutInflater.from(parent.context),
                R.layout.item_list, parent, false)

        //直接传入ItemListBinding对象
        return MyAdapter.ViewHolder(binding)
    }

    //自定义的viewHolder，超类传view过去
    open class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)
}