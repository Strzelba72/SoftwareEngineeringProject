package com.example.labfirebase

import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import kotlin.random.Random

//class card
data class Card(val name: String, val strength: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()!!
    ) {
    }

    override fun toString(): String =name

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(strength)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}

//class deck
object Deck {

    val cards: MutableList<Card> = ArrayList()
    private var cardsIndexes = mutableListOf<Int>()

    fun setFraction(number: Int) {
        when (number) {
            1 -> fractionFirst()
            2 -> fractionSecond()
            else -> {
                fractionFirst()
            }
        }
    }

     fun fractionFirst(): MutableList<Card> {
        for (i in 0..9) {
            var index: Int
            do {
                index = random()
            } while (cardsIndexes.contains(index))
            cardsIndexes.add(index)

            cards.add(Card("card_flamingo", index%9))
        }
         return cards
    }

     fun fractionSecond(): MutableList<Card> {
        for (i in 0..9) {
            var index: Int
            do {
                index = random()
            } while (cardsIndexes.contains(index))
            cardsIndexes.add(index)

            cards.add(Card("card_flamingo", index%9))
        }
         return cards
    }

     fun random(): Int {
         //incydent
        return Random.nextInt(0, 30)
    }
}
