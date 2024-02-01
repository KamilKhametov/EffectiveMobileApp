package com.khametov.effectivemobileapp.base


interface BaseMapper<FROM, TO> {
    fun map(from: FROM): TO
}