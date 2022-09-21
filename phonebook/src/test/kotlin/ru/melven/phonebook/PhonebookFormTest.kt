package ru.melven.phonebook

import impl.org.controlsfx.skin.NotificationBar
import javafx.scene.control.Button
import javafx.scene.control.DatePicker
import javafx.scene.input.KeyCode
import org.testfx.assertions.api.Assertions.assertThat
import java.time.LocalDate
import kotlin.test.Test

class PhonebookFormTest: TestBase() {
    override fun initView() {
        showView<PhonebookForm, PhonebookApp>()
    }

    @Test
    fun testForm() {
        val saveButton = lookup("#save").query<Button>()

        clickOn("#name").write("John Smith").push(KeyCode.ENTER)
        assertThat(saveButton).isDisabled
        interact{
            lookup("#birthday").query<DatePicker>().value = LocalDate.of(1970, 1, 1)
        }
        assertThat(saveButton).isEnabled

        clickOn(saveButton)
        assertThat(lookup(".notification-bar").queryAs(NotificationBar::class.java).title).isEqualTo("Customer saved!")
    }
}