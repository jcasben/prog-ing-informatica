import cv2 as cv
import numpy as np

video = cv.VideoCapture('vtest.avi')
backSub = cv.createBackgroundSubtractorMOG2()

while True:
    ret, frame = video.read()
    if frame is None:
        break
    gray_frame = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)
    ret, threshold = cv.threshold(gray_frame, 127, 255, 0)

    fgMask = backSub.apply(frame)

    kernel = np.ones((2, 2), np.uint8)
    opening = cv.morphologyEx(fgMask, cv.MORPH_OPEN, kernel, iterations=2)

    contours, hierarchy = cv.findContours(opening, cv.RETR_TREE, cv.CHAIN_APPROX_SIMPLE)
    for cnt in contours:
        if cv.contourArea(cnt) > 100:
            cv.drawContours(frame, [cnt], -1, (0, 255, 0), 2)

    cv.rectangle(frame, (10, 2), (100, 20), (255, 255, 255), -1)
    cv.putText(frame, str(contours.count), (15, 15), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0))

    cv.imshow('frame', frame)
    cv.imshow('FG Mask', opening)

    k = cv.waitKey(30)