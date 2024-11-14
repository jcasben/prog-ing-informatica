import cv2 as cv

img = cv.imread('original.jpg')
img2 = cv.imread('original.jpg')
# img = cv.cvtColor(img, cv.COLOR_BGR2GRB)
blue = img[:,:,0]
green = img[:,:,1]
red = img[:,:,2]

img2[:,:,0] = red
img2[:,:,2] = green
img2[:,:,1] = blue

cv.imshow("Display", img2)
k = cv.waitKey(0)