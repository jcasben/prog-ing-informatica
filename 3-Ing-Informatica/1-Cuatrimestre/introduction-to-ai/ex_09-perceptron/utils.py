import numpy as np
import matplotlib.pyplot as plt
import matplotlib
matplotlib.use('Qt5Agg')  # fixme if plotting doesn`t work (try 'Qt5Agg' or 'Qt4Agg')
import random
import atexit
import os

clean_data = {'inputs':None, 'targets':None}
noisy_data = {'inputs':None, 'targets':None}
img_shape = (0, 0)


def prepare_data(file_path, num_copy=10, noise=0.02):
    global clean_data, noisy_data, img_shape
    inp = []
    trg = []
    with open(file_path, 'r') as f:
        r, c, o = [int(x) for x in f.readline().split()]  # rows, cols, output_dim
        img_shape = (r, c)
        # Load inputs and targets
        lines = f.read().splitlines()
        for i in range(0, len(lines), r+1):
            inp.append(np.array([float(a) for line in lines[i:(i+r)] for a in line]))
            trg.append(np.array([float(a) for a in lines[i+r]]))

    # Make numpy arrays
    inp = np.array(inp).T
    trg = np.array(trg).T

    # Generate noisy inputs
    n_trg = np.tile(trg, (1, num_copy))
    n_inp = np.tile(inp, (1, num_copy))
    mask = np.random.rand(*n_inp.shape) < noise
    n_inp[mask] = 1 - n_inp[mask]

    # Save (for plotting)
    clean_data = {'inputs': inp, 'targets': trg}
    noisy_data = {'inputs': n_inp, 'targets': n_trg}
    return n_inp, n_trg


def plot_images(inputs, targets, model=None, title=None, block=False):
    # Plot all inputs in data, each in subplot
    count = inputs.shape[1]
    n_rows = int(np.round(np.sqrt(count/2)))
    n_cols = int((count-1) // n_rows + 1)
    fig, _ = plt.subplots(n_rows, n_cols, num=title)
    fig.canvas.mpl_connect('key_press_event', keypress)
    for i in range(count):
        x = inputs[:, i]
        d = targets[:, i]
        plt.subplot(n_rows, n_cols, i+1)
        plt.imshow(x.reshape(img_shape), cmap=plt.cm.gray_r, interpolation='nearest')
        plt.axis('off')
        dd = d.argmax()
        if model:
            y = model.compute_output(model.add_bias(x)).argmax()
            plt.title('Target: {}\nNN output: {}'.format(dd, y), fontsize=11)
        else:
            plt.title('Target: {}'.format(dd), fontsize=11)
    i = count
    while i < n_rows * n_cols:
        plt.subplot(n_rows, n_cols, i+1)
        plt.axis('off')
        i += 1
    plt.tight_layout()
    plt.show(block=block)


def plot_original_inputs(**kwargs):
    # Plot the 10 original inputs, without noise
    plot_images(**clean_data, title='Original inputs', **kwargs)


def plot_noisy_inputs(count=10, **kwargs):
    # Plot few noisy inputs
    n_inputs = noisy_data['inputs'].shape[1]
    idx = np.random.choice(n_inputs, count)
    plot_images(noisy_data['inputs'][:, idx], noisy_data['targets'][:, idx], title='Noisy inputs', **kwargs)


def plot_errors(training_history, block=False):
    # Plot history of errors
    (errs, accs) = training_history
    fig, ax = plt.subplots(2, 1, num='Training history', sharex=True)
    fig.canvas.mpl_connect('key_press_event', keypress)
    plt.subplot(2, 1, 1)
    plt.title('Regression error per epoch')
    plt.plot(errs, '-r')
    plt.grid(True)
    plt.xlim(left=-1); plt.ylim(bottom=-1)
    plt.subplot(2, 1, 2)
    plt.title('Classification accuracy per epoch [%]')
    plt.plot(np.array(accs)*100, '-b')
    plt.grid(True)
    plt.xlim(left=-1); plt.ylim(-3, 103)
    plt.tight_layout()
    plt.show(block=block)


def keypress(e):
    if e.key in {'q', 'escape'}:
        os._exit(0)  # unclean exit, but exit() or sys.exit() won't work
    if e.key in {' ', 'enter'}:
        plt.close()  # skip blocking figures


def finish():
    if plt.get_fignums():
        plt.show(block=True)  # Workaround to prevent plots from closing


atexit.register(finish)
