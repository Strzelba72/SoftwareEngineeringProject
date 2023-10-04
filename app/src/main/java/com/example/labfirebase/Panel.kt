package com.example.labfirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.labfirebase.databinding.ActivityMainBinding
import com.example.labfirebase.databinding.FragmentPanelBinding

class Panel : Fragment() {
    private lateinit var binding: FragmentPanelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPanelBinding.inflate(inflater,container,false)
        binding.gameButton.setOnClickListener()
        {
            startLobby()

        }
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun startLobby()
    {
        findNavController().navigate(R.id.action_panel_to_lobbyFragment)
    }



}