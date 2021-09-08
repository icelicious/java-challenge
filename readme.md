##What I have done:
  - Added a simple Spring Security + RBAC sample to protect controller end points. 
      - USER: can only fetch GET end points(ID:spring PW:123456)

      - ADMIN:can fetch all end points includes H2 console and swagger ui(ID:admin PW:admin)

  - Added global exceptionHandler
      - Added some global error handlers
  - Customized Swagger ui
      - Added some customized info to swagger ui
  - Import init data for demo
      - please refer to /resources/import.sql
  - Implementation to ensure all APIs are available
  - Added cache to EmployeeController for API calls(key:ID,value:employee)
      - Added @Cacheable to getEmployee
      - Added @CachePut to createEmployee/updateEmployee
      - Added @CacheEvict to deleteEmployee
  - Added Junit test to controller and service class

##What to do:
  - Protect controller end points. 
      - add white list & black list by using Spring Security 

      - use Oauth2 and create a process for this
      
      - create a proxy server to hide real IPs(Use nginx + tomcat)

  - Global exceptionHandler
      - add more biz exception cases
  - Implementation more APIs by customized methods
  - Use redis to handle cache issue
      - add bloom filter to avoid attack from requests to data which is not existed
      - use redis cluster to handle high traffic 
  - Use JMeter to perform load test
  - Use Kafka as a rate limiter if needed

##System requirement assumption and further analyse:
  - read heavy
      - add redis cluster and DB cluster(eg:MySql cluster)
      - add load balancer
  - global
      - add global CDN service 
  - always available
      - use cluster and avoid single point failure
      - add monitor service to all system modules
  - scalable
      - calculate DAU and QPS
      - use auto scalable services(scale out and in)
## Experience in Java

I have over 6 years of experience of developing J2EE systems with high concurrency and REST API.