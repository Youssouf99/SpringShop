# Projet E-Commerce API

Ce projet est une API pour la gestion d'un système de commerce électronique. Il permet de gérer des commandes, des produits et des clients.


## Entités

### Commande (Command)

La classe `Command` représente une commande passée par un client. Elle contient les attributs suivants :

- **id** : Identifiant unique de la commande (UUID).
- **dateCreated** : Date de création de la commande.
- **priceTotal** : Prix total de la commande.
- **stateCommand** : État de la commande.
- **customer** : Client ayant passé la commande.
- **products** : Liste des produits commandés.

### Produit (Product)

La classe `Product` représente un produit disponible à la vente. Elle contient les attributs suivants :

- **id** : Identifiant unique du produit (UUID).
- **name** : Nom du produit.
- **description** : Description du produit.
- **price** : Prix du produit.
- **image** : URL de l'image du produit.
- **stockQuantity** : Quantité en stock du produit.
- **dateCreated** : Date de création du produit.

### Client (Customer)

La classe `Customer` représente un client du magasin. Elle contient les attributs suivants :

- **id** : Identifiant unique du client (UUID).
- **firstName** : Prénom du client.
- **lastName** : Nom du client.
- **email** : Adresse e-mail du client.
- **phone** : Numéro de téléphone du client.
- **dateCreated** : Date de création du compte client.

## Structure du Projet

Le projet est organisé de la manière suivante :

- **controllers** : Contrôleurs de l'API.
- **dtos** : Objets de transfert de données.
- **entities** : Entités JPA représentant les données.
- **exceptions** : Classes d'exception personnalisées.
- **mappers** : Mappers pour la conversion entre entités et DTOs.
- **repositories** : Interfaces de repository pour l'accès aux données.
- **services** : Services métier pour la manipulation des données.
- **GlobalExceptionHandler.java** : Gestionnaire global des exceptions.


## Documentation Swagger

La documentation de l'API est disponible via Swagger.
![Documentation Swagger](URL_de_l'image)

