package com.xtagwgj.dbindingsample.entity

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.xtagwgj.dbindingsample.BR

/**
 * 实体的学生类
 * Created by xtagwgj on 2017/8/25.
 */
data class Teacher(
        val id: Long
) : BaseObservable() {

    var name: String = "Lucy"
            //绑定数据
        @Bindable
        get() = field
        set(value) {
            field = value
            //数据发生变化时还是需要手动发出通知。
            // 通过调用notifyPropertyChanged(BR.name)来通知系统 BR.name 这个 entry 的数据已经发生变化，
            // 需要更新 UI。
            notifyPropertyChanged(BR.name)
        }
    var age: Int = 38
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }
    var sex: Boolean = false
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.sex)
        }
    var money: Double = 30000.0
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.money)
        }

    override fun toString(): String {
        return "Student(id=$id,name=$name,age=$age,sex=$sex,money=$money)"
    }

}
