-- phpMyAdmin SQL Dump
-- version 4.4.1.1
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Nov 22, 2016 alle 15:44
-- Versione del server: 5.5.42
-- PHP Version: 5.6.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `musa_db`
--
CREATE DATABASE IF NOT EXISTS `musa_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `musa_db`;

-- --------------------------------------------------------

--
-- Struttura della tabella `abstract_capability`
--

CREATE TABLE `abstract_capability` (
  `idAbstrat_Capability` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `input` varchar(45) DEFAULT NULL,
  `output` varchar(45) DEFAULT NULL,
  `params` varchar(45) DEFAULT NULL,
  `pre-condition` varchar(45) DEFAULT NULL,
  `post_condition` varchar(45) DEFAULT NULL,
  `idDomain` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `capability_instance`
--

CREATE TABLE `capability_instance` (
  `idCapability_Instance` int(11) NOT NULL,
  `state` varchar(45) DEFAULT NULL,
  `agent` varchar(45) DEFAULT NULL,
  `idCapability` int(11) DEFAULT NULL,
  `idCase` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `capability_log`
--

CREATE TABLE `capability_log` (
  `idCapability_Operation` int(11) NOT NULL,
  `timeOperation` varchar(45) DEFAULT NULL,
  `action` varchar(45) DEFAULT NULL,
  `idInstance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `case_execution`
--

CREATE TABLE `case_execution` (
  `idCase` int(11) NOT NULL,
  `startedTime` varchar(45) DEFAULT NULL,
  `terminatedTime` varchar(45) DEFAULT NULL,
  `idSpecification` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `concrete_capability`
--

CREATE TABLE `concrete_capability` (
  `idConcrete_Capability` int(11) NOT NULL,
  `idAbstract_Capability` int(11) DEFAULT NULL,
  `fileUpload` blob,
  `Provider` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `domain`
--

CREATE TABLE `domain` (
  `idDomain` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `description` varchar(250) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `domain`
--

INSERT INTO `domain` (`idDomain`, `name`, `description`) VALUES
(1, 'dom name', 'New Description'),
(8, 'new dom name', 'my descr');

-- --------------------------------------------------------

--
-- Struttura della tabella `domain_assumption`
--

CREATE TABLE `domain_assumption` (
  `idAssumption` int(11) NOT NULL,
  `idDomain` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `domain_configuration`
--

CREATE TABLE `domain_configuration` (
  `idDomain_Configuration` int(11) NOT NULL,
  `idDomain` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `functional_req`
--

CREATE TABLE `functional_req` (
  `idFunctional_Req` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `current_state` varchar(45) DEFAULT NULL,
  `trigger_condition` varchar(45) DEFAULT NULL,
  `final_state` varchar(45) DEFAULT NULL,
  `idWF` int(11) DEFAULT NULL,
  `idSpecification` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `general_configuration`
--

CREATE TABLE `general_configuration` (
  `idGeneral_Configuration` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `general_configuration`
--

INSERT INTO `general_configuration` (`idGeneral_Configuration`, `name`, `value`, `description`) VALUES
(2, 'Config2', 'Value2', 'Description2'),
(3, 'Config3', 'Value3', 'Description3'),
(7, 'Config2ttt', 'Value2', 'Description2'),
(19, 'te', 'oopppuu', 'kkkggg799');

-- --------------------------------------------------------

--
-- Struttura della tabella `non_functional_req`
--

CREATE TABLE `non_functional_req` (
  `idNonFunctional_Req` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `idSpecification` int(11) DEFAULT NULL,
  `current_state` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `predicate`
--

CREATE TABLE `predicate` (
  `idPredicate` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `idCase` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `process`
--

CREATE TABLE `process` (
  `idWorkflow` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `fileWF` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `scenario_evo`
--

CREATE TABLE `scenario_evo` (
  `idScenario_Evo` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `idAbstract_Cap` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `specification`
--

CREATE TABLE `specification` (
  `idSpecification` int(11) NOT NULL,
  `descriptions` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `idDomain` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `variable`
--

CREATE TABLE `variable` (
  `idVariable` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  `idCase` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `abstract_capability`
--
ALTER TABLE `abstract_capability`
  ADD PRIMARY KEY (`idAbstrat_Capability`),
  ADD KEY `FAC_idx` (`idDomain`);

--
-- Indexes for table `capability_instance`
--
ALTER TABLE `capability_instance`
  ADD PRIMARY KEY (`idCapability_Instance`),
  ADD KEY `FKCINC_idx` (`idCase`),
  ADD KEY `FKCC_idx` (`idCapability`);

--
-- Indexes for table `capability_log`
--
ALTER TABLE `capability_log`
  ADD PRIMARY KEY (`idCapability_Operation`),
  ADD KEY `idInstance_idx` (`idInstance`);

--
-- Indexes for table `case_execution`
--
ALTER TABLE `case_execution`
  ADD PRIMARY KEY (`idCase`),
  ADD KEY `FKCS_idx` (`idSpecification`);

--
-- Indexes for table `concrete_capability`
--
ALTER TABLE `concrete_capability`
  ADD PRIMARY KEY (`idConcrete_Capability`),
  ADD KEY `idABSTRACT_idx` (`idAbstract_Capability`);

--
-- Indexes for table `domain`
--
ALTER TABLE `domain`
  ADD PRIMARY KEY (`idDomain`);

--
-- Indexes for table `domain_assumption`
--
ALTER TABLE `domain_assumption`
  ADD PRIMARY KEY (`idAssumption`),
  ADD KEY `FD1_idx` (`idDomain`);

--
-- Indexes for table `domain_configuration`
--
ALTER TABLE `domain_configuration`
  ADD PRIMARY KEY (`idDomain_Configuration`),
  ADD KEY `FDC_idx` (`idDomain`);

--
-- Indexes for table `functional_req`
--
ALTER TABLE `functional_req`
  ADD PRIMARY KEY (`idFunctional_Req`),
  ADD KEY `idSpec_idx` (`idSpecification`),
  ADD KEY `idWf_idx` (`idWF`);

--
-- Indexes for table `general_configuration`
--
ALTER TABLE `general_configuration`
  ADD PRIMARY KEY (`idGeneral_Configuration`);

--
-- Indexes for table `non_functional_req`
--
ALTER TABLE `non_functional_req`
  ADD PRIMARY KEY (`idNonFunctional_Req`),
  ADD KEY `FNFS_idx` (`idSpecification`);

--
-- Indexes for table `predicate`
--
ALTER TABLE `predicate`
  ADD PRIMARY KEY (`idPredicate`),
  ADD KEY `FKPC_idx` (`idCase`);

--
-- Indexes for table `process`
--
ALTER TABLE `process`
  ADD PRIMARY KEY (`idWorkflow`);

--
-- Indexes for table `scenario_evo`
--
ALTER TABLE `scenario_evo`
  ADD PRIMARY KEY (`idScenario_Evo`),
  ADD KEY `idAbstract_Cap_idx` (`idAbstract_Cap`);

--
-- Indexes for table `specification`
--
ALTER TABLE `specification`
  ADD PRIMARY KEY (`idSpecification`),
  ADD KEY `FKSDOM_idx` (`idDomain`);

--
-- Indexes for table `variable`
--
ALTER TABLE `variable`
  ADD PRIMARY KEY (`idVariable`),
  ADD KEY `idCase_idx` (`idCase`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `capability_instance`
--
ALTER TABLE `capability_instance`
  MODIFY `idCapability_Instance` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `capability_log`
--
ALTER TABLE `capability_log`
  MODIFY `idCapability_Operation` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `concrete_capability`
--
ALTER TABLE `concrete_capability`
  MODIFY `idConcrete_Capability` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `domain`
--
ALTER TABLE `domain`
  MODIFY `idDomain` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `domain_assumption`
--
ALTER TABLE `domain_assumption`
  MODIFY `idAssumption` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `functional_req`
--
ALTER TABLE `functional_req`
  MODIFY `idFunctional_Req` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `general_configuration`
--
ALTER TABLE `general_configuration`
  MODIFY `idGeneral_Configuration` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `non_functional_req`
--
ALTER TABLE `non_functional_req`
  MODIFY `idNonFunctional_Req` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `process`
--
ALTER TABLE `process`
  MODIFY `idWorkflow` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `scenario_evo`
--
ALTER TABLE `scenario_evo`
  MODIFY `idScenario_Evo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `specification`
--
ALTER TABLE `specification`
  MODIFY `idSpecification` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `variable`
--
ALTER TABLE `variable`
  MODIFY `idVariable` int(11) NOT NULL AUTO_INCREMENT;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `abstract_capability`
--
ALTER TABLE `abstract_capability`
  ADD CONSTRAINT `FAC` FOREIGN KEY (`idDomain`) REFERENCES `domain` (`idDomain`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `capability_instance`
--
ALTER TABLE `capability_instance`
  ADD CONSTRAINT `FKCC` FOREIGN KEY (`idCapability`) REFERENCES `concrete_capability` (`idConcrete_Capability`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FKCINC` FOREIGN KEY (`idCase`) REFERENCES `case_execution` (`idCase`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `capability_log`
--
ALTER TABLE `capability_log`
  ADD CONSTRAINT `idInstance` FOREIGN KEY (`idInstance`) REFERENCES `capability_instance` (`idCapability_Instance`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `case_execution`
--
ALTER TABLE `case_execution`
  ADD CONSTRAINT `FKCS` FOREIGN KEY (`idSpecification`) REFERENCES `specification` (`idSpecification`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `concrete_capability`
--
ALTER TABLE `concrete_capability`
  ADD CONSTRAINT `idABSTRACT` FOREIGN KEY (`idAbstract_Capability`) REFERENCES `abstract_capability` (`idAbstrat_Capability`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `domain_assumption`
--
ALTER TABLE `domain_assumption`
  ADD CONSTRAINT `FD1` FOREIGN KEY (`idDomain`) REFERENCES `domain` (`idDomain`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `domain_configuration`
--
ALTER TABLE `domain_configuration`
  ADD CONSTRAINT `FDC` FOREIGN KEY (`idDomain`) REFERENCES `domain` (`idDomain`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `functional_req`
--
ALTER TABLE `functional_req`
  ADD CONSTRAINT `idSpec` FOREIGN KEY (`idSpecification`) REFERENCES `specification` (`idSpecification`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idWf` FOREIGN KEY (`idWF`) REFERENCES `process` (`idWorkflow`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `non_functional_req`
--
ALTER TABLE `non_functional_req`
  ADD CONSTRAINT `FNFS` FOREIGN KEY (`idSpecification`) REFERENCES `specification` (`idSpecification`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `predicate`
--
ALTER TABLE `predicate`
  ADD CONSTRAINT `FKPC` FOREIGN KEY (`idCase`) REFERENCES `case_execution` (`idCase`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `scenario_evo`
--
ALTER TABLE `scenario_evo`
  ADD CONSTRAINT `idAbstract_Cap` FOREIGN KEY (`idAbstract_Cap`) REFERENCES `abstract_capability` (`idAbstrat_Capability`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `specification`
--
ALTER TABLE `specification`
  ADD CONSTRAINT `FKSDOM` FOREIGN KEY (`idDomain`) REFERENCES `domain` (`idDomain`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limiti per la tabella `variable`
--
ALTER TABLE `variable`
  ADD CONSTRAINT `idCase` FOREIGN KEY (`idCase`) REFERENCES `case_execution` (`idCase`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
