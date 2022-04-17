package yaroslavgorbach.reaction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.reaction.data.exercises.local.model.ExerciseName
import yaroslavgorbach.reaction.feature.description.ui.Description
import yaroslavgorbach.reaction.feature.exercise.airport.ui.AirportExercise
import yaroslavgorbach.reaction.feature.exercise.cpmplexSort.ui.ComplexSort
import yaroslavgorbach.reaction.feature.exercise.extraNumber.ui.ExtraNumbers
import yaroslavgorbach.reaction.feature.exercise.extraWord.ui.ExtraWords
import yaroslavgorbach.reaction.feature.exercise.faceControl.ui.FaceControl
import yaroslavgorbach.reaction.feature.exercise.geoSwitching.ui.GeoSwitchingExercise
import yaroslavgorbach.reaction.feature.exercise.numbersAndLetters.ui.NumbersAndLettersExercise
import yaroslavgorbach.reaction.feature.exercise.rotation.ui.RotationExercise
import yaroslavgorbach.reaction.feature.exercise.stroop.ui.StroopExercise
import yaroslavgorbach.reaction.feature.esercises.ui.Exercises

const val EXERCISE_NAME_ARG = "EXERCISE_NAME_ARG"

sealed class Screen(val route: String) {
    object Exercises : Screen("Exercises")
}

private sealed class LeafScreen(
    private val route: String,
) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object Exercises : LeafScreen("Exercises")
    object ExtraNumbers : LeafScreen("ExtraNumbers")
    object ExtraWords : LeafScreen("ExtraWords")
    object FaceControl : LeafScreen("FaceControl")
    object ComplexSort : LeafScreen("ComplexSort")
    object Stroop : LeafScreen("Stroop")
    object GeoSwitching : LeafScreen("GeoSwitching")
    object NumberAndLetter : LeafScreen("NumberAndLetter")
    object Airport : LeafScreen("Airport")
    object Rotation : LeafScreen("Rotation")

    object ShowDescription : LeafScreen("Description/{${EXERCISE_NAME_ARG}}") {
        fun createRoute(root: Screen, exerciseName: ExerciseName): String {
            return "${root.route}/Description/$exerciseName"
        }
    }
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Exercises.route,
        modifier = modifier,
    ) {
        addExercisesTopLevel(navController)
    }
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addExercisesTopLevel(
    navController: NavController,
) {
    navigation(
        route = Screen.Exercises.route,
        startDestination = LeafScreen.Exercises.createRoute(Screen.Exercises),
    ) {
        addExercises(navController, Screen.Exercises)
        addDescription(navController, Screen.Exercises)
        addExtraNumbersExercise(navController, Screen.Exercises)
        addExtraWordsExercise(navController, Screen.Exercises)
        addFaceControlExercise(navController, Screen.Exercises)
        addComplexSortExercise(navController, Screen.Exercises)
        addStroopExercise(navController, Screen.Exercises)
        addGeoSwitchingExercise(navController, Screen.Exercises)
        addNumberAndLetterExercise(navController, Screen.Exercises)
        addAirportExercise(navController, Screen.Exercises)
        addRotationExercise(navController, Screen.Exercises)
    }
}

@ExperimentalMaterialApi
private fun NavGraphBuilder.addExercises(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.Exercises.createRoute(root)) {
        Exercises(openDescription = { exerciseName ->
            navController.navigate(
                LeafScreen.ShowDescription.createRoute(
                    root = root,
                    exerciseName = exerciseName
                )
            )
        })
    }
}

