INSERT INTO owner (ownerid, firstname, lastname) VALUES (1, 'Tim', 'Taylor');
INSERT INTO owner (ownerid, firstname, lastname) VALUES (2, 'Randy', 'Taylor');
INSERT INTO owner (ownerid, firstname, lastname) VALUES (3, 'Al', 'Borland');
INSERT INTO owner (ownerid, firstname, lastname) VALUES (4, 'Brad', 'Taylor');
INSERT INTO owner (ownerid, firstname, lastname) VALUES (5, 'Tim', 'Allen');
INSERT INTO owner (ownerid, firstname, lastname) VALUES (6, 'Richard', 'Karn');

INSERT INTO tool (toolid, toolname, quantity, price) VALUES (11, 'Electric Drill', 2, 20);
INSERT INTO tool (toolid, toolname, quantity, price) VALUES (12, 'Chainsaw', 2, 20);
INSERT INTO tool (toolid, toolname, quantity, price) VALUES (13, 'Mallet', 2, 20);
INSERT INTO tool (toolid, toolname, quantity, price) VALUES (14, 'Hammer', 2, 20);
INSERT INTO tool (toolid, toolname, quantity, price) VALUES (15, 'Wrench', 2, 20);

INSERT INTO owns (toolid, ownerid) VALUES (11, 6);
INSERT INTO owns (toolid, ownerid) VALUES (12, 2);
INSERT INTO owns (toolid, ownerid) VALUES (13, 2);
INSERT INTO owns (toolid, ownerid) VALUES (14, 5);
INSERT INTO owns (toolid, ownerid) VALUES (14, 3);
INSERT INTO owns (toolid, ownerid) VALUES (15, 4);

alter sequence hibernate_sequence restart with 25;
