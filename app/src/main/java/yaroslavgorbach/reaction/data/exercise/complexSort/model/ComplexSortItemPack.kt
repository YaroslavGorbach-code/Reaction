package yaroslavgorbach.reaction.data.exercise.complexSort.model

class ComplexSortItemPack(val items: List<ComplexSortItem>){
    companion object{
        val Empty = ComplexSortItemPack(emptyList())
        val Test = ComplexSortItemPack(listOf(ComplexSortItem.Test))
    }
}