import cv2 as cv
import numpy as np

img = cv.imread('box.png',0)
sobelx8u = cv.Sobel(img,cv.CV_8U,1,0,ksize=5)
cv.imshow('sobelx8u',sobelx8u)
k = cv.waitKey(0)

img = cv.imread('box.png',0)
sobelx8u = cv.Sobel(img,cv.CV_8U,1,0,ksize=5)
sobelx64f = cv.Sobel(img,cv.CV_64F,1,0,ksize=5)
abs_sobel64f = np.absolute(sobelx64f)
sobel_8u = np.uint8(abs_sobel64f)
cv.imshow('sobel_8u',sobel_8u)
cv.waitKey(0)