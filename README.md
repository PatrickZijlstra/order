# README

### Order application
Order application that can receive an input and return the total price of the order.
If the order contains too many products, then the customer will receive a message saying that it is 'out of stock'.
There are two special promotions that the customer can use:
- Buy one apple, get the second apple free
- Buy three oranges for the price of two

#The input can be as follows:
- ["Apple", "Orange", "Orange", "12"]
Here the customer will buy an apple and two oranges, where the stock is defined as 12.

- ["Apple", "Apple", "Orange", "Orange", "Orange"]
Here the stock is not specified, so it is defaulted to 5. This customer makes use of both special offers. 