package com.example.project_modile_application.domain.dataclasses.searchPage

interface SearchPageParameter

interface ApiMappedSearchPageParameter : SearchPageParameter {
    val apiValue: String
    val uiValue: String
}

interface IdSearchPageParameter : SearchPageParameter {
    val id: Int
    val value: String
}