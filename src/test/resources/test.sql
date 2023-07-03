DROP TABLE IF EXISTS player;

CREATE TABLE player
(
    id             BIGINT(20)  NOT NULL AUTO_INCREMENT,
    name           VARCHAR(12) NULL,
    title          VARCHAR(30) NULL,
    race           VARCHAR(20) NULL,
    profession     VARCHAR(20) NULL,
    birthday       DATE        NULL,
    banned         BIT(1)      NULL,
    experience     INT(10)     NULL,
    level          INT(3)      NULL,
    untilNextLevel INT(10)     NULL,
    PRIMARY KEY (id)
);

INSERT INTO player(name, title, race, profession, birthday, banned, experience, level, untilNextLevel)
VALUES ('Nius', 'Coming Without Noise', 'HOBBIT', 'ROGUE', '2010-10-12', false, 58347, 33, 1153)
     , ('Nikrashsh', 'NightWolf', 'ORC', 'WARLOCK', '2010-02-14', false, 174403, 58, 2597)
     , ('Ezzessel', 'hissing', 'DWARF', 'CLERIC', '2006-02-28', true, 804, 3, 196)
     , ('Balan', 'Tse Raa', 'DWARF', 'ROGUE', '2008-02-25', true, 44553, 29, 1947)
     , ('Eleanor', 'Grandma', 'HUMAN', 'SORCERER', '2006-01-07', true, 63986, 35, 2614)
     , ('Eman', 'Eared Flyer', 'ELF', 'SORCERER', '2004-06-21', false, 163743, 56, 1557)
     , ('Talan', 'Bronx-born', 'GIANT', 'ROGUE', '2005-05-15', false, 68950, 36, 1350)
     , ('Arilan', 'Benefactor', 'ELF', 'SORCERER', '2006-08-10', false, 61023, 34, 1977)
     , ('Deract', 'Red Ear Elf', 'ELF', 'ROGUE', '2010-06-22', false, 156630, 55, 2970)
     , ('Archille', 'Deadly', 'GIANT', 'PALADIN', '2005-01-12', false, 76010, 38, 1990)
     , ('Endarion', 'Little Elf', 'ELF', 'DRUID', '2001-04-24', false, 103734, 45, 4366)
     , ('Fierwyn', 'Dark Ideologist', 'HUMAN', 'NAZGUL', '2010-09-06', false, 7903, 12, 1197)
     , ('Haridin', 'Bedouin', 'TROLL', 'WARRIOR', '2009-09-08', false, 114088, 47, 3512)
     , ('Jur', 'THIRST FIGHTER', 'ORC', 'DRUID', '2009-07-14', false, 29573, 23, 427)
     , ('Gron', 'Warrior bound for battle', 'GIANT', 'PALADIN', '2005-04-28', false, 174414, 58, 2586)
     , ('Morviel', 'Spear of Kalima', 'ELF', 'CLERIC', '2010-03-15', false, 49872, 31, 2928)
     , ('Nnufis', 'Diamond', 'HUMAN', 'ROGUE', '2001-09-03', false, 162477, 56, 2823)
     , ('Ypx', 'Troll oppresses spruce', 'TROLL', 'WARRIOR', '2001-04-08', true, 136860, 51, 940)
     , ('Blake', 'Grey Warrior', 'HUMAN', 'ROGUE', '2005-05-23', false, 151039, 54, 2961)
     , ('Ness', 'Bead', 'TROLL', 'WARRIOR', '2008-02-09', true, 64945, 35, 1655)
     , ('Ferin', 'Warrior', 'TROLL', 'WARRIOR', '2003-07-08', false, 120006, 48, 2494)
     , ('Solkah', 'Student of Magic', 'ELF', 'SORCERER', '2001-11-07', false, 152996, 54, 1004)
     , ('Skink', 'War Titan', 'GIANT', 'WARRIOR', '2008-01-04', true, 86585, 41, 3715)
     , ('Aisha', 'Temptress', 'HUMAN', 'CLERIC', '2010-01-25', false, 106181, 45, 1919)
     , ('Tant', 'Goddamn it', 'DWARF', 'PALADIN', '2010-10-03', false, 33889, 25, 1211)
     , ('Trainigan', 'Great Wizard', 'ELF', 'SORCERER', '2004-05-17', false, 91676, 42, 2924)
     , ('Wooger', 'Sad', 'TROLL', 'NAZGUL', '2010-10-04', false, 93079, 42, 1521)
     , ('Kamirage', 'Banker', 'DWARF', 'CLERIC', '2005-08-05', true, 79884, 39, 2116)
     , ('Larkin', 'Holy', 'HOBBIT', 'CLERIC', '2003-07-10', false, 111868, 46, 932)
     , ('Zandir', 'Darkseer', 'ELF', 'WARLOCK', '2003-05-24', false, 29654, 23, 346)
     , ('Balgor', 'cave Uruk', 'ORC', 'NAZGUL', '2005-02-23', false, 18869, 18, 131)
     , ('Regarne', 'Olivier Lover', 'GIANT', 'WARRIOR', '2006-12-23', false, 144878, 53, 3622)
     , ('Angelli', 'Young Fighter', 'DWARF', 'WARRIOR', '2010-04-08', false, 59281, 33, 219)
     , ('Jerys', 'Imperial Warrior', 'ORC', 'WARRIOR', '2001-05-12', false, 173807, 58, 3193)
     , ('Zheks', 'Yarochkino Sun', 'GIANT', 'WARRIOR', '2008-01-04', false, 848, 3, 152)
     , ('Filuel', 'Chemist and Carpuscular.', 'ELF', 'WARLOCK', '2008-08-03', false, 48496, 30, 1104)
     , ('Yara', 'Seductive', 'HUMAN', 'CLERIC', '2004-06-12', false, 138306, 52, 4794)
     , ('Illinas', 'Hieroglyph', 'HOBBIT', 'WARRIOR', '2007-06-03', false, 115546, 47, 2054)
     , ('Ardong', 'Flash A', 'HUMAN', 'WARLOCK', '2009-09-16', false, 24984, 21, 316)
     , ('Attiris', 'acting Karvandos', 'ELF', 'SORCERER', '2010-04-15', true, 60520, 34, 2480);