-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 01-12-2017 a las 05:31:03
-- Versión del servidor: 5.7.19
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
CREATE TABLE IF NOT EXISTS `asistencia` (
  `idAsistencia` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `activo` int(1) NOT NULL,
  PRIMARY KEY (`idAsistencia`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asistencia`
--

INSERT INTO `asistencia` (`idAsistencia`, `nombre`, `activo`) VALUES
(1, 'Asistio', 1),
(2, 'No asistio', 1),
(3, 'En espera', 1),
(4, 'Cancelado', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `barrio`
--

DROP TABLE IF EXISTS `barrio`;
CREATE TABLE IF NOT EXISTS `barrio` (
  `idBarrio` int(11) NOT NULL AUTO_INCREMENT,
  `idDepartamento` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `activo` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idBarrio`),
  KEY `fk_Barrio_Departamento_idx` (`idDepartamento`)
) ENGINE=InnoDB AUTO_INCREMENT=364 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `barrio`
--

INSERT INTO `barrio` (`idBarrio`, `idDepartamento`, `nombre`, `activo`) VALUES
(1, 1, 'Artigas', 1),
(2, 1, 'Baltasar Brum', 1),
(3, 1, 'Bella Union', 1),
(4, 1, 'Bernabe Rivera', 1),
(5, 1, 'Calpica Itacumbú', 1),
(6, 1, 'ColoniaPalma', 1),
(7, 1, 'Cuaró', 1),
(8, 1, 'Javier dee Viana', 1),
(9, 1, 'Paso Campamento', 1),
(10, 1, 'Sequeira', 1),
(11, 1, 'Tomas Gomensoro', 1),
(12, 2, 'Aeropuerto', 1),
(13, 2, 'Aguas Corrientes', 1),
(14, 2, 'Araminda', 1),
(15, 2, 'Argentino', 1),
(16, 2, 'Atlántida', 1),
(17, 2, 'Barra de Carrasco', 1),
(18, 2, 'Barros Blancos', 1),
(19, 2, 'Bello Horizonte', 1),
(20, 2, 'Biarritz', 1),
(21, 2, 'Bolívar', 1),
(22, 2, 'Campo Militar', 1),
(23, 2, 'Canelon Grande Represa', 1),
(24, 2, 'Canelones', 1),
(25, 2, 'Castellanos', 1),
(26, 2, 'Chamizo', 1),
(27, 2, 'ColoniaNicolich', 1),
(28, 2, 'Colonia Treinta y Tres Orientales', 1),
(29, 2, 'CostaAzul', 1),
(30, 2, 'Cuchilla Alta', 1),
(31, 2, 'El Bosque', 1),
(32, 2, 'El Dorado', 1),
(33, 2, 'El Fortín de Santa Rosa', 1),
(34, 2, 'El Pinar', 1),
(35, 2, 'Empalme Olmos', 1),
(36, 2, 'Estación Migues', 1),
(37, 2, 'Estación Pedrera', 1),
(38, 2, 'Estación Tapia', 1),
(39, 2, 'Fray Marcos', 1),
(40, 2, 'Guazuvirá', 1),
(41, 2, 'Guazuvirá Nuevo', 1),
(42, 2, 'Jaureguiberry', 1),
(43, 2, 'Joanicó', 1),
(44, 2, 'Joaquín Suárez', 1),
(45, 2, 'La Escobilla', 1),
(46, 2, 'La Floresta', 1),
(47, 2, 'La Paz', 1),
(48, 2, 'La Tuna', 1),
(49, 2, 'Lagomar', 1),
(50, 2, 'Las Piedras', 1),
(51, 2, 'Las Toscas', 1),
(52, 2, 'Las Vegas', 1),
(53, 2, 'Lomas de Solymar', 1),
(54, 2, 'Los Cerrillos', 1),
(55, 2, 'Los Titanes', 1),
(56, 2, 'Marindia', 1),
(57, 2, 'Médanos de Solymar', 1),
(58, 2, 'Migues', 1),
(59, 2, 'Montes', 1),
(60, 2, 'Neptunia', 1),
(61, 2, 'Pando', 1),
(62, 2, 'Paraje San Juan', 1),
(63, 2, 'Parque Carrasco', 1),
(64, 2, 'Parque del Plata', 1),
(65, 2, 'Paso de Carrasco', 1),
(66, 2, 'Paso de Pache', 1),
(67, 2, 'Paso del Bote', 1),
(68, 2, 'Piedras de Afilar', 1),
(69, 2, 'Pinamar', 1),
(70, 2, 'Pine Park', 1),
(71, 2, 'Progreso', 1),
(72, 2, 'Salinas', 1),
(73, 2, 'San Antonio', 1),
(74, 2, 'San Bautista', 1),
(75, 2, 'San Jacinto', 1),
(76, 2, 'San José de Carrasco', 1),
(77, 3, 'Aceguá', 1),
(78, 3, 'Bañado de Medina', 1),
(79, 3, 'Cerro de las Cuentas', 1),
(80, 3, 'Fraile Muerto', 1),
(81, 3, 'Isidoro Noblía', 1),
(82, 3, 'Melo', 1),
(83, 3, 'Plácido Rosas', 1),
(84, 3, 'Río Branco', 1),
(85, 3, 'Tupambaé', 1),
(86, 4, 'Agraciada', 1),
(87, 4, 'Artilleros', 1),
(88, 4, 'Barker', 1),
(89, 4, 'Campana', 1),
(90, 4, 'Carmelo', 1),
(91, 4, 'Colonia', 1),
(92, 4, 'Colonia Valdense', 1),
(93, 4, 'Conchilla', 1),
(94, 4, 'Cufré', 1),
(95, 4, 'Florencio Sánchez', 1),
(96, 4, 'Juan Lacaze', 1),
(97, 4, 'La Estanzuela', 1),
(98, 4, 'La Paz', 1),
(99, 4, 'Los Pinos', 1),
(100, 4, 'Miguelete', 1),
(101, 4, 'Minuano', 1),
(102, 4, 'Nueva Helvecia', 1),
(103, 4, 'Nueva Palmira', 1),
(104, 4, 'Ombúes de Lavalle', 1),
(105, 4, 'Playa Fomento', 1),
(106, 4, 'Riachuelo', 1),
(107, 4, 'Rosario', 1),
(108, 4, 'Santa Ana', 1),
(109, 4, 'Tarariras', 1),
(110, 5, 'Blanquillo', 1),
(111, 5, 'Carlos Reyles', 1),
(112, 5, 'Centenario', 1),
(113, 5, 'Durazno', 1),
(114, 5, 'Feliciano', 1),
(115, 5, 'La Paloma', 1),
(116, 5, 'Santa Bernardina', 1),
(117, 5, 'Sarandí del Yí', 1),
(118, 6, 'Andresito', 1),
(119, 6, 'Ismael Cortinas', 1),
(120, 6, 'Trinidad', 1),
(121, 7, '25 de Agosto', 1),
(122, 7, '25 de Mayo', 1),
(123, 7, 'Capilla del Sauce', 1),
(124, 7, 'Cardal', 1),
(125, 7, 'Casupá', 1),
(126, 7, 'Cerro Colorado', 1),
(127, 7, 'Chamizo', 1),
(128, 7, 'Florida', 1),
(129, 7, 'Fray Marcos', 1),
(130, 7, 'Goñi', 1),
(131, 7, 'Independencia', 1),
(132, 7, 'La Cruz', 1),
(133, 7, 'Mendoza Chico', 1),
(134, 7, 'Mendoza Grande', 1),
(135, 7, 'Monte Coral', 1),
(136, 7, 'Pintado', 1),
(137, 7, 'Polanco del Yí', 1),
(138, 7, 'Puntas de Maciel', 1),
(139, 7, 'Reboledo', 1),
(140, 7, 'Sarandí Grande', 1),
(141, 8, 'Colón', 1),
(142, 8, 'Estación Solís', 1),
(143, 8, 'Illescas', 1),
(144, 8, 'José Batlle y Ordóñez', 1),
(145, 8, 'José Pedro Varela', 1),
(146, 8, 'Mariscala', 1),
(147, 8, 'Minas', 1),
(148, 8, 'Nico Pérez', 1),
(149, 8, 'Pirarajá', 1),
(150, 8, 'Polanco Norte', 1),
(151, 8, 'Solís de Mataojo', 1),
(152, 8, 'Valentines', 1),
(153, 8, 'Zapicán', 1),
(154, 9, 'Aiguá', 1),
(155, 9, 'Garzón', 1),
(156, 9, 'José Ignacio', 1),
(157, 9, 'La Barra', 1),
(158, 9, 'Las Flores', 1),
(159, 9, 'Maldonado', 1),
(160, 9, 'Manantiales', 1),
(161, 9, 'Pan de Azúcar', 1),
(162, 9, 'Pinares - Las Delicias', 1),
(163, 9, 'Piriápolis', 1),
(164, 9, 'Playa Verde', 1),
(165, 9, 'Punta Ballena', 1),
(166, 9, 'Punta del Este', 1),
(167, 9, 'San Carlos', 1),
(168, 9, 'San Rafael - El Placer', 1),
(169, 9, 'Sauce de Portezuelo', 1),
(170, 9, 'Solís', 1),
(171, 10, 'Ciudad Vieja', 1),
(172, 10, 'Centro ', 1),
(173, 10, 'Barrió Sur', 1),
(174, 10, 'Cordón ', 1),
(175, 10, 'Palermo', 1),
(176, 10, 'Parque Rodó', 1),
(177, 10, 'Punta Carretas', 1),
(178, 10, 'Pocitos', 1),
(179, 10, 'Buceo', 1),
(180, 10, 'Parque Batlle', 1),
(181, 10, 'Villa Dolores', 1),
(182, 10, 'Malvín', 1),
(183, 10, 'Malvín Norte', 1),
(184, 10, 'Punta Gorda', 1),
(185, 10, 'Carrasco', 1),
(186, 10, 'Carrasco Norte', 1),
(187, 10, 'Bañados de Carrasco', 1),
(188, 10, 'Maroñas', 1),
(189, 10, 'Parque Guaraní', 1),
(190, 10, 'Flor de Maroñas', 1),
(191, 10, 'Las Canteras', 1),
(192, 10, 'Punta de Rieles', 1),
(193, 10, 'Bella Italia', 1),
(194, 10, 'Jardines del Hipódromo', 1),
(195, 10, 'Ituzaingó', 1),
(196, 10, 'Unión', 1),
(197, 10, 'Villa Española', 1),
(198, 10, 'Mercado Modelo', 1),
(199, 10, 'Bolívar', 1),
(200, 10, 'Castro', 1),
(201, 10, 'Perez Castellanos', 1),
(202, 10, 'Cerrito de la Victoria', 1),
(203, 10, 'Las Acacias', 1),
(204, 10, 'Aires puros', 1),
(205, 10, 'Casavalle', 1),
(206, 10, 'Piedras Blancas', 1),
(207, 10, 'Manga', 1),
(208, 10, 'Toledo Chico', 1),
(209, 10, 'Paso de las Duranas ', 1),
(210, 10, 'Peñarol', 1),
(211, 10, 'Lavalleja', 1),
(212, 10, 'Villa del Cerro', 1),
(213, 10, 'Casabó ', 1),
(214, 10, 'Pajas Blancas', 1),
(215, 10, 'La Paloma', 1),
(216, 10, 'Tomkinson', 1),
(217, 10, 'La Teja', 1),
(218, 10, 'Prado', 1),
(219, 10, 'Nueva Savona', 1),
(220, 10, 'Capurro ', 1),
(221, 10, 'Bella Vista', 1),
(222, 10, 'Arroyo Seco', 1),
(223, 10, 'Aguada', 1),
(224, 10, 'Reducto', 1),
(225, 10, 'Atahualpa', 1),
(226, 10, 'Jacinto Vera', 1),
(227, 10, 'La Figurita', 1),
(228, 10, 'Larrañaga', 1),
(229, 10, 'La Blanqueada', 1),
(230, 10, 'Villa Muñoz ', 1),
(231, 10, 'Retiro', 1),
(232, 10, 'Goes', 1),
(233, 10, 'La Comercial', 1),
(234, 10, 'Tres Cruces', 1),
(235, 10, 'Brazo Oriental', 1),
(236, 10, 'Sayago', 1),
(237, 10, 'Conciliación ', 1),
(238, 10, 'Belvedere', 1),
(239, 10, 'Paso Molino', 1),
(240, 10, 'Nueno París', 1),
(241, 10, 'Tres Ombúes ', 1),
(242, 10, 'Pueblo Victoria', 1),
(243, 10, 'Paso de la Arena', 1),
(244, 10, 'Santiago Vázquez', 1),
(245, 10, 'Colón', 1),
(246, 10, 'Abayubá', 1),
(247, 10, 'Lezica', 1),
(248, 10, 'Melilla', 1),
(249, 10, 'Villa García ', 1),
(250, 10, 'Manga', 1),
(251, 10, 'Manga Rural', 1),
(252, 11, 'Algorta', 1),
(253, 11, 'Beisso', 1),
(254, 11, 'Cerro Chato', 1),
(255, 11, 'Chapicuy', 1),
(256, 11, 'Eucaliptus', 1),
(257, 11, 'Gallinal', 1),
(258, 11, 'Guichón', 1),
(259, 11, 'Lorenzo Geyres', 1),
(260, 11, 'Merinos', 1),
(261, 11, 'P.Pandule', 1),
(262, 11, 'Paysandú', 1),
(263, 11, 'Piedras Coloradas', 1),
(264, 11, 'Piñera', 1),
(265, 11, 'Porvenir', 1),
(266, 11, 'Quebracho', 1),
(267, 11, 'San Javier', 1),
(268, 12, 'Algorta', 1),
(269, 12, 'Bellaco', 1),
(270, 12, 'Fray Bentos', 1),
(271, 12, 'Menafra', 1),
(272, 12, 'Nuevo Berlín', 1),
(273, 12, 'Paso de los Mellizos', 1),
(274, 12, 'San Javier', 1),
(275, 12, 'Sarandí de Navarro', 1),
(276, 12, 'Young', 1),
(277, 13, 'Lapuente', 1),
(278, 13, 'Masoller', 1),
(279, 13, 'Minas de Corrales', 1),
(280, 13, 'Rivera', 1),
(281, 13, 'Tranqueras', 1),
(282, 13, 'Vichadero', 1),
(283, 14, '18 de Julio', 1),
(284, 14, '19 de Abril', 1),
(285, 14, 'Aguas Dulces', 1),
(286, 14, 'Arachania', 1),
(287, 14, 'Barra del Chuy', 1),
(288, 14, 'Castillos', 1),
(289, 14, 'Cebollatí', 1),
(290, 14, 'Chuy', 1),
(291, 14, 'La Aguada - Costa Azul', 1),
(292, 14, 'La Coronilla', 1),
(293, 14, 'La Paloma', 1),
(294, 14, 'La Pedrera', 1),
(295, 14, 'Lascano', 1),
(296, 14, 'Punta del Diablo', 1),
(297, 14, 'Rocha', 1),
(298, 14, 'San Luis al Medio', 1),
(299, 14, 'Velázquez', 1),
(300, 15, 'Belén', 1),
(301, 15, 'Biassini', 1),
(302, 15, 'Cerro de Vera', 1),
(303, 15, 'Colonia Itapebí', 1),
(304, 15, 'Constitución', 1),
(305, 15, 'Palomas', 1),
(306, 15, 'Puntas de Valentín', 1),
(307, 15, 'Salto', 1),
(308, 15, 'San Antonio', 1),
(309, 15, 'Sarandí del Arapey', 1),
(310, 15, 'Saucedo', 1),
(311, 15, 'Termas del Arapey', 1),
(312, 16, 'Capurro', 1),
(313, 16, 'Delta del Tigre', 1),
(314, 16, 'Ecilda Paullier', 1),
(315, 16, 'Ituzaingó', 1),
(316, 16, 'Juan Soler', 1),
(317, 16, 'Kiyú - Ordeig', 1),
(318, 16, 'Libertad', 1),
(319, 16, 'Mal Abrigo', 1),
(320, 16, 'Playa Pascual', 1),
(321, 16, 'Puntas de Valdés', 1),
(322, 16, 'Rafael Perazza', 1),
(323, 16, 'Rincón de la Bolsa', 1),
(324, 16, 'San José de Mayo', 1),
(325, 16, 'Villa María', 1),
(326, 16, 'Villa Rodriguez', 1),
(327, 17, 'Cañada Nieto', 1),
(328, 17, 'Cardona', 1),
(329, 17, 'Cuchilla del Perdido', 1),
(330, 17, 'Dolores', 1),
(331, 17, 'Egaña', 1),
(332, 17, 'José Enrique Rodó', 1),
(333, 17, 'Mercedes', 1),
(334, 17, 'Palmar', 1),
(335, 17, 'Palmitas', 1),
(336, 17, 'Palo Solo', 1),
(337, 17, 'Risso', 1),
(338, 17, 'Santa Catalina', 1),
(339, 17, 'Villa Darwin', 1),
(340, 17, 'Villa de Soriano', 1),
(341, 18, 'Achar', 1),
(342, 18, 'Ansina', 1),
(343, 18, 'Cardoso', 1),
(344, 18, 'Chamberlain', 1),
(345, 18, 'Cuchilla de Peralta', 1),
(346, 18, 'Curtina', 1),
(347, 18, 'La Pedrera', 1),
(348, 18, 'Las Toscas', 1),
(349, 18, 'Laureles', 1),
(350, 18, 'Paso Bonilla', 1),
(351, 18, 'Paso de los Toros', 1),
(352, 18, 'Paso del Cerro', 1),
(353, 18, 'Piedra Sola', 1),
(354, 18, 'San Gregorio de Polanco', 1),
(355, 18, 'Tacuarembó', 1),
(356, 18, 'Tambores', 1),
(357, 18, 'Valle Edén', 1),
(358, 19, 'Cerro Chato', 1),
(359, 19, 'Isla Patrulla', 1),
(360, 19, 'María Albina', 1),
(361, 19, 'Santa Clara de Olimar', 1),
(362, 19, 'Santa Clara de Olimar', 1),
(363, 19, 'Valentines', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `activo` int(1) NOT NULL DEFAULT '1',
  `Tipo_Servicio_idTipo` int(11) NOT NULL,
  PRIMARY KEY (`idCategoria`),
  KEY `fk_Categoria_Tipo_Servicio1_idx` (`Tipo_Servicio_idTipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

DROP TABLE IF EXISTS `departamento`;
CREATE TABLE IF NOT EXISTS `departamento` (
  `idDepartamento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `activo` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idDepartamento`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`idDepartamento`, `nombre`, `activo`) VALUES
(1, 'Artigas', 1),
(2, 'Canelones', 1),
(3, 'CerroLargo', 1),
(4, 'Colonia', 1),
(5, 'Durazno', 1),
(6, 'Flores', 1),
(7, 'Florida', 1),
(8, 'Lavalleja', 1),
(9, 'Maldonado', 1),
(10, 'Montevideo', 1),
(11, 'Paysandu', 1),
(12, 'RioNegro', 1),
(13, 'Rivera', 1),
(14, 'Rocha', 1),
(15, 'Salto', 1),
(16, 'SanJose', 1),
(17, 'Soriano', 1),
(18, 'Tacuarembo', 1),
(19, 'TreintaYTres', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_servicio`
--

DROP TABLE IF EXISTS `detalle_servicio`;
CREATE TABLE IF NOT EXISTS `detalle_servicio` (
  `idDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `idServicio` int(11) NOT NULL,
  `idPersona` int(11) NOT NULL,
  `idAsistencia` int(11) NOT NULL,
  `hora` time NOT NULL,
  `fecha` date NOT NULL,
  `precio` int(11) NOT NULL,
  `observacion` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idDetalle`),
  KEY `fk_Detalle_Servicio_Servicio1_idx` (`idServicio`),
  KEY `fk_Detalle_Servicio_Persona1_idx` (`idPersona`),
  KEY `fk_Detalle_Servicio_Asistencia1_idx` (`idAsistencia`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_trabajador`
--

DROP TABLE IF EXISTS `detalle_trabajador`;
CREATE TABLE IF NOT EXISTS `detalle_trabajador` (
  `idDetalle` int(11) NOT NULL AUTO_INCREMENT,
  `idServicio` int(11) NOT NULL,
  `idTrabajador` int(11) NOT NULL,
  PRIMARY KEY (`idDetalle`),
  KEY `fk_Detalle_Trabajador_Trabajador1_idx` (`idTrabajador`),
  KEY `fk_Detalle_Trabajador_Detalle_Servicio1_idx` (`idServicio`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidad`
--

DROP TABLE IF EXISTS `especialidad`;
CREATE TABLE IF NOT EXISTS `especialidad` (
  `idEspecialidad` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `activo` int(1) NOT NULL,
  PRIMARY KEY (`idEspecialidad`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `especialidad`
--

INSERT INTO `especialidad` (`idEspecialidad`, `nombre`, `activo`) VALUES
(1, 'Especialista en pinturas', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

DROP TABLE IF EXISTS `genero`;
CREATE TABLE IF NOT EXISTS `genero` (
  `idGenero` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `activo` int(1) NOT NULL,
  PRIMARY KEY (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`idGenero`, `nombre`, `activo`) VALUES
(1, 'Masculino', 1),
(2, 'Femenino', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `huella`
--

DROP TABLE IF EXISTS `huella`;
CREATE TABLE IF NOT EXISTS `huella` (
  `idHuella` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) NOT NULL,
  `huella` blob,
  PRIMARY KEY (`idHuella`),
  KEY `fk_Huella_Persona1_idx` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `huella`
--

INSERT INTO `huella` (`idHuella`, `idPersona`, `huella`) VALUES
(14, 17, NULL),
(15, 18, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

DROP TABLE IF EXISTS `persona`;
CREATE TABLE IF NOT EXISTS `persona` (
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `idBarrio` int(11) NOT NULL,
  `idGenero` int(11) NOT NULL,
  `dni` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `apellido` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `numero` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `correo` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `activo` int(1) NOT NULL,
  PRIMARY KEY (`idPersona`),
  KEY `fk_Persona_Barrio1_idx` (`idBarrio`),
  KEY `fk_Persona_Genero1_idx` (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`idPersona`, `idBarrio`, `idGenero`, `dni`, `nombre`, `apellido`, `direccion`, `fechaNacimiento`, `numero`, `correo`, `activo`) VALUES
(17, 186, 1, '1911536266', 'Julio', 'Herrera', 'huascar 123', '2017-07-12', '11234561', 'jaherreraurra@live.cl', 1),
(18, 12, 2, '1911536262', 'Julio', 'PEdro', 'jaherreraurra@live.cl', '2017-08-08', '1233122', 'jaherreraurra@live.cl', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

DROP TABLE IF EXISTS `servicio`;
CREATE TABLE IF NOT EXISTS `servicio` (
  `idServicio` int(11) NOT NULL AUTO_INCREMENT,
  `idCategoria` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `duracion` int(11) NOT NULL,
  `precio` int(11) NOT NULL,
  `detalle` varchar(500) COLLATE utf8_spanish_ci DEFAULT NULL,
  `activo` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idServicio`),
  KEY `fk_Servicio_Categoria1_idx` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
CREATE TABLE IF NOT EXISTS `sucursal` (
  `idSucursal` int(11) NOT NULL AUTO_INCREMENT,
  `idBarrio` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `numero` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `codigoPostal` int(11) NOT NULL,
  `activo` int(1) NOT NULL,
  PRIMARY KEY (`idSucursal`),
  KEY `fk_Sucursal_Barrio1_idx` (`idBarrio`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `sucursal`
--

INSERT INTO `sucursal` (`idSucursal`, `idBarrio`, `nombre`, `numero`, `direccion`, `codigoPostal`, `activo`) VALUES
(2, 77, 'Primera sucursal', '12341223', 'Por ahí', 5080000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_servicio`
--

DROP TABLE IF EXISTS `tipo_servicio`;
CREATE TABLE IF NOT EXISTS `tipo_servicio` (
  `idTipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `activo` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idTipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipo_servicio`
--

INSERT INTO `tipo_servicio` (`idTipo`, `nombre`, `activo`) VALUES
(1, 'Servicio', '1'),
(2, 'Clase', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
CREATE TABLE IF NOT EXISTS `tipo_usuario` (
  `idTipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `activo` int(1) NOT NULL,
  PRIMARY KEY (`idTipo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`idTipo`, `nombre`, `activo`) VALUES
(1, 'Administrador', 1),
(2, 'Secretaria', 1),
(3, 'Temporal', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
CREATE TABLE IF NOT EXISTS `trabajador` (
  `idTrabajador` int(11) NOT NULL AUTO_INCREMENT,
  `idSucursal` int(11) NOT NULL,
  `idPersona` int(11) NOT NULL,
  `idEspecialidad` int(11) NOT NULL,
  `activo` int(1) NOT NULL,
  PRIMARY KEY (`idTrabajador`),
  KEY `fk_Trabajador_Persona1_idx` (`idPersona`),
  KEY `fk_Trabajador_Especialidad1_idx` (`idEspecialidad`),
  KEY `fk_Trabajador_Sucursal1_idx` (`idSucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`idTrabajador`, `idSucursal`, `idPersona`, `idEspecialidad`, `activo`) VALUES
(9, 2, 18, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `idPersona` int(11) NOT NULL,
  `idTipo` int(11) NOT NULL,
  `contrasena` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `activo` int(1) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_Usuario_Persona1_idx` (`idPersona`),
  KEY `fk_Usuario_Tipo_Usuario1_idx` (`idTipo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `idPersona`, `idTipo`, `contrasena`, `activo`) VALUES
(4, 18, 1, 'e10adc3949ba59abbe56e057f20f883e', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `barrio`
--
ALTER TABLE `barrio`
  ADD CONSTRAINT `fk_Barrio_Departamento` FOREIGN KEY (`idDepartamento`) REFERENCES `departamento` (`idDepartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD CONSTRAINT `fk_Categoria_Tipo_Servicio1` FOREIGN KEY (`Tipo_Servicio_idTipo`) REFERENCES `tipo_servicio` (`idTipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalle_servicio`
--
ALTER TABLE `detalle_servicio`
  ADD CONSTRAINT `fk_Detalle_Servicio_Asistencia1` FOREIGN KEY (`idAsistencia`) REFERENCES `asistencia` (`idAsistencia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Detalle_Servicio_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Detalle_Servicio_Servicio1` FOREIGN KEY (`idServicio`) REFERENCES `servicio` (`idServicio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalle_trabajador`
--
ALTER TABLE `detalle_trabajador`
  ADD CONSTRAINT `fk_Detalle_Trabajador_Detalle_Servicio1` FOREIGN KEY (`idServicio`) REFERENCES `detalle_servicio` (`idDetalle`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Detalle_Trabajador_Trabajador1` FOREIGN KEY (`idTrabajador`) REFERENCES `trabajador` (`idTrabajador`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `huella`
--
ALTER TABLE `huella`
  ADD CONSTRAINT `fk_Huella_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `fk_Persona_Barrio1` FOREIGN KEY (`idBarrio`) REFERENCES `barrio` (`idBarrio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Persona_Genero1` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD CONSTRAINT `fk_Servicio_Categoria1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD CONSTRAINT `fk_Sucursal_Barrio1` FOREIGN KEY (`idBarrio`) REFERENCES `barrio` (`idBarrio`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD CONSTRAINT `fk_Trabajador_Especialidad1` FOREIGN KEY (`idEspecialidad`) REFERENCES `especialidad` (`idEspecialidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Trabajador_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Trabajador_Sucursal1` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`idSucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_Usuario_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Usuario_Tipo_Usuario1` FOREIGN KEY (`idTipo`) REFERENCES `tipo_usuario` (`idTipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
