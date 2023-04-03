-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 30 jan. 2020 à 04:06
-- Version du serveur :  10.4.8-MariaDB
-- Version de PHP :  7.3.11

DROP DATABASE IF EXISTS java_credit;

CREATE DATABASE java_credit;

USE java_credit;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `java_credit`
--

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

CREATE TABLE `produits` (
  `id` int(11) NOT NULL,
  `type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`id`, `type`) VALUES
(1, 'produits pour femmes'),
(2, 'produits pour hommes'),
(3, 'Produits pour bébés');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `numero` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `prix` int(11) NOT NULL,
  `idCredit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `numero`, `prenom`, `nom`, `date`, `prix`, `idCredit`) VALUES
(1, 'Numero: 01', 'Hiba', 'Alhachimi', '2023-02-01', 1000, 4),
(2, 'Numero: 02', 'Nora', 'Nesiri', '2022-10-10', 2000, 4),
(3, 'Numero: 03', 'Hamza', 'Al moulahid', '2022-11-01', 500, 1),
(4, 'Numero: 04', 'Youssef', 'Alaichi', '2021-02-12', 200, 1),
(5, 'Numero: 05', 'Aya', 'Al aifia', '2022-12-31', 100, 2);

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

CREATE TABLE `credit` (
  `id` int(11) NOT NULL,
  `type` varchar(100) NOT NULL,
  `idPord` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `credit` (`id`, `type`, `idPord`) VALUES
(1, 'Hebdomadaire', 1),
(2, 'Mensuel', 1),
(3, 'Annuel', 1),
(4, 'Hebdomadaire', 2),
(5, 'Mensuel', 2),
(6, 'Annuel', 2),
(7, 'Hebdomadaire', 3),
(8, 'Mensuel', 3),
(9, 'Annuel', 3);


--
-- Index pour les tables déchargées
--

--
-- Index pour la table `produits`
--
ALTER TABLE `produits`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numero` (`numero`),
  ADD KEY `idCredit` (`idCredit`);

--
-- Index pour la table `service`
--
ALTER TABLE `credit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idPord` (`idPord`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `produits`
--
ALTER TABLE `produits`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `credit`
--
ALTER TABLE `credit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`idCredit`) REFERENCES `credit` (`id`);

--
-- Contraintes pour la table `service`
--
ALTER TABLE `credit`
  ADD CONSTRAINT `credit_ibfk_1` FOREIGN KEY (`idPord`) REFERENCES `produits` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
