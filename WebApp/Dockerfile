FROM php:7.2-apache

WORKDIR /var/www/html/dgform/webapp

# Instalacion de herramientas
RUN a2enmod rewrite
RUN a2enmod headers

# Intalacion de las dependencias del proyecto
COPY . ./
