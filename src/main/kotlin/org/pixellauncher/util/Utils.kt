package org.pixellauncher.util

fun String.removeWhitespace() = this.replace("\\s+".toRegex(), "")