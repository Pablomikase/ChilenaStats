package io.pdaa.chilenastats.framework.models.remote.fixture

import io.pdaa.chilenastats.domain.fixture.ScoreUi
import kotlinx.serialization.Serializable

@Serializable
data class ScoreRemote(
    val extratime: ExtratimeRemote,
    val fulltime: FulltimeRemote,
    val halftime: HalftimeRemote,
    val penalty: PenaltyRemote
)

fun ScoreRemote.asUiModel(): ScoreUi = ScoreUi(
    extratime = extratime.asUiModel(),
    fulltime = fulltime.asUiModel(),
    halftime = halftime.asUiModel(),
    penalty = penalty.asUiModel()
)