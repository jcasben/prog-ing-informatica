import cv2 as cv
import numpy as np
from matplotlib import pyplot as plt

# Load grayscale image
img_grayscale = cv.imread('original.jpg', 0)
cv.imshow('Assignment', img_grayscale)
k = cv.waitKey(0)

# Add noise using Gaussian Noise
noisy_image = img_grayscale.copy()
mean = 0
var = 300
sigma = var ** 0.5
gaussian = np.random.normal(mean, sigma, (img_grayscale.shape[0], img_grayscale.shape[1]))
noisy_image = noisy_image + gaussian
cv.normalize(noisy_image, noisy_image, 0, 255, cv.NORM_MINMAX, dtype=-1)
noisy_image = noisy_image.astype(np.uint8)
cv.imshow("Gaussian noise - Grayscale", noisy_image)
k = cv.waitKey(0)

# Denoise using average method and median filter
denoised_avg = cv.blur(noisy_image, (3, 3))
denoised_median = cv.medianBlur(noisy_image, 3)
cv.imshow("Denoised - Average", denoised_avg)
cv.imshow("Denoised - Median", denoised_median)
k = cv.waitKey(0)
# Calculate distance between original and denoised
hist_org = cv.calcHist([img_grayscale], [0], None, [256], [0, 256])
hist_avg = cv.calcHist([denoised_avg], [0], None, [256], [0, 256])
hist_median = cv.calcHist([denoised_median], [0], None, [256], [0, 256])


# Add noise to a color image using Gaussian Noise
img_color = cv.imread('original.jpg')
noisy_color = img_color.copy()
mean = 0
var = 200
sigma = var ** 0.5
gaussian = np.random.normal(mean, sigma, img_color.shape)
noisy_color = noisy_color + gaussian
cv.normalize(noisy_color, noisy_color, 0, 255, cv.NORM_MINMAX, dtype=-1)
noisy_image = noisy_color.astype(np.uint8)
cv.imshow("Gaussian noise - Color", noisy_image)
k = cv.waitKey(0)