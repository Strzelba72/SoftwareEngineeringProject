package com.example.labfirebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.labfirebase.Deck.fractionFirst
import com.example.labfirebase.databinding.FragmentLobbyBinding

class LobbyFragment : Fragment() {

    private lateinit var binding: FragmentLobbyBinding
    val player1Deck: MutableList<Card> = fractionFirst()
    val player2Deck: MutableList<Card> = fractionFirst()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLobbyBinding.inflate(inflater, container, false)
        binding.playButton.setOnClickListener {

            startGame()
        }

        return binding.root
    }

    private fun startGame() {
        findNavController().navigate(R.id.action_lobbyFragment_to_boardFragment)
    }

}
