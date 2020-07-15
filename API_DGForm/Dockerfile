FROM keymetrics/pm2:12-jessie

WORKDIR /var/www

# Instalacion de herramientas
RUN apt-get update
RUN apt-get install -y apt-transport-https
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add -
RUN echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list
RUN apt-get update && apt-get install -y yarn
RUN yarn --version

# Intalacion de las dependencias del proyecto
COPY . ./
RUN yarn global add @nestjs/cli -y
# RUN yarn install -y

# Ejecucion del api
CMD [ "pm2-runtime","start","pm2.json"]