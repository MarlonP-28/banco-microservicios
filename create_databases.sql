-- Script para crear las bases de datos del banco microservicios

-- Conéctate como el usuario postgres (el usuario por defecto con privilegios de creación)
-- Si ya estás conectado como un usuario con permisos para crear bases de datos (como myuser), puedes omitir la reconexión.
-- \c postgres

-- Crea la base de datos para el servicio de clientes y personas
CREATE DATABASE client_persona;

-- Comenta la siguiente línea si no necesitas cambiar el propietario de la base de datos
-- ALTER DATABASE client_persona OWNER TO myuser;

-- Conéctate a la base de datos client_persona (opcional, si quieres ejecutar comandos específicos en esta base de datos)
-- \c client_persona

-- Crea la base de datos para el servicio de cuentas y movimientos
CREATE DATABASE account_movement;

-- Comenta la siguiente línea si no necesitas cambiar el propietario de la base de datos
-- ALTER DATABASE account_movement OWNER TO myuser;

-- Conéctate a la base de datos account_movement (opcional)
-- \c account_movement

-- ¡Script completado!