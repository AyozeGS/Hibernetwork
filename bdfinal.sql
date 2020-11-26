-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-05-2020 a las 04:27:30
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdfinal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `frequest`
--

CREATE TABLE `frequest` (
  `id` int(11) NOT NULL,
  `orig_user` int(11) NOT NULL,
  `dest_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `friend`
--

CREATE TABLE `friend` (
  `id_user1` int(11) NOT NULL,
  `id_user2` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `friend`
--

INSERT INTO `friend` (`id_user1`, `id_user2`) VALUES
(49, 52),
(52, 49),
(49, 57),
(57, 49),
(49, 54),
(54, 49),
(49, 56),
(56, 49),
(49, 53),
(53, 49),
(49, 50),
(50, 49),
(49, 59),
(59, 49),
(49, 51),
(51, 49),
(49, 58),
(58, 49),
(49, 55),
(55, 49),
(49, 64),
(64, 49);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `content` varchar(120) NOT NULL,
  `id_topic` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `message`
--

INSERT INTO `message` (`id`, `content`, `id_topic`, `id_user`, `datetime`) VALUES
(55, 'No sé que regalar!', 57, 53, '2020-05-30 02:07:21'),
(56, 'Aún queda', 58, 54, '2020-05-30 02:09:11'),
(57, 'toca esperar un poco', 58, 54, '2020-05-30 02:09:20'),
(58, '¿Dónde y cúando?', 59, 58, '2020-05-30 02:16:31'),
(59, 'Estudien', 60, 60, '2020-05-30 02:21:37');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `topic`
--

CREATE TABLE `topic` (
  `id` int(11) NOT NULL,
  `title` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `topic`
--

INSERT INTO `topic` (`id`, `title`) VALUES
(57, 'Amigo Invisible'),
(58, 'Navidad'),
(59, 'Cena de Fin de Curso'),
(60, 'Examen');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trequest`
--

CREATE TABLE `trequest` (
  `id` int(11) NOT NULL,
  `dest_user` int(11) NOT NULL,
  `id_topic` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `birthdate` date NOT NULL,
  `mail` varchar(30) NOT NULL,
  `photourl` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `name`, `lastname`, `birthdate`, `mail`, `photourl`) VALUES
(49, 'Ayoze', 'ayo', 'Ayoze', 'Gil Sosa', '1990-08-08', 'ayoze@elrincon.es', './src/resources/49.png'),
(50, 'Alejandro', 'ale', 'Alejandro Alfonso', 'Garcia Agrelo', '1997-05-13', 'alejandro@elrincon', './src/resources/50.png'),
(51, 'Carmelo', 'car', 'Carmelo David', 'Diaz Hernandez', '1985-10-24', 'carmelo@elrincon.es', './src/resources/51.png'),
(52, 'Dionisio', 'dio', 'Dionisio Javier', 'Alamo Santana', '2000-09-19', 'dionisio@elrincon.es', './src/resources/52.png'),
(53, 'Eric', 'eri', 'Eric', 'Driussi', '2002-01-01', 'eric@elrincon.es', './src/resources/53.png'),
(54, 'Gumidafe', 'gum', 'Gumidafe', 'Socorro Delgado', '1995-07-06', 'gumidafe@elrincon.es', './src/resources/54.png'),
(55, 'Jorge', 'jor', 'Jorge Luis', 'Velez Nolasco', '1993-11-17', 'jorge@elrincon.es', './src/resources/55.png'),
(56, 'Jonatan', 'jon', 'Jonatan', 'Bresso Verdu', '1999-12-31', 'jonatan@elrincon.es', './src/resources/56.png'),
(57, 'JAurelio', 'jau', 'Jose Aurelio', 'Monte Soto', '1988-02-29', 'jaurelio@elrincon.es', './src/resources/57.png'),
(58, 'JPrieto', 'jpr', 'Jose Manuel', 'Prieto Medina', '1970-03-18', 'jprieto@@elrincon.es', './src/resources/58.png'),
(59, 'Maria Jose', 'mar', 'Maria Jose', 'Moreno Gomez', '1994-09-12', 'mariajose@elrincon.es', './src/resources/59.png'),
(60, 'Gemma', 'gem', 'Gemma', 'Aleman Santana', '1980-10-21', 'gemma@elrincon.es', './src/resources/60.png'),
(64, 'Cesar', 'ces', 'Cesar', 'Torres Cerpa', '1981-03-02', 'cesar@elrincon.es', './src/resources/64.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_topic`
--

CREATE TABLE `user_topic` (
  `id_user` int(11) NOT NULL,
  `id_topic` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_topic`
--

INSERT INTO `user_topic` (`id_user`, `id_topic`) VALUES
(49, 58),
(49, 57),
(49, 60),
(49, 59),
(54, 60),
(54, 58),
(53, 60),
(58, 60),
(53, 57),
(58, 59),
(51, 60),
(55, 60),
(56, 60),
(59, 60),
(57, 60),
(50, 60),
(52, 60),
(64, 60);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `frequest`
--
ALTER TABLE `frequest`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_freq_ouser` (`orig_user`),
  ADD KEY `fk_freq_duser` (`dest_user`);

--
-- Indices de la tabla `friend`
--
ALTER TABLE `friend`
  ADD KEY `fk_amistad_user1` (`id_user1`),
  ADD KEY `fk_amistad_user2` (`id_user2`);

--
-- Indices de la tabla `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_msg_user` (`id_user`),
  ADD KEY `fk_msg_topic` (`id_topic`) USING BTREE;

--
-- Indices de la tabla `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `trequest`
--
ALTER TABLE `trequest`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_treq_user` (`dest_user`),
  ADD KEY `fk_treq_topic` (`id_topic`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mail` (`mail`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indices de la tabla `user_topic`
--
ALTER TABLE `user_topic`
  ADD KEY `fk_usr_tpc_user` (`id_user`),
  ADD KEY `fk_usr_tpc_topic` (`id_topic`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `frequest`
--
ALTER TABLE `frequest`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT de la tabla `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT de la tabla `topic`
--
ALTER TABLE `topic`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `trequest`
--
ALTER TABLE `trequest`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `frequest`
--
ALTER TABLE `frequest`
  ADD CONSTRAINT `fk_freq_duser` FOREIGN KEY (`dest_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_freq_ouser` FOREIGN KEY (`orig_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `friend`
--
ALTER TABLE `friend`
  ADD CONSTRAINT `fk_amistad_user1` FOREIGN KEY (`id_user1`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_amistad_user2` FOREIGN KEY (`id_user2`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `fk_msg_topic` FOREIGN KEY (`id_topic`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_msg_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `trequest`
--
ALTER TABLE `trequest`
  ADD CONSTRAINT `fk_treq_topic` FOREIGN KEY (`id_topic`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_treq_user` FOREIGN KEY (`dest_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `user_topic`
--
ALTER TABLE `user_topic`
  ADD CONSTRAINT `fk_usr_tpc_topic` FOREIGN KEY (`id_topic`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_usr_tpc_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
