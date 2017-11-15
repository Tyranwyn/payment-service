# Togeth Air Payment Service

This is the dummy service that the Togeth Air project uses to finish credit card payments.

## How to install

Execute following commands:

```bash
# Clone the repository and change directory
git clone https://github.com/Tyranwyn/payment-service.git
cd payment-service

# Build with maven
mvn clean install -DskipTests

```

## How to run

Using eru-illuvatar wildfly, put the following xml in the 
__eru-illuvatar/wildfly/standalone/configuration/standalone.xml__
between the "subsystem datasources" tags:

```xml
<datasource jndi-name="java:/rd/datasources/RealDolmenPayment" pool-name="RealDolmenPaymentPool" enabled="true" use-java-context="true">
    <connection-url>jdbc:mysql://localhost:3306/payment</connection-url>
    <driver>mysql</driver>
    <security>
        <user-name>root</user-name>
    </security>
</datasource>
```

Now you want to __Edit Configurations...__ and set up the server with
a __Port offset__ of __1000__.

Next you want to run the TogethAir application server and when this is
running you start the payment application server.