from math import floor
line_dict = {}
n = int(input())
points = []
for i in range(n):
    x, y = map(int, input().split())
    points.append((x, y))

for i in range(n):
    for j in range(i + 1, n):
        if points[i][0] - points[j][0] == 0:
            key = f"slope:inf, intercept:{points[i][0]}"
            if line_dict.get(key, None):
                line_dict[key].add(points[i])
                line_dict[key].add(points[j])
            else:
                line_dict[key] = set()
                line_dict[key].add(points[i])
                line_dict[key].add(points[j])
        else:
            slope = (points[j][1] - points[i][1])/(points[j][0] - points[i][0])
            intercept = points[j][1] - points[j][0] * slope
            key = f"slope:{slope}, intercept:{intercept}"
            if line_dict.get(key,None):
                line_dict[key].add(points[i])
                line_dict[key].add(points[j])
            else:
                line_dict[key] = set()
                line_dict[key].add(points[i])
                line_dict[key].add(points[j])
max_in_line = - 1
for elements in line_dict.values():
    max_in_line = max(max_in_line, len(elements))
imp = n - floor(n/3) + 1
if max_in_line < imp:
    print(floor(n/3))
else:
    print(n - max_in_line)