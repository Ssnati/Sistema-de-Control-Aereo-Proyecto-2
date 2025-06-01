# Sistema de Control Aéreo - Proyecto 2

Simulador interactivo implementado en Java cuyo objetivo principal es aprender y demostrar el manejo de hilos (threads) y el control de múltiples entidades en pantalla de manera simultánea. El proyecto fue desarrollado como un juego/simulador educativo para experimentar con concurrencia y gestión de recursos en interfaces gráficas.

## 🛫 Objetivo

Este proyecto permite simular la gestión de aviones con el objetivo de llegar a la pista de aterrizaje, empleando hilos para controlar de forma independiente cada entidad en pantalla. Es ideal para comprender conceptos de concurrencia y programación orientada a eventos en Java.

## 🛠️ Tecnologías utilizadas

- **Java**
- **Java Swing** (para la interfaz gráfica)
- Uso de hilos para el manejo independiente de entidades

## ⚙️ Configuración

Puedes personalizar el comportamiento del simulador editando el archivo de configuración:

`src/resources/config.properties`

Parámetros disponibles:
- `PLANE_IMAGE_URL`: Ruta de la imagen del avión.
- `AIRSTRIP_IMAGE_URL`: Ruta de la imagen de la pista de aterrizaje.
- `GENERATION_SPEED_IN_SECONDS`: Velocidad de generación de aviones (en segundos).
- `MAX_SPEED_INT`: Velocidad máxima de los aviones.
- `HIT_BOX_ARRIVED_ZONE_SIZE`: Tamaño de la zona de llegada.

Ejemplo de contenido:
```ini
PLANE_IMAGE_URL=src/resources/assets/Avion8BIT.png
AIRSTRIP_IMAGE_URL=src/resources/assets/Pista_Aterrizaje.png
GENERATION_SPEED_IN_SECONDS=1
MAX_SPEED_INT=10
HIT_BOX_ARRIVED_ZONE_SIZE=16
```

## 🚀 Ejecución

No requiere instalación especial. Puedes ejecutar el simulador como cualquier aplicación estándar de Java Swing:

1. Abre el proyecto en tu IDE favorito (por ejemplo, IntelliJ IDEA o Eclipse).
2. Asegúrate de que el archivo de configuración y los recursos están en su lugar.
3. Ejecuta la clase principal del proyecto.

## 👨‍💻 Autores

- Ssnati

## 📄 Licencia

Este proyecto no cuenta con una licencia específica.

---

¿Tienes sugerencias, dudas o ideas para mejorar el simulador? ¡No dudes en abrir un issue o contribuir!
