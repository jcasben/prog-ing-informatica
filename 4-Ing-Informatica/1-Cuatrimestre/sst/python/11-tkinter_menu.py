import tkinter as tk

pms = tk.Tk()

pmsbar = tk.Menu(pms)
pms.config(menu=pmsbar)

menu_reserv = tk.Menu(pmsbar, tearoff=0)
menu_client = tk.Menu(pmsbar, tearoff=0)
pmsbar.add_cascade(label="Reservations", menu=menu_reserv)
pmsbar.add_cascade(label="Clients", menu=menu_client)

menu_reserv.add_command(label="Consult")
menu_reserv.add_command(label="Reserve")

pms.mainloop()

