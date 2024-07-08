mod = 998244353
g = 3
ginv = 332748118
W = [pow(g, (mod-1)>>i, mod) for i in range(24)]
Winv = [pow(ginv, (mod-1)>>i, mod) for i in range(24)]

def fft(k,f):
    for l in range(k,0,-1):
        d = 1 << l - 1
        U = [1]
        for i in range(d):
            U.append(U[-1]*W[l]%mod)
        for i in range(1<<k - l):
            for j in range(d):
                s = i*2*d + j
                f[s],f[s+d] = (f[s]+f[s+d])%mod, U[j]*(f[s]-f[s+d])%mod

def fftinv(k,f):
    for l in range(1,k+1):
        d = 1 << l - 1
        for i in range(1<<k - l):
            u = 1
            for j in range(i*2*d, (i*2+1)*d):
                f[j+d] *= u
                f[j],f[j+d] = (f[j]+f[j+d])%mod, (f[j]-f[j+d])%mod
                u *= Winv[l] 
                u %= mod

def convolution(a,b):
    le = len(a)+len(b)-1
    k = le.bit_length()
    n = 1 << k
    a = a + [0]*(n-len(a))
    b = b + [0]*(n-len(b))
    fft(k,a)
    fft(k,b)
    for i in range(n):
        a[i] *= b[i]
        a[i] %= mod
    fftinv(k,a)
    
    ninv = pow(n,mod-2,mod)
    for i in range(le):
        a[i] *= ninv
        a[i] %= mod
    
    return a[:le]

import random

dic = {}
use = set()
x = [chr(ord("a")+i) for i in range(26)] + [chr(ord("A")+i) for i in range(26)] + ["."]
for s in x:
    while True:
        r = random.randint(1,600)
        if r in use:
            continue
        dic[s] = r
        break
dic["_"] = 0

l,w = map(int,input().split())
S = input()
P = input()

base = S
S += "." * (w-1)

need = l+w-1 + w-1
dif = need - len(S)

if dif >= l:
    S += base
    dif -= l
else:
    S += base[:dif]
    dif = 0


S += "."*dif

S = [dic[s] for s in S]
P = [dic[s] for s in P]

P = P[::-1]

size = len(S)+len(P)-1

cand = [0]*size

for sp,pp,f in [[3,1,1],[2,2,-2],[1,3,1]]:

    nS = [s**sp for s in S]
    nP = [p**pp for p in P]

    conv = convolution(nS,nP)
    for i in range(size):
        cand[i] += conv[i]*f
ans = 0
for i in range(w-1,need):
    if cand[i] == 0:
        ans += 1
print(ans)
