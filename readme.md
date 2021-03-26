# ABC Enterprise Ecommerce

## Requirement
The jar require Java 16 and the following environment variable/java option :
API_ABC_ECOMMERCE_BDD_USER
API_ABC_ECOMMERCE_BDD_MDP
API_ABC_ECOMMERCE_BDD_URL exemple : jdbc:mysql://localhost:3307/ecommerce?createDatabaseIfNotExist=true
API_ABC_ECOMMERCE_JWT_SECRET

## Starting
The jar is auto runable.
```bash
export JAVA_OPTS="-Xms128m -Xmx256m -DAPI_ABC_ECOMMERCE_BDD_USER=user -DAPI_ABC_ECOMMERCE_BDD_MDP=password -DAPI_ABC_ECOMMERCE_BDD_URL=jdbcUrl -DAPI_ABC_ECOMMERCE_JWT_SECRET=jwtSecret"
./ecommerce-0.0.1-SNAPSHOT.jar
```
```bash
java -Xms128m -Xmx256m -DAPI_ABC_ECOMMERCE_BDD_USER=user -DAPI_ABC_ECOMMERCE_BDD_MDP=password -DAPI_ABC_ECOMMERCE_BDD_URL=jdbcUrl -DAPI_ABC_ECOMMERCE_JWT_SECRET=jwtSecret -jar ecommerce-0.0.1-SNAPSHOT.jar
```