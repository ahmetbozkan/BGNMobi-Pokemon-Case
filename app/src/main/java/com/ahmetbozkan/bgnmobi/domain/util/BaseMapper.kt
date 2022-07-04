package com.ahmetbozkan.bgnmobi.domain.util

interface BaseMapper<in A, out B> {

    fun map(from: A): B
}