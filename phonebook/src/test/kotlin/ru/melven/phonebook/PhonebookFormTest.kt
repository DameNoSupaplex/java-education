package ru.melven.phonebook

import impl.org.controlsfx.skin.NotificationBar
import javafx.scene.Scene
import javafx.scene.control.DatePicker
import javafx.scene.input.KeyCode
import javafx.stage.Stage
import org.junit.jupiter.api.extension.ExtendWith
import org.testfx.api.FxRobot
import org.testfx.assertions.api.Assertions.assertThat
import org.testfx.framework.junit5.ApplicationExtension
import org.testfx.framework.junit5.Start
import java.time.LocalDate
import kotlin.test.Test


@ExtendWith(ApplicationExtension::class)
class PhonebookFormTest {

    @Start
    private fun start(stage: Stage) {
        stage.scene = Scene(PhonebookForm().root)
        stage.show()
    }

    @Test
    fun test_form(robot: FxRobot) {
        val saveButton = robot.lookup("#save").queryButton()

        assertThat(saveButton).isDisabled
        robot.clickOn("#name").write("John Smith").push(KeyCode.ENTER)
        assertThat(saveButton).isEnabled
        robot.interact{
            robot.lookup("#birthday").query<DatePicker>().value = LocalDate.of(1970, 1, 1)
        }
        assertThat(saveButton).isEnabled

        robot.clickOn(saveButton)
        assertThat(robot.lookup(".notification-bar").queryAs(NotificationBar::class.java).title).isEqualTo("Contact saved!")
    }
}