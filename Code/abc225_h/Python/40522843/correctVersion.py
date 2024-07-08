MOD = 998244353
IMAG = 911660635
IIMAG = 86583718
rate2 = (0, 911660635, 509520358, 369330050, 332049552, 983190778, 123842337, 238493703, 975955924, 603855026, 856644456, 131300601, 842657263, 730768835, 942482514, 806263778, 151565301, 510815449, 503497456, 743006876, 741047443, 56250497, 867605899, 0)
irate2 = (0, 86583718, 372528824, 373294451, 645684063, 112220581, 692852209, 155456985, 797128860, 90816748, 860285882, 927414960, 354738543, 109331171, 293255632, 535113200, 308540755, 121186627, 608385704, 438932459, 359477183, 824071951, 103369235, 0)
rate3 = (0, 372528824, 337190230, 454590761, 816400692, 578227951, 180142363, 83780245, 6597683, 70046822, 623238099, 183021267, 402682409, 631680428, 344509872, 689220186, 365017329, 774342554, 729444058, 102986190, 128751033, 395565204, 0)
irate3 = (0, 509520358, 929031873, 170256584, 839780419, 282974284, 395914482, 444904435, 72135471, 638914820, 66769500, 771127074, 985925487, 262319669, 262341272, 625870173, 768022760, 859816005, 914661783, 430819711, 272774365, 530924681, 0)

def butterfly(a):
  n = len(a)
  h = (n - 1).bit_length()
  le = 0
  while le < h:
    if h - le == 1:
      p = 1 << (h - le - 1)
      rot = 1
      for s in range(1 << le):
        offset = s << (h - le)
        for i in range(p):
          l = a[i + offset]
          r = a[i + offset + p] * rot
          a[i + offset] = (l + r) % MOD
          a[i + offset + p] = (l - r) % MOD
        rot *= rate2[(~s & -~s).bit_length()]
        rot %= MOD
      le += 1
    else:
      p = 1 << (h - le - 2)
      rot = 1
      for s in range(1 << le):
        rot2 = rot * rot % MOD
        rot3 = rot2 * rot % MOD
        offset = s << (h - le)
        for i in range(p):
          a0 = a[i + offset]
          a1 = a[i + offset + p] * rot
          a2 = a[i + offset + p * 2] * rot2
          a3 = a[i + offset + p * 3] * rot3
          a1na3imag = (a1 - a3) % MOD * IMAG
          a[i + offset] = (a0 + a2 + a1 + a3) % MOD
          a[i + offset + p] = (a0 + a2 - a1 - a3) % MOD
          a[i + offset + p * 2] = (a0 - a2 + a1na3imag) % MOD
          a[i + offset + p * 3] = (a0 - a2 - a1na3imag) % MOD
        rot *= rate3[(~s & -~s).bit_length()]
        rot %= MOD
      le += 2

def butterfly_inv(a):
  n = len(a)
  h = (n - 1).bit_length()
  le = h
  while le:
    if le == 1:
      p = 1 << (h - le)
      irot = 1
      for s in range(1 << (le - 1)):
        offset = s << (h - le + 1)
        for i in range(p):
          l = a[i + offset]
          r = a[i + offset + p]
          a[i + offset] = (l + r) % MOD
          a[i + offset + p] = (l - r) * irot % MOD
        irot *= irate2[(~s & -~s).bit_length()]
        irot %= MOD
      le -= 1
    else:
      p = 1 << (h - le)
      irot = 1
      for s in range(1 << (le - 2)):
        irot2 = irot * irot % MOD
        irot3 = irot2 * irot % MOD
        offset = s << (h - le + 2)
        for i in range(p):
          a0 = a[i + offset]
          a1 = a[i + offset + p]
          a2 = a[i + offset + p * 2]
          a3 = a[i + offset + p * 3]
          a2na3iimag = (a2 - a3) * IIMAG % MOD
          a[i + offset] = (a0 + a1 + a2 + a3) % MOD
          a[i + offset + p] = (a0 - a1 + a2na3iimag) * irot % MOD
          a[i + offset + p * 2] = (a0 + a1 - a2 - a3) * irot2 % MOD
          a[i + offset + p * 3] = (a0 - a1 - a2na3iimag) * irot3 % MOD
        irot *= irate3[(~s & -~s).bit_length()]
        irot %= MOD
      le -= 2

def multiply(s, t):
  n = len(s)
  m = len(t)
  if min(n, m) <= 60:
    a = [0] * (n + m - 1)
    for i in range(n):
      if i % 8 == 0:        
        for j in range(m):
          a[i + j] += s[i] * t[j]
          a[i + j] %= MOD
      else:
        for j in range(m):
          a[i + j] += s[i] * t[j]
    return [x % MOD for x in a]
  a = s.copy()
  b = t.copy()
  z = 1 << (n + m - 2).bit_length()
  a += [0] * (z - n)
  b += [0] * (z - m)
  butterfly(a)
  butterfly(b)
  for i in range(z):
    a[i] *= b[i]
    a[i] %= MOD
  butterfly_inv(a)
  a = a[:n + m - 1]
  iz = pow(z, MOD - 2, MOD)
  return [v * iz % MOD for v in a]

mod=998244353
M=(10**5)*4+10
fac=[1]*M
ninv=[1]*M
finv=[1]*M
for i in range(2,M):
  fac[i]=fac[i-1]*i%mod
  ninv[i]=(-(mod//i)*ninv[mod%i])%mod
  finv[i]=finv[i-1]*ninv[i]%mod

def binom(n,k):
  if n<0 or k<0:
    return 0
  if k>n:
    return 0
  return (fac[n]*finv[k]%mod)*finv[n-k]%mod

def calc1(n):
  A=[1]+[0]*n
  for i in range(1,n+1):
    A[i]=binom(n+i,2*i)
  return A

def calc2(d):
  A=[0]*(d)
  for i in range(1,d+1):
    A[i-1]=binom(d+i-1,2*i-1)
  return A


from heapq import heappop, heappush
N,M,K=map(int,input().split())
a=list(map(int,input().split()))

if K==0:
  ans=0
  for n in range(1,N):
    res=binom(n+M-2,2*M-3)
    ans+=res*(N-n)
  ans%=mod
  ans*=fac[M]
  print(ans%mod)
  exit()
  
hq=[]
heappush(hq,(a[0]-1,calc1(a[0]-1)))
heappush(hq,(N-a[-1],calc1(N-a[-1])))
for i in range(K-1):
  d=a[i+1]-a[i]
  heappush(hq,(d-1,calc2(d)))

while len(hq)>=2:
  _,p1=heappop(hq)
  _,p2=heappop(hq)
  q=multiply(p1,p2)
  heappush(hq,(len(q)-1,q))

ans=hq[0][1][M-K]*fac[M-K]
print(ans%mod)