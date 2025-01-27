package blackjack.domain.game

import blackjack.domain.card.CardScoreNormalAcePolicy
import blackjack.domain.card.CardScorePolicyGroup
import blackjack.domain.card.CardScoreSpecialAcePolicy
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerGroup
import blackjack.domain.player.PlayerScore

class BlackJackGame(
    private val cardDealer: CardDealer = RandomCardDealer(),
    private val cardScorePolicyGroup: CardScorePolicyGroup = CardScorePolicyGroup(
        listOf(
            CardScoreSpecialAcePolicy,
            CardScoreNormalAcePolicy
        )
    )
) {
    fun dealCardToEveryOne(playerGroup: PlayerGroup): PlayerGroup {
        return PlayerGroup(
            playerGroup.players.map {
                it.receiveCard(cardDealer.selectCard(START_CARD_COUNT))
            }
        )
    }

    fun dealCardTo(playerGroup: PlayerGroup, player: Player): PlayerGroup {
        return PlayerGroup(
            playerGroup.players.map {
                if (it == player && !it.cardSet.isFull(cardScorePolicyGroup)) {
                    it.receiveCard(cardDealer.selectCard(DEFAULT_CARD_COUNT))
                } else {
                    it
                }
            }
        )
    }

    fun end(playerGroup: PlayerGroup): BlackJackGameResult {
        return BlackJackGameResult(playerGroup.players.map {
            PlayerScore(
                it,
                it.cardSet.sumOfBest(cardScorePolicyGroup, GOAL_SCORE)
            )
        })
    }

    companion object {
        private const val START_CARD_COUNT = 2
        private const val DEFAULT_CARD_COUNT = 1
        private const val GOAL_SCORE = 21
    }
}
