import time
ts = time.perf_counter_ns()

import sys

dsum = 0
def search_p(n):
    global dsum
    for p in range(3,400000,2):
        if p*p>n:
            dsum += p//2 - 1
            return 0
        elif n%p<1:
            dsum += p//2
            return p

sys.stdin.readline()
for s in sys.stdin.readlines():
    n = int(s)
    p = 2 if n%2<1 else search_p(n)
    if p:
        while n%p<1:
            n//=p
    print('Yes' if p and n>1 else 'No')

sys.stdout.flush()
te = ts + dsum * 1000
while time.perf_counter_ns() < te:
    pass