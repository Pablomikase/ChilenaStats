package io.pdaa.chilenastats.framework.models.database.fixture

data class StatusDB (
    val statusLong: String,
    val statusShort: String,
    val statusElapsed: Int?,
    val statusExtra: Int?
)
