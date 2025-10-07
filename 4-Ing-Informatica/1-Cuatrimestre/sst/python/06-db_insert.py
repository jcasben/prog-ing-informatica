import mysql.connector

db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="sst"
)

cursor = db.cursor()

nombre = input("Nombre: ")
apellido = input("Apellido: ")
email = input("Email: ")

query = "INSERT INTO user (firstname, lastname, email) VALUES (%s, %s, %s)"
cursor.execute(query, (nombre, apellido, email))
print(cursor.lastrowid)

db.commit()

cursor.close()
db.close()