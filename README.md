# online-book-store

This is a simple backend that manages a simple online bookstore

APIs :

List all books : 
URL : http://localhost:8080/api/v1/books
sample Response : 
{"books":[{"id":2,"name":"name1","category":"category1","available":true},{"id":3,"name":"name2","category":"category1","available":true},{"id":5,"name":"name3","category":"category2","available":true},{"id":7,"name":"name4","category":"category3","available":true}]}


Get Book details
url : http://localhost:8080/api/v1/books/2
{"id":2,"name":"name1","category":"category1","available":true}

if the book is not found, it returns a 404