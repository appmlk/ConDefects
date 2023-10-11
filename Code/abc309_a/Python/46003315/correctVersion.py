import sys
import string
import numpy as np
from collections import defaultdict, deque
from math import dist
import itertools
import operator

INF = -1000000000000000000

A, B = map(int, input().split())
ax = (A - 1) // 3
ay = (A - 1) % 3
bx = (B - 1) // 3
by = (B - 1) % 3

print("Yes" if ax == bx and abs(ay - by) == 1 else "No")
