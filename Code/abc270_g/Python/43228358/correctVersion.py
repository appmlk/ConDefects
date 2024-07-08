def extgcd(a, b):
    """returns gcd(a, b), s, r s.t. a * s + b * r == gcd(a, b)"""
    s, bs = 0, 1
    r, br = b, a
    while r:
        q = br // r
        br, r = r, br - q * r
        bs, s = s, bs - q * s
    return br, bs, (br-bs*a)//b if b else 0

import math
def sol(a, b, mod):
    """solve ax≡b %mod"""
    g = math.gcd(a, mod)
    if g==1:
        gg, x, y = extgcd(a, mod)
        return (b*x)%mod
    else:
        if b%g==0:
            a//=g
            b//=g
            mod//=g
            gg, x, y = extgcd(a, mod)
            return (b*x)%mod
        else:
            return -1

def bsgs(X, Y, M):
    D = {1: 0}

    sq = int(M**.5)+1

    # Baby-step
    Z = 1
    for i in range(sq):
        Z = Z * X % M
        if Z not in D:D[Z] = i+1

    if Y in D:
        return D[Y]

    # Giant-step
    R = pow(Z, M-2, M) # R = X^(-sq)

    for i in range(1, sq+1):
        Y = Y * R % M
        if Y in D:
            return D[Y] + i*sq
    return -1

def solve(p,a,b,s,g):
    if a==0:
        if g==s:return 0
        elif g==b:return 1
        else:return -1
    elif a==1:
        return sol(b,(g-s)%p,p)
    else:
        inv = b*pow(a-1,p-2,p)%p
        s+=inv;g+=inv
        s%=p;g%=p
        if s==0:
            if g==0:
                return 0
            else:
                return -1
        g *=pow(s,p-2,p)
        g %= p
        return bsgs(a,g,p)

t = int(input())
for i in range(t):
    p,a,b,s,g = map(int, input().split())
    print(solve(p,a,b,s,g))