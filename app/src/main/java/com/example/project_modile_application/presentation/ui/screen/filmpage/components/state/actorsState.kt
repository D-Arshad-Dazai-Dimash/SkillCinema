package com.example.project_modile_application.presentation.ui.screen.filmpage.components.state

import com.example.project_modile_application.domain.dataclasses.StaffData

data class ActorsState(
    var isLoading: Boolean = false,
    var actor: List<StaffData> = emptyList(),
    var error: String = "",
    var id: Int? = 0
)