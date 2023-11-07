package lotto.domain

import camp.nextstep.edu.missionutils.Randoms
import lotto.util.errorPrefix
import lotto.util.lottoWinningNumberQuantity
import lotto.util.maxLottoWinningNumber
import lotto.util.minLottoWinningNumber

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6)
        if (numbers.toSet().size != lottoWinningNumberQuantity) throw IllegalArgumentException("$errorPrefix 로또 번호는 중복이 될 수 없습니다.")
    }

    fun getNumbers(): List<Int> = numbers
    fun printWinningNumbers() {
        println("[${numbers.joinToString()}]")
    }

    fun checkWinning(userInputNumbers: Lotto, userInputBonusNumber: Int): Winning? {
        val intersectCnt = userInputNumbers.numbers.intersect(numbers).size
        val isBonusNumberMatched = numbers.contains(userInputBonusNumber)
        return when (intersectCnt) {
            3 -> Winning.MatchingThreeCount
            4 -> Winning.MatchingFourCount
            5 -> {
                if (isBonusNumberMatched) Winning.MatchingFiveCountWithBonus
                Winning.MatchingFiveCount
            }

            6 -> Winning.MatchingSixCount
            else -> null
        }
    }
}

