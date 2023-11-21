package blackjack.domain.game

import blackjack.domain.card.Card

class RandomCardDealer : CardDealer {
    private val cards: List<Card> = Card.all().shuffled()
    private var index = 0

    override fun selectCard(): Card {
        if (cards.size <= index) {
            throw IllegalArgumentException("카드가 더 이상 존재하지 않습니다")
        }
        return cards[index++]
    }
}
