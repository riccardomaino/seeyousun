INSERT INTO public.resorts (id, description, location, name, photo_cover, rating, total_umbrella_line, total_umbrella_column)
VALUES (1, 'Il più bel lido che puoi trovare', 'Varigotti, Liguria', 'Baia dei Saraceni', 'https://storage.googleapis.com/pod_public/1300/122734.jpg', 4, 4, 6);
INSERT INTO public.resorts (id, description, location, name, photo_cover, rating, total_umbrella_line, total_umbrella_column)
VALUES (2, 'Un luogo incantevole con acque cristalline e servizi di prima classe. Ideale per trascorrere una giornata rilassante sotto il sole salentino.', 'Gallipoli, Puglia', 'Lido Punta della Suina', '../../assets/Lidi/PuntaDellaSuina/1.jpg', 4, 4, 6);
INSERT INTO public.resorts (id, description, location, name, photo_cover, rating, total_umbrella_line, total_umbrella_column)
VALUES (3, 'Situato in una baia mozzafiato, questo lido offre paesaggi mozzafiato e una vasta gamma di attività acquatiche. Perfetto per famiglie e amanti del mare.', 'Otranto, Puglia', 'Lido Baia dei Turchi', '../../assets/Lidi/LidoBaiadeiTurchi/1.jpg', 5, 4, 6);
INSERT INTO public.resorts (id, description, location, name, photo_cover, rating, total_umbrella_line, total_umbrella_column)
VALUES (4, 'Un oasi di tranquillità con acque termali naturali. Le piscine naturali rendono questo lido unico nel suo genere. Un luogo perfetto per rilassarsi e rigenerarsi.', 'Roca Vecchia, Puglia', 'Lido La Poesia', '../../assets/Lidi/LidoLaPoesia/1.jpg', 3, 4, 6);
INSERT INTO public.resorts (id, description, location, name, photo_cover, rating, total_umbrella_line, total_umbrella_column)
VALUES (5, 'Un mix di divertimento e relax, con una spiaggia dorata e servizi moderni. Ideale per chi cerca una giornata di sole e mare senza rinunciare al comfort.', 'Porto Cesareo, Puglia', 'Lido Tabù', '../../assets/Lidi/LidoTabu/1.jpg', 4, 4, 6);
INSERT INTO public.resorts (id, description, location, name, photo_cover, rating, total_umbrella_line, total_umbrella_column)
VALUES (6, 'A serene oasis nestled along the Amalfi Coast, Lido del Sole offers breathtaking views of the azure Mediterranean. With 4-star amenities and a total of 6 umbrella lines, it''s the perfect spot for sun-soaked relaxation.', 'Positano, Campania', 'Lido Del Sole', '../../assets/Lidi/LidoDelSole/1.jpg', 4, 4, 6);
INSERT INTO public.resorts (id, description, location, name, photo_cover, rating, total_umbrella_line, total_umbrella_column)
VALUES (7, 'Discover paradise at Cala Luna Beach Club. Crystal-clear waters, white sandy beaches, and 6 umbrella columns await you. Ideal for families and couples seeking tranquility.', 'San Teodoro, Sardinia', 'Cala Luna Beach Club', '../../assets/Lidi/CalaLunaBeachClub/1.jpg', 5, 4, 6);
INSERT INTO public.resorts (id, description, location, name, photo_cover, rating, total_umbrella_line, total_umbrella_column)
VALUES (8, 'Unwind at Sunset Cove, where golden sands meet turquoise tides. Enjoy 4 umbrella lines and stunning sunsets. Perfect for beach lovers and sunset chasers.', 'Taormina, Sicily', 'Sunset Cove Resort', '../../assets/Lidi/SunsetCoveResort/1.jpg', 4, 4, 6);

