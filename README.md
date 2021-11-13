## prepare

- run container

<!-- - [bcrypt](https://www.npmjs.com/package/bcrypt)
```shell
npm install -s bcrypt
node
> const bcypt = require('bcrypt');
> const rawPassword = 'hogefuga';
> bcypt.hashSync(rawPassword, 10);
'$2b$10$hFj1P4vSQ5yxM4J4kb0TyOSHgFmaWVzP5zsGUopCKajLPWg6a73p.'
> bcypt.compareSync(rawPassword, '$2b$10$hFj1P4vSQ5yxM4J4kb0TyOSHgFmaWVzP5zsGUopCKajLPWg6a73p.');
true
``` -->

## test api

you can use [postman collection](./postman)

```shell
# login
$ curl -i -c cookie.txt -H 'Content-Type:application/x-www-form-urlencoded' -X POST -d 'email=user@example.com' -d 'pass=1qazxsw2' http://localhost:8080/login

# get books with cookie
$ curl -i -b cookie.txt http://localhost:8080/books
HTTP/1.1 200 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 12 Nov 2021 15:02:13 GMT

{"books":[{"id":100,"title":"kotlin beginner","author":"mike","is_rental":false},{"id":200,"title":"java beginner","author":"popcorn","is_rental":false},{"id":300,"title":"scala beginner","author":"taro","is_rental":false}]}
```
