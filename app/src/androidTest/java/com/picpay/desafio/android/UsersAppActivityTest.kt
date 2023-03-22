package com.picpay.desafio.android

import org.junit.Test

class UsersAppActivityTest {

    // nome funcoes: "when... thenShould..."

    @Test
    fun whenLoading_thenShouldAppearList() {
        setup {
            //funções de configuração
            loadUsersSuccess()
        } launch {
            //funções interações

        } check {
            //funções de checagem
            displayedList()
        }
    }

    @Test
    fun whenDeviceHasConnectionProblem_thenShouldShowErrorConnectionWarning() {
        setup {
            //funções de configuração
            loadUsersErrorConnection()
        } launch {
            //funções interações

        } check {
            //funções de checagem
            displayedConnectionWarningMessage()
        }
    }

    @Test
    fun whenSomethingWentWrongAtLoading_thenShouldShowGenericErrorWarning() {
        setup {
            //funções de configuração
            loadUsersGenericError()
        } launch {
            //funções interações

        } check {
            //funções de checagem
            displayedGenericWarningMessage()
        }
    }
}