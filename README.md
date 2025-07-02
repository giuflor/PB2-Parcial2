# 🕵️‍♂️ Sistema de Cazadores de Recompensas
> Trabajo Práctico de Programación Básica II – Universidad Nacional de La Matanza  
> Docentes: Moreira Rubén Darío y Abal, Juan Marcelo

## Integrantes
|  Apellido  | Nombres | Github |
| ------------ | ------------ | ------------ |
|Maidana | Franco Gabriel | [![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Maidana0)|
| Pachilla | Giuliana Florencia| [![Github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/giuflor)|

---

## 🎯 **Objetivo**

Desarrollar un sistema en Java que modele un universo de cazadores de recompensas, prófugos y zonas, aplicando:
- Programación orientada a objetos (herencia, polimorfismo, composición, delegación)
- Patrón Decorator para la evolución de prófugos
- Desarrollo colaborativo con Git y GitHub
- Buenas prácticas, TDD y cobertura de tests mínima del 70%

---

## 🛠 **Descripción del sistema**

El sistema permite:
- Registrar **cazadores** (urbanos, rurales, sigilosos) que capturan o intimidan prófugos según reglas específicas.
- Modelar **prófugos** que pueden evolucionar entrenándose (artes marciales, elite, protección legal).
- Gestionar **zonas** donde se produce la captura.
- Calcular la experiencia ganada por los cazadores.
- Generar **reportes** para el jefe de la agencia:
  - Todos los prófugos capturados
  - Prófugo capturado más hábil
  - Cazador con más capturas

El proyecto incluye:
- Excepciones personalizadas para capturas duplicadas, prófugos repetidos, etc.
- Tests JUnit que cubren capturas, intimidaciones, evolución de prófugos y reportes.

---

## ⚙️ **Tecnologías y herramientas**
- Java 8+ (se utiliza el jdk 21)
- JUnit 4 (4.13.2)
- Hamcrest (3.0.0)
- Eclipse IDE
- Git y GitHub para control de versiones y trabajo colaborativo

---

## 🚀 **Cómo ejecutar el proyecto**

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/giuflor/PB2-Parcial2.git
    ```
2. Importar el proyecto en Eclipse como Java Project.
3. Ejecutar las clases de test (paquete ar.edu.unlam.pb2.sitemaDeCazadores) para validar la cobertura.

---

## 📦 Diagrama de clases (resumen)
[![diagrama-agencia-de-cazarecompensas.png](https://i.postimg.cc/76dcN1ym/diagrama-agencia-de-cazarecompensas.png)](https://postimg.cc/sQPwjhxh)

## ✅ Cobertura de Tests (resumen)

### 🏢 **AgenciaTest**
- ✅ queLaAgenciaObtengaTodosLosProfugosCapturados
- ✅ queLaAgenciaObtengaElProfugoMasHabilCapturado
- ✅ queLaAgenciaObtengaElCazadorConMasCapturas
- ✅ queLaAgenciaDevuelvaNullSiNoHayCapturados
- ✅ queLaAgenciaElijaUnoEnCasoDeEmpateDeCapturas
- ✅ queNoSeAgreguenCazadoresDuplicadosEnLaAgencia
- ✅ queLaAgenciaAdministreCazadoresEnVariasZonas

---

### 🕵️‍♂️ **CazadorTest**
- ✅ queUnCazadorUrbanoPuedaCapturarUnProfugoNoNerviosoConMenorInocencia
- ✅ queUnCazadorIntimideSiNoPuedeCapturar
- ✅ queElCazadorSumeExperienciaCorrectamente
- ✅ queLanceExcepcionSiZonaEsNula *(ExceptionCapturaFallida)*
- ✅ queNoSeIntimideNiSeCaptureUnProfugoConMayorInocenciaQueLaExperienciaDelCazador
- ✅ queLanceExceptionSiUnCazadorIntentaCapturarUnMismoProfugoCapturado *(ExceptionElProfugoYaFueCapturado)*
- ✅ queSeMantengaElOrdenDeCapturas
- ✅ queNoSeIntimideAUnProfugoYaIntimidadoEnOtraZona
- ✅ queElCazadorCaptureEIntimideEnLaMismaZonaYCalculeBienLaExperiencia
- ✅ queUnCazadorNoPuedaCapturarUnProfugoConHabilidadCero
- ✅ queUnCazadorNoCaptureSiSuExperienciaEsIgualALaInocenciaDelProfugo
- ✅ queUnCazadorUrbanoNoSeaIgualQueUnCazadorRuralAunqueTenganElMismoNombre
- ✅ queUnCazadorUrbanoSeaIgualQueOtroConElMismoNombre


---

### 🧬 **ProfugoEntrenadoTest**
- ✅ queCazadorIntimideAProfugoEntrenadoYSeReduzcaCorrectamenteLosAtributos
- ✅ queCazadorPuedaCapturarAProfugoEntrenadoConHabilidadDuplicada
- ✅ queCazadorNoCaptureNiIntimideSiInocenciaSuperaExperienciaAunConEntrenamiento
- ✅ queProteccionLegalMantengaMinimoDeInocenciaAlIntimidar
- ✅ queLaHabilidadNoSupereElMaximoDe100ConArtesMarciales

---

### 🧪 **ProfugoTest**
- ✅ queSePuedaCrearUnProfugo
- ✅ queSePuedaReducirLaInocenciaYHabilidad
- ✅ queSePuedaEvolucionarConEntrenamientos
- ✅ queLaInocenciaNoSeaNegativa
- ✅ queLaHabilidadNoSeaNegativa
- ✅ queSePuedaCambiarElEstadoDeNerviosismo
- ✅ queDosProfugosConElMismoNombreSeanIguales
- ✅ queElHashCodeDeProfugosConElMismoNombreSeaIgual
- ✅ queLosAtributosSeGuardenCorrectamente



