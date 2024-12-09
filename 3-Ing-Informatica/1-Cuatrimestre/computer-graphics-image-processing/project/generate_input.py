import cv2
import numpy as np
import random


def generate_test_image(output_path="input_image.png"):
    # Image dimensions
    width, height = 500, 500
    image = np.ones((height, width), dtype=np.uint8) * 255  # White background

    # Randomly generate rectangles
    num_rectangles = 7
    for _ in range(num_rectangles):
        # Randomly decide if the rectangle will be small or large
        if random.random() > 0.5:
            size_range = (50, 100)
        else:
            size_range = (10, 40)

        x1 = random.randint(10, width - max(size_range) - 10)
        y1 = random.randint(10, height - max(size_range) - 10)

        x2 = x1 + random.randint(size_range[0], size_range[1])
        y2 = y1 + random.randint(size_range[0], size_range[1])

        cv2.rectangle(image, (x1, y1), (x2, y2), (0, 0, 0), -1)

    # Add noise
    num_noise_points = 150
    for _ in range(num_noise_points):
        x = random.randint(0, width - 1)
        y = random.randint(0, height - 1)
        image[y, x] = 50

    # Apply smoothing (3x3 averaging filter)
    smoothed = cv2.GaussianBlur(image, (3, 3), 0)

    # Save the image
    cv2.imwrite(output_path, smoothed)
    print(f"Generated image saved to {output_path}")

generate_test_image()
