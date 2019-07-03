package ru.skillbranch.devintensive.utils

object Utils {


    fun parseFullName(fullName: String?): Pair<String?, String?> {
        var firstName: String? = null
        var lastName: String? = null
        if (fullName != null) {
            if(fullName.length > 1) {
                val parts: List<String>? = fullName.split(" ")
                firstName = parts?.getOrNull(0)?.trim()
                lastName = parts?.getOrNull(1)?.trim()
            }
        }
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var initials: String? = when (firstName) {
            null -> ""
            " " -> ""
            "" -> ""
            else -> firstName[0].toUpperCase().toString()
        }
        initials += when (lastName) {
            null -> ""
            " " -> ""
            "" -> ""
            else -> lastName[0].toUpperCase().toString()
        }
        if (initials?.isEmpty()!!) initials = null
        return initials
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var transString: String = ""
        val letterMap = mapOf<String, String>(
            "а" to "a", "б" to "b", "в" to "v",
            "г" to "g","д" to "d","е" to "e","ё" to "e","ж" to "zh",
            "з" to "z","и" to "i","й" to "i","к" to "k","л" to "l","м" to "m",
            "н" to "n","о" to "o","п" to "p","р" to "r","с" to "s","т" to "t",
            "у" to "u","ф" to "f","х" to "h","ц" to "c","ч" to "ch","ш" to "sh",
            "щ" to "sh'","ъ" to "","ы" to "i","ь" to "","э" to "e","ю" to "yu",
            "я" to "ya"
        )

        for(char in payload){

           if(char.toString() == " "){
              transString += divider

           }else if (letterMap[char.toString()] != null) {
              transString += letterMap[char.toString()]

           }else if(letterMap[char.toLowerCase().toString()] != null){

               val buff = letterMap[char.toLowerCase().toString()]
               if(buff!!.length>1) {
                   transString += buff[0].toUpperCase()
                   transString += buff[1]
               }else{
                   transString += buff.toUpperCase()
               }
               
           }else{
               transString += char
           }

        }
        return transString
    }
}