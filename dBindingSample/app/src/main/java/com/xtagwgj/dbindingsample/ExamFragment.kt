package com.xtagwgj.dbindingsample

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xtagwgj.dbindingsample.databinding.FragmentExamBinding
import com.xtagwgj.dbindingsample.entity.Teacher

/**
 * Fragment
 * Created by xtagwgj on 2017/8/28.
 */
class ExamFragment : Fragment() {

    var binding: FragmentExamBinding? = null

    val teacher = Teacher(3)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exam, container, false)
        }

        binding?.teacher = teacher

        return binding!!.root
    }
}