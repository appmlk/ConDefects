n,a,b,c = map(int,input().split())
mod = 998244353

## nCkのmodを求める関数
# テーブルを作る(前処理)
max_n = n + 10
fac, finv, inv = [0]*max_n, [0]*max_n, [0]*max_n

def comInit(max_n):
    fac[0] = fac[1] = 1
    finv[0] = finv[1] = 1
    inv[1] = 1

    for i in range(2,max_n):
      fac[i] = fac[i-1]* i% mod
      inv[i] = mod - inv[mod%i] * (mod // i) % mod
      finv[i] = finv[i-1] * inv[i] % mod

comInit(max_n)

# 二項係数の計算
def com(n,k):
    if(n < k):
        return 0
    if( (n<0) | (k < 0)):
        return 0
    return fac[n] * (finv[k] * finv[n-k] % mod) % mod


ans = 1
mul = (-1)**(n%2)
xa,xb,xc = 1,1,1
for i in range(1,n+1):
    mul *= -1
    xa = (xa * 2 - com(i-1,a)) % mod
    xb = (xb * 2 - com(i-1,b)) % mod
    xc = (xc * 2 - com(i-1,c)) % mod
    ans += (mul * xa * xb % mod) * (xc * com(n,i) % mod)
    ans %= mod

print(ans) 