package ru.melven.phonebook

import javafx.application.Application
import tornadofx.App

class PhonebookApp : App(PhonebookForm::class, Styles::class)

fun main(args: Array<String>) {
    Application.launch(PhonebookApp::class.java, *args)
}