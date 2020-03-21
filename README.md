# Spring Boot Monitoring

## TICK Stack
- Telegraf
- InfluxDB
- Chronograf
- Kapacitor

## Publish Docker

### Login Docker
```
docker login
```

### Build image
```
docker build . -t gglee/spring-monitoring:0.0.1
```

### Push image
```
docker push gglee/spring-monitoring:0.0.1
```

## Run
```
docker-compose up
```

## Actuator
[http://localhost:8080/actuator](http://localhost:8080/actuator)

```json
{
   "_links":{
      "self":{
         "href":"http://localhost:8080/actuator",
         "templated":false
      },
      "health":{
         "href":"http://localhost:8080/actuator/health",
         "templated":false
      },
      "health-path":{
         "href":"http://localhost:8080/actuator/health/{*path}",
         "templated":true
      },
      "metrics":{
         "href":"http://localhost:8080/actuator/metrics",
         "templated":false
      },
      "metrics-requiredMetricName":{
         "href":"http://localhost:8080/actuator/metrics/{requiredMetricName}",
         "templated":true
      },
      "httptrace":{
         "href":"http://localhost:8080/actuator/httptrace",
         "templated":false
      }
   }
}
```