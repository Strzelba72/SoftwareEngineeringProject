package com.example.labfirebase

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.labfirebase.Deck.cards
import com.example.labfirebase.Deck.random
import com.example.labfirebase.databinding.FragmentBoardBinding
import com.google.android.material.snackbar.Snackbar

class BoardFragment : Fragment() {
    var playerTurn: Boolean = true
    var isTurn: Boolean = true
    var turnCounter: Int = 0
    private lateinit var binding: FragmentBoardBinding
    private val args: BoardFragmentArgs by navArgs()
    var randomList = IntArray(9)
    var randomListenemy = IntArray(9)
    private val initialDrawNumber: Int = 9
    var round = IntArray(2)
    private var playerScore = 0
    private var enemyScore = 0
    private var playerRoundCount = 0
    private var enemyRoundCount = 0
    private var roundCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardBinding.inflate(inflater, container, false)

        initialDraw()
/*
        binding.passButton.setOnClickListener{
            if(playerTurn and isTurn) {
                binding.imageView5.visibility = View.VISIBLE
                binding.imageView5.setImageResource(R.drawable.back)
            }
            else {
                binding.imageView6.visibility = View.VISIBLE
                binding.imageView6.setImageResource(R.drawable.back)
            }
            resetTurn()
        }
*/
        binding.playerCard1.setOnClickListener{
            if(playerTurn and isTurn) {
                binding.imageView5.visibility = View.VISIBLE
                playerCardImgIdsArrayINVISIBLE(1)
                binding.imageView5.setImageDrawable(binding.playerCard1.drawable)
                updateScore(randomList[0], true)
                resetTurn()
            }

        }
        binding.playerCard2.setOnClickListener{
            if(playerTurn and isTurn) {
                binding.imageView5.visibility = View.VISIBLE
                playerCardImgIdsArrayINVISIBLE(2)
                binding.imageView5.setImageDrawable(binding.playerCard2.drawable)
                updateScore(randomList[1], true)
                resetTurn()
            }
        }
        binding.playerCard3.setOnClickListener{
            if(playerTurn and isTurn) {
                binding.imageView5.visibility = View.VISIBLE
                playerCardImgIdsArrayINVISIBLE(3)
                binding.imageView5.setImageDrawable(binding.playerCard3.drawable)
                updateScore(randomList[2], true)
                resetTurn()
            }
        }
        binding.playerCard4.setOnClickListener{
            if(playerTurn and isTurn) {
                binding.imageView5.visibility = View.VISIBLE
                playerCardImgIdsArrayINVISIBLE(4)
                binding.imageView5.setImageDrawable(binding.playerCard4.drawable)
                updateScore(randomList[3], true)
                resetTurn()
            }
        }
        binding.playerCard5.setOnClickListener{
            if(playerTurn and isTurn) {
                binding.imageView5.visibility = View.VISIBLE
                playerCardImgIdsArrayINVISIBLE(5)
                binding.imageView5.setImageDrawable(binding.playerCard5.drawable)
                updateScore(randomList[4], true)
                resetTurn()
            }
        }
        binding.playerCard6.setOnClickListener{
            if(playerTurn and isTurn) {
                binding.imageView5.visibility = View.VISIBLE
                playerCardImgIdsArrayINVISIBLE(6)
                binding.imageView5.setImageDrawable(binding.playerCard6.drawable)
                updateScore(randomList[5], true)
                resetTurn()
            }
        }
        binding.playerCard7.setOnClickListener{
            if(playerTurn and isTurn) {
                binding.imageView5.visibility = View.VISIBLE
                playerCardImgIdsArrayINVISIBLE(7)
                binding.imageView5.setImageDrawable(binding.playerCard7.drawable)
                updateScore(randomList[6], true)
                resetTurn()
            }
        }
        binding.playerCard8.setOnClickListener{
            if(playerTurn and isTurn) {
                binding.imageView5.visibility = View.VISIBLE
                playerCardImgIdsArrayINVISIBLE(8)
                binding.imageView5.setImageDrawable(binding.playerCard8.drawable)
                updateScore(randomList[7], true)
                resetTurn()
            }


        }
        binding.playerCard9.setOnClickListener{
            if(playerTurn and isTurn) {
                binding.imageView5.visibility = View.VISIBLE
                playerCardImgIdsArrayINVISIBLE(9)
                binding.imageView5.setImageDrawable(binding.playerCard9.drawable)
                updateScore(randomList[8], true)
                resetTurn()
            }
        }
        binding.enemyCard1.setOnClickListener{
            if(!playerTurn and isTurn) {
                binding.imageView6.visibility = View.VISIBLE
                enemyCardImgIdsArrayINVISIBLE(1)
                binding.imageView6.setImageResource(R.drawable.card_rewers)
                updateScore(randomListenemy[0], false)
                resetTurn()
            }
        }
        binding.enemyCard2.setOnClickListener{
            if(!playerTurn and isTurn) {
                binding.imageView6.visibility = View.VISIBLE
                enemyCardImgIdsArrayINVISIBLE(2)
                binding.imageView6.setImageResource(R.drawable.card_rewers)
                updateScore(randomListenemy[1], false)
                resetTurn()
            }
        }
        binding.enemyCard3.setOnClickListener{
            if(!playerTurn and isTurn) {
                binding.imageView6.visibility = View.VISIBLE
                enemyCardImgIdsArrayINVISIBLE(3)
                binding.imageView6.setImageResource(R.drawable.card_rewers)
                updateScore(randomListenemy[2], false)
                resetTurn()
            }
        }
        binding.enemyCard4.setOnClickListener{
            if(!playerTurn and isTurn) {
                binding.imageView6.visibility = View.VISIBLE
                enemyCardImgIdsArrayINVISIBLE(4)
                binding.imageView6.setImageResource(R.drawable.card_rewers)
                updateScore(randomListenemy[3], false)
                resetTurn()
            }
        }
        binding.enemyCard5.setOnClickListener{
            if(!playerTurn and isTurn) {
                binding.imageView6.visibility = View.VISIBLE
                enemyCardImgIdsArrayINVISIBLE(5)
                binding.imageView6.setImageResource(R.drawable.card_rewers)
                updateScore(randomListenemy[4], false)
                resetTurn()
            }
        }
        binding.enemyCard6.setOnClickListener{
            if(!playerTurn and isTurn) {
                binding.imageView6.visibility = View.VISIBLE
                enemyCardImgIdsArrayINVISIBLE(6)
                binding.imageView6.setImageResource(R.drawable.card_rewers)
                updateScore(randomListenemy[5], false)
                resetTurn()
            }
        }
        binding.enemyCard7.setOnClickListener{
            if(!playerTurn and isTurn) {
                binding.imageView6.visibility = View.VISIBLE
                enemyCardImgIdsArrayINVISIBLE(7)
                binding.imageView6.setImageResource(R.drawable.card_rewers)
                updateScore(randomListenemy[6], false)
                resetTurn()
            }
        }
        binding.enemyCard8.setOnClickListener{
            if(!playerTurn and isTurn) {
                binding.imageView6.visibility = View.VISIBLE
                enemyCardImgIdsArrayINVISIBLE(8)
                binding.imageView6.setImageResource(R.drawable.card_rewers)
                updateScore(randomListenemy[7], false)
                resetTurn()
            }
        }
        binding.enemyCard9.setOnClickListener{
            if(!playerTurn and isTurn) {
                binding.imageView6.visibility = View.VISIBLE
                enemyCardImgIdsArrayINVISIBLE(9)
                binding.imageView6.setImageResource(R.drawable.card_rewers)
                updateScore(randomListenemy[8], false)
                resetTurn()
            }
        }
        return binding.root
    }

    private fun initialDraw() {
        drawCards()
        for (num in 1..initialDrawNumber) {
            playerCardImgIdsArray(num)
            enemyCardImgIdsArray(num)
        }
        binding.enemyCard1.setImageResource(R.drawable.card_rewers)
        binding.enemyCard2.setImageResource(R.drawable.card_rewers)
        binding.enemyCard3.setImageResource(R.drawable.card_rewers)
        binding.enemyCard4.setImageResource(R.drawable.card_rewers)
        binding.enemyCard5.setImageResource(R.drawable.card_rewers)
        binding.enemyCard6.setImageResource(R.drawable.card_rewers)
        binding.enemyCard7.setImageResource(R.drawable.card_rewers)
        binding.enemyCard8.setImageResource(R.drawable.card_rewers)
        binding.enemyCard9.setImageResource(R.drawable.card_rewers)
    }

    private fun resetRound() {
        hideCards()
        binding.imageView5.setImageResource(resolveDrawable(playerScore))
        binding.imageView6.setImageResource(resolveDrawable(enemyScore))
        isTurn = false
        roundCount++

        val winner = when {
            playerScore > enemyScore -> "Gracz dolny"
            enemyScore > playerScore -> "Gracz górny"
            else -> "remis"
        }

        when {
            playerScore > enemyScore -> playerRoundCount++
            enemyScore > playerScore -> enemyRoundCount++
        }

        if(roundCount >= 9) {

            binding.playerCount.text = "Wynik: $playerRoundCount"
            binding.enemyCount.text = "Wynik: $enemyRoundCount"

            val winner = when {
                playerRoundCount > enemyRoundCount -> "Gracz dolny"
                enemyRoundCount > playerRoundCount -> "Gracz górny"
                else -> "remis"
            }

            Snackbar.make(
                binding.root,
                "Zwycięzcą meczu zostaje: $winner",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("Zakończ mecz") {
                findNavController().navigate(R.id.action_boardFragment_to_panel2)
            }.show()
        }
        else {
            Snackbar.make(
                binding.root,
                "Zwycięzca rozdania: $winner",
                Snackbar.LENGTH_INDEFINITE
            ).setAction("Następna runda") {
                binding.imageView5.visibility = View.INVISIBLE
                binding.imageView6.visibility = View.INVISIBLE
                reverseCard()
                binding.playerCount.text = "Wynik: $playerRoundCount"
                binding.enemyCount.text = "Wynik: $enemyRoundCount"
                isTurn = true
            }.show()
        }

        turnCounter = 0
        playerScore = 0
        enemyScore = 0


    }

    private fun resetTurn() {
        playerTurn = !playerTurn
        turnCounter++

        if(turnCounter >= 2) {
            resetRound()
            return
        }

        hideCards()
        isTurn = false

        Snackbar.make(
            binding.root,
            "Podaj telefon drugiemu graczowi.",
            Snackbar.LENGTH_INDEFINITE
        ).setAction("GRAJ DALEJ") {
            reverseCard()
            isTurn = true
        }.show()
    }

    private fun randomCard(number: Int): Int {
        var cardNum : Int
        do {
            cardNum = (1..9).random()
        } while(randomList.contains(cardNum))
        randomList.set(number - 1, cardNum)
        return cardNum
    }
    private fun randomCardenemy(number: Int): Int {
        var cardNum : Int
        do {
            cardNum = (1..9).random()
        } while(randomListenemy.contains(cardNum))
        randomListenemy.set(number - 1, cardNum)
        return cardNum
    }

    private fun updateScore(cardValue: Int = 0, forPlayer: Boolean = true) {
        var score = 0

        when(cardValue) {
            1 -> score += 1
            2 -> score += 2
            3 -> score += 3
            4 -> score += 4
            5 -> score += 5
            6 -> score += 6
            7 -> score += 7
            8 -> score += 8
            9 -> score += 9
        }

        if(forPlayer)
            playerScore = score
        else
            enemyScore = score
    }

    private fun drawCards() {
        binding.playerCard1.setImageResource(resolveDrawable(randomCard(1)))
        binding.playerCard2.setImageResource(resolveDrawable(randomCard(2)))
        binding.playerCard3.setImageResource(resolveDrawable(randomCard(3)))
        binding.playerCard4.setImageResource(resolveDrawable(randomCard(4)))
        binding.playerCard5.setImageResource(resolveDrawable(randomCard(5)))
        binding.playerCard6.setImageResource(resolveDrawable(randomCard(6)))
        binding.playerCard7.setImageResource(resolveDrawable(randomCard(7)))
        binding.playerCard8.setImageResource(resolveDrawable(randomCard(8)))
        binding.playerCard9.setImageResource(resolveDrawable(randomCard(9)))

        binding.enemyCard1.setImageResource(resolveDrawable(randomCardenemy(1)))
        binding.enemyCard2.setImageResource(resolveDrawable(randomCardenemy(2)))
        binding.enemyCard3.setImageResource(resolveDrawable(randomCardenemy(3)))
        binding.enemyCard4.setImageResource(resolveDrawable(randomCardenemy(4)))
        binding.enemyCard5.setImageResource(resolveDrawable(randomCardenemy(5)))
        binding.enemyCard6.setImageResource(resolveDrawable(randomCardenemy(6)))
        binding.enemyCard7.setImageResource(resolveDrawable(randomCardenemy(7)))
        binding.enemyCard8.setImageResource(resolveDrawable(randomCardenemy(8)))
        binding.enemyCard9.setImageResource(resolveDrawable(randomCardenemy(9)))


    }
//
//    private fun updateImg(number: Int) {
//        image.setImageResource(resolveDrawable(number))
//    }

    private fun resolveDrawable(value: Int): Int {
        return when (value) {
            1 -> R.drawable.one
            2 -> R.drawable.two
            3 -> R.drawable.three
            4 -> R.drawable.four
            5 -> R.drawable.five
            6 -> R.drawable.six
            7 -> R.drawable.seven
            8 -> R.drawable.eight
            9 -> R.drawable.nine
            else -> R.drawable.one
        }
    }

    private fun playerCardImgIdsArray(num: Int)
    {
        when(num)
        {
            1->binding.playerCard1.visibility=View.VISIBLE
            2->binding.playerCard2.visibility=View.VISIBLE
            3->binding.playerCard3.visibility=View.VISIBLE
            4->binding.playerCard4.visibility=View.VISIBLE
            5->binding.playerCard5.visibility=View.VISIBLE
            6->binding.playerCard6.visibility=View.VISIBLE
            7->binding.playerCard7.visibility=View.VISIBLE
            8->binding.playerCard8.visibility=View.VISIBLE
            9->binding.playerCard9.visibility=View.VISIBLE
        }
    }
    private fun playerCardImgIdsArrayINVISIBLE(num: Int)
    {
        when(num)
        {
            1->binding.playerCard1.visibility=View.INVISIBLE
            2->binding.playerCard2.visibility=View.INVISIBLE
            3->binding.playerCard3.visibility=View.INVISIBLE
            4->binding.playerCard4.visibility=View.INVISIBLE
            5->binding.playerCard5.visibility=View.INVISIBLE
            6->binding.playerCard6.visibility=View.INVISIBLE
            7->binding.playerCard7.visibility=View.INVISIBLE
            8->binding.playerCard8.visibility=View.INVISIBLE
            9->binding.playerCard9.visibility=View.INVISIBLE
        }
    }
    private fun enemyCardImgIdsArray(num: Int)
    {
        when(num)
        {
            1->binding.enemyCard1.visibility=View.VISIBLE
            2->binding.enemyCard2.visibility=View.VISIBLE
            3->binding.enemyCard3.visibility=View.VISIBLE
            4->binding.enemyCard4.visibility=View.VISIBLE
            5->binding.enemyCard5.visibility=View.VISIBLE
            6->binding.enemyCard6.visibility=View.VISIBLE
            7->binding.enemyCard7.visibility=View.VISIBLE
            8->binding.enemyCard8.visibility=View.VISIBLE
            9->binding.enemyCard9.visibility=View.VISIBLE
        }
    }
    private fun enemyCardImgIdsArrayINVISIBLE(num: Int)
    {
        when(num)
        {
            1->binding.enemyCard1.visibility=View.INVISIBLE
            2->binding.enemyCard2.visibility=View.INVISIBLE
            3->binding.enemyCard3.visibility=View.INVISIBLE
            4->binding.enemyCard4.visibility=View.INVISIBLE
            5->binding.enemyCard5.visibility=View.INVISIBLE
            6->binding.enemyCard6.visibility=View.INVISIBLE
            7->binding.enemyCard7.visibility=View.INVISIBLE
            8->binding.enemyCard8.visibility=View.INVISIBLE
            9->binding.enemyCard9.visibility=View.INVISIBLE
        }
    }
    private fun reverseCard()
    {
        if(!playerTurn) { // and isturn
            binding.enemyCard1.setImageResource(resolveDrawable(randomListenemy[0]))
            binding.enemyCard2.setImageResource(resolveDrawable(randomListenemy[1]))
            binding.enemyCard3.setImageResource(resolveDrawable(randomListenemy[2]))
            binding.enemyCard4.setImageResource(resolveDrawable(randomListenemy[3]))
            binding.enemyCard5.setImageResource(resolveDrawable(randomListenemy[4]))
            binding.enemyCard6.setImageResource(resolveDrawable(randomListenemy[5]))
            binding.enemyCard7.setImageResource(resolveDrawable(randomListenemy[6]))
            binding.enemyCard8.setImageResource(resolveDrawable(randomListenemy[7]))
            binding.enemyCard9.setImageResource(resolveDrawable(randomListenemy[8]))
            binding.playerCard1.setImageResource(R.drawable.card_rewers)
            binding.playerCard2.setImageResource(R.drawable.card_rewers)
            binding.playerCard3.setImageResource(R.drawable.card_rewers)
            binding.playerCard4.setImageResource(R.drawable.card_rewers)
            binding.playerCard5.setImageResource(R.drawable.card_rewers)
            binding.playerCard6.setImageResource(R.drawable.card_rewers)
            binding.playerCard7.setImageResource(R.drawable.card_rewers)
            binding.playerCard8.setImageResource(R.drawable.card_rewers)
            binding.playerCard9.setImageResource(R.drawable.card_rewers)
        }
        else
        {
            binding.playerCard1.setImageResource(resolveDrawable(randomList[0]))
            binding.playerCard2.setImageResource(resolveDrawable(randomList[1]))
            binding.playerCard3.setImageResource(resolveDrawable(randomList[2]))
            binding.playerCard4.setImageResource(resolveDrawable(randomList[3]))
            binding.playerCard5.setImageResource(resolveDrawable(randomList[4]))
            binding.playerCard6.setImageResource(resolveDrawable(randomList[5]))
            binding.playerCard7.setImageResource(resolveDrawable(randomList[6]))
            binding.playerCard8.setImageResource(resolveDrawable(randomList[7]))
            binding.playerCard9.setImageResource(resolveDrawable(randomList[8]))
            binding.enemyCard1.setImageResource(R.drawable.card_rewers)
            binding.enemyCard2.setImageResource(R.drawable.card_rewers)
            binding.enemyCard3.setImageResource(R.drawable.card_rewers)
            binding.enemyCard4.setImageResource(R.drawable.card_rewers)
            binding.enemyCard5.setImageResource(R.drawable.card_rewers)
            binding.enemyCard6.setImageResource(R.drawable.card_rewers)
            binding.enemyCard7.setImageResource(R.drawable.card_rewers)
            binding.enemyCard8.setImageResource(R.drawable.card_rewers)
            binding.enemyCard9.setImageResource(R.drawable.card_rewers)
        }


    }

    private fun hideCards() {
        binding.imageView5.setImageResource(R.drawable.card_rewers)
        binding.imageView6.setImageResource(R.drawable.card_rewers)
        binding.playerCard1.setImageResource(R.drawable.card_rewers)
        binding.playerCard2.setImageResource(R.drawable.card_rewers)
        binding.playerCard3.setImageResource(R.drawable.card_rewers)
        binding.playerCard4.setImageResource(R.drawable.card_rewers)
        binding.playerCard5.setImageResource(R.drawable.card_rewers)
        binding.playerCard6.setImageResource(R.drawable.card_rewers)
        binding.playerCard7.setImageResource(R.drawable.card_rewers)
        binding.playerCard8.setImageResource(R.drawable.card_rewers)
        binding.playerCard9.setImageResource(R.drawable.card_rewers)
        binding.enemyCard1.setImageResource(R.drawable.card_rewers)
        binding.enemyCard2.setImageResource(R.drawable.card_rewers)
        binding.enemyCard3.setImageResource(R.drawable.card_rewers)
        binding.enemyCard4.setImageResource(R.drawable.card_rewers)
        binding.enemyCard5.setImageResource(R.drawable.card_rewers)
        binding.enemyCard6.setImageResource(R.drawable.card_rewers)
        binding.enemyCard7.setImageResource(R.drawable.card_rewers)
        binding.enemyCard8.setImageResource(R.drawable.card_rewers)
        binding.enemyCard9.setImageResource(R.drawable.card_rewers)
    }



}
