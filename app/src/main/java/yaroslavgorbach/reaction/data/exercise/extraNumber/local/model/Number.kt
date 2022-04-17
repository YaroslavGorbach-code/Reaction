package yaroslavgorbach.reaction.data.exercise.extraNumber.local.model

import kotlin.random.Random

data class Number(val number: Int, val isExtra: Boolean) {
    companion object {
        val Test
            get() = Number(Random.nextInt(100), Random.nextBoolean())
    }
}
