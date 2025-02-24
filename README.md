Mini Application CRUD avec Spring Boot et Angular

Description

Cette application est une solution CRUD développée avec Spring Boot pour le backend et Angular pour le frontend. Elle permet de gérer trois entités : Student, Registration et Classe, en effectuant les opérations de création, lecture, mise à jour et suppression.

Technologies utilisées

Backend (Spring Boot)

- Java 17+

- Spring Boot 3+

- Spring Data JPA

- XAMPP

- MySQL

- Lombok

- Spring Web

- MapStruct (pour le mapping DTOs-Entities)

- Log4j (pour la gestion des logs)

Frontend (Angular)

- Angular 17+

- TypeScript

- Ng Zorro (pour l'interface utilisateur)

- RxJS

Installation et exécution

Backend

1.  Créer la base de données MySQL : examen_java_db
   
3.  Configurer la base de données dans applications.properties
   
4. Lancer l'application et xampp :

   
   <img width="493" alt="image" src="https://github.com/user-attachments/assets/ba26c4a7-7d86-4154-817f-6c6c21f6090e" />

   <img width="791" alt="image" src="https://github.com/user-attachments/assets/1a704e30-f49b-4515-9b70-a7abe67292ee" />
        
Frontend

1. Aller dans le dossier Angular :

   
<img width="526" alt="image" src="https://github.com/user-attachments/assets/7ba64b4f-e34a-4b62-800d-3f67b4a66799" />


3. Installer les dépendances : npm install
4. Démarrer l'application:

   
<img width="571" alt="image" src="https://github.com/user-attachments/assets/dcec8ffa-7cbe-4eb7-87fa-d270f822d6b0" />

6. Accéder à l'application :
Ouvrir http://localhost:4200/ dans le navigateur.

<img width="957" alt="image" src="https://github.com/user-attachments/assets/b49e49ff-314e-40e3-a096-6998f721df16" />

CRUD pour classe:

- CREATE
  
  <img width="950" alt="image" src="https://github.com/user-attachments/assets/715cb938-2637-441c-9525-fdc1a3fb0beb" />

- LIST
  
  <img width="941" alt="image" src="https://github.com/user-attachments/assets/b943a923-2693-4487-be9b-8775eb17ec69" />
  
- DELETE
  ici on va supprimer la classe licence 2
  message de confirmation avant suppression
  
  <img width="959" alt="image" src="https://github.com/user-attachments/assets/b5bad57c-75c9-427d-8366-ac783872c79c" />

  
  Click sur yes et licence 2 supprime

  -CREATE REGISTRATION

    <img width="957" alt="image" src="https://github.com/user-attachments/assets/f9c90613-8bdf-4d1f-a90d-3af17a9d8b59" />

Structure du projet
Backend
On a 3 classes : students;registrations et classes

Students:

Entity---->StudentEntity,

Dto---->StudentDtoRequest et StudentDtoResponse

Mapper--->StudentMapper

Repository---> StudentRepository

Services----> StudentService(Contrat des méthodes) et StudentServiceImpl

Controllers--->StudentController

Suivre la  meme logique pour les autres classes

- Config: dossier de configuration 
- ressources:
application.properties: Configurations Spring Boot

log4j.xml:Configuration des logs

<img width="362" alt="image" src="https://github.com/user-attachments/assets/90aeeb39-4167-4552-9ed5-4110548221aa" />

   

Frontend

<img width="198" alt="image" src="https://github.com/user-attachments/assets/74a96f5b-1199-41c4-b807-9df2d60cd0ca" />

     
          
Fonctionnalités principales

Backend

- API REST pour la gestion des étudiants, inscriptions et classes

- Validation des entrées avec Spring Validation

- Système de logs avec Log4j

Frontend

- Interfaces utilisateur pour gérer Students, Registrations, Classes

- CRUD complet avec formulaire de validation

- Appels API via Angular Services

- Utilisation de Ng Zorro pour le design

Endpoints API REST


<img width="343" alt="image" src="https://github.com/user-attachments/assets/4461bc29-c8fa-4917-95a2-a0fd67ce0c8d" />


(Similaire pour Registration et Classe)


  





