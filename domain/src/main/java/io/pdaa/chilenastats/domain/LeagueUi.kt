package io.pdaa.chilenastats.domain


data class LeagueUi(
    val id:Int,
    val name: String,
    val type: String,
    val logo: String,
    val isFavourite: Boolean,
    val country: CountryUi,
    val season: String?
){
    fun doesMatchSearch(searchText: String): Boolean {
        val matchingCombination: List<String> = listOf(
            name,
            country.name,
            type
        )
        return matchingCombination.any {
            it.contains(searchText, ignoreCase = true)
        }
    }
}