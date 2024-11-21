package com.example.project_modile_application.presentation.ui.screen.actorpage.state

import com.example.project_modile_application.domain.dataclasses.StaffDataWithFilms

data class ActorDataState(
    var isLoading: Boolean = false,
    var staff: StaffDataWithFilms ?= null,
    var error: String = "",
    var id: Int? = 0
)
