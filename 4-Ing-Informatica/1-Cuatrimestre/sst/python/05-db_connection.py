import mysql.connector

db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="sst"
)

# variable para recorrer
cursor = db.cursor()

# query
query = "SELECT * FROM user"
cursor.execute(query)
results = cursor.fetchall()

for result in results:
    print(result)

cursor.close()
db.close()