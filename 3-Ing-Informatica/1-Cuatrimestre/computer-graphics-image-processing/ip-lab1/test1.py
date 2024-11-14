import cv2 as cv
import numpy as np

img = cv.imread('messi5.jpg')
ball = img[285:335, 337:387]
print(ball.shape)
img[285:335,45:95] = ball
img = cv.cvtColor(img, cv.COLOR_BGR2RGB)
cv.imshow("Display", img)
k = cv.waitKey(0)
cv.imwrite('messi.jpg', img)