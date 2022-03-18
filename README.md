[![Online-shop](https://github.com/PolyakPavlo/shop/actions/workflows/products.yml/badge.svg)](https://github.com/PolyakPavlo/shop/actions/workflows/products.yml)

##Web-shop
###Online-shop v1:
- [GET /products & / => return table with all products]()
- [POST /products/add => adding new product to db]()
- [PUT /products/edit => editing  product to db]()
- [DELETE /products/remove => removing product to db]()

keep goods in DB -> postgres, mysql, h2
test JUNIT5 + Mockito + anything you want!!

Servlet API + JDBC API

architecture 3-layer MVC => UML

###Online-shop V2:
- [Use DataSource]()
- [add, edit and remove products can only auth users]()
- [keep users into db]()
- [Deploy to Heroku]()
- [Add Flyway]()

// locator setAtr(session);

// db dump
// register -> login, password -> register(login, password) ->
//					String salt = UUID.getUuid().toString();
// 					hashedPassword = MD5(password + salt) ->
// 					save(new User(login, hashedPassword, salt))

// login -> login, password -> login(login, password) -> User user = findByLogin(login);
//  -> MD5(password + user.getSalt()).equals(user.getPassword()) -> generate token

// without salt
// value   |  MD5       | SHA256
// hello   | md5(hello) | sha256(hello)
// select * from rainbowTable where md5 == password or sha256 == password or ...

// static salt
// login1, hash1 -> 1234
// hash2 -> 757
// hash3 ->
// most popular 12345 -   login1 12345 -> fail.
// 2 most popular qwerty - login1 qwerty -> success

// dynamic salt
// login hashedSaltedPassword salt