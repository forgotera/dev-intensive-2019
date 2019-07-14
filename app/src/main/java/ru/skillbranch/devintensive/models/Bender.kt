package ru.skillbranch.devintensive.models

import android.util.Log
import android.view.View.Z

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question

    }

    fun listenAnswer(answer: String):Pair<String, Triple<Int,Int,Int>>{
        return if(question.validation(answer)){
             answer.toLowerCase()
            if (question.answers.contains(answer)) {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            } else {
                if (status == Status.CRITICAL) {
                    status = Status.NORMAL
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                } else {
                    status = status.nextStatus()
                    "Это неправильный ответ\n${question.question}" to status.color
                }
            }
        }else{
           return "${question.mistake}\n${question.question}" to status.color
        }

    }

    enum class Status(val color: Triple<Int,Int,Int>) {
        NORMAL(Triple(255, 255, 255)) ,
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)) ;

        fun nextStatus():Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1 ]
            }else{
                values()[0]
            }
        }
    }



    enum class Question(val question: String, val answers: List<String>,val mistake:String?) {
        NAME("Как меня зовут?", listOf("Бендер", "Bender"),mistake = "Имя должно начинаться с заглавной буквы") {
            override fun validation(answer:String):Boolean {
               return answer[0].isUpperCase()
            }

            override fun nextQuestion(): Question =PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender"),mistake = "Профессия должна начинаться со строчной буквы") {
            override fun validation(answer: String):Boolean {
                return answer[0].isLowerCase()
            }

            override fun nextQuestion(): Question = MATERIAL


        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood"),mistake = "Материал не должен содержать цифр") {
            override fun validation(answer: String):Boolean {
              return answer.all{it.isLetter()}
            }

            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993"),"Год моего рождения должен содержать только цифры") {
            override fun validation(answer: String):Boolean {
                return answer.all { it.isDigit() }
            }

            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057"),"Серийный номер содержит только цифры, и их 7") {
            override fun validation(answer: String):Boolean {
                return answer.all { it.isDigit() } && answer.length == 7
            }

            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf(),mistake = null) {
            override fun validation(answer: String):Boolean {
                return true
            }

            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion():Question
        abstract fun validation(answer:String):Boolean
    }
}