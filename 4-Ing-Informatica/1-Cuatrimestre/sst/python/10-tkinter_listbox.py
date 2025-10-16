import tkinter as tk
root = tk.Tk()

entries = ["a", "b", "c", "d", "e"]
box = tk.Listbox(root)
box.insert(tk.END, *entries)
bar = tk.Scrollbar(root)
bar.config(command=box.yview)
box.config(yscrollcommand=bar.set)

box.pack(side=tk.LEFT, fill=tk.BOTH)
bar.pack(side=tk.RIGHT, fill=tk.BOTH)

button = tk.Button(
    root,
    text="Send",
    command=lambda: print(entries[box.curselection()[0]])
)
button.pack()

root.mainloop()