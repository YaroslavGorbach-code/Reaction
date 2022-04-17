package yaroslavgorbach.reaction.feature.exercise.common.mapper

import yaroslavgorbach.reaction.BuildConfig
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.exercise.airport.model.AirportWinRule
import yaroslavgorbach.reaction.feature.exercise.common.model.WinRule
import yaroslavgorbach.reaction.feature.exercise.cpmplexSort.model.ComplexSortWinRule
import yaroslavgorbach.reaction.feature.exercise.extraNumber.model.ExtraNumberWinRule
import yaroslavgorbach.reaction.feature.exercise.extraWord.model.ExtraWordWinRule
import yaroslavgorbach.reaction.feature.exercise.faceControl.model.FaceControlWinRule
import yaroslavgorbach.reaction.feature.exercise.geoSwitching.model.GeoSwitchingWinRule
import yaroslavgorbach.reaction.feature.exercise.numbersAndLetters.model.NumbersAndLettersWinRule
import yaroslavgorbach.reaction.feature.exercise.rotation.model.RotationWinRule
import yaroslavgorbach.reaction.feature.exercise.stroop.model.StroopWinRule

object ExerciseNameToWinRuleMapper {
    fun map(exerciseName: ExerciseName): WinRule {

        if (BuildConfig.IS_PROD.not()) {
            return WinRule
        }

        return when (exerciseName) {
            ExerciseName.EXTRA_NUMBER -> ExtraNumberWinRule
            ExerciseName.EXTRA_WORD -> ExtraWordWinRule
            ExerciseName.FACE_CONTROL -> FaceControlWinRule
            ExerciseName.COMPLEX_SORT -> ComplexSortWinRule
            ExerciseName.STROOP -> StroopWinRule
            ExerciseName.GEO_SWITCHING -> GeoSwitchingWinRule
            ExerciseName.NUMBERS_AND_LETTERS -> NumbersAndLettersWinRule
            ExerciseName.AIRPORT -> AirportWinRule
            ExerciseName.ROTATION -> RotationWinRule
            ExerciseName.NO_NAME -> WinRule
        }
    }
}