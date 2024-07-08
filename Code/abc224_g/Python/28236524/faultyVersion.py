N, S, T, A, B = map(int, input().split())


def value(k):
    if k < S <= T:
        return A*(T-S)/2
    elif k >= T:
        return float('inf')
    else:
        return B*N/(T-k)+A*(T-k-1)/2


l, r = 0, T
if r-l == 1:
    print(min(value(0), value(1)))
    exit()
if r-l == 2:
    print(min(value(0), value(1), value(2)))
    exit()
while r-l > 2:
    m1 = l+(r-l)//3
    m2 = r-(r-l)//3
    if value(m1) >= value(m2):
        l = m1
    else:
        r = m2
print(min(value(l), value(l+1), value(r)))
