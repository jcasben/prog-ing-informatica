import cv2 as cv
import numpy as np
from matplotlib import pyplot as plt

img = cv.imread('original.jpg', 0)
plt.hist(img.ravel(),256,[0,256]); plt.show()

# HSV representation
img2 = cv.imread('original.jpg')
img3 = cv.cvtColor(img2, cv.COLOR_BGR2HSV)
hist = cv.calcHist([img3], [0], None, [180], [0, 180])
plt.plot(hist)
plt.xlim([0, 180])
plt.show()

# original = cv.imread('original.jpg')
# Quantization
original = cv.imread('original.jpg')
Z = original.reshape((-1, 3))
Z = np.float32(Z)

criteria = (cv.TERM_CRITERIA_EPS + cv.TERM_CRITERIA_MAX_ITER, 10, 1.0)
K = 8
ret, label, center = cv.kmeans(Z, K, None, criteria, 10, cv.KMEANS_RANDOM_CENTERS)
center = np.uint(center)
res = center[label.flatten()]
res2 = res.reshape(original.shape)

# Noise
rnd = np.random.rand(original.shape[0], original.shape[1])
noisy = original.copy()
prob=0.3
noisy[rnd < prob] = 0
noisy[rnd > 1 - prob] = 1
cv.imshow("Salt & Pepper Noise", noisy)
k = cv.waitKey(0)

# Gaussian Noise
noisy_image = original.copy()
mean = 0
var = 100
sigma = var ** 0.5
gaussian = np.random.normal(mean, sigma, (original.shape[0], original.shape[1]))
noisy_image = noisy_image + gaussian
cv.normalize(noisy_image, noisy_image, 0, 255, cv.NORM_MINMAX, dtype=-1)
noisy_image = noisy_image.astype(np.uint8)
cv.imshow("Gaussian noise", noisy_image)
k = cv.waitKey(0)

cv.imshow("Original", original)
k = cv.waitKey(0)

# Filtering
blur = cv.blur(original,(5,5))
blur2=cv.GaussianBlur(original,(5,5))
blur3=cv.medianBlur(original,5)
