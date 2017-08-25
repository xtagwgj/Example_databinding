package com.xtagwgj.dbindingsample.entity

import android.databinding.*

/**
 * 实体的学生类
 * Created by xtagwgj on 2017/8/25.
 */
data class Student(
        val id: Long,
        var name: ObservableField<String> = ObservableField("Mike"),
        var age: ObservableInt = ObservableInt(18),
        var sex: ObservableBoolean = ObservableBoolean(true),
        var money: ObservableDouble = ObservableDouble(1000.0)
) : BaseObservable() {

    override fun toString(): String {
        return "Student(id=$id,name=${name.get()},age=${age.get()},sex=${sex.get()},money=${money.get()})"
    }
}