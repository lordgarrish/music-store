# music-store
Music store website made with JavaEE, Servlets, JSP and JDBC.

The Music Store website is a website for a fictional record company "Music Against Humanity". Although this isn't a real website, it illustrates
the skills that I've learned during studying Java Servlets and JSP API, that can be helpful for developing a real site.

This web application implements MVC (model-view-controller) software design pattern, that divides application into three main parts.<br/>
For creating user's view I've used JSP (Java Server Pages) that can create dynamic web pages without JavaScript.<br/>
For controller I've used Java Servlets API. Servlet is a Java interface that responds to user's requests and also serves as a connector between the front view and the
database. It's responsible for all server-side logic.<br/>
Finally, MySQL database serves as model, and for connecting to database server this application uses Java JDBC API. To connect to your DB you will need to edit
file 'context.xml' in 'src/main/webapp/META-INF/' folder either by changing Datasource properties or by defining your own Datasource.<br/>

Here's a schema for website database<br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/3f/febf08394ace89fbfff67bc66ba06a3f.jpeg)](https://fastpic.org/view/115/2021/1014/febf08394ace89fbfff67bc66ba06a3f.png.html)<br>
<b>Usage:</b><br>
Clone this repository and open in IntelliJ Idea, choose Tomcat Server in Run/Debug Configuration and run it! Or you can manually build the project in Maven
and deploy it in your Apache Tomcat folder.
