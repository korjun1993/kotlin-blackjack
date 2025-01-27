package blackjack.domain.card

data class Card(val kind: CardKind, val number: CardNumber) {
    fun isAce(): Boolean {
        return number == CardNumber.ACE
    }

    companion object {
        private val CARDS: List<Card> = CardKind.values().flatMap { kind ->
            CardNumber.values().map { number ->
                Card(kind, number)
            }
        }

        private val CARDS_MAP = CARDS.associateWith { it }

        fun of(cardKind: CardKind, cardNumber: CardNumber): Card {
            return CARDS_MAP[Card(cardKind, cardNumber)] ?: throw IllegalArgumentException()
        }

        fun all(): List<Card> {
            return CARDS
        }
    }
}
