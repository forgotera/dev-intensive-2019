package ru.skillbranch.devintensive.extensions



fun String.truncate(trunk:Int = 16):String{
    val str = this.dropLast(this.length - trunk).trimEnd()
    return str.padEnd(str.length+3,'.')
}

fun String.stripHtml() =
    this.replace(Regex("<[^<]*?>|&\\d+;"), "").replace(Regex("\\s+"), " ")
