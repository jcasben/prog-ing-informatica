import cv2

def find_rightmost_centroid(image_path):
    image = cv2.imread(image_path, 0)

    _, thresholded = cv2.threshold(image, 100, 255, cv2.THRESH_BINARY_INV)

    # Remove small noise with morphological opening
    kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (3, 3))
    denoised = cv2.morphologyEx(thresholded, cv2.MORPH_OPEN, kernel)

    contours, _ = cv2.findContours(denoised, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    centroids = []
    for contour in contours:
        # Check if the contour is more or less a rectangle
        approx = cv2.approxPolyDP(contour, 0.02 * cv2.arcLength(contour, True), True)
        if len(approx) == 4:
            # Calculate the centroid of the rectangle
            M = cv2.moments(contour)
            if M["m00"] != 0:
                cx = int(M["m10"] / M["m00"])
                cy = int(M["m01"] / M["m00"])
                centroids.append((cx, cy))

    # Find the rightmost centroid
    if centroids:
        rightmost_centroid = max(centroids, key=lambda c: c[0])
        print("Rightmost Centroid:", rightmost_centroid)

        output_image = cv2.cvtColor(image, cv2.COLOR_GRAY2BGR)
        cv2.circle(output_image, rightmost_centroid, 5, (0, 0, 255), -1)
        cv2.imshow("Output", output_image)
        cv2.waitKey(0)
        cv2.destroyAllWindows()
    else:
        print("No rectangles detected.")

find_rightmost_centroid("input_image.png")
