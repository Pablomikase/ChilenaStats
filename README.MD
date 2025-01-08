# **Goal Pulse**

Goal Pulse es una aplicación móvil diseñada para los fanáticos del fútbol. Permite consultar datos sobre partidos, ligas y equipos de fútbol de manera sencilla y eficiente. Con un diseño accesible y personalizable, la app ofrece información en tiempo real y resúmenes claros para mantenerte siempre al tanto de tu equipo favorito.

## **Características Principales**

- Selección de país, liga y equipo favorito durante el proceso de onboarding.
- Visualización de datos en tiempo real sobre partidos, resultados y estadísticas.
- Diseño accesible y adaptable para dispositivos móviles y tabletas.
- Información de equipos, jugadores y ligas con datos actualizados.
- Resumen diario de los próximos partidos destacados.

## **Tecnologías Utilizadas**

- **Android:**
    - **Jetpack Compose**: Para la construcción de interfaces de usuario modernas.
    - **MVVM Architecture**: Para una separación clara de responsabilidades.
    - **Room**: Para la persistencia local de datos.
    - **Retrofit**: Para el consumo de APIs externas.
    - **Kotlin Coroutines y Flows**: Para manejo de datos en tiempo real y asincronismo.

- **API de Fútbol**: Utilizada para obtener datos actualizados sobre partidos, equipos y ligas.

## **Estructura del Proyecto**

El proyecto está organizado siguiendo la arquitectura Clean Architecture:

1. **Domain**: Define las entidades principales y casos de uso.
2. **Data**: Contiene los repositorios y los DataSources (remotos y locales).
3. **Framework**: Implementaciones específicas de los DataSources y otras integraciones de plataformas.
4. **UI**: Capa de presentación que incluye pantallas y lógica de interfaz de usuario.

## **Cómo Configurar el Proyecto**

### **Requisitos Previos**
- Android Studio Arctic Fox o superior.
- JDK 17 o superior.
- Gradle 8.0 o superior.

### **Configuración Inicial**
1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/goal-pulse.git