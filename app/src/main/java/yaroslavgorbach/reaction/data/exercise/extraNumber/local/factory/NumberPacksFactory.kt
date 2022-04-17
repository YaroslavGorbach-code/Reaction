package yaroslavgorbach.reaction.data.exercise.extraNumber.local.factory

import yaroslavgorbach.reaction.data.exercise.extraNumber.local.model.Number
import yaroslavgorbach.reaction.data.exercise.extraNumber.local.model.NumberPack
import kotlin.random.Random

@ExperimentalStdlibApi
class NumberPacksFactory {
    companion object{
        private const val NUMBERS_IN_A_PACK = 30
    }

    fun create(maxValue: Int = 100): List<NumberPack> {
        return buildList {
            repeat(1000) {
                this.add(
                    NumberPack(buildList {
                        val extraIndex = Random.nextInt(NUMBERS_IN_A_PACK)
                        val number = Random.nextInt(maxValue)
                        var extraNumber = Random.nextInt(maxValue)

                        while (extraNumber == number) {
                            extraNumber = Random.nextInt(maxValue)
                        }

                        repeat(NUMBERS_IN_A_PACK) { index ->
                            if (index == extraIndex) {
                                this.add(Number(extraNumber, true))
                            } else {
                                this.add(Number(number, false))
                            }
                        }
                    })
                )
            }
        }
    }
}