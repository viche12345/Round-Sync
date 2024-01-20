package de.felixnuesse.extract.extensions

fun Any.tag(): String { return this::class.java.simpleName }