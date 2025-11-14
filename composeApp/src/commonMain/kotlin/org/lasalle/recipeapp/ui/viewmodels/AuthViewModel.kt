package org.lasalle.recipeapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.lasalle.recipeapp.data.services.KtorfitFactory
import org.lasalle.recipeapp.models.LoginBody
import org.lasalle.recipeapp.models.RegisterBody

class AuthViewModel() : ViewModel(){
    // DE ALGUNA FORMA CREAR LA INSTANCIA DE KTORFIT
    // CREAR EL SERVICIO DE AUTENTICACION
    // MANDAR A LLAMAR A LA API CON LOS DATOS QUE ME PIDEN
    // TODO: UTILIZAR ESTE MENSAJE PARA UN SNACKBAR
    var message by mutableStateOf("")
    val authService = KtorfitFactory.getAuthService()

    fun register(name: String, email: String, password : String){
        viewModelScope.launch {
            try {
                val register = RegisterBody(
                    name = name,
                    email = email,
                    password = password
                )
                val result = authService.register(register)
                if (result.isLogged){
                    // TODO: CREAR NAVEGACIÓN A HOME SCREEN
                } else{
                    message = result.message
                }
                println(result.toString())
            }catch (e: Exception){
                message = "No se pudo registrar el usuario"
                println(e.toString())
            }
        }
    }

    fun login(email: String, password: String){
        viewModelScope.launch {
            try {
                val request = LoginBody(
                    email = email,
                    password = password
                )
                val response = authService.login(request)
                if (response.isLogged){
                    // TODO: NAVEGAR A HOME SCREEN Y GUARDAR EL USER ID EN MEMORIA
                } else{
                    message = response.message
                }
            } catch (e: Exception){
                println(e.toString())
            }
        }
    }
}