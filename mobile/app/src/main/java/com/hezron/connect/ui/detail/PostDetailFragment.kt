package com.hezron.connect.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hezron.connect.databinding.FragmentPostDetailBinding

class PostDetailFragment : Fragment() {

    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val adapter = com.hezron.connect.ui.adapter.CommentAdapter()
        binding.rvComments.adapter = adapter
        binding.rvComments.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        
        adapter.submitList(com.hezron.connect.util.DummyDataUtil.getComments())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
