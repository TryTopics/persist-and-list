FROM node:6.10@sha256:af117acf2793c48aad74b70a78cb2e2cca28985f5dc57e73bb57f8b06548808c

COPY package.json .

RUN npm install

COPY . .

EXPOSE 80

ENTRYPOINT ["node", "./server"]
