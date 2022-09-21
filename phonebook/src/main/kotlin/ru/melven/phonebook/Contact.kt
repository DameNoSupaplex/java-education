package ru.melven.phonebook

import javafx.beans.property.Property
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.LocalDate

class Contact(name: String? = null, birthday: String? = null) {
    val nameProperty = SimpleStringProperty(this, "name", name)
    var name: String by nameProperty

    val birthdayProperty = SimpleObjectProperty(LocalDate.parse(birthday))
    var birthday: LocalDate by birthdayProperty

    override fun toString() = name
}

class CustomerModel : ItemViewModel<Contact>(Contact(birthday = LocalDate.now().toString())) {
    val name = bind(Contact::nameProperty)
    val birthday: Property<LocalDate> = bind(Contact::birthdayProperty)
}