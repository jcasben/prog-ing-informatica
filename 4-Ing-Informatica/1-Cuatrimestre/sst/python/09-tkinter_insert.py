import tkinter as tk
import mysql.connector

window = tk.Tk()
db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="sst"
)

cursor = db.cursor()

window.title("PMS")
window.geometry("600x600")

name = tk.Label(window)
name.configure(text="Name")
name.grid(row=0, column=0)

entryName = tk.Entry(window)
entryName.grid(row=0, column=1)

lastname = tk.Label(window)
lastname.configure(text="Lastname")
lastname.grid(row=1, column=0)

entryLast = tk.Entry(window)
entryLast.grid(row=1, column=1)

email = tk.Label(window)
email.configure(text="Email")
email.grid(row=2, column=0)

entryEmail = tk.Entry(window)
entryEmail.grid(row=2, column=1)

def execute_insert(name, lastname, email):
    query = "INSERT INTO user (firstname, lastname, email) VALUES (%s, %s, %s)"
    cursor.execute(query, (name, lastname, email))
    print(cursor.lastrowid) 
    db.commit()
    entryName.delete(0, 'end')

sendButton = tk.Button(
    window, text="Send", 
    command=lambda: execute_insert(entryName.get(), entryLast.get(), entryEmail.get())
)
sendButton.grid(row=3,column=0, columnspan=2, pady=10)

window.mainloop()
