# KPPRO_TheRoomGameOrganizer

## Popis
TheRoomGameOrganizer je komplexní aplikace zaměřená na organizování a správu herních turnajů. Je navržena tak, aby usnadnila proces plánování, řízení a sledování turnajů pro různé hry. Aplikace umožňuje uživatelům spravovat své účty, registrovat se na turnaje, sledovat výsledky a mnoho dalšího.

### Hlavní funkce:
- **Správa turnajů**: Umožňuje vytvářet, upravovat a mazat turnaje. Uživatelé s rolí ADMIN mohou spravovat všechny aspekty turnajů, včetně nastavení pravidel, časového plánu a účastníků.
- **Registrace na turnaje**: Uživatelé s rolí USER se mohou registrovat na dostupné turnaje a sledovat své přihlášky.
- **Sledování výsledků**: Uživatelé mohou sledovat výsledky turnajů, na které se registrovali, a prohlížet si historické výsledky.
- **Správa uživatelů**: Uživatelé s rolí ADMIN mohou spravovat uživatelské účty, včetně přidělování rolí a správy oprávnění.
- **Bezpečnost a autentizace**: Aplikace využívá JWT (JSON Web Token) pro zabezpečenou autentizaci a autorizaci uživatelů.

### Klíčové výhody:
- **Uživatelská přívětivost**: Intuitivní rozhraní, které usnadňuje používání aplikace i méně zkušeným uživatelům.
- **Flexibilita**: Možnost přizpůsobit turnaje různým hrám a pravidlům.
- **Bezpečnost**: Použití moderních bezpečnostních standardů pro ochranu uživatelských dat.

## Použíté technologie
- **Java 21**
- **Spring Boot framework**
- **H2 database**
- **JUNIT**
- **Spring Security**
- **Thymeleaf**
- **HTML**
- **CSS**
- **JavaScript**

### Role a oprávnění

#### Nepřihlášený uživatel
- Může prohlížet veřejné informace o turnajích.
- Nemůže se registrovat na turnaje ani spravovat uživatelské účty.

#### Uživatel s rolí USER
- Může se registrovat na turnaje.
- Může prohlížet a upravovat své osobní údaje.
- Může sledovat výsledky turnajů, na které se registroval.

#### Uživatel s rolí ADMIN
- Může spravovat všechny uživatelské účty.
- Může vytvářet, upravovat a mazat turnaje.
- Může schvalovat a spravovat registrace na turnaje.
- Má plný přístup ke všem funkcím a nastavením aplikace.

## Základní model použití

### Popis Use Case
- **Prohlížení turnajů**: Nepřihlášený uživatel může prohlížet seznam veřejných turnajů a jejich detaily.
- **Registrace na turnaj**: Uživatel s rolí USER se může registrovat na dostupné turnaje.
- **Sledování výsledků**: Uživatel s rolí USER může prohlížet výsledky turnajů, na které se registroval.
- **Správa uživatelů**: Uživatel s rolí ADMIN může spravovat všechny uživatelské účty.
- **Správa turnajů**: Uživatel s rolí ADMIN může vytvářet, upravovat a mazat turnaje.
- **Schvalování registrací**: Uživatel s rolí ADMIN může schvalovat registrace na turnaje.

---

## Description
The web application will serve as a real project for the "The Room" clubs located in three cities. Among other things, "The Room" clubs organize tournaments in board and card games.
TheRoomGameOrganizer is a comprehensive application focused on organizing and managing gaming tournaments. It is designed to facilitate the planning, management, and tracking of tournaments for various games. The application allows users to manage their accounts, register for tournaments, track results, and much more.

### Key Features:
- **Tournament Management**: Allows creating, editing, and deleting tournaments. Users with the ADMIN role can manage all aspects of tournaments, including setting rules, schedules, and participants.
- **Tournament Registration**: Users with the USER role can register for available tournaments and track their entries.
- **Results Tracking**: Users can track the results of tournaments they have registered for and view historical results.
- **User Management**: Users with the ADMIN role can manage user accounts, including assigning roles and managing permissions.
- **Security and Authentication**: The application uses JWT (JSON Web Token) for secure user authentication and authorization.

### Key Benefits:
- **User-Friendly**: An intuitive interface that makes the application easy to use even for less experienced users.
- **Flexibility**: The ability to customize tournaments for various games and rules.
- **Security**: Use of modern security standards to protect user data.

## Technologies Used
This project utilizes the following technologies:

- **Java 21**
- **Spring Boot framework**
- **H2 database**
- **JUNIT**
- **Spring Security**
- **Thymeleaf**
- **HTML**
- **CSS**
- **JavaScript**

## Usage
After installation, you can start organizing and managing gaming tournaments. Here are some key features:

### Roles and Permissions

#### Unauthenticated User
- Can view public information about tournaments.
- Cannot register for tournaments or manage user accounts.

#### User with USER Role
- Can register for tournaments.
- Can view and edit their personal information.
- Can track the results of tournaments they have registered for.

#### User with ADMIN Role
- Can manage all user accounts.
- Can create, edit, and delete tournaments.
- Can approve and manage tournament registrations.
- Has full access to all application features and settings.

### Use Case Description
- **View Tournaments**: An unauthenticated user can view a list of public tournaments and their details.
- **Register for Tournament**: A user with the USER role can register for available tournaments.
- **Track Results**: A user with the USER role can view the results of tournaments they have registered for.
- **Manage Users**: A user with the ADMIN role can manage all user accounts.
- **Manage Tournaments**: A user with the ADMIN role can create, edit, and delete tournaments.
- **Approve Registrations**: A user with the ADMIN role can approve registrations for tournaments.

## Latest Commits
- [Results of tournaments, last updates...](https://github.com/ard0p8v/KPPRO_TheRoomGameOrganizer/commit/7363193ef22f52fed9498d0d50a67fd33a4408cf)
- [Reservations on tournaments, data, schema, user administration](https://github.com/ard0p8v/KPPRO_TheRoomGameOrganizer/commit/b87629641bc8c757000f18e399b839058790fe41)
- [Views, services, and implementations](https://github.com/ard0p8v/KPPRO_TheRoomGameOrganizer/commit/95da0c1386059b92704195959ea7d2d14f8f5b3f)

For more details on the latest commits, visit the [commits page](https://github.com/ard0p8v/KPPRO_TheRoomGameOrganizer/commits/master).

## Basic Use Case Model

### Use Case Diagram
```plaintext
+-------------------------+       +------------------------+
|                         |       |                        |
|     Unauthenticated     |       |    User (USER)         |
|        User             |       |                        |
|                         |       |                        |
|  +------------------+   |       |  +------------------+  |
|  |  View Tournaments|<--+       |  |  Register for    |  |
|  |                  |          |  |  Tournament       |  |
|  +------------------+          |  +------------------+  |
|                                 |                        |
|                                 |  +------------------+  |
|                                 |  |  Track Results   |  |
|                                 |  |                  |  |
|                                 |  +------------------+  |
+-------------------------+       +------------------------+
            ^                                ^
            |                                |
            |                                |
+-------------------------+       +------------------------+
|                         |       |                        |
|     User (ADMIN)        |       |     Tournament         |
|                         |       |                        |
|  +------------------+   |       |  +------------------+  |
|  |  Manage Users    |<--+------>|  |  Manage Tournaments  |
|  +------------------+   |       |  +------------------+  |
|  +------------------+   |       |                        |
|  |  Manage Tournaments| |       +------------------------+
|  +------------------+   |
|  +------------------+   |
|  |  Approve         |   |
|  |  Registrations   |   |
|  +------------------+   |
+-------------------------+
