package io.pdaa.chilenastats.domain

data class TeamUi(
    val id: Int,
    val name: String,
    val logo: String,
    val founded: Int?,
    val national: Boolean?,
    val isFavourite: Boolean,
    val country: String,
    val venue: VenueUi?,
){
    fun doesMatchSearch(searchText: String): Boolean {
        val matchingCombination: List<String> = listOf(
            name,
            country
        )
        return matchingCombination.any {
            it.contains(searchText, ignoreCase = true)
        }
    }
}


