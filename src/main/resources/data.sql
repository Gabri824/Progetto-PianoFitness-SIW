-- ==========================================
-- 1. CREAZIONE UTENTI (10 DEFAULT, 2 ADMIN)
-- ==========================================
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (1, 'Mario', 'Rossi', '1990-05-15', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (2, 'Luigi', 'Verdi', '1992-08-20', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (3, 'Giulia', 'Bianchi', '1995-11-10', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (4, 'Anna', 'Neri', '1998-03-25', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (5, 'Marco', 'Gialli', '1985-12-05', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (6, 'Sofia', 'Romano', '1993-07-30', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (7, 'Lorenzo', 'Colombo', '1991-02-14', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (8, 'Chiara', 'Ricci', '1997-09-22', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (9, 'Matteo', 'Marino', '1989-06-18', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (10, 'Francesca', 'Greco', '1994-04-12', false) ON CONFLICT (id) DO NOTHING;

INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (11, 'Admin', 'Superiore', '1980-01-01', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (12, 'Istruttore', 'Capo', '1982-10-10', false) ON CONFLICT (id) DO NOTHING;
INSERT INTO utente (id, nome, cognome, data_nascita, banned) VALUES (13, 'Gabriele', 'Iorio', '2003-07-08', false) ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- 2. CREAZIONE CREDENZIALI (Password per tutti: 'password')
-- ==========================================
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (1, 'mario@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'DEFAULT', 1) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (2, 'luigi@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'DEFAULT', 2) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (3, 'giulia@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'DEFAULT', 3) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (4, 'anna@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'DEFAULT', 4) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (5, 'marco@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'DEFAULT', 5) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (6, 'sofia@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'DEFAULT', 6) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (7, 'lorenzo@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'DEFAULT', 7) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (8, 'chiara@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'DEFAULT', 8) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (9, 'matteo@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'DEFAULT', 9) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (10, 'francesca@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'DEFAULT', 10) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (11, 'admin@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'ADMIN', 11) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (12, 'istruttore@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'ADMIN', 12) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO credentials (id, email, password, role, utente_id) VALUES (13, 'gabri.iorio@gmail.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'ADMIN', 13) ON CONFLICT (id) DO UPDATE SET password = EXCLUDED.password;

-- ==========================================
-- 3. CREAZIONE ESERCIZI (40 Esercizi)
-- ==========================================
-- I 30 precedenti
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (1, 'Panca Piana Bilanciere', 'Bilanciere', 'Pettorali', 'Esercizio base per lo sviluppo del gran pettorale.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (2, 'Spinte Manubri Panca Inclinata', 'Manubri', 'Pettorali Alti', 'Ottimo per il focus sui fasci clavicolari del petto.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (3, 'Croci ai Cavi', 'Macchinario', 'Pettorali', 'Esercizio di isolamento per il petto, garantisce tensione continua.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (4, 'Chest Press', 'Macchinario', 'Pettorali', 'Macchinario guidato perfetto per i principianti.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (5, 'Piegamenti sulle braccia', 'Corpo Libero', 'Pettorali', 'I classici push-up, ottimi per la forza a corpo libero.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (6, 'Lat Machine Avanti', 'Macchinario', 'Dorsali', 'Trazione verticale guidata per la larghezza del dorso.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (7, 'Rematore con Bilanciere', 'Bilanciere', 'Dorsali', 'Trazione orizzontale per lo spessore della schiena.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (8, 'Pulley Basso', 'Macchinario', 'Dorsali', 'Rematore al cavo basso seduti.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (9, 'Trazioni alla Sbarra', 'Corpo Libero', 'Dorsali', 'Esercizio fondamentale per la forza del tronco.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (10, 'Pull-down braccia tese', 'Macchinario', 'Dorsali', 'Isolamento del gran dorsale al cavo alto.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (11, 'Squat con Bilanciere', 'Bilanciere', 'Quadricipiti', 'Il re degli esercizi per la forza delle gambe e del core.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (12, 'Leg Press 45 gradi', 'Macchinario', 'Quadricipiti', 'Spinta pesante senza sovraccaricare la colonna.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (13, 'Affondi con Manubri', 'Manubri', 'Quadricipiti/Glutei', 'Lavoro monolaterale eccellente per simmetria ed equilibrio.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (14, 'Leg Extension', 'Macchinario', 'Quadricipiti', 'Isolamento del quadricipite da seduti.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (15, 'Leg Curl', 'Macchinario', 'Femorali', 'Isolamento dei muscoli posteriori della coscia.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (16, 'Stacco da Terra', 'Bilanciere', 'Glutei/Femorali', 'Esercizio multiarticolare per catena cinetica posteriore.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (17, 'Calf Raise in piedi', 'Macchinario', 'Polpacci', 'Estensioni delle caviglie per i polpacci.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (18, 'Military Press', 'Bilanciere', 'Spalle', 'Spinta in alto con bilanciere per i deltoidi anteriori.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (19, 'Alzate Laterali', 'Manubri', 'Spalle', 'Isolamento per il capo laterale del deltoide (larghezza spalle).') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (20, 'Alzate a 90 gradi', 'Manubri', 'Spalle', 'Lavoro per i deltoidi posteriori.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (21, 'Shoulder Press', 'Macchinario', 'Spalle', 'Versione guidata della spinta in alto.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (22, 'Curl con Bilanciere', 'Bilanciere', 'Bicipiti', 'Flessione del gomito pesante per i bicipiti.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (23, 'Curl Manubri Alternato', 'Manubri', 'Bicipiti', 'Esercizio classico per lo sviluppo delle braccia.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (24, 'Pushdown Tricipiti', 'Macchinario', 'Tricipiti', 'Estensione del gomito al cavo alto.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (25, 'French Press', 'Bilanciere', 'Tricipiti', 'Estensione dietro la testa con bilanciere sagomato.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (26, 'Crunch a terra', 'Corpo Libero', 'Addominali', 'Il classico esercizio per il retto dell addome.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (27, 'Plank', 'Corpo Libero', 'Addominali', 'Isometria per il core.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (28, 'Tapis Roulant', 'Cardio', 'Tutto il corpo', 'Corsa o camminata sul nastro.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (29, 'Cyclette', 'Cardio', 'Gambe', 'Pedalata stazionaria.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (30, 'Vogatore', 'Cardio', 'Dorso/Cardio', 'Ottimo per unire resistenza e lavoro muscolare di spinta-trazione.') ON CONFLICT (id) DO NOTHING;
-- I 10 Nuovi
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (31, 'Hip Thrust', 'Bilanciere', 'Glutei', 'Il miglior esercizio per lisolamento e la forza dei glutei.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (32, 'Front Squat', 'Bilanciere', 'Quadricipiti', 'Squat con bilanciere frontale, forte attivazione dei quadricipiti e del core.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (33, 'Alzate Frontali', 'Manubri', 'Spalle', 'Isolamento per il deltoide anteriore.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (34, 'Tirate al Mento', 'Bilanciere', 'Spalle/Trapezi', 'Ottimo per lo sviluppo di trapezi e deltoidi laterali.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (35, 'Curl a Martello', 'Manubri', 'Bicipiti', 'Flessione del braccio a presa neutra, ottimo per il brachioradiale.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (36, 'Dips alle Parallele', 'Corpo Libero', 'Tricipiti/Petto', 'Esercizio fondamentale a corpo libero per la spinta.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (37, 'Ellittica', 'Cardio', 'Tutto il corpo', 'Lavoro cardiovascolare a basso impatto articolare.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (38, 'Step-up su Panca', 'Manubri', 'Gambe/Glutei', 'Salite sul gradino con sovraccarico per l equilibrio e la forza asimmetrica.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (39, 'Russian Twist', 'Corpo Libero', 'Addominali Obliqui', 'Torsioni del busto per attivare l addome laterale.') ON CONFLICT (id) DO NOTHING;
INSERT INTO esercizio (id, nome, categoria, muscolo_target, descrizione) VALUES (40, 'Rematore Singolo Manubrio', 'Manubri', 'Dorsali', 'Lavoro monolaterale per uno stiramento e una contrazione ottimali del dorso.') ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- 4. CREAZIONE SCHEDE
-- ==========================================
-- Schede Utenti Base (1-20)
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (1, 'Massa Petto-Tricipiti', 'Focus sull ipertrofia spinta.', 'Intermedio', '2024-01-10', 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (2, 'Cardio e Core', 'Sessione leggera di recupero.', 'Principiante', '2024-01-15', 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (3, 'Forza Gambe', 'Scheda per incrementare i massimali.', 'Avanzato', '2024-02-01', 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (4, 'Full Body Express', 'Allenamento di 45 minuti per chi ha poco tempo.', 'Principiante', '2024-02-05', 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (5, 'Tonificazione Glutei', 'Focus parte bassa con alti volumi.', 'Intermedio', '2024-03-12', 3) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (6, 'Upper Body', 'Spalle e dorso per postura.', 'Principiante', '2024-03-20', 3) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (7, 'Preparazione Maratona', 'Mix di corsa e potenziamento leggero.', 'Avanzato', '2024-04-10', 4) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (8, 'Mobilità e Flessibilità', 'Stretching dinamico e isometrie.', 'Principiante', '2024-04-18', 4) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (9, 'Powerlifting Base', 'Panca, Squat e Stacco.', 'Elite', '2024-05-02', 5) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (10, 'Richiamo Braccia', 'Focus bicipiti e tricipiti.', 'Intermedio', '2024-05-15', 5) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (11, 'Circuito Bruciagrassi', 'HIIT con tempi di recupero minimi.', 'Intermedio', '2024-06-05', 6) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (12, 'Addome d Acciaio', 'Solo esercizi per il core.', 'Principiante', '2024-06-12', 6) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (13, 'Schiena a V', 'Trazioni e rematori pesanti.', 'Avanzato', '2024-07-20', 7) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (14, 'Defaticamento', 'Camminata e allungamenti.', 'Principiante', '2024-07-25', 7) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (15, 'Gambe e Spalle', 'Combinazione inusuale ma efficace.', 'Intermedio', '2024-08-01', 8) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (16, 'Inizio Palestra', 'Macchinari base per imparare.', 'Principiante', '2024-08-10', 8) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (17, 'Calisthenics Base', 'Solo corpo libero, no pesi.', 'Intermedio', '2024-09-05', 9) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (18, 'Resistenza Lattacida', 'Alte ripetizioni, bruciore estremo.', 'Avanzato', '2024-09-15', 9) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (19, 'Bikini Prep', 'Isolamento dettagliato muscoli.', 'Elite', '2024-10-01', 10) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (20, 'Postura Schiena', 'Rinforzo lombare e deltoidi.', 'Principiante', '2024-10-10', 10) ON CONFLICT (id) DO NOTHING;

-- NUOVE Schede Admin
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (21, 'Ipertrofia Avanzata Pro', 'Scheda strutturata in 3 giorni per massimizzare la massa muscolare. Consigliata dallo staff.', 'Avanzato', '2024-11-01', 11) ON CONFLICT (id) DO NOTHING;
INSERT INTO scheda_allenamento (id, titolo, descrizione, difficolta, data_creazione, autore_id) VALUES (22, 'Forza Funzionale', 'Programma di forza in 3 giorni incentrato sui multiarticolari fondamentali.', 'Elite', '2024-11-05', 12) ON CONFLICT (id) DO NOTHING;


-- ==========================================
-- 5. CREAZIONE RIGHE SCHEDA
-- ==========================================
-- Scheda 1 (Mario - Petto Tricipiti) - Aggiunti nuovi esercizi
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (1, 'Lunedì', 4, 8, 90, 'Spinta esplosiva', 1, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (2, 'Lunedì', 3, 10, 60, 'Lenta la discesa', 2, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (3, 'Lunedì', 3, 15, 60, 'Pompaggio', 3, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (4, 'Lunedì', 3, 12, 60, 'Allungamento massimo', 4, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (5, 'Giovedì', 4, 12, 60, 'Gomiti fermi', 24, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (6, 'Giovedì', 3, 10, 90, 'Pesante', 25, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (7, 'Giovedì', 3, 10, 60, 'Corpo libero a cedimento', 36, 1) ON CONFLICT (id) DO NOTHING;

-- Scheda 2 (Mario - Cardio e Core) - Aggiunti nuovi esercizi
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (8, 'Martedì', 1, 20, 0, 'Minuti di corsa leggera', 28, 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (9, 'Martedì', 4, 20, 45, 'Crunch controllati', 26, 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (10, 'Martedì', 1, 15, 0, 'Minuti di ellittica agilità', 37, 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (11, 'Martedì', 3, 15, 30, 'Per lato', 39, 2) ON CONFLICT (id) DO NOTHING;

-- Scheda 3 (Luigi - Forza Gambe)
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (12, 'Mercoledì', 5, 5, 180, 'Massimale', 11, 3) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (13, 'Mercoledì', 4, 8, 120, 'Non stendere le ginocchia', 12, 3) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (14, 'Mercoledì', 3, 12, 90, 'Squeeze in alto', 15, 3) ON CONFLICT (id) DO NOTHING;

-- Scheda 5 (Giulia - Glutei)
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (15, 'Venerdì', 4, 10, 90, 'Lenti', 13, 5) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (16, 'Venerdì', 4, 15, 60, 'Burnout', 16, 5) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (17, 'Venerdì', 4, 12, 90, 'Contrazione di picco 2 sec', 31, 5) ON CONFLICT (id) DO NOTHING;

-- NUOVE RIGHE PER ADMIN 11 (Scheda 21: Spalmate su 3 giorni)
-- Giorno 1: Lunedì
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (18, 'Lunedì', 4, 8, 120, 'Focus Petto', 1, 21) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (19, 'Lunedì', 3, 10, 90, 'Manubri', 2, 21) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (20, 'Lunedì', 4, 10, 90, 'Bicipiti pesanti', 22, 21) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (21, 'Lunedì', 3, 12, 60, 'Isolamento', 35, 21) ON CONFLICT (id) DO NOTHING;
-- Giorno 2: Mercoledì
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (22, 'Mercoledì', 4, 8, 120, 'Gambe', 11, 21) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (23, 'Mercoledì', 3, 12, 90, 'Pressa', 12, 21) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (24, 'Mercoledì', 4, 8, 120, 'Spalle multiarticolare', 18, 21) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (25, 'Mercoledì', 3, 15, 60, 'Alzate', 19, 21) ON CONFLICT (id) DO NOTHING;
-- Giorno 3: Venerdì
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (26, 'Venerdì', 4, 8, 120, 'Dorso larghezza', 9, 21) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (27, 'Venerdì', 4, 10, 90, 'Dorso spessore', 7, 21) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (28, 'Venerdì', 3, 12, 90, 'Tricipiti isolamento', 24, 21) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (29, 'Venerdì', 3, 12, 60, 'Dips', 36, 21) ON CONFLICT (id) DO NOTHING;

-- NUOVE RIGHE PER ADMIN 12 (Scheda 22: Spalmate su 3 giorni)
-- Giorno 1: Lunedì
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (30, 'Lunedì', 5, 5, 180, 'Forza frontale', 32, 22) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (31, 'Lunedì', 3, 10, 90, 'Affondi pesanti', 13, 22) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (32, 'Lunedì', 4, 1, 60, 'Plank da 1 minuto', 27, 22) ON CONFLICT (id) DO NOTHING;
-- Giorno 2: Mercoledì
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (33, 'Mercoledì', 4, 15, 90, 'Piegamenti zavorrati', 5, 22) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (34, 'Mercoledì', 4, 10, 90, 'Tirate spalle', 34, 22) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (35, 'Mercoledì', 3, 12, 60, 'Brachioradiale', 35, 22) ON CONFLICT (id) DO NOTHING;
-- Giorno 3: Venerdì
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (36, 'Venerdì', 5, 5, 180, 'Forza catena posteriore', 16, 22) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (37, 'Venerdì', 4, 10, 90, 'Rematore singolo profondo', 40, 22) ON CONFLICT (id) DO NOTHING;
INSERT INTO riga_scheda (id, giorno, serie, ripetizioni, tempo_riposo_secondi, note, esercizio_id, scheda_id) VALUES (38, 'Venerdì', 3, 12, 60, 'Pulley di isolamento', 8, 22) ON CONFLICT (id) DO NOTHING;


-- ==========================================
-- 6. RECENSIONI DAGLI ADMIN
-- ==========================================
INSERT INTO recensione (id, testo, valutazione, data_creazione, autore_id, scheda_id) VALUES (1, 'Ottima struttura della scheda, volume bilanciato sui pettorali. Complimenti!', 5, '2024-02-01', 11, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO recensione (id, testo, valutazione, data_creazione, autore_id, scheda_id) VALUES (2, 'Buona la scelta degli esercizi di forza, ma inserirei più recupero tra le serie.', 4, '2024-03-05', 12, 3) ON CONFLICT (id) DO NOTHING;
INSERT INTO recensione (id, testo, valutazione, data_creazione, autore_id, scheda_id) VALUES (3, 'Scheda un po scarna per le gambe. Bene linserimento finale dell Hip Thrust!', 4, '2024-03-20', 11, 5) ON CONFLICT (id) DO NOTHING;
INSERT INTO recensione (id, testo, valutazione, data_creazione, autore_id, scheda_id) VALUES (4, 'Perfetta per il Powerlifting, volume e intensità da manuale.', 5, '2024-06-01', 12, 9) ON CONFLICT (id) DO NOTHING;
INSERT INTO recensione (id, testo, valutazione, data_creazione, autore_id, scheda_id) VALUES (5, 'Ottima per i principianti assoluti, eviterà infortuni.', 4, '2024-08-15', 11, 16) ON CONFLICT (id) DO NOTHING;

-- ==========================================
-- 7. RESTART SEQUENCE ID
-- ==========================================
SELECT setval('utente_id_seq', (SELECT MAX(id) FROM utente));
SELECT setval('credentials_id_seq', (SELECT MAX(id) FROM credentials));
SELECT setval('esercizio_id_seq', (SELECT MAX(id) FROM esercizio));
SELECT setval('scheda_allenamento_id_seq', (SELECT MAX(id) FROM scheda_allenamento));
SELECT setval('riga_scheda_id_seq', (SELECT MAX(id) FROM riga_scheda));
SELECT setval('recensione_id_seq', (SELECT MAX(id) FROM recensione));