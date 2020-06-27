FROM node:lts-stretch

WORKDIR /var/www

# Intalacion de las dependencias
COPY package.json ./
RUN npm install -g @nestjs/cli -y
RUN npm install -y

CMD [ "npm", "start:dev"]