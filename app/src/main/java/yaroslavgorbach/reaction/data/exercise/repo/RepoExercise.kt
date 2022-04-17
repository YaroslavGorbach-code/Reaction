package yaroslavgorbach.reaction.data.exercise.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.reaction.data.exercise.airport.model.Plane
import yaroslavgorbach.reaction.data.exercise.complexSort.model.ComplexSortItem
import yaroslavgorbach.reaction.data.exercise.extraNumber.local.model.NumberPack
import yaroslavgorbach.reaction.data.exercise.extraWord.model.WordPack
import yaroslavgorbach.reaction.data.exercise.faceControl.model.FacePack
import yaroslavgorbach.reaction.data.exercise.geoSwitching.model.GeoFigure
import yaroslavgorbach.reaction.data.exercise.numbersLetters.model.NumberAndLetter
import yaroslavgorbach.reaction.data.exercise.rotation.model.Tables
import yaroslavgorbach.reaction.data.exercise.stroop.model.StroopWord

interface RepoExercise {
    // TODO: 12/16/2021 refactor naming
    fun observeExtraNumber(): Flow<List<NumberPack>>

    fun observeExtraWord(): Flow<List<WordPack>>

    fun observeFaceControl(): Flow<List<FacePack>>

    fun observeStroopWords(): Flow<List<StroopWord>>

    fun observeComplexSort(): Flow<List<ComplexSortItem>>

    fun observeGeoFigures(): Flow<List<GeoFigure>>

    fun observeNumbersAnLetters(): Flow<List<NumberAndLetter>>

    fun observePlanes(): Flow<List<Plane>>

    fun observeTables(): Flow<List<Tables>>
}