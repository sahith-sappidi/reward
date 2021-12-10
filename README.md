#Scenario

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction

(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

 Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.


##Steps to run
 
 - Clone the repo
 
 - Run as maven build

 - Run as java application.
 
 - Try it out in swagger : http://localhost:8080/swagger-ui.html
 
 - Sample input available in the root folder: sampleInput.json
 
##Sample: 
 
 - Request
 
 [{
		"transactionId": 1,
		"customerName": "Greg",
		"amount": 120,
		"transactionDate": "05-01-2019"
	},
	{
		"transactionId": 1,
		"customerName": "Paul",
		"amount": 75,
		"transactionDate": "05-21-2019"
	}
 ]
  
  - Response
  
  {
  "Greg": {
    "TOTAL": 90,
    "MAY": 90
  },
  "Paul": {
    "TOTAL": 25,
    "MAY": 25
  }
}
  
  