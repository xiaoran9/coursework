This assignment attempts to all enhancements 
1. it returns binary image and it can not only display in the HTML file but also display directly such as http://localhost:1112/beer.jpg in the browser 
2. it used thread pool to implement multithreading
3. it creates a new text file which contains the data request and response when using the project
4. it adds the third methods DELETE, 
   - it may return HTTP/1.1 200 OK  when deleting succeed 
   - HTTP/1.1 202 (Accepted) when it finds the file but can't delete
   - HTTP/1.1 404 Not Found when didn't find the file
