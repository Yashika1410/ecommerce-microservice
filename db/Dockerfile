FROM node:slim
EXPOSE 5000
RUN npm install -g json-server
COPY ./data.json data.json
ENTRYPOINT ["json-server","--watch", "data.json", "--host", "0.0.0.0","--port","5000"]