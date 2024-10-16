# Projet COO
## Auteurs: Nicolas Han, Farès Abdelli
### Présentation: 
Ce projet visait à modéliser un gestionnaire de stations de locations de véhicules, en utilisant les notions vues en cours de Conception Orientée Objet.

### Diagramme UML:
Veuillez y accéder via ce lien (compte nécessaire pour pouvoir y accéder) : 
https://lucid.app/lucidchart/cda834a1-f092-4bb8-b9ab-71ee156a819a/edit?viewport_loc=-596%2C-404%2C2219%2C1108%2C0_0&invitationId=inv_accf13bb-3e30-4576-b835-cb97c4503c4a

## HowTo:
### Récupérer les sources du projet:
Pour récupérer les sources, placez-vous dans le dossier de votre choix et exécutez la commande suivante:
```
git clone https://gitlab-etu.fil.univ-lille.fr/fares.abdelli.etu/projet_coo.git
```
Veuillez vous placer dans le dossier `projet-coo` pour exécuter toutes les commandes ci-dessous, et exécuter la commande suivante avant toute autre:
```
export CLASSPATH="src:bin"
```
### Générer la documentation:
```
make docs
```
### Compiler et exécuter les sources:
```
make run
```
### Compiler et exécuter les tests:
```
make runtest
```
### Générer et exécuter l'archive
```
make runjar
```

## Présentation d'éléments de code:
Nous avons utilisé différents concepts et patterns de programmation au cours du projet:
- State Pattern pour représenter les différents états que peuvent avoir les stations et les véhicules (station pleine, vide, etc...)
- Factory pour créer les véhicules
- Decorator pour pouvoir personnaliser les véhicules (ici, nous l'avons fait avec les vélos)
- Strategy pour les méthodes de réarrangements des véhicules dans les stations
- Visitor pour les réparateurs
- Observer pour notifier le centre de contrôle de chaque location/rendu
