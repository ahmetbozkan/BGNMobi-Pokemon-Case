package com.ahmetbozkan.bgnmobi.domain.util

interface BaseMapper<in A, out B> {

    /**
     * map the response data to not nullable UI entities
     * to handle data conversion (date parsing etc.) or getting rid of nullchecks in later usage in UI
     */
    fun map(from: A): B
}