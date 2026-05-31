-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-05-2026 a las 13:38:17
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `licoreria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `backup_licores`
--

CREATE TABLE `backup_licores` (
  `nombre` varchar(100) NOT NULL,
  `fecha_capacidad` int(100) NOT NULL,
  `stock` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `backup_refrescos`
--

CREATE TABLE `backup_refrescos` (
  `nombre` varchar(100) DEFAULT NULL,
  `fecha_caducidad` int(100) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `licores`
--

CREATE TABLE `licores` (
  `id_licor` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `fecha_caducidad` date DEFAULT NULL,
  `stock` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `licores`
--

INSERT INTO `licores` (`id_licor`, `nombre`, `fecha_caducidad`, `stock`) VALUES
(7, 'negrita', '2029-01-11', 13),
(8, 'eristoff', '2027-04-12', 5),
(14, 'Puerto de indias', '2028-04-12', 45);

--
-- Disparadores `licores`
--
DELIMITER $$
CREATE TRIGGER `backup_licores` BEFORE DELETE ON `licores` FOR EACH ROW begin
insert into backup_licores
(nombre, fecha_caducidad, stock)
VALUES(OLD.nombre, OLD.fecha_caducidad, OLD.stock);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `refrescos`
--

CREATE TABLE `refrescos` (
  `id_refrescos` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `fecha_caducidad` date DEFAULT NULL,
  `stock` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `refrescos`
--

INSERT INTO `refrescos` (`id_refrescos`, `nombre`, `fecha_caducidad`, `stock`) VALUES
(1, 'coca cola', '2026-05-24', 32),
(2, 'fanta naranja', '2028-11-02', 23),
(6, 'nestea', '2027-04-22', 23);

--
-- Disparadores `refrescos`
--
DELIMITER $$
CREATE TRIGGER `backup_refrescos` BEFORE DELETE ON `refrescos` FOR EACH ROW begin

insert into backup_refrescos
(nombre,fecha_caducidad, stock)

values(OLD.nombre, OLD.fecha_caducidad, OLD.stock);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_inventario`
--

CREATE TABLE `registro_inventario` (
  `id_registro` int(11) NOT NULL,
  `id_empleado_aux` int(11) DEFAULT NULL,
  `id_refrescos_aux` int(11) DEFAULT NULL,
  `id_licor_aux` int(11) DEFAULT NULL,
  `cantidad_ingresada` int(11) NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitudes_compra`
--

CREATE TABLE `solicitudes_compra` (
  `id_solicitud` int(11) NOT NULL,
  `id_gerente_aux` int(11) DEFAULT NULL,
  `proveedor` varchar(100) DEFAULT NULL,
  `monto_total` decimal(12,2) DEFAULT NULL,
  `fecha_solicitud` date DEFAULT NULL,
  `estado` enum('Pendiente','Aprobado','Recibido') DEFAULT 'Pendiente',
  `producto` varchar(50) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `solicitudes_compra`
--

INSERT INTO `solicitudes_compra` (`id_solicitud`, `id_gerente_aux`, `proveedor`, `monto_total`, `fecha_solicitud`, `estado`, `producto`, `tipo`, `cantidad`) VALUES
(1, NULL, 'asd', 100.00, '2026-05-01', 'Aprobado', 'negrita', 'LICOR', 10),
(2, NULL, 'asdf', 89.00, '2026-05-04', 'Aprobado', 'Agua', 'REFRESCO', 12),
(3, NULL, 'asdfg', 199.00, '2026-05-05', 'Aprobado', 'Agua', 'REFRESCO', 18);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `cargo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `contrasena`, `cargo`) VALUES
(5, 'Celeste', '12345', ''),
(6, 'Santiago', '1234567', ''),
(7, 'Axel', '1234', ''),
(8, 'Raul', '5478123', ''),
(9, 'Miller', '657849', ''),
(10, 'Javier', '123456', 'Gerente'),
(11, 'Paco', '98761234334', 'Empleado'),
(12, 'Luis', '12345', 'Empleado'),
(13, '', '', 'Gerente'),
(14, '', '', 'Gerente'),
(15, '', '', 'Gerente'),
(16, '', '', 'Gerente'),
(17, '', '', 'Empleado'),
(18, 'Axel', '12345', 'Gerente'),
(19, 'Javier', '123456', 'Gerente'),
(20, '', '', 'Gerente'),
(21, 'pepito', '12345', 'Gerente'),
(22, 'santiago', '12345678', 'Gerente'),
(23, 'AsdSAdgg', '11111111111111', 'Empleado'),
(24, 'kjasdjieroiargkdg', '1231234256344869', 'Empleado'),
(25, 'santiago', 'aljsdh', 'Gerente'),
(26, 'santiago', '', 'Gerente');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `licores`
--
ALTER TABLE `licores`
  ADD PRIMARY KEY (`id_licor`);

--
-- Indices de la tabla `refrescos`
--
ALTER TABLE `refrescos`
  ADD PRIMARY KEY (`id_refrescos`);

--
-- Indices de la tabla `registro_inventario`
--
ALTER TABLE `registro_inventario`
  ADD PRIMARY KEY (`id_registro`),
  ADD KEY `id_empleado_aux` (`id_empleado_aux`),
  ADD KEY `id_refrescos_aux` (`id_refrescos_aux`),
  ADD KEY `id_licor_aux` (`id_licor_aux`);

--
-- Indices de la tabla `solicitudes_compra`
--
ALTER TABLE `solicitudes_compra`
  ADD PRIMARY KEY (`id_solicitud`),
  ADD KEY `id_gerente_aux` (`id_gerente_aux`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `licores`
--
ALTER TABLE `licores`
  MODIFY `id_licor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `refrescos`
--
ALTER TABLE `refrescos`
  MODIFY `id_refrescos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `registro_inventario`
--
ALTER TABLE `registro_inventario`
  MODIFY `id_registro` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `solicitudes_compra`
--
ALTER TABLE `solicitudes_compra`
  MODIFY `id_solicitud` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `registro_inventario`
--
ALTER TABLE `registro_inventario`
  ADD CONSTRAINT `registro_inventario_ibfk_1` FOREIGN KEY (`id_empleado_aux`) REFERENCES `empleados` (`id_empleado`),
  ADD CONSTRAINT `registro_inventario_ibfk_2` FOREIGN KEY (`id_refrescos_aux`) REFERENCES `refrescos` (`id_refrescos`),
  ADD CONSTRAINT `registro_inventario_ibfk_3` FOREIGN KEY (`id_licor_aux`) REFERENCES `licores` (`id_licor`);

--
-- Filtros para la tabla `solicitudes_compra`
--
ALTER TABLE `solicitudes_compra`
  ADD CONSTRAINT `solicitudes_compra_ibfk_1` FOREIGN KEY (`id_gerente_aux`) REFERENCES `gerentes` (`id_gerente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
