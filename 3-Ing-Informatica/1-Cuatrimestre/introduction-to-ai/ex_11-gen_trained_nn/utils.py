import math
import numpy as np


def rotate(origin, point, angle):
    """
    Rotate a point counterclockwise by a given angle around a given origin.
    The angle should be given in degrees.
    """
    angle = - angle * math.pi / 180
    rot = np.array([[math.cos(angle), -math.sin(angle)], [math.sin(angle), math.cos(angle)]])
    return origin + rot @ (point - origin)


def normalize_vector(point_1, point_2, norm=50):
    n = np.linalg.norm(point_2-point_1)
    return point_1, point_1 + (point_2-point_1) / n * norm


def get_angle_degrees(v1):
    v1 /= np.linalg.norm(v1)
    v2 = np.array([1, 0])
    prod = np.dot(v1, v2)
    over_180 = True if v1[1] > 0 else False
    angle = np.arccos(prod) * 180 / math.pi

    return 360 - angle if over_180 else angle


def ls_ls_intersection(ls1, ls2):
    # returns tuple the point of intersection of two line segments (where ls2 is perpendicular to one of the axis)
    # if there is no intersection returns False
    (ax, ay), (bx, by) = ls1
    cx, cy, dx, dy = ls2

    if dy == cy:
        if (by < dy and ay < dy) or (by > dy and ay > dy) or by == ay:
            return False
        t = (cy-ay)/(by-ay)
        if not (0 <= t <= 1):
            return False
        point = np.array([ax + t * (bx - ax), ay + t * (by - ay)])
        in_bounds = cx < point[0] < dx or dx <= point[0] <= cx
        return point if in_bounds else False
    elif dx == cx:
        if (bx < dx and ax < dx) or (bx > dx and ax > dx) or bx == ax:
            return False
        t = (cx-ax)/(bx-ax)
        if not (0 <= t <= 1):
            return False
        point = np.array([ax + t * (bx - ax), ay + t * (by - ay)])
        in_bounds = cy < point[1] < dy or dy <= point[1] <= cy
        return point if in_bounds else False
    return False


def ls_arc_intersection(ls, arc):
    (x1_o, y1_o), (x2_o, y2_o) = ls
    cx, cy, r, ang_start, d_ang = arc
    x1, y1, x2, y2 = x1_o - cx, y1_o - cy, x2_o - cx, y2_o - cy

    dx = x2-x1
    dy = y2-y1
    dr = np.sqrt(dx**2+dy**2)
    d = x1*y2 - x2*y1
    disc = r**2*dr**2-d**2

    if disc < 0:
        return False
    res = []
    for i in [0, 1]:
        x = d * dy + (-1)**i * np.sign(dy) * dx * np.sqrt(disc)
        x /= dr**2
        x += cx
        y = - d * dx + (-1)**i * np.abs(dy) * np.sqrt(disc)
        y /= dr**2
        y += cy

        point = np.array([x, y])
        if ang_start <= get_angle_degrees(point - np.array([cx, cy])) <= ang_start + d_ang:
            vs_new = np.sign(point - np.array([x1_o, y1_o]))
            vs_rest = np.sign(np.array([x2_o, y2_o]) - point)

            if vs_new[0] == vs_rest[0] and vs_new[1] == vs_rest[1]:
                res.append((x, y))

    if len(res) > 0:
        return res[0]
    return False


def create_track():
    # You can use these basic shapes to create a track:
    # design.append(['circle_seg', [x, y, r, init_angle, angle]])
    # design.append(['rec', [x1, y1, x2, y2]])

    track = []
    lss = []

    track.append(['rec', [200, 550, 500, 650], 'left'])
    track.append(['circle_seg', [200, 550, 100, 180, 90], 'clockwise'])
    track.append(['rec', [100, 250, 200, 550], 'up'])
    track.append(['circle_seg', [200, 250, 100, 90, 90], 'clockwise'])
    track.append(['rec', [200, 150, 300, 250], 'right'])
    track.append(['circle_seg', [300, 250, 100, 0, 90], 'clockwise'])
    track.append(['rec', [300, 250, 400, 350], 'down'])
    track.append(['circle_seg', [400, 350, 100, 180, 90], 'anti_clockwise'])
    track.append(['rec', [400, 350, 600, 450], 'right'])
    track.append(['circle_seg', [600, 350, 100, 270, 90], 'anti_clockwise'])
    track.append(['rec', [600, 250, 700, 350], 'up'])
    track.append(['circle_seg', [700, 250, 100, 90, 90], 'clockwise'])
    track.append(['rec', [700, 150, 800, 250], 'right'])
    track.append(['circle_seg', [800, 250, 100, 0, 90], 'clockwise'])
    track.append(['rec', [800, 250, 900, 550], 'down'])
    track.append(['circle_seg', [800, 550, 100, 270, 90], 'clockwise'])
    track.append(['rec', [500, 550, 800, 650], 'left'])

    lss.append(['ls', [200, 150, 300, 150]])
    lss.append(['ls', [200, 250, 300, 250]])
    lss.append(['ls', [300, 250, 300, 350]])
    lss.append(['ls', [400, 250, 400, 350]])
    lss.append(['ls', [400, 350, 600, 350]])
    lss.append(['ls', [400, 450, 600, 450]])
    lss.append(['ls', [600, 350, 600, 250]])
    lss.append(['ls', [700, 350, 700, 250]])
    lss.append(['ls', [700, 250, 800, 250]])
    lss.append(['ls', [700, 150, 800, 150]])
    lss.append(['ls', [800, 250, 800, 550]])
    lss.append(['ls', [900, 250, 900, 550]])
    lss.append(['ls', [800, 550, 200, 550]])
    lss.append(['ls', [800, 650, 200, 650]])
    lss.append(['ls', [200, 550, 200, 250]])
    lss.append(['ls', [100, 550, 100, 250]])
    lss.append(['cs', [200, 250, 100, 90, 90]])
    lss.append(['cs', [300, 250, 100, 0, 90]])
    lss.append(['cs', [400, 350, 100, 180, 90]])
    lss.append(['cs', [600, 350, 100, 270, 90]])
    lss.append(['cs', [700, 250, 100, 90, 90]])
    lss.append(['cs', [800, 250, 100, 0, 90]])
    lss.append(['cs', [800, 550, 100, 270, 90]])
    lss.append(['cs', [200, 550, 100, 180, 90]])

    return [track, lss]
