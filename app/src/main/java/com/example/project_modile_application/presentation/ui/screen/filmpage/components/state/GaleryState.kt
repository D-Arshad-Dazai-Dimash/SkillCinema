package com.example.project_modile_application.presentation.ui.screen.filmpage.components.state

import com.example.project_modile_application.domain.dataclasses.Images

data class GaleryState(
    var isLoading: Boolean = false,
    var galery: Images? = null,
    var error: String = "",
    var id: Int? = 0
)