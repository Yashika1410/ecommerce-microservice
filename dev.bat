@echo off 
cd ecommerce-server
call mvn clean install
docker build -t ecommerce-server .
docker tag ecommerce-server:latest yashika1410/ecommerce-server:latest
docker push yashika1410/ecommerce-server:latest
cd ..
cd ecommerce-gate-way
call mvn clean install
docker build -t ecommerce-gate-way .
docker tag ecommerce-gate-way:latest yashika1410/ecommerce-gate-way:latest
docker push yashika1410/ecommerce-gate-way:latest
cd ..
cd ecommerce-admin
call mvn clean install
docker build -t ecommerce-admin .
docker tag ecommerce-admin:latest yashika1410/ecommerce-admin:latest
docker push yashika1410/ecommerce-admin:latest
cd ..
cd ecommerce-product
call mvn clean install
docker build -t ecommerce-product .
docker tag ecommerce-product:latest yashika1410/ecommerce-product:latest
docker push yashika1410/ecommerce-product:latest
cd ..
cd db
docker build -t json-db .
docker tag json-db:latest yashika1410/json-db:latest
docker push yashika1410/json-db:latest
cd ..
docker-compose up