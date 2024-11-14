import cv2 as cv
import numpy as np

img = np.zeros((512, 512, 3), np.uint8)
cv.line(img, (0, 0), (511, 511), (255, 0, 0), 5)
cv.line(img, (0, 255), (511, 255), (0, 0, 255), 5)

px = img[100, 100]
print(px[2])

cv.imshow("Drawing", img)
k = cv.waitKey(0)
