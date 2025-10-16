import tkinter as tk

window = tk.Tk()

window.title("PMS")
window.geometry("600x600")

message = tk.Label(window)
message.configure(
    text="Name", font=("Arial", 20),
    fg="white", bg="orange",
    anchor="center"
)
message.place(x=100, y=100)
message.pack(side="left");

entry = tk.Entry(window)
entry.pack(side="right")

sendButton = tk.Button(
    window, text="Send", 
    command=lambda: print(entry.get())
)
sendButton.pack(side="bottom")

window.mainloop()