#!/usr/bin/env python3

import sys

a, b, c, x = map(int, input().split())

res = 0.0
if x <= a:
   res = 1.0
elif x <= b:
   res = c / (b - a)

print('%.9f' % (res))

