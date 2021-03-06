package com.github.kerberos.authorizationservice.interaction.cqrs

import com.github.kerberos.authorizationservice.interaction.role.CreateRole
import com.github.kerberos.authorizationservice.interaction.role.create.CreateRoleCommand
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class SimpleCommandBusTest {
    private val createRole: CreateRole = mockk()
    private val simpleCommandBus: SimpleCommandBus = SimpleCommandBus(createRole)

    @Test
    fun `given a command, should dispatch execution to appropriate handler`() {
        justRun { createRole.handle(any()) }

        simpleCommandBus.dispatch(command)

        verify(exactly = 1) {
            createRole.handle(command)
        }
    }

    companion object {
        private val command = CreateRoleCommand(
                description = "Person who delivers lectures in a school",
                name = "Teacher"
        )
    }
}