@ExperimentalMaterialApi
private fun NavGraphBuilder.addDescription(
    navController: NavController,
    root: Screen,
) {
    composable(
        LeafScreen.ShowDescription.createRoute(root), arguments = listOf(
            navArgument(EXERCISE_NAME_ARG) {
                type = NavType.EnumType(ExerciseName::class.java)
            })
    ) { backStackEntry ->
        Description(
            exerciseName = backStackEntry.arguments?.getSerializable(EXERCISE_NAME_ARG) as ExerciseName,
            openExercise = { exerciseName ->
                navController.navigate(
                    mapExerciseNameToLeafScreen(exerciseName = exerciseName).createRoute(root = root)
                ) {
                    popUpTo(LeafScreen.ShowDescription.createRoute(root)) {
                        inclusive = true
                    }
                }
            }, onBackClick = navController::popBackStack)
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addExtraNumbersExercise(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.ExtraNumbers.createRoute(root)) {
        ExtraNumbers(onBackClick = navController::popBackStack, onRepeatExerciseClick = {
            navController.navigate(
                LeafScreen.ShowDescription.createRoute(
                    root = root,
                    exerciseName = ExerciseName.EXTRA_NUMBER
                )
            ) {
                popUpTo(LeafScreen.Exercises.createRoute(root = root)) {
                    inclusive = false
                }
            }
        })
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addExtraWordsExercise(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.ExtraWords.createRoute(root)) {
        ExtraWords(onBackClick = navController::popBackStack, onRepeatExerciseClick = {
            navController.navigate(
                LeafScreen.ShowDescription.createRoute(
                    root = root,
                    exerciseName = ExerciseName.EXTRA_WORD
                )
            ) {
                popUpTo(LeafScreen.Exercises.createRoute(root = root)) {
                    inclusive = false
                }
            }
        })
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addFaceControlExercise(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.FaceControl.createRoute(root)) {
        FaceControl(onBackClick = navController::popBackStack, onRepeatExerciseClick = {
            navController.navigate(
                LeafScreen.ShowDescription.createRoute(
                    root = root,
                    exerciseName = ExerciseName.FACE_CONTROL
                )
            ) {
                popUpTo(LeafScreen.Exercises.createRoute(root = root)) {
                    inclusive = false
                }
            }
        })
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addComplexSortExercise(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.ComplexSort.createRoute(root)) {
        ComplexSort(onBackClick = navController::popBackStack, onRepeatExerciseClick = {
            navController.navigate(
                LeafScreen.ShowDescription.createRoute(
                    root = root,
                    exerciseName = ExerciseName.COMPLEX_SORT
                )
            ) {
                popUpTo(LeafScreen.Exercises.createRoute(root = root)) {
                    inclusive = false
                }
            }
        })
    }
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addStroopExercise(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.Stroop.createRoute(root)) {
        StroopExercise(onBackClick = navController::popBackStack, onRepeatExerciseClick = {
            navController.navigate(
                LeafScreen.ShowDescription.createRoute(
                    root = root,
                    exerciseName = ExerciseName.STROOP
                )
            ) {
                popUpTo(LeafScreen.Exercises.createRoute(root = root)) {
                    inclusive = false
                }
            }
        })
    }
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addGeoSwitchingExercise(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.GeoSwitching.createRoute(root)) {
        GeoSwitchingExercise(onBackClick = navController::popBackStack, onRepeatExerciseClick = {
            navController.navigate(
                LeafScreen.ShowDescription.createRoute(
                    root = root,
                    exerciseName = ExerciseName.GEO_SWITCHING
                )
            ) {
                popUpTo(LeafScreen.Exercises.createRoute(root = root)) {
                    inclusive = false
                }
            }
        })
    }
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addNumberAndLetterExercise(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.NumberAndLetter.createRoute(root)) {
        NumbersAndLettersExercise(onBackClick = navController::popBackStack, onRepeatExerciseClick = {
            navController.navigate(
                LeafScreen.ShowDescription.createRoute(
                    root = root,
                    exerciseName = ExerciseName.NUMBERS_AND_LETTERS
                )
            ) {
                popUpTo(LeafScreen.Exercises.createRoute(root = root)) {
                    inclusive = false
                }
            }
        })
    }
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addAirportExercise(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.Airport.createRoute(root)) {
        AirportExercise(onBackClick = navController::popBackStack, onRepeatExerciseClick = {
            navController.navigate(
                LeafScreen.ShowDescription.createRoute(
                    root = root,
                    exerciseName = ExerciseName.AIRPORT
                )
            ) {
                popUpTo(LeafScreen.Exercises.createRoute(root = root)) {
                    inclusive = false
                }
            }
        })
    }
}

@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.addRotationExercise(
    navController: NavController,
    root: Screen,
) {
    composable(LeafScreen.Rotation.createRoute(root)) {
        RotationExercise(onBackClick = navController::popBackStack, onRepeatExerciseClick = {
            navController.navigate(
                LeafScreen.ShowDescription.createRoute(
                    root = root,
                    exerciseName = ExerciseName.ROTATION
                )
            ) {
                popUpTo(LeafScreen.Exercises.createRoute(root = root)) {
                    inclusive = false
                }
            }
        })
    }
}

private fun mapExerciseNameToLeafScreen(exerciseName: ExerciseName): LeafScreen {
    return when (exerciseName) {
        ExerciseName.EXTRA_NUMBER -> LeafScreen.ExtraNumbers
        ExerciseName.EXTRA_WORD -> LeafScreen.ExtraWords
        ExerciseName.FACE_CONTROL -> LeafScreen.FaceControl
        ExerciseName.COMPLEX_SORT -> LeafScreen.ComplexSort
        ExerciseName.STROOP -> LeafScreen.Stroop
        ExerciseName.GEO_SWITCHING -> LeafScreen.GeoSwitching
        ExerciseName.NUMBERS_AND_LETTERS -> LeafScreen.NumberAndLetter
        ExerciseName.AIRPORT -> LeafScreen.Airport
        ExerciseName.ROTATION -> LeafScreen.Rotation
        ExerciseName.NO_NAME -> error("No name screen")
    }
}
