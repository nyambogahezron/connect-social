package com.hezron.connect.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hezron.connect.databinding.FragmentChatListBinding

class ChatListFragment : Fragment() {

    private var _binding: FragmentChatListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val adapter = com.hezron.connect.ui.adapter.ChatListAdapter { chat ->
             // Navigate to Chat Detail
             // findNavController().navigate(R.id.action_chatList_to_chatDetail)
        }
        
        binding.rvChatList.adapter = adapter
        binding.rvChatList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        
        adapter.submitList(com.hezron.connect.util.DummyDataUtil.getChats())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
