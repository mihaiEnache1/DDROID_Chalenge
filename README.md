# DDROID_Chalenge
Documentation of my solution for the challenge

Mandatory Requirements
1. I built the data model classes Product and ShippingRate. The informations are stocked in a database in two tables: one for products and one for shipping rates. A product will have an ID which will be unique (it will be auto incremented in the table + primary key), a name, a price, the country where is shipped from and the weight. A shipping rate will have a country and a rate. I also have a class (Order) for orders which contains a Map in which we will save the information about an order: the products and the quantity of each product. The Order class contains a method "compute(Map<Product, Integer>, ArrayList<ShippingRate>)" used to compute the total price of the order. In the MainClass, I use the ArrayList structure to stock the information about the catalog of products and the shipping rates and display them from the ArrayList.

2. For allowing the client to select different products as many as desired, I created a GUI which is minimalistic, but very easy to use. The client has access to the products using the table which is displayed on the view and contains all the products stocked in the database. The client has just to click on a product and press the button "Add to cart". Everytime the button is pressed, on the reserved text area (top right corner, the client can see the products added to the cart and the quantity of each product. When the button "Check out" is pressed, an invoice will be displayed on the reserved text area (lower right corner).
   I have a class which connects the application to the database and retrieves the informations from the tables.
  
