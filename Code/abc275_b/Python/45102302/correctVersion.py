k = list(map(int, input().split()))

mod = 998244353

abc = (k[0]%mod)*(k[1]%mod)*(k[2]%mod)
deg = (k[3]%mod)*(k[4]%mod)*(k[5]%mod)

print(((abc%mod)-(deg%mod))%mod)