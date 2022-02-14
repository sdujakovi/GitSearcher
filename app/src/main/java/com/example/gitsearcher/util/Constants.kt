package com.example.gitsearcher.util

/***
 * API constants.
 *
 * This class contains all constants used for creating the Retrofit2 call.
 * All constants are defined inside a companion object
 *
 * @property BASE_URL base url for API call
 * @property SEARCH end point for API call
 * @property SORT_BY_UPDATE sort by last update constant
 */
class Constants{
    companion object{
        const val BASE_URL = "https://api.github.com/"
        const val SEARCH = "search/repositories"
        const val SORT_BY_UPDATE = "updated"
        const val PER_PAGE = "10"
    }
}