INSERT INTO public.services (id, name, category) VALUES (1, 'Spiagge accessibili a disabili', 'accessibilità');
INSERT INTO public.services (id, name, category) VALUES (2, 'Idromassaggio', 'benessere');
INSERT INTO public.services (id, name, category) VALUES (3, 'Bar', 'ristorazione');
INSERT INTO public.services (id, name, category) VALUES (4, 'Ristorante', 'ristorazione');
INSERT INTO public.services (id, name, category) VALUES (5, 'Cabine', 'servizio');
INSERT INTO public.services (id, name, category) VALUES (6, 'Acceso animali', 'servizio');
INSERT INTO public.services (id, name, category) VALUES (7, 'Doccia calda', 'servizio');
INSERT INTO public.services (id, name, category) VALUES (8, 'Posto barca', 'servizio');
INSERT INTO public.services (id, name, category) VALUES (9, 'Wifi', 'servizio');
INSERT INTO public.services (id, name, category) VALUES (10, 'Tv', 'servizio');
INSERT INTO public.services (id, name, category) VALUES (11, 'Giochi da tavolo', 'servizio');
INSERT INTO public.services (id, name, category) VALUES (12, 'Carte', 'servizio');
INSERT INTO public.services (id, name, category) VALUES (13, 'Videogiochi', 'servizio');
INSERT INTO public.services (id, name, category) VALUES (14, 'Piscina', 'servizio');
INSERT INTO public.services (id, name, category) VALUES (15, 'Animazione', 'servizi per famiglie');
INSERT INTO public.services (id, name, category) VALUES (16, 'Area giochi', 'servizi per famiglie');
INSERT INTO public.services (id, name, category) VALUES (17, 'Palestra', 'sport e attività');
INSERT INTO public.services (id, name, category) VALUES (18, 'Beach volley', 'sport e attività');
INSERT INTO public.services (id, name, category) VALUES (19, 'Beach soccer', 'sport e attività');
INSERT INTO public.services (id, name, category) VALUES (20, 'Calcio balilla', 'sport e attività');
INSERT INTO public.services (id, name, category) VALUES (21, 'Pedalo', 'sport e attività');
INSERT INTO public.services (id, name, category) VALUES (22, 'Canoe', 'sport e attività');
INSERT INTO public.services (id, name, category) VALUES (23, 'Corsi di windsurf', 'sport e attività');
INSERT INTO public.services (id, name, category) VALUES (24, 'Corsi kitesurfing', 'sport e attività');
INSERT INTO public.services (id, name, category) VALUES (25, 'Corso vela', 'sport e attività');

INSERT INTO public.resorts_services (resort_id, service_id) VALUES (1, 1);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (1, 2);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (1, 3);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (1, 7);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (2, 14);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (2, 8);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (3, 7);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (3, 9);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (3, 10);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (4, 1);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (4, 3);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (4, 25);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (5, 3);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (5, 10);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (5, 19);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (7, 3);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (7, 13);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (7, 14);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (7, 15);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (8, 3);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (8, 12);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (8, 16);
INSERT INTO public.resorts_services (resort_id, service_id) VALUES (8, 17);

INSERT INTO public.phone_numbers (resort_id, phone_numbers) VALUES (1, '+39 3453773947');
INSERT INTO public.phone_numbers (resort_id, phone_numbers) VALUES (2, '+39 3664758782');
INSERT INTO public.phone_numbers (resort_id, phone_numbers) VALUES (3, '+39 3331485664');
INSERT INTO public.phone_numbers (resort_id, phone_numbers) VALUES (4, '+39 3881343236');
INSERT INTO public.phone_numbers (resort_id, phone_numbers) VALUES (5, '+39 3543747485');
INSERT INTO public.phone_numbers (resort_id, phone_numbers) VALUES (6, '+39 3664758782');
INSERT INTO public.phone_numbers (resort_id, phone_numbers) VALUES (7, '+39 3346724153');
INSERT INTO public.phone_numbers (resort_id, phone_numbers) VALUES (8, '+39 3143646457');

INSERT INTO public.time_tables (resort_id, time_tables) VALUES (1, 'Lun - Dom');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (1, '7:30 - 19:00');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (2, 'Lun - Sab');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (2, '8:30 - 20:00');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (3, 'Mar - Dom');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (3, '6:30 - 19:30');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (4, 'Lun - Ven');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (4, '9:30 - 13:00 e 14:00 - 19:00');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (5, 'Lun - Dom');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (5, '7:30 - 19:00');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (6, 'Lun - Sab');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (6, '8:30 - 20:00');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (7, 'Mar - Dom');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (7, '6:30 - 19:30');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (8, 'Lun - Ven');
INSERT INTO public.time_tables (resort_id, time_tables) VALUES (8, '9:30 - 13:00 e 14:00 - 19:00');

INSERT INTO public.price_period (id, period_final_date, period_initial_date, sunbed_price, resort_id) VALUES (1, '2024-05-31', '2024-01-01', 5, 2);
INSERT INTO public.price_period (id, period_final_date, period_initial_date, sunbed_price, resort_id) VALUES (2, '2024-06-30', '2024-06-01', 10, 2);
INSERT INTO public.price_period (id, period_final_date, period_initial_date, sunbed_price, resort_id) VALUES (3, '2024-05-31', '2024-01-01', 5, 8);
INSERT INTO public.price_period (id, period_final_date, period_initial_date, sunbed_price, resort_id) VALUES (4, '2024-05-31', '2024-01-01', 5, 1);
INSERT INTO public.price_period (id, period_final_date, period_initial_date, sunbed_price, resort_id) VALUES (5, '2024-05-31', '2024-01-01', 5, 3);
INSERT INTO public.price_period (id, period_final_date, period_initial_date, sunbed_price, resort_id) VALUES (6, '2024-05-31', '2024-01-01', 5, 4);
INSERT INTO public.price_period (id, period_final_date, period_initial_date, sunbed_price, resort_id) VALUES (7, '2024-05-31', '2024-01-01', 5, 5);
INSERT INTO public.price_period (id, period_final_date, period_initial_date, sunbed_price, resort_id) VALUES (8, '2024-05-31', '2024-01-01', 5, 6);
INSERT INTO public.price_period (id, period_final_date, period_initial_date, sunbed_price, resort_id) VALUES (9, '2024-05-31', '2024-01-01', 5, 7);


INSERT INTO public.umbrella_line_price (price_period_id, umbrella_price, umbrella_price_order) VALUES (1, 15, 0);
INSERT INTO public.umbrella_line_price (price_period_id, umbrella_price, umbrella_price_order) VALUES (1, 10, 1);
INSERT INTO public.umbrella_line_price (price_period_id, umbrella_price, umbrella_price_order) VALUES (1, 10, 2);
INSERT INTO public.umbrella_line_price (price_period_id, umbrella_price, umbrella_price_order) VALUES (2, 20, 0);
INSERT INTO public.umbrella_line_price (price_period_id, umbrella_price, umbrella_price_order) VALUES (2, 15, 1);
INSERT INTO public.umbrella_line_price (price_period_id, umbrella_price, umbrella_price_order) VALUES (2, 10, 2);

INSERT INTO public.resorts_photos (resort_id, photos) VALUES (2, 'https://i.postimg.cc/Kjf4QGvq/1.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (2, 'https://i.postimg.cc/Qx6HTdzV/2.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (2, 'https://i.postimg.cc/CLMR5qmW/3.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (2, 'https://i.postimg.cc/mkBtQzR1/4.webp');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (2, 'https://i.postimg.cc/Y2z4t3f6/5.gif');

INSERT INTO public.resorts_photos (resort_id, photos) VALUES (3, 'https://i.postimg.cc/3w047QnN/1.png');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (3, 'https://i.postimg.cc/8z0Jqzdx/2.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (3, 'https://i.postimg.cc/sgXG6hWp/3.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (3, 'https://i.postimg.cc/28TB7w1k/4.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (3, 'https://i.postimg.cc/JnwyzY2q/5.jpg');

INSERT INTO public.resorts_photos (resort_id, photos) VALUES (4, 'https://i.postimg.cc/BvRF1nX2/1.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (4, 'https://i.postimg.cc/nhRBpkxR/2.webp');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (4, 'https://i.postimg.cc/K8vB6W63/3.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (4, 'https://i.postimg.cc/xCHzGVzh/4.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (4, 'https://i.postimg.cc/SKn8sMNy/5.jpg');

INSERT INTO public.resorts_photos (resort_id, photos) VALUES (5, 'https://i.postimg.cc/Wbc7HXp0/1.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (5, 'https://i.postimg.cc/Pfw4yBHt/2.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (5, 'https://i.postimg.cc/gJfKdKVF/3.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (5, 'https://i.postimg.cc/pT9BgSnJ/4.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (5, 'https://i.postimg.cc/cCwBJ8hK/5.jpg');

INSERT INTO public.resorts_photos (resort_id, photos) VALUES (6, 'https://i.postimg.cc/4478MY6R/1.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (6, 'https://i.postimg.cc/L4cyMmDY/2.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (6, 'https://i.postimg.cc/sX8TxzRL/3.avif');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (6, 'https://i.postimg.cc/sxhwZLjg/4.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (6, 'https://i.postimg.cc/1t4Wytb6/5.jpg');

INSERT INTO public.resorts_photos (resort_id, photos) VALUES (7, 'https://i.postimg.cc/PxF1rqTG/1.webp');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (7, 'https://i.postimg.cc/prw8z1mq/2.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (7, 'https://i.postimg.cc/gjX8wP2x/3.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (7, 'https://i.postimg.cc/RCywkqgK/4.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (7, 'https://i.postimg.cc/5Nz8b1rG/5.jpg');

INSERT INTO public.resorts_photos (resort_id, photos) VALUES (8, 'https://i.postimg.cc/59Rmfs2H/1.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (8, 'https://i.postimg.cc/brW0LwB0/2.jpg ');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (8, 'https://i.postimg.cc/k4jv6cNp/3.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (8, 'https://i.postimg.cc/J0GcbkP4/4.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (8, 'https://i.postimg.cc/qM6xq2N1/5.jpg');

INSERT INTO public.resorts_photos (resort_id, photos) VALUES (1, 'https://i.postimg.cc/2SccVVPP/5.webpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (1, 'https://i.postimg.cc/GmhgLkSF/4.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (1, 'https://i.postimg.cc/Gp4MYbR9/3.jpg');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (1, 'https://i.postimg.cc/XvtsktNL/2.webp');
INSERT INTO public.resorts_photos (resort_id, photos) VALUES (1, 'https://i.postimg.cc/gkKBbM4c/1.jpg');


