from time import time
st_time = time()

N = int(input())
A = list(map(int,input().split()))

def calc_divs(n):
    divs = set()
    m = 1
    while m*m <= n:
        if n%m==0:
            divs.add(m)
            divs.add(n//m)
        m += 1
    return divs

from collections import Counter
end = set()
from random import sample
while time() - st_time < 1.8:
    i,j = sample(range(N), 2)
    n = abs(A[i]-A[j])
    if n in end: continue
    end.add(n)
    for d in calc_divs(n):
        if d <= 2: continue
        c = Counter([a%d for a in A])
        v = c.most_common()[0][1]
        if v+v >= N:
            exit(print(d))
print(-1)
