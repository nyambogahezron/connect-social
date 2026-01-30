package com.hezron.connect.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hezron.connect.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val adapter = com.hezron.connect.ui.adapter.FeedAdapter { post ->
            // Handle click, e.g., navigate to detail
            // findNavController().navigate(...)
            // Toast.makeText(context, "Clicked ${post.user.name}", Toast.LENGTH_SHORT).show()
        }
        
        binding.rvFeed.adapter = adapter
        binding.rvFeed.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        
        // Load dummy data
        adapter.submitList(com.hezron.connect.util.DummyDataUtil.getPosts())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}