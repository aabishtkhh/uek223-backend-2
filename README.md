# README #

## MU-Applikation Backend

Hier erklären wir kurz wie Sie unseres Projekt aufsetzen kann. Im Projekt benutzen wir die Java JDK Version 18. Zudem benutzen wir einen Docker Container der ein PostgreSQL-Server besitzt. Der Docker lauft auf dem Port 5432. Der unterstehende Command erstellt einen passenden Docker für dieses Projekt.
### Docker command
```
docker run --name postgres_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```
Falls Sie den Benutzername oder das Passwort, die als "postgres" definiert sind, ändern möchten, könne Sie das auch gerne tun. Jedoch müssten Sie diese Änderungen auch im Projekt in der Datei *application.properties* vornehemen.
### Users
Im Backend haben Sie schon zwei User zur Verfügung.
| User-Email        	| Passwort 	| Role  	|
|-------------------	|----------	|-------	|
| admin@example.com 	| 1234     	| ADMIN 	|
| user@example.com  	| 1234     	| USER  	|

Der Admin besitzt alle Rechte, d.h. dass er einen neuen Blog Post erstellen, bearbeiten, löschen und lesen kann. Zudem kann er auch alle User bearbeiten, löschen und kann alle auch lesen. Hingegen kann ein einfacher User nur seine eigenen Blog Posts die er erstellt hatte, bearbeiten und löschen. Der einfache User kann auch alle Blogs lesen, diese aber nicht verwalten, wenn es nicht seine eigenen Posts sind.

### Git-Repository clonen
Dieses Git-Hub Repository können Sie ganz einfach mit diesem Command clonen.
```
git clone https://github.com/aabishtkhh/uek223-backend-2.git
```
Danach können Sie den vorher erstellten Docker laufen lassen. Öffnen Sie dann dieses Projekt in IntelliJ und lassen Sie auch dies laufen. 
### Troubleshooting
Wenn dieser Fehler Auftritt starten Sie einfach die Applikation erneut. Hibernate initialisiert die Tabellen manchmal nicht schnell genug und verursacht diesen Fehler. Ein Neustart der Applikation behebt dies.
```
org.postgresql.util.PSQLException: ERROR: relation "role_authority" does not exist
```
