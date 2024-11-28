import cv2 as cv
import numpy as np
from matplotlib import pyplot as plt

imw_wiki = cv.imread('wiki.jpg', 0)
equ_wiki = cv.equalizeHist(imw_wiki)
res = np.hstack((imw_wiki, equ_wiki))
plt.hist(res.ravel(),256,[0,256]); plt.show()

img_coins = cv.imread('coins.jpg')
img_coins_gray = cv.cvtColor(img_coins, cv.COLOR_BGR2GRAY)
ret, threshold = cv.threshold(img_coins_gray, 0, 255, cv.THRESH_BINARY_INV + cv.THRESH_OTSU)

ret, thresh = cv.threshold(img_coins_gray, 127, 255, 0)
contours, hierarchy = cv.findContours(thresh, cv.RETR_TREE, cv.CHAIN_APPROX_SIMPLE)
cv.drawContours(img_coins, contours, -1, (0,255,0), 3)
cv.imshow('Contures', img_coins)
k = cv.waitKey(0)
