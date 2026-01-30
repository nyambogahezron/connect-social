package com.hezron.connect.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hezron.connect.databinding.FragmentChatDetailBinding

class ChatDetailFragment : Fragment() {

    private var _binding: FragmentChatDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val adapter = com.hezron.connect.ui.adapter.MessageAdapter()
        binding.rvMessages.adapter = adapter
        binding.rvMessages.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        
        adapter.submitList(com.hezron.connect.util.DummyDataUtil.getMessages())
        
        binding.btnBack.setOnClickListener {
            // findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
