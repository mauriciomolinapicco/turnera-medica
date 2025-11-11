# Turnera Médica - Sistema de Gestión de Turnos Médicos

Sistema de gestión de turnos médicos desarrollado en Java con interfaz gráfica Swing. Permite administrar médicos, pacientes y turnos, así como generar reportes de cobros.

## 📋 Descripción

Turnera Médica es una aplicación de escritorio que facilita la gestión de una clínica médica, permitiendo:
- Administrar información de médicos y pacientes
- Gestionar turnos médicos
- Buscar turnos por médico o paciente
- Generar reportes de cobros médicos

## ✨ Características

### Gestión de Médicos
- Panel de administración de médicos
- Búsqueda de médicos
- Registro de información: nombre completo, matrícula, especialidad, precio de consulta

### Gestión de Pacientes
- Panel de administración de pacientes
- Búsqueda de pacientes
- Registro de información: DNI, nombre completo, ficha médica, teléfono

### Gestión de Turnos
- Panel de administración de turnos
- Búsqueda de turnos por médico
- Búsqueda de turnos por paciente
- Asignación de turnos con fecha y hora

### Reportes
- Reporte de cobro de un médico específico
- Reporte de cobro de todos los médicos

## 🏗️ Arquitectura

El proyecto utiliza el patrón de diseño **DAO (Data Access Object)** para separar la lógica de acceso a datos:

- **Modelos**: `Medico`, `Paciente`, `Turno`
- **DAO**: Interfaces y implementaciones MySQL para acceso a datos
- **Servicios**: Capa de lógica de negocio (`MedicoService`, `PacienteService`, `TurnoService`)
- **Vista**: Paneles Swing para la interfaz de usuario
- **Factory**: `ConnectionFactory` para gestión de conexiones a base de datos

## 📦 Requisitos

- **Java JDK 8 o superior**
- **MySQL 5.7 o superior**
- **MySQL Connector/J 8.4.0** (incluido en `resources/`)

## 🚀 Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone <repository-url>
cd turnera-medica
```

### 2. Configurar la base de datos

1. Crear la base de datos MySQL:
```sql
CREATE DATABASE turnera;
```

2. Configurar las credenciales de conexión en `ConnectionFactory.java`:
```java
private static final String USERNAME = "root";
private static final String PASSWORD = "tu_contraseña";
private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/turnera";
```

### 3. Crear las tablas

Ejecutar los scripts SQL necesarios para crear las tablas:
- `medicos`
- `pacientes`
- `turnos`

### 4. Compilar el proyecto

```bash
javac -cp "resources/mysql-connector-j-8.4.0.jar" -d bin src/TurneraMedicaTP/*.java
```

### 5. Ejecutar la aplicación

```bash
java -cp "bin:resources/mysql-connector-j-8.4.0.jar" TurneraMedicaTP.Main
```

## 📁 Estructura del Proyecto

```
turnera-medica/
├── bin/                          # Archivos compilados (.class)
├── resources/
│   └── mysql-connector-j-8.4.0.jar
├── src/
│   └── TurneraMedicaTP/
│       ├── Main.java             # Punto de entrada de la aplicación
│       ├── PanelManager.java     # Gestor de paneles y navegación
│       │
│       # Modelos
│       ├── Medico.java
│       ├── Paciente.java
│       ├── Turno.java
│       │
│       # DAO
│       ├── MedicoDAO.java
│       ├── MedicoDAOMySQLImpl.java
│       ├── PacienteDAO.java
│       ├── PacienteDAOMySQLImpl.java
│       ├── TurnoDAO.java
│       ├── TurnoDAOMySQLImpl.java
│       │
│       # Servicios
│       ├── MedicoService.java
│       ├── PacienteService.java
│       ├── TurnoService.java
│       ├── Service.java
│       │
│       # Excepciones
│       ├── DAOException.java
│       ├── ServiceException.java
│       │
│       # Utilidades
│       ├── ConnectionFactory.java
│       │
│       # Vistas/Paneles
│       ├── PanelBase.java
│       ├── PanelMedicos.java
│       ├── PanelPacientes.java
│       ├── PanelTurnos.java
│       ├── PanelBusquedaMedico.java
│       ├── PanelBusquedaPacientes.java
│       ├── PanelBuscarTurnos.java
│       ├── PanelBuscarTurnosPorPaciente.java
│       ├── PanelReporteCobroMedico.java
│       ├── PanelReporteCobroMedicos.java
│       │
│       # Table Models
│       ├── BaseTableModel.java
│       ├── MedicoTableModel.java
│       ├── PacienteTableModel.java
│       └── TurnoTableModel.java
└── readme.md
```

## 🎯 Uso

1. **Iniciar la aplicación**: Ejecutar `Main.java`
2. **Navegar por los menús**: Usar la barra de menú superior para acceder a las diferentes secciones
3. **Gestionar médicos**: Menú "Medicos" → "Panel de Médicos" o "Buscar medico"
4. **Gestionar pacientes**: Menú "Pacientes" → "Panel de pacientes" o "Buscar paciente"
5. **Gestionar turnos**: Menú "Turnos" → "Panel de turnos" o buscar por médico/paciente
6. **Ver reportes**: Menú "Reportes" → Seleccionar el tipo de reporte deseado

## 🛠️ Tecnologías Utilizadas

- **Java**: Lenguaje de programación
- **Java Swing**: Framework para interfaz gráfica
- **MySQL**: Base de datos relacional
- **MySQL Connector/J**: Driver JDBC para MySQL
- **JDBC**: API para acceso a bases de datos

## 📝 Notas

- La aplicación utiliza transacciones para garantizar la integridad de los datos
- Las conexiones a la base de datos se gestionan mediante `ConnectionFactory`
- El proyecto sigue el patrón DAO para mantener la separación de responsabilidades

## 🔧 Configuración de Base de Datos

Asegúrate de que MySQL esté corriendo y que la base de datos `turnera` esté creada antes de ejecutar la aplicación. Las credenciales por defecto son:
- Usuario: `root`
- Contraseña: `12345678`
- Base de datos: `turnera`
- Puerto: `3306`

**⚠️ Importante**: Cambia las credenciales en `ConnectionFactory.java` antes de usar en producción.

## 📄 Licencia

Este proyecto es un trabajo práctico académico.

