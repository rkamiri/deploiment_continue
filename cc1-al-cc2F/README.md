# cc1-al

start app from src/main/java/com/exemple/cc1romainkamiri/main.java

REST TEST 

User : 

get 3rd user
GET  localhost:8080/user/3

post user 
POST   localhost:8080/user
{
    "lastname":"aa",
    "firstname":"bb",
    "password":"cc",
    "email":"dd"
}


membership : 

POST : localhost:8080/membership/apply

 { 
    "userId":1, 
    "paymentInfosRequest": {
        "cardNumber" : "aa", 
        "expirationDate" : "bb", 
        "secretPictogram" : "cc"
    }, 
    "infosMembershipRequest":
    {
         "amount" : 15, 
         "durationTime" : 31 
         }, 
    "creationDate":"2022-01-01"

}

GET localhost:8080/membership/1

POST END RENEWAL : localhost:8080/membership/stop-renewal/1

POST RENEW FOR CRON TASK : localhost:8080/membership/renewal/1
