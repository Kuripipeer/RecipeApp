# RecipeApp

Aplicación de Kotlin Multiplatform sobre recetas con apoyo de IA para
sugerir y generar ideas de cocina en Android e iOS.

> Repositorio: `Kuripipeer/RecipeApp`

------------------------------------------------------------------------

## Descripción

RecipeApp es una aplicación de recetas construida con Kotlin
Multiplatform y Compose Multiplatform.\
La idea del proyecto es permitir que el usuario:

-   Busque recetas a partir de ingredientes o palabras clave.
-   Genere sugerencias de recetas usando un servicio de IA.
-   Consulte detalles de cada receta (ingredientes, pasos, tiempos).
-   Guarde recetas favoritas para revisarlas después.

El código compartido vive principalmente en el módulo `composeApp`, que
contiene la lógica común para Android e iOS.

------------------------------------------------------------------------

## Tecnologías y stack

-   Kotlin Multiplatform (Android + iOS)
-   UI: Compose Multiplatform
-   Gradle KTS como sistema de construcción
-   Arquitectura sugerida: capas `data / domain / ui` con enfoque MVVM
-   Integración con servicios de IA para sugerencias de recetas
    (pendiente de definir/implementar en este repo)

------------------------------------------------------------------------

## Estructura del proyecto

-   `/composeApp`\
    Código compartido entre plataformas (Android e iOS). Incluye:
    -   `commonMain`: lógica común, modelos, casos de uso, vistas
        compartidas.
    -   `androidMain`, `iosMain`, etc.: código específico para cada
        plataforma cuando sea necesario.
-   `/iosApp`\
    Punto de entrada de la app para iOS. Desde aquí se integra el módulo
    compartido y se configura el proyecto en Xcode.

------------------------------------------------------------------------

## Cómo ejecutar el proyecto

### 1. Clonar el repositorio

``` bash
git clone https://github.com/Kuripipeer/RecipeApp.git
cd RecipeApp
```

### 2. Ejecutar en Android

Opción 1: desde Android Studio

1.  Abre el proyecto en Android Studio.
2.  Deja que Gradle sincronice el proyecto.
3.  Selecciona un emulador o dispositivo físico Android.
4.  Ejecuta la app desde el botón Run.

### 3. Ejecutar en iOS

1.  Abre la carpeta `iosApp` en Xcode.
2.  Selecciona un simulador o dispositivo físico.
3.  Compila y corre el proyecto desde Xcode.

------------------------------------------------------------------------

## Funcionalidades (objetivo del proyecto)

-   Listado de recetas en pantalla principal.
-   Búsqueda de recetas por ingrediente o nombre.
-   Sugerencias de recetas generadas con IA a partir de:
    -   Ingredientes disponibles.
    -   Tipo de comida (desayuno, comida, cena, snack, etc.).
-   Vista de detalle de receta:
    -   Ingredientes.
    -   Pasos de preparación.
    -   Tiempo estimado.
-   Gestión básica de favoritos:
    -   Guardar receta como favorita.
    -   Listar recetas favoritas.

------------------------------------------------------------------------

## Autor

Christian Axel Moreno Flores (Kuripipeer)\
Proyecto académico/desarrollo personal en Kotlin Multiplatform.

Repositorio: https://github.com/Kuripipeer/RecipeApp
