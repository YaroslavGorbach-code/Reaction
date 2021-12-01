package yaroslavgorbach.reaction.data.exercise.extranumber.local.factory

import yaroslavgorbach.reaction.data.exercise.extranumber.local.model.Number
import yaroslavgorbach.reaction.data.exercise.extranumber.local.model.NumberPack
import kotlin.random.Random

@ExperimentalStdlibApi
class NumberPacksFactory {
    fun create(maxValue: Int = 100): List<NumberPack> {
        return buildList {
            repeat(1000) {
                this.add(
                    NumberPack(buildList {
                        val extraIndex = Random.nextInt(15)
                        val number = Random.nextInt(maxValue)
                        var extraNumber = Random.nextInt(maxValue)

                        while (extraNumber == number) {
                            extraNumber = Random.nextInt(maxValue)
                        }

                        repeat(15) { index ->
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