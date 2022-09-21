package ru.melven.phonebook

import org.controlsfx.control.Notifications
import tornadofx.*

class PhonebookForm : View("Phonebook") {
    private val model: CustomerModel by inject()

    override val root = form {
        fieldset("Personal Information") {
            field("Name") {
                textfield(model.name) { id = "name" }.required()
            }

            field("Birthday") {
                datepicker(model.birthday) { id = "birthday" }
            }
        }

        button("Save") {
            id = "save"
            action {
                model.commit {
                    val customer = model.item
                    Notifications.create()
                        .title("Contact saved!")
                        .text("${customer.name} was born ${customer.birthday}")
                        .owner(this)
                        .showInformation()
                }
            }

            enableWhen(model.valid)
        }
    }
}