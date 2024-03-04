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
![Documentation Swagger](https://github.com/Youssouf99/SpringShop/blob/master/image/Capture%20d%E2%80%99%C3%A9cran%202024-03-04%20%C3%A0%2018.12.00.png)
![](https://github.com/Youssouf99/SpringShop/blob/master/image/Capture%20d%E2%80%99%C3%A9cran%202024-03-04%20%C3%A0%2018.12.27.png)

