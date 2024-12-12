import math
import cv2 as cv
import numpy as np
import itertools

def find_intersection(line1, line2):
    """Find the intersection point of two lines given in Hough space."""
    rho1, theta1 = line1
    rho2, theta2 = line2
    A = np.array([
        [np.cos(theta1), np.sin(theta1)],
        [np.cos(theta2), np.sin(theta2)]
    ])
    B = np.array([rho1, rho2])
    try:
        intersection = np.linalg.solve(A, B)
        return tuple(intersection.astype(int))
    except np.linalg.LinAlgError:
        return None

def calculate_angle(theta1, theta2):
    """Calculate the angle between two lines."""
    angle = abs(theta1 - theta2)
    return min(angle, abs(math.pi - angle))  # Normalize angle to [0, π/2]

def order_points(points):
    """Order points in top-left, top-right, bottom-right, bottom-left order."""
    points = sorted(points, key=lambda p: (p[1], p[0]))  # Sort by y, then x
    top_two = sorted(points[:2], key=lambda p: p[0])      # Top-left, top-right
    bottom_two = sorted(points[2:], key=lambda p: p[0])   # Bottom-left, bottom-right
    return [top_two[0], top_two[1], bottom_two[1], bottom_two[0]]

# Load the image in grayscale
img = cv.imread('white.jpg')
img_gray = cv.imread('white.jpg', 0)
cv.imshow('Original grayscale image', img_gray)
cv.waitKey(0)

# Apply Gaussian blur to reduce noise
blurred = cv.GaussianBlur(img_gray, (5, 5), 0)

# Use Canny edge detection
edges = cv.Canny(blurred, 50, 200, apertureSize=3)
cv.imshow('Edges', edges)
cv.waitKey(0)

# Convert edges to BGR for visualization
cdst = cv.cvtColor(edges, cv.COLOR_GRAY2BGR)

# Apply the Hough Line Transform
lines = cv.HoughLines(edges, 1, np.pi / 180, 100, None, 0, 0)

# Check if lines were detected
if lines is not None:
    # Draw the detected lines
    for i in range(0, len(lines)):
        rho = lines[i][0][0]
        theta = lines[i][0][1]
        a = math.cos(theta)
        b = math.sin(theta)
        x0 = a * rho
        y0 = b * rho
        pt1 = (int(x0 + 1000 * (-b)), int(y0 + 1000 * (a)))
        pt2 = (int(x0 - 1000 * (-b)), int(y0 - 1000 * (a)))
        cv.line(cdst, pt1, pt2, (0, 0, 255), 2, cv.LINE_AA)

    # Find intersections between line pairs and validate angles
    intersections = []
    for line1, line2 in itertools.combinations(lines, 2):
        rho1, theta1 = line1[0]
        rho2, theta2 = line2[0]
        angle = calculate_angle(theta1, theta2)
        if 1.2 < angle < 1.9:  # Filter for angles close to 90° (π/2 radians ± tolerance)
            intersection = find_intersection((rho1, theta1), (rho2, theta2))
            if intersection:
                intersections.append(intersection)

    # Filter intersections to retain only those within image bounds
    height, width = img_gray.shape
    filtered_intersections = [
        point for point in intersections
        if 0 <= point[0] <= width and 0 <= point[1] <= height
    ]

    # Cluster points to ensure only one point per corner
    clustered_points = []
    for point in filtered_intersections:
        cv.circle(cdst, point, 5, (0, 255, 0), -1)
        cv.circle(img, point, 5, (0, 255, 0), -1)
        if not any(np.linalg.norm(np.array(point) - np.array(clustered_point)) < 20 for clustered_point in clustered_points):
            clustered_points.append(point)

    # Ensure exactly 4 points are detected
    if len(clustered_points) == 4:
        # Order the points for perspective transformation
        ordered_points = order_points(clustered_points)

        # Define the destination points for the A4 document
        dst_width, dst_height = 595, 842  # A4 size in pixels at 72 DPI
        dst_points = np.array([
            [0, 0],
            [dst_width - 1, 0],
            [dst_width - 1, dst_height - 1],
            [0, dst_height - 1]
        ], dtype="float32")

        # Compute the perspective transformation matrix
        src_points = np.array(ordered_points, dtype="float32")
        M = cv.getPerspectiveTransform(src_points, dst_points)

        # Apply the perspective transformation
        warped = cv.warpPerspective(img, M, (dst_width, dst_height))

        # Display the result
        cv.imshow("Warped Image", warped)
        cv.waitKey(0)

cv.imshow("Detected Lines and Valid Corners", cdst)
cv.imshow("Detected Corners", img)
cv.waitKey(0)

# Close all windows
cv.destroyAllWindows()
