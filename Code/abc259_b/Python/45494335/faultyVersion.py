import numpy as np

a, b, d = map(int, input().split())
theta = np.radians(d, dtype=np.float128)
c, s = np.cos(theta, dtype=np.float128), np.sin(theta, dtype=np.float128)
R = np.array(((c, -s), (s, c)), dtype=np.float128)
ret = np.dot(R, np.array((a, b), dtype=np.float128))
print(ret)
