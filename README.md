#Documentation pour le déploiement de l'application CartographieVue

## 1. Externalisation des propriétés

Les fichiers de configuration application.properties et logback-spring.xml sont externalisés en dehors du code source afin de faciliter la maintenance et de protéger l'accès. Ils se trouvent dans E:\CartographieVue\configuration

**Avant de déployer l'application** il convient d'abord d'aller dans le fichier `tomcat10w.exe` pour ajouter ces java options :

`-Dspring.config.additional-location=file:E:/CartographieVue/configuration/`
`-Dspring.profiles.active=prod`
`--Dlogging.config=file:E:/CartographieVue/configuration/logback-spring.xml`

## 2. Déploiement d'une nouvelle versoin

### 2.1 Déploiement côté Tomcat (WAR)

Exporter le projet au format WAR depuis Eclipse ou IntelliJ. Déposer le fichier dans le dossier C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps
Redémarrer Tomcat pour prendre en compte la nouvelle version.

### 2.2 Si aucune modification est à faire dans la structure de la base de données

Aller dans le application.properties et passer la ligne `spring.jpa.hibernate.ddl-auto=` à `none` au lieu de `create`

### 2.3 Si aucun utilisateur n'est inscrit en base de données

Executer le `script nouvel utilisateur.sql` présent dans le dossier `init`.
