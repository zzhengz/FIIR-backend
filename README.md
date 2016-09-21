# FIIR-backend
Database and API endpoints for FIIR app

## REST API Endpoints

Promo code is known as `token` in the following

| Endpoint               | Type | Parameters                                                   | Done? |
| ---------------------- | ---- | ------------------------------------------------------------ | ----- |
| /users/create          | POST | {phone:"phonenumber", invitedby:"userid", email:"emailaddress"} | no |
| /pics/create           | POST | {key:"key", user:"userid", price:"price", token:"code/null"} | no    |
| /pics/created          | GET  | {key:"key", user:"userid"}                                   | no    |
| /friends/list          | GET  | {key:"key", user:"userid"}                                   | no    |
| /friends/add           | POST | {key:"key", user:"userid", friend:"userid"}                  | no    |
| /friends/remove        | POST | {key:"key", user:"userid", friend:"userid"}                  | no    |
| /settings/update_email | POST | {key:"key", user:"userid", email:"emailaddress"}             | no    |
| /settings/update_phone | POST | {key:"key", user:"userid", phone:"phonenumber"}              | no    |



## Number verification
We will be using API of numverify.con to determine carriers for any given number. We will then use the email to SMS gateway to send messages to the correct carrier:

Alltel 	`1234567890@message.alltel.com`  
AT&T (formerly Cingular) 	`1234567890@txt.att.net`  
Boost Mobile 	`1234567890@myboostmobile.com`  
Nextel (now Sprint Nextel) 	`1234567890@messaging.nextel.com`  
Sprint PCS (now Sprint Nextel) 	`1234567890@messaging.sprintpcs.com`  
T-Mobile 	`1234567890@tmomail.net`  
US Cellular 	`1234567890email.uscc.net`   
Verizon 	`1234567890@vtext.com`  
Virgin Mobile USA 	`1234567890@vmobl.com`  
