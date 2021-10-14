# music-store
Music store website made with JavaEE, Servlets, JSP and JDBC.

The Music Store website is a website for a fictional record company "Music Against Humanity". Although this isn't a real website, it illustrates
the skills that I've learned during studying Java Servlets and JSP API, that can be helpful for developing a real site.

Music Store website consists of 4 main sections: Home page, Catalog, News (work in progress) and Cart.<br>
<br>
<b>Home page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/6a/_2314e5814cdf1db021ea37eb88c5f06a.jpeg)](https://fastpic.org/view/115/2021/1014/_2314e5814cdf1db021ea37eb88c5f06a.png.html)
<br><br>
<b>Catalog page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/87/_36fa36c13de1dde1f18273baa7383387.jpeg)](https://fastpic.org/view/115/2021/1014/_36fa36c13de1dde1f18273baa7383387.png.html)

While browsing store catalog, user can view music cd by clicking on its titles. This redirects user to album description page.<br>

<b>Product description page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/5d/_8689fa28d0eedf4adb8c2a5ae63c5d5d.jpeg)](https://fastpic.org/view/115/2021/1014/_8689fa28d0eedf4adb8c2a5ae63c5d5d.png.html)

Users can add and/or remove individual products from the cart. Cart page shows all products in cart and total price.<br>

<b>Cart page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/e8/_3cba5d493ce4cb21bbc8a912b5926be8.jpeg)](https://fastpic.org/view/115/2021/1014/_3cba5d493ce4cb21bbc8a912b5926be8.png.html)

After selecting products and putting them in cart, user then goes to checkout page to enter his personal info and shipping address.<br>

<b>Checkout page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/83/_05ce0a1d0d445515d924d8c1fb3fe583.jpeg)](https://fastpic.org/view/115/2021/1014/_05ce0a1d0d445515d924d8c1fb3fe583.png.html)

Finally, after filling his shipping address and credit card info, user gets to page with all information about his order<br>

<b>Order information page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/88/_831ad412403ed6910b04a7a7f014ff88.jpeg)](https://fastpic.org/view/115/2021/1014/_831ad412403ed6910b04a7a7f014ff88.png.html)
<br>

<b>Background</b><br>
This web application implements MVC (model-view-controller) software design pattern, that divides application into three main parts.<br/>
For creating user's view I've used JSP (Java Server Pages) that can create dynamic web pages without JavaScript.<br/>
For controller I've used Java Servlets API. Servlet is a Java interface that responds to user's requests and also serves as a connector between the front view and the
database. It's responsible for all server-side logic.<br/>
Finally, MySQL database serves as model, and for connecting to database server this application uses Java JDBC API. To connect to your DB you will need to edit
file 'context.xml' in 'src/main/webapp/META-INF/' folder either by changing Datasource properties or by defining your own Datasource.<br/>
The SQL script for the database: src/main/webapp/sql/db_script.sql<br/>

<b>'music_store_db' database schema</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/3f/febf08394ace89fbfff67bc66ba06a3f.jpeg)](https://fastpic.org/view/115/2021/1014/febf08394ace89fbfff67bc66ba06a3f.png.html)<br><br/>
<b>Usage:</b><br>
Clone this repository and open in IntelliJ Idea, choose Tomcat Server in Run/Debug Configuration and run it! Or you can manually build the project in Maven
and deploy it in your Apache Tomcat folder.
