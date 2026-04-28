-- ============================================
-- Script SQL - Base de données PostgreSQL 15
-- ============================================

-- 1. Créer la base de données (exécuter en tant que superuser)
CREATE DATABASE remarques_db;

-- 2. Se connecter à la base : \c remarques_db

-- 3. Créer la table remarque
CREATE TABLE IF NOT EXISTS remarque (
    id BIGSERIAL PRIMARY KEY,
    contenu TEXT NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT NOW(),
    etudiant_id BIGINT NOT NULL
);

-- 4. Insérer des données de test
INSERT INTO remarque (contenu, date, etudiant_id) VALUES
    ('Bon travail sur le projet !', NOW(), 1),
    ('Améliorer la présentation', NOW(), 1),
    ('Excellent rapport écrit', NOW(), 2),
    ('Revoir la méthodologie', NOW(), 2);

-- 5. Vérifier les données
SELECT * FROM remarque;
