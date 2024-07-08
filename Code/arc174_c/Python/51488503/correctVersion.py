
BigPrime = 998244353

N = int(input())

# ac ¥equiv 1 mod b となるcを求める
# @lru_cache(maxsize=4096)
# def modinv(a, b):
#     b0 = b
#     x0, x1 = 0, 1
#     while a > 1:
#         q = a // b
#         a, b = b, a % b
#         x0, x1 = x1 - q * x0, x0
#     if x1 < 0:
#         x1 += b0
#     return x1

def modinv(a, b):
    return pow(a, b-2, b)

def mod_time(a,b):
    return (a*b) % BigPrime

# 高速化のために後で使う値を計算しておく
# M は N^2 mod BigPrime
# n は M の modinv
# m は n の modinv
# poweri[i] は i^2 mod BigPrime
# rate2jou[i] i^2/N^2 mod BigPrime
# timesiN_i[i] i*(N-i) mod BigPrime
# fractimesiN_i[i] i*(N-i)/N^2 mod BigPrime
# fraci[i] i/N mod BigPrime
# complementfraci[i] 1-(i/N) mod BigPrime

n = modinv(N, BigPrime)
M = (N ** 2) % BigPrime
m = modinv(M, BigPrime)
fraci = [0] * (N+1)
complementfraci = [0] * (N+1)
poweri = [0] * (N+1)
rate2jou = [0] * (N+1)
# i/N mod BigPrime
# 1-(i/N) mod BigPrime
for i in range(1, N+1):
    fraci[i] = (fraci[i-1] + n) % BigPrime
    complementfraci[i] = (BigPrime - fraci[i]+1) % BigPrime
# i^2 mod BigPrime
# i^2/N^2 mod BigPrime
for i in range(1, N+1):
    poweri[i] = (i**2) % BigPrime
    rate2jou[i] = mod_time(poweri[i], m)


def limitPr(init, rate):
    init = init % BigPrime
    rate = rate % BigPrime
    limval = mod_time(init, modinv(BigPrime+1 - rate, BigPrime))
    return limval

def first_to_second(i):
    return limitPr(complementfraci[i], rate2jou[i])

def val_first(i):
    return limitPr(fraci[i],rate2jou[i])

ans = [0, 0]
Prob = [[0, 0] for _ in range(N+1)]
Prob[0] = [1, 0]

for i in range(N):
    f_s = first_to_second(i)
    f_f = mod_time(f_s, fraci[i])
    Prob[i+1][0] = (mod_time(f_f, Prob[i][0]) + mod_time(f_s, Prob[i][1])) % BigPrime
    Prob[i+1][1] = (BigPrime - Prob[i+1][0]+1) % BigPrime
    v_f = val_first(i)
    v_s = mod_time(v_f, fraci[i])

    ans[0] += (mod_time(v_f, Prob[i][0]) + mod_time(v_s, Prob[i][1])) % BigPrime
    ans[0] %= BigPrime
    ans[1] += (mod_time(v_s, Prob[i][0]) + mod_time(v_f, Prob[i][1])) % BigPrime
    ans[1] %= BigPrime

print(*ans)

