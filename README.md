# Mini Klarna
 A RESTful service which users are able to purchase an order, 
 pay their debts, see their orders and see their total debts.
 
 ## How to Run
 ```
 git clone https://github.com/erdalzeynep/mini-klarna.git  
 cd mini-klarna
 ./gradlew build
 docker run -p 8080:8080 dal.zeynep/mini-klarna:1.0-SNAPSHOT
 ```
 
 ### To Purchase a New Order
 ```
http://localhost:8080/purchase/{userEmail}/{price}
```

 ### To See User Orders
 ```
http://localhost:8080/getOrders/{userEmail}
```

 ### To Pay Specific Order
  ```
http://localhost:8080/pay/{userEmail}/{orderId}
 ```

 ### To See Total Debt of Specific User 
  ```
http://localhost:8080/getUserDebt/{userEmail}
 ```


 
 
 