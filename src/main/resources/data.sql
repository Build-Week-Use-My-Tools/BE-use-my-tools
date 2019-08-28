INSERT INTO owner (ownerid, firstname, lastname) VALUES (1, 'Tim', 'Taylor');
INSERT INTO owner (ownerid, firstname, lastname) VALUES (2, 'Randy', 'Taylor');
INSERT INTO owner (ownerid, firstname, lastname) VALUES (3, 'Al', 'Borland');
INSERT INTO owner (ownerid, firstname, lastname) VALUES (4, 'Brad', 'Taylor');
INSERT INTO owner (ownerid, firstname, lastname) VALUES (5, 'Tim', 'Allen');
INSERT INTO owner (ownerid, firstname, lastname) VALUES (6, 'Richard', 'Karn');

INSERT INTO tool (toolid, toolname, quantity, price, image, borrowed) VALUES (11, 'Electric Drill', 2, 50, 'https://images.unsplash.com/photo-1504148455328-c376907d081c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1443&q=80', false);
INSERT INTO tool (toolid, toolname, quantity, price, image, borrowed) VALUES (12, 'Blue Bosch Chainsaw', 3, 30, 'https://images.unsplash.com/photo-1556912743-4a6f9e70dbd4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60', false);
INSERT INTO tool (toolid, toolname, quantity, price, image, borrowed) VALUES (13, 'Ryobi Grinder', 1, 40, 'https://images.unsplash.com/photo-1557854730-100df509b2d8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60', false);
INSERT INTO tool (toolid, toolname, quantity, price, image, borrowed) VALUES (14, 'Electric Saw', 4, 15, 'https://images.unsplash.com/photo-1505855796860-aa05646cbf1f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60', false);
INSERT INTO tool (toolid, toolname, quantity, price, image, borrowed) VALUES (15, 'Dewalt Finishing Sander', 8, 10, 'https://images.unsplash.com/photo-1513467655676-561b7d489a88?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60', false);

INSERT INTO owns (toolid, ownerid) VALUES (11, 6);
INSERT INTO owns (toolid, ownerid) VALUES (12, 2);
INSERT INTO owns (toolid, ownerid) VALUES (13, 2);
INSERT INTO owns (toolid, ownerid) VALUES (14, 5);
INSERT INTO owns (toolid, ownerid) VALUES (14, 3);
INSERT INTO owns (toolid, ownerid) VALUES (15, 4);

alter sequence hibernate_sequence restart with 25